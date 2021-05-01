package com.icotools.sql;


import com.icotools.sql.pgsql.Condition;
import com.icotools.common.pgsql.Operator;
import com.icotools.common.pgsql.Where;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Where条件组装
 */
public interface ICondition extends IQuery {
  ICondition andWhereRaw(String where);

  ICondition andWhere(ICondition condition);
  ICondition andWhere(String field, Operator opt, Object value);

  ICondition andWhereField(String field1, Operator opt, String field2);

  ICondition andWhereIn(String field, Collection<?> value);
  ICondition andWhereIn(String field, IQuery value);

  ICondition andExists(IQuery value);
  ICondition andNotExists(IQuery value);

  ICondition andWhereNotIn(String field, Collection<?> value);
  ICondition andWhereNotIn(String field, IQuery value);

  ICondition andWhereBetween(String field, Object value1, Object value2);
  ICondition andWhereNotBetween(String field, Object value1, Object value2);

  ICondition andWhereNull(String field);
  ICondition andWhereNotNull(String field);

  ICondition andWhereLike(String field, Object value);
  ICondition andWhereLeftLike(String field, Object value);
  ICondition andWhereRightLike(String field, Object value);
  ICondition andWhereNotLike(String field, Object value);
  ICondition andWhereNotLeftLike(String field, Object value);
  ICondition andWhereNotRightLike(String field, Object value);

  ICondition andFilterWhere(boolean condition, String field, Operator opt, Object value);
  ICondition andFilterWhere(Predicate<Object> condition, String field, Operator opt, Object value);

  ICondition andFilterWhereIn(boolean condition, String field, Collection<?> value);
  ICondition andFilterWhereIn(boolean condition, String field, IQuery value);

  ICondition andFilterWhereNotIn(boolean condition, String field, Collection<?> value);
  ICondition andFilterWhereNotIn(boolean condition, String field, IQuery value);

  ICondition andFilterWhereBetween(boolean condition, String field, Object value1, Object value2);
  ICondition andFilterWhereNotBetween(boolean condition, String field, Object value1, Object value2);

  ICondition andFilterWhereNull(boolean condition, String field);
  ICondition andFilterWhereNotNull(boolean condition, String field);

  ICondition andFilterWhereLike(boolean condition, String field, Object value);
  ICondition andFilterWhereLeftLike(boolean condition, String field, Object value);
  ICondition andFilterWhereRightLike(boolean condition, String field, Object value);
  ICondition andFilterWhereNotLike(boolean condition, String field, Object value);
  ICondition andFilterWhereNotLeftLike(boolean condition, String field, Object value);
  ICondition andFilterWhereNotRightLike(boolean condition, String field, Object value);

  ICondition orWhereRaw(String where);

  ICondition orWhere(Condition condition);
  ICondition orWhere(String field, Operator opt, Object value);

  ICondition orWhereField(String field1, Operator opt, String field2);

  ICondition orWhereIn(String field, Collection<?> value);
  ICondition orWhereIn(String field, IQuery value);

  ICondition orExists(IQuery value);
  ICondition orNotExists(IQuery value);

  ICondition orWhereNotIn(String field, Collection<?> value);
  ICondition orWhereNotIn(String field, IQuery value);

  ICondition orWhereBetween(String field, Object value1, Object value2);
  ICondition orWhereNotBetween(String field, Object value1, Object value2);

  ICondition orWhereNull(String field);
  ICondition orWhereNotNull(String field);

  ICondition orWhereLike(String field, Object value);
  ICondition orWhereLeftLike(String field, Object value);
  ICondition orWhereRightLike(String field, Object value);
  ICondition orWhereNotLike(String field, Object value);
  ICondition orWhereNotLeftLike(String field, Object value);
  ICondition orWhereNotRightLike(String field, Object value);

  ICondition orFilterWhere(boolean condition, String field, Operator opt, Object value);
  ICondition orFilterWhere(Predicate<Object> condition, String field, Operator opt, Object value);

  ICondition orFilterWhereIn(boolean condition, String field, Collection<?> value);
  ICondition orFilterWhereIn(boolean condition, String field, IQuery value);

  ICondition orFilterWhereNotIn(boolean condition, String field, Collection<?> value);
  ICondition orFilterWhereNotIn(boolean condition, String field, IQuery value);

  ICondition orFilterWhereBetween(boolean condition, String field, Object value1, Object value2);
  ICondition orFilterWhereNotBetween(boolean condition, String field, Object value1, Object value2);

  ICondition orFilterWhereNull(boolean condition, String field);
  ICondition orFilterWhereNotNull(boolean condition, String field);
  ICondition orFilterWhereLike(boolean condition, String field, Object value);
  ICondition orFilterWhereLeftLike(boolean condition, String field, Object value);
  ICondition orFilterWhereRightLike(boolean condition, String field, Object value);
  ICondition orFilterWhereNotLike(boolean condition, String field, Object value);
  ICondition orFilterWhereNotLeftLike(boolean condition, String field, Object value);
  ICondition orFilterWhereNotRightLike(boolean condition, String field, Object value);

  Where getWhere();
  void setWrapper(IWrapper wrapper);
  String toStatement();
}
