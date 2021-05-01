package cn.voiceads.sql.sql;

import cn.voiceads.sql.common.pgsql.Operator;
import cn.voiceads.sql.common.pgsql.Where;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Having 条件
 */
public interface IHaving extends IQuery {

  IHaving havingRaw(String where);

  IHaving having(IHaving condition);
  IHaving having(String field, Operator opt, Object value);

  IHaving havingField(String field1, Operator opt, String field2);

  IHaving havingIn(String field, Collection<?> value);
  IHaving havingIn(String field, IQuery value);
  IHaving havingNotIn(String field, Collection<?> value);
  IHaving havingNotIn(String field, IQuery value);

  IHaving havingBetween(String field, Object value1, Object value2);
  IHaving havingNotBetween(String field, Object value1, Object value2);

  IHaving havingNull(String field);
  IHaving havingNotNull(String field);

  IHaving havingLike(String field, Object value);
  IHaving havingLeftLike(String field, Object value);
  IHaving havingRightLike(String field, Object value);

  IHaving havingNotLike(String field, Object value);
  IHaving havingNotLeftLike(String field, Object value);
  IHaving havingNotRightLike(String field, Object value);

  IHaving andHavingRaw(String where);

  IHaving andHaving(IHaving condition);
  IHaving andHaving(String field, Operator opt, Object value);

  IHaving andHavingField(String field1, Operator opt, String field2);

  IHaving andHavingIn(String field, Collection<?> value);
  IHaving andHavingIn(String field, IQuery value);

  IHaving andHavingNotIn(String field, Collection<?> value);
  IHaving andHavingNotIn(String field, IQuery value);

  IHaving andHavingBetween(String field, Object value1, Object value2);
  IHaving andHavingNotBetween(String field, Object value1, Object value2);

  IHaving andHavingNull(String field);
  IHaving andHavingNotNull(String field);

  IHaving andHavingLike(String field, Object value);
  IHaving andHavingLeftLike(String field, Object value);
  IHaving andHavingRightLike(String field, Object value);
  IHaving andHavingNotLike(String field, Object value);
  IHaving andHavingNotLeftLike(String field, Object value);
  IHaving andHavingNotRightLike(String field, Object value);

  IHaving andFilterHaving(boolean condition, String field, Operator opt, Object value);
  IHaving andFilterHaving(Predicate<Object> condition, String field, Operator opt, Object value);

  IHaving andFilterHavingIn(boolean condition, String field, Collection<?> value);
  IHaving andFilterHavingIn(boolean condition, String field, IQuery value);

  IHaving andFilterHavingNotIn(boolean condition, String field, Collection<?> value);
  IHaving andFilterHavingNotIn(boolean condition, String field, IQuery value);

  IHaving andFilterHavingBetween(boolean condition, String field, Object value1, Object value2);
  IHaving andFilterHavingNotBetween(boolean condition, String field, Object value1, Object value2);

  IHaving andFilterHavingNull(boolean condition, String field);
  IHaving andFilterHavingNotNull(boolean condition, String field);

  IHaving andFilterHavingLike(boolean condition, String field, Object value);
  IHaving andFilterHavingLeftLike(boolean condition, String field, Object value);
  IHaving andFilterHavingRightLike(boolean condition, String field, Object value);
  IHaving andFilterHavingNotLike(boolean condition, String field, Object value);
  IHaving andFilterHavingNotLeftLike(boolean condition, String field, Object value);
  IHaving andFilterHavingNotRightLike(boolean condition, String field, Object value);

  IHaving orHavingRaw(String where);

  IHaving orHaving(IHaving condition);
  IHaving orHaving(String field, Operator opt, Object value);

  IHaving orHavingField(String field1, Operator opt, String field2);

  IHaving orHavingIn(String field, Collection<?> value);
  IHaving orHavingIn(String field, IQuery value);

  IHaving orHavingNotIn(String field, Collection<?> value);
  IHaving orHavingNotIn(String field, IQuery value);

  IHaving orHavingBetween(String field, Object value1, Object value2);
  IHaving orHavingNotBetween(String field, Object value1, Object value2);

  IHaving orHavingNull(String field);
  IHaving orHavingNotNull(String field);

  IHaving orHavingLike(String field, Object value);
  IHaving orHavingLeftLike(String field, Object value);
  IHaving orHavingRightLike(String field, Object value);
  IHaving orHavingNotLike(String field, Object value);
  IHaving orHavingNotLeftLike(String field, Object value);
  IHaving orHavingNotRightLike(String field, Object value);

  IHaving orFilterHaving(boolean condition, String field, Operator opt, Object value);
  IHaving orFilterHaving(Predicate<Object> condition, String field, Operator opt, Object value);

  IHaving orFilterHavingIn(boolean condition, String field, Collection<?> value);
  IHaving orFilterHavingIn(boolean condition, String field, IQuery value);

  IHaving orFilterHavingNotIn(boolean condition, String field, Collection<?> value);
  IHaving orFilterHavingNotIn(boolean condition, String field, IQuery value);

  IHaving orFilterHavingBetween(boolean condition, String field, Object value1, Object value2);
  IHaving orFilterHavingNotBetween(boolean condition, String field, Object value1, Object value2);

  IHaving orFilterHavingNull(boolean condition, String field);
  IHaving orFilterHavingNotNull(boolean condition, String field);

  IHaving orFilterHavingLike(boolean condition, String field, Object value);
  IHaving orFilterHavingLeftLike(boolean condition, String field, Object value);
  IHaving orFilterHavingRightLike(boolean condition, String field, Object value);
  IHaving orFilterHavingNotLike(boolean condition, String field, Object value);
  IHaving orFilterHavingNotLeftLike(boolean condition, String field, Object value);
  IHaving orFilterHavingNotRightLike(boolean condition, String field, Object value);

  Where getHaving();
  void setWrapper(IWrapper wrapper);
  String toStatement();

}
