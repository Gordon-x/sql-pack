/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.icotools.sql.pgsql;

import cn.icotools.sql.IHaving;
import cn.icotools.sql.IQuery;
import cn.icotools.common.pgsql.Keyword;
import cn.icotools.common.pgsql.Node;
import cn.icotools.common.pgsql.Operator;
import cn.icotools.common.pgsql.Where;

import java.util.Collection;
import java.util.function.Predicate;

public class Having extends Query implements IHaving {

  public Having() {
    if (wrapper == null) {
      wrapper = new Wrapper();
      wrapper.havingCondition = this;
    }
  }

  @Override
  public IHaving havingRaw(String where) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    wrapper.having = new Where(wrapper.having, logic, new Node(where));
    return this;
  }

  @Override
  public IHaving having(IHaving condition) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    wrapper.having = new Where(wrapper.having, logic, new Node(condition.getHaving()));
    return this;
  }

  @Override
  public IHaving having(String field, Operator opt, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    wrapper.having = new Where(wrapper.having, logic, new Node(whereFormat(field, opt, value)));
    return this;
  }

  @Override
  public IHaving havingField(String field1, Operator opt, String field2) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = String.format("%s %s %s", field1, opt.getValue(), field2);
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingIn(String field, Collection<?> value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.IN, value.toArray());
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingIn(String field, IQuery value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = String.format("%s in (%s)", field, value.toSql());
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingNotIn(String field, Collection<?> value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOT_IN, value.toArray());
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingNotIn(String field, IQuery value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = String.format("%s not in (%s)", field, value.toSql());
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingBetween(String field, Object value1, Object value2) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    wrapper.having = new Where(wrapper.having, logic, new Node(whereFormat(field, Operator.BETWEEN, value1, value2)));
    return this;
  }

  @Override
  public IHaving havingNotBetween(String field, Object value1, Object value2) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    wrapper.having = new Where(wrapper.having, logic, new Node(whereFormat(field, Operator.NOT_BETWEEN, value1, value2)));
    return this;
  }

  @Override
  public IHaving havingNull(String field) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.ISNULL);
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingNotNull(String field) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOTNULL);
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.LIKE, String.format("%%%s%%", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingLeftLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.LIKE, String.format("%%%s", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingRightLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.LIKE, String.format("%s%%", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingNotLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%%%s%%", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingNotLeftLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%%%s", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving havingNotRightLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%s%%", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving andHavingRaw(String where) {
    return havingRaw(where);
  }

  @Override
  public IHaving andHaving(IHaving condition) {
    return having(condition);
  }

  @Override
  public IHaving andHaving(String field, Operator opt, Object value) {
    return having(field, opt, value);
  }

  @Override
  public IHaving andHavingField(String field1, Operator opt, String field2) {
    return havingField(field1, opt, field2);
  }

  @Override
  public IHaving andHavingIn(String field, Collection<?> value) {
    return havingIn(field, value);
  }

  @Override
  public IHaving andHavingIn(String field, IQuery value) {
    return havingIn(field, value);
  }

  @Override
  public IHaving andHavingNotIn(String field, Collection<?> value) {
    return havingNotIn(field, value);
  }

  @Override
  public IHaving andHavingNotIn(String field, IQuery value) {
    return havingIn(field, value);
  }

  @Override
  public IHaving andHavingBetween(String field, Object value1, Object value2) {
    return havingBetween(field, value1, value2);
  }

  @Override
  public IHaving andHavingNotBetween(String field, Object value1, Object value2) {
    return havingNotBetween(field, value1, value2);
  }

  @Override
  public IHaving andHavingNull(String field) {
    return havingNull(field);
  }

  @Override
  public IHaving andHavingNotNull(String field) {
    return havingNotNull(field);
  }

  @Override
  public IHaving andHavingLike(String field, Object value) {
    return havingLike(field, value);
  }

  @Override
  public IHaving andHavingLeftLike(String field, Object value) {
    return havingLeftLike(field, value);
  }

  @Override
  public IHaving andHavingRightLike(String field, Object value) {
    return havingRightLike(field, value);
  }

  @Override
  public IHaving andHavingNotLike(String field, Object value) {
    return havingNotLike(field, value);
  }

  @Override
  public IHaving andHavingNotLeftLike(String field, Object value) {
    return havingNotLeftLike(field, value);
  }

  @Override
  public IHaving andHavingNotRightLike(String field, Object value) {
    return havingNotRightLike(field, value);
  }

  @Override
  public IHaving andFilterHaving(boolean condition, String field, Operator opt, Object value) {
    if (!condition) {
      return this;
    }
    return having(field, opt, value);
  }

  @Override
  public IHaving andFilterHaving(Predicate<Object> condition, String field, Operator opt, Object value) {
    if (!condition.test(value)) {
      return this;
    }
    return having(field, opt, value);
  }

  @Override
  public IHaving andFilterHavingIn(boolean condition, String field, Collection<?> value) {
    if (!condition) {
      return this;
    }
    return havingIn(field, value);
  }

  @Override
  public IHaving andFilterHavingIn(boolean condition, String field, IQuery value) {
    if (!condition) {
      return this;
    }
    return havingIn(field, value);
  }

  @Override
  public IHaving andFilterHavingNotIn(boolean condition, String field, Collection<?> value) {
    if (!condition) {
      return this;
    }
    return havingNotIn(field, value);
  }

  @Override
  public IHaving andFilterHavingNotIn(boolean condition, String field, IQuery value) {
    if (!condition) {
      return this;
    }
    return havingNotIn(field, value);
  }

  @Override
  public IHaving andFilterHavingBetween(boolean condition, String field, Object value1, Object value2) {
    if (!condition) {
      return this;
    }
    return havingBetween(field, value1, value2);
  }

  @Override
  public IHaving andFilterHavingNotBetween(boolean condition, String field, Object value1, Object value2) {
    if (!condition) {
      return this;
    }
    return havingNotBetween(field, value1, value2);
  }

  @Override
  public IHaving andFilterHavingNull(boolean condition, String field) {
    if (!condition) {
      return this;
    }
    return havingNull(field);
  }

  @Override
  public IHaving andFilterHavingNotNull(boolean condition, String field) {
    if (!condition) {
      return this;
    }
    return havingNotNull(field);
  }

  @Override
  public IHaving andFilterHavingLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return havingLike(field, value);
  }

  @Override
  public IHaving andFilterHavingLeftLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return havingLeftLike(field, value);
  }

  @Override
  public IHaving andFilterHavingRightLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return havingRightLike(field, value);
  }

  @Override
  public IHaving andFilterHavingNotLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return havingNotLike(field, value);
  }

  @Override
  public IHaving andFilterHavingNotLeftLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return havingNotLeftLike(field, value);
  }

  @Override
  public IHaving andFilterHavingNotRightLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return havingNotRightLike(field, value);
  }

  @Override
  public IHaving orHavingRaw(String where) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    wrapper.having = new Where(wrapper.having, logic, new Node(where));
    return this;
  }

  @Override
  public IHaving orHaving(IHaving condition) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    wrapper.having = new Where(wrapper.having, logic, new Node(condition.getHaving()));
    return this;
  }

  @Override
  public IHaving orHaving(String field, Operator opt, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    wrapper.having = new Where(wrapper.having, logic, new Node(whereFormat(field, opt, value)));
    return this;
  }

  @Override
  public IHaving orHavingField(String field1, Operator opt, String field2) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = String.format("%s %s %s", field1, opt.getValue(), field2);
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingIn(String field, Collection<?> value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.IN, value.toArray());
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingIn(String field, IQuery value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = String.format("%s in (%s)", field, value.toSql());
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingNotIn(String field, Collection<?> value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.NOT_IN, value.toArray());
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingNotIn(String field, IQuery value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = String.format("%s not in (%s)", field, value.toSql());
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingBetween(String field, Object value1, Object value2) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    wrapper.having = new Where(wrapper.having, logic, new Node(whereFormat(field, Operator.BETWEEN, value1, value2)));
    return this;
  }

  @Override
  public IHaving orHavingNotBetween(String field, Object value1, Object value2) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    wrapper.having = new Where(wrapper.having, logic, new Node(whereFormat(field, Operator.NOT_BETWEEN, value1, value2)));
    return this;
  }

  @Override
  public IHaving orHavingNull(String field) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.ISNULL);
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingNotNull(String field) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.NOTNULL);
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.LIKE, String.format("%%%s%%", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingLeftLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.LIKE, String.format("%%%s", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingRightLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.LIKE, String.format("%s%%", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingNotLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%%%s%%", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingNotLeftLike(String field, Object value) {
    Keyword logic = wrapper.having == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%%%s", value));
    wrapper.having = new Where(wrapper.having, logic, new Node(v));
    return this;
  }

  @Override
  public IHaving orHavingNotRightLike(String field, Object value) {
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%s%%", value));
    wrapper.having = new Where(wrapper.having, Keyword.OR, new Node(v));
    return this;
  }

  @Override
  public IHaving orFilterHaving(boolean condition, String field, Operator opt, Object value) {
    if (!condition) {
      return this;
    }
    return orHaving(field, opt, value);
  }

  @Override
  public IHaving orFilterHaving(Predicate<Object> condition, String field, Operator opt, Object value) {
    if (!condition.test(value)) {
      return this;
    }
    return orHaving(field, opt, value);
  }

  @Override
  public IHaving orFilterHavingIn(boolean condition, String field, Collection<?> value) {
    if (!condition) {
      return this;
    }
    return orHavingIn(field, value);
  }

  @Override
  public IHaving orFilterHavingIn(boolean condition, String field, IQuery value) {
    if (!condition) {
      return this;
    }
    return orHavingIn(field, value);
  }

  @Override
  public IHaving orFilterHavingNotIn(boolean condition, String field, Collection<?> value) {
    if (!condition) {
      return this;
    }
    return orHavingNotIn(field, value);
  }

  @Override
  public IHaving orFilterHavingNotIn(boolean condition, String field, IQuery value) {
    if (!condition) {
      return this;
    }
    return orHavingNotIn(field, value);
  }

  @Override
  public IHaving orFilterHavingBetween(boolean condition, String field, Object value1, Object value2) {
    if (!condition) {
      return this;
    }
    return orHavingBetween(field, value1, value2);
  }

  @Override
  public IHaving orFilterHavingNotBetween(boolean condition, String field, Object value1, Object value2) {
    if (!condition) {
      return this;
    }
    return orHavingNotBetween(field, value1, value2);
  }

  @Override
  public IHaving orFilterHavingNull(boolean condition, String field) {
    if (!condition) {
      return this;
    }
    return orHavingNull(field);
  }

  @Override
  public IHaving orFilterHavingNotNull(boolean condition, String field) {
    if (!condition) {
      return this;
    }
    return orHavingNotNull(field);
  }

  @Override
  public IHaving orFilterHavingLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orHavingLike(field, value);
  }

  @Override
  public IHaving orFilterHavingLeftLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orHavingLeftLike(field, value);
  }

  @Override
  public IHaving orFilterHavingRightLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orHavingRightLike(field, value);
  }

  @Override
  public IHaving orFilterHavingNotLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orHavingNotLike(field, value);
  }

  @Override
  public IHaving orFilterHavingNotLeftLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orHavingNotLeftLike(field, value);
  }

  @Override
  public IHaving orFilterHavingNotRightLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orHavingNotRightLike(field, value);
  }

  @Override
  public Where getHaving() {
    return wrapper.having;
  }

  @Override
  public void setWrapper(IWrapper wrapper) {
    this.wrapper = (Wrapper) wrapper;
  }

  @Override
  public String toStatement() {
    return wrapper.havingToSql();
  }

  @Override
  public String toString() {
    return toStatement();
  }
}
