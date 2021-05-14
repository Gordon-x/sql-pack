/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.icotools.sql;

import cn.icotools.common.pgsql.Keyword;
import cn.icotools.common.pgsql.Operator;

import java.util.Collection;

/**
 * SQL组装主体
 */
public interface IQuery {

  IQuery selectRaw(String fields);
  IQuery select(String... fields);
  IQuery select(Collection<String> fields);

  IRelation from(String table, String alias);
  IRelation from(IQuery query, String alias);
  IRelation with(IQuery query, String alias);

  ICondition whereRaw(String where);

  ICondition where(ICondition condition);
  ICondition where(String field, Operator opt, Object value);

  ICondition whereField(String field1, Operator opt, String field2);

  ICondition whereIn(String field, Collection<?> value);
  ICondition whereIn(String field, IQuery value);
  ICondition whereNotIn(String field, Collection<?> value);
  ICondition whereNotIn(String field, IQuery value);

  ICondition whereBetween(String field, Object value1, Object value2);
  ICondition whereNotBetween(String field, Object value1, Object value2);

  ICondition whereNull(String field);
  ICondition whereNotNull(String field);

  ICondition whereExists(IQuery query);
  ICondition whereNotExists(IQuery query);

  ICondition whereLike(String field, Object value);
  ICondition whereLeftLike(String field, Object value);
  ICondition whereRightLike(String field, Object value);
  ICondition whereNotLike(String field, Object value);
  ICondition whereNotLeftLike(String field, Object value);
  ICondition whereNotRightLike(String field, Object value);

  IHaving groupBy(String... columns);
  IHaving groupBy(String column);

  IQuery orderBy(String... columns);
  IQuery orderBy(String columns, Keyword order);
  IQuery orderBy(int columnIndex, Keyword order);

  String whereFormat(String field, Operator opt, Object... value);

  IQuery offset(int offset);

  IQuery limit(int limit);

  String toSql();

  interface IWrapper {
    String whereToSql();
    String havingToSql();
    String relationToSql();
  }
}
