package org.example;

import org.example.api.RateLimiter;
import org.example.impl.RateLimiterImpl;
import org.example.util.Identifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RateLimiterTest {

    private RateLimiter rateLimiter;

    @Before
    public void init(){
        rateLimiter = new RateLimiterImpl();
    }

    @Test
    public void testLimit() throws InterruptedException {
        rateLimiter.registerService("login", 1, 6, Identifier.SESSION);
        rateLimiter.registerService("login", 12, 6, Identifier.IP);

        Assert.assertTrue(rateLimiter.check("login", Identifier.SESSION, "1"));
        Assert.assertFalse(rateLimiter.check("login", Identifier.SESSION, "1"));
        Assert.assertTrue(rateLimiter.check("login", Identifier.SESSION, "2"));

        Thread.sleep(6000);

        Assert.assertTrue(rateLimiter.check("login", Identifier.SESSION, "1"));
    }
}
