package com.xin.controller.api;

import com.xin.dao.ClientDao;
import com.xin.dto.api.EmailDTO;
import com.xin.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author 吴泽欣
 * @since 2021/1/14 17:48
 */
@Slf4j
@RestController
@RequestMapping(value = "/email")
public class EmailController {
    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ClientDao clientDao;

    /**
     *
     * The valid characters are defined in RFC 7230 and RFC 3986
     * @param emailDTO
     * @return
     * @throws MessagingException
     */
    @PostMapping("/sendCode")
    public R sendVerificationCode(@RequestBody @Validated EmailDTO emailDTO) throws MessagingException {
        String toEmail = emailDTO.getEmail();
        if(clientDao.existsClientEmail(toEmail)){
            return R.error("邮箱已经被注册");
        }
        log.info("send email to:{}", emailDTO);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setCc(sender);
        helper.setTo(toEmail);
        String verificationCode = String.valueOf((int)(1234567 + Math.random() * 1000000));
        redisTemplate.opsForValue().set(toEmail, verificationCode);
        helper.setSubject("项目管理系统邮箱验证信息");
        helper.setText("您的验证码为：" + verificationCode);
        mailSender.send(message);
        return R.ok("邮箱验证码发送成功！");
    }
}
