package com.azxx.demon.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Smile on 2018/9/26.
 */
public class MyDetailQuartzJobBean extends QuartzJobBean {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private String targetObject;

    private String targetMethod;

    private ApplicationContext ctx;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("execute [" + targetObject + "] at once>>>>>>");
        Object otargetObject = ctx.getBean(targetObject);
        Method m = null;
        try {
            m = otargetObject.getClass().getMethod(targetMethod, new Class[]{});
            m.invoke(otargetObject, new Object[]{});
        } catch (SecurityException e) {
            logger.error("", e);
        } catch (NoSuchMethodException e) {
            logger.error("", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }
}
