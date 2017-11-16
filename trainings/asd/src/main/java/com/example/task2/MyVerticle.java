package com.example.task2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by tkaczenko on 26.01.17.
 */
public class MyVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer()
                .requestHandler(r -> r.response().end("<h1>Hello, World</h1>"))
                .listen(8081, result -> {
                    if (result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }
}
