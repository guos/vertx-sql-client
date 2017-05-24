package com.julienviet.pgclient;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import com.julienviet.pgclient.impl.PostgresClientImpl;
import io.vertx.ext.sql.SQLClient;

/**
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public interface PostgresClient extends SQLClient {

  static PostgresClient create(Vertx vertx, PostgresClientOptions options) {
    return new PostgresClientImpl(vertx, options);
  }

  void connect(Handler<AsyncResult<PostgresConnection>> completionHandler);

  PostgresConnectionPool createPool(int size);
}