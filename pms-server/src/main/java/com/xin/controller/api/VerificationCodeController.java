package com.xin.controller.api;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * 图片生成返回二进制给前端，验证码保存在session中，以此机制来验证
 * session是增加了状态的，保存在服务器，前端无法获知里边存在着什么信息
 */
@Slf4j
@RestController
@RequestMapping("/verificationCode")
public class VerificationCodeController {

    @Resource
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "生成验证码")
    @GetMapping
    public void generateVerificationCode(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        // 生产验证码字符串并保存到session中
        String createText = defaultKaptcha.createText();
        log.info("save verificationCode:{}",createText);
        httpServletRequest.getSession().setAttribute("verificationCode", createText);
        // 使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage challenge = defaultKaptcha.createImage(createText);
        ImageIO.write(challenge, "jpg", jpegOutputStream);
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

//    @ApiOperation(value = "校对验证码")
//    @PostMapping
//    public R checkVerificationCode(@RequestParam(value = "verificationCode") String verificationCode, HttpServletRequest httpServletRequest) {
//        String verificationCodeIn = (String) httpServletRequest.getSession().getAttribute("verificationCode");
//        httpServletRequest.getSession().removeAttribute("verificationCode");
//        if (StringUtils.isEmpty(verificationCodeIn) || !verificationCodeIn.equals(verificationCode)) {
//            return R.error("验证码错误，或已失效");
//        }
//        return R.ok("校验成功");
//    }
}