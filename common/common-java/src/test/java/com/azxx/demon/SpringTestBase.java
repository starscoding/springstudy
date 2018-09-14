package com.azxx.demon;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 将所需加载的xml文件指定为locations的value。如：@ContextConfiguration(locations = { "classpath:Application-Redis.xml" })
 */
@ContextConfiguration(locations = "classpath*:**/applicationContext*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestBase extends AbstractJUnit4SpringContextTests {
}
