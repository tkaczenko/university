package com.example.task2;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by tkaczenko on 26.01.17.
 */
@RunWith(VertxUnitRunner.class)
public class MyVerticleTest {
    private Vertx vertx;

    @Before
    public void setUp(TestContext context) throws Exception {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyVerticle.class.getName(),
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) throws Exception {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testVerticle(TestContext context) throws Exception {
        final Async async = context.async();

        vertx.createHttpClient().getNow(8081, "localhost", "/",
                response -> response.handler(body -> {
                    context.assertTrue(body.toString().contains("Hello"));
                    async.complete();
                }));
    }
}