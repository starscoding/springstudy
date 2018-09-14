package com.azxx.demon;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestDITest extends SpringTestBase {

    @Autowired
    private TestDI testDI;

    @Test
    public void TestAdd(){
//        Assert.assertEquals(4,testDI.add(1,2));
    }

    @Test
    public void TestAdd2(){
        Assert.assertEquals(3,testDI.add(1,2));
    }

    @Test
    public void testMethod(){
        System.out.println("");
        testDI.testMethod();
    }
}