package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;

import junit.framework.Assert;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static android.support.test.InstrumentationRegistry.getContext;

/**
 * Created by ruchi on 22/1/17.
 */

public class TestAsyncTask {

    @Test
    public void testNow(){
        Assert.assertNotNull("hi");
    }

    @Test
    public void testEndpointsAsyncTask(){
        final CountDownLatch signal = new CountDownLatch(1);
        new EndpointsAsyncTask(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String result) {
                Assert.assertNotNull(result);
                signal.countDown();// notify the count down latch
            }
        }).execute(new Pair<Context, String>(getContext(), "Joking..."));

        try {
            signal.await();// wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
