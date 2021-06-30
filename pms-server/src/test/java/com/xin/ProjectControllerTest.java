package com.xin;

import cn.hutool.crypto.SecureUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author 吴泽欣
 * @since 2021/1/6 20:50
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class ProjectControllerTest {

    @Test
    public void testEncoder(){
        //不重复
        System.out.println(SecureUtil.md5("1"));
        System.out.println(SecureUtil.md5("1"));
        System.out.println(SecureUtil.md5("2333"));
        System.out.println(SecureUtil.md5("133332"));
        System.out.println(SecureUtil.md5("2313123123123"));
    }
    @Test
    public void testEncoder2(){
        System.out.println(SecureUtil.md5(1 + UUID.randomUUID().toString()));
        System.out.println(SecureUtil.md5(2 + UUID.randomUUID().toString()));
        System.out.println(SecureUtil.md5(3 + UUID.randomUUID().toString()));
        System.out.println(SecureUtil.md5(5 + UUID.randomUUID().toString()));
        System.out.println(SecureUtil.md5(5 + UUID.randomUUID().toString()));

    }
}
