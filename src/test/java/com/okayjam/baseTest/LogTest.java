package com.okayjam.baseTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: ${description}
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/08/06 16:39
 **/
public class LogTest {
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog(){
        logger.info("info");
        logger.debug("debug");
        logger.error("error");
        logger.warn("warn");
    }
}
