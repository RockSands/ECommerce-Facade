package com.ec.upload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

import com.github.tobato.fastdfs.FdfsClientConfig;

/**
 * 
 * 此处使用了JMX的方式注册，该注册方式是Fdfs提供的
 * JMX可以理解为Java对各类扩展提供的一致接口。
 * 一般面向的是应用、设备、系统
 * @author Administrator
 *
 */
@Configuration
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastClientImporter {
}