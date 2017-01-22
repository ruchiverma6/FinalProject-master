package com.udacity.gradle.builditbigger;

import junit.framework.Assert;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ruchi on 22/1/17.
 */

public class TestAsyncTask {

    @Test
    public void testNow(){
        Assert.assertNotNull("hi");
    }

    @Test
    public void testSomething(){
        final CountDownLatch signal = new CountDownLatch(1);
        new EndpointsAsyncTask(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String result) {
                Assert.assertNotNull(result);
                signal.countDown();// notify the count down latch
            }
        }).execute();

        try {
            signal.await();// wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
