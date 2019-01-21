package com.ec.auth.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ec.auth.properties.JwtProperties;

/**
 * @author bystander
 * @date 2018/10/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtPropertiesTest {

    @Autowired
    private JwtProperties props;


    @Test
    public void testCreateIndex() {
    	System.out.println(props.getPrivateKey().toString());
    	
    }
}
