/*
 * Copyright (C) 2017 Julien Viet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.vertx.pgclient.impl.codec;

import io.vertx.sqlclient.impl.PreparedStatement;
import io.vertx.sqlclient.impl.ParamDesc;
import io.vertx.sqlclient.impl.TupleInternal;

class PgPreparedStatement implements PreparedStatement {

  final String sql;
  final Bind bind;
  final PgParamDesc paramDesc;
  final PgRowDesc rowDesc;
  final boolean cacheable;

  PgPreparedStatement(String sql, long statement, PgParamDesc paramDesc, PgRowDesc rowDesc, boolean cacheable) {
    this.paramDesc = paramDesc;
    this.rowDesc = rowDesc;
    this.sql = sql;
    this.bind = new Bind(statement, paramDesc != null ? paramDesc.paramDataTypes() : null, rowDesc != null ? rowDesc.columns : PgColumnDesc.EMPTY_COLUMNS);
    this.cacheable = cacheable;
  }

  @Override
  public ParamDesc paramDesc() {
    return paramDesc;
  }

  @Override
  public PgRowDesc rowDesc() {
    return rowDesc;
  }

  @Override
  public String sql() {
    return sql;
  }

  @Override
  public String prepare(TupleInternal values) {
    return paramDesc.prepare(values);
  }

  /**
   * @return true if the statement is a unnamed prepared statement
   */
  @Override
  public boolean cacheable() {
    return cacheable;
  }
}
