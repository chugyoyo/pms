package com.xin.web;

import cn.hutool.json.JSONUtil;
import com.xin.config.AppConfig;
import com.xin.consts.AuthConsts;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import net.scode.commons.util.JWTUtil;
import net.scode.commons.util.StringUtil;
import net.scode.commons.web.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 管理后台业务相关拦截器
 *
 * @author tanghuang 2020年02月25日
 */
@Slf4j
public class WebServiceInterceptor implements HandlerInterceptor {
    private static final String JSON_CONTENT_TYPE = "application/json;charset=UTF-8";


    @Autowired
    private AppConfig appConfig;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param request
     * @param response
     * @param handler
     * @return true，如果false，停止流程，api被拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        log.info("request=>{}", request.getRemoteAddr() + request.getRequestURI());
        // 填充公共参数
        String bearToken = request.getHeader(AuthConsts.CLIENT_AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(bearToken)) {
            bearToken = request.getParameter("token");
            log.info("header 中token 为空，尝试从request中获取:{}", bearToken);
        }
        String ip = RequestUtil.getRemoteAddr(request);
        WebContext.PublicParameter publicParameter = WebContext.getPublicParameter();
        publicParameter.setToken(bearToken);
        publicParameter.setIp(ip);

        if (StringUtil.isEmpty(bearToken)) {
            setResponse(response, R.error(Consts.NO_LOGIN_CODE, "未登录"));
            return false;
        }

        // 这里如果可解密，不报错，则说明token是正确的
        Claims claims = JWTUtil.parseJWT(appConfig.getJwtSecret(), bearToken);

        // 验证过期否
        if (JWTUtil.isExpired(claims, appConfig.getJwtSubject())) {
            log.info("token过期，登录失败");
            setResponse(response, R.error(Consts.NO_LOGIN_CODE, "未登录"));
            return false;
        }
        //TODO 自动续约token
        Integer clientId = (Integer) claims.get("id");
        String email = String.valueOf(claims.get("email"));
        log.info("WebContext save:id=" + clientId);
        log.info("WebContext save:email=" + email);
        publicParameter.setClientId(clientId);
        publicParameter.setEmail(email);
        // 检查token
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // TODO
        }

        return true;
    }

    private void setResponse(ServletResponse response, R result) {
        try {
            OutputStream os = response.getOutputStream();
            String jsonStr = JSONUtil.toJsonStr(result);
            os.write(jsonStr.getBytes(StandardCharsets.UTF_8));
            response.setContentType(JSON_CONTENT_TYPE);
        } catch (Exception ex) {
            log.warn("exception=" + ex);
        }
    }
}
