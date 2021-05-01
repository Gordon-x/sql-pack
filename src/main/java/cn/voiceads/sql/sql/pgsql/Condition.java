package cn.voiceads.sql.sql.pgsql;

import cn.voiceads.sql.common.pgsql.Keyword;
import cn.voiceads.sql.common.pgsql.Node;
import cn.voiceads.sql.common.pgsql.Operator;
import cn.voiceads.sql.common.pgsql.Where;
import cn.voiceads.sql.sql.ICondition;
import cn.voiceads.sql.sql.IQuery;

import java.util.Collection;
import java.util.function.Predicate;

public class Condition extends Query implements ICondition {

  public Condition() {
    if (wrapper == null) {
      wrapper = new Wrapper();
      wrapper.condition = this;
    }
  }

  @Override
  public ICondition andWhereRaw(String where) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    wrapper.where = new Where(wrapper.where, logic, new Node(where));
    return this;
  }

  @Override
  public ICondition andWhere(ICondition condition) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    wrapper.where = new Where(wrapper.where, logic, new Node(condition.getWhere()));
    return this;
  }

  @Override
  public ICondition andWhere(String field, Operator opt, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    wrapper.where = new Where(wrapper.where, logic, new Node(whereFormat(field, opt, value)));
    return this;
  }

  @Override
  public ICondition andWhereField(String field1, Operator opt, String field2) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = String.format("%s %s %s", field1, opt.getValue(), field2);
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereIn(String field, Collection<?> value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.IN, value.toArray());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereIn(String field, IQuery value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = String.format("%s in (%s)", field, value.toSql());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andExists(IQuery value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = String.format("%s (%s)", Operator.EXISTS.getValue(), value.toSql());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andNotExists(IQuery value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = String.format("%s (%s)", Operator.NOT_EXISTS.getValue(), value.toSql());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereNotIn(String field, Collection<?> value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOT_IN, value.toArray());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereNotIn(String field, IQuery value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = String.format("%s not in (%s)", field, value.toSql());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereBetween(String field, Object value1, Object value2) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    wrapper.where = new Where(wrapper.where, logic, new Node(whereFormat(field, Operator.BETWEEN, value1, value2)));
    return this;
  }

  @Override
  public ICondition andWhereNotBetween(String field, Object value1, Object value2) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    wrapper.where = new Where(wrapper.where, logic, new Node(whereFormat(field, Operator.NOT_BETWEEN, value1, value2)));
    return this;
  }

  @Override
  public ICondition andWhereNull(String field) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.ISNULL);
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereNotNull(String field) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOTNULL);
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.LIKE, String.format("%%%s%%", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereLeftLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.LIKE, String.format("%%%s", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereRightLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.LIKE, String.format("%s%%", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereNotLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%%%s%%", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereNotLeftLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%%%s", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andWhereNotRightLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.AND;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%s%%", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition andFilterWhere(boolean condition, String field, Operator opt, Object value) {
    if (!condition) {
      return this;
    }
    return andWhere(field, opt, value);
  }

  @Override
  public ICondition andFilterWhere(Predicate<Object> filter, String field, Operator opt, Object value) {
    if (!filter.test(value)) {
      return this;
    }
    return andWhere(field, opt, value);
  }

  @Override
  public ICondition andFilterWhereIn(boolean condition, String field, Collection<?> value) {
    if (!condition) {
      return this;
    }
    return andWhereIn(field, value);
  }

  @Override
  public ICondition andFilterWhereIn(boolean condition, String field, IQuery value) {
    if (!condition) {
      return this;
    }
    return andWhereIn(field, value);
  }

  @Override
  public ICondition andFilterWhereNotIn(boolean condition, String field, Collection<?> value) {
    if (!condition) {
      return this;
    }
    return andWhereNotIn(field, value);
  }

  @Override
  public ICondition andFilterWhereNotIn(boolean condition, String field, IQuery value) {
    if (!condition) {
      return this;
    }
    return andWhereNotIn(field, value);
  }

  @Override
  public ICondition andFilterWhereBetween(boolean condition, String field, Object value1, Object value2) {
    if (!condition) {
      return this;
    }
    return andWhereBetween(field, value1, value2);
  }

  @Override
  public ICondition andFilterWhereNotBetween(boolean condition, String field, Object value1, Object value2) {
    if (!condition) {
      return this;
    }
    return andWhereNotBetween(field, value1, value2);
  }

  @Override
  public ICondition andFilterWhereNull(boolean condition, String field) {
    if (!condition) {
      return this;
    }
    return andWhereNull(field);
  }

  @Override
  public ICondition andFilterWhereNotNull(boolean condition, String field) {
    if (!condition) {
      return this;
    }
    return andWhereNotNull(field);
  }

  @Override
  public ICondition andFilterWhereLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return andWhereLike(field, value);
  }

  @Override
  public ICondition andFilterWhereLeftLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return andWhereLeftLike(field, value);
  }

  @Override
  public ICondition andFilterWhereRightLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return andWhereRightLike(field, value);
  }

  @Override
  public ICondition andFilterWhereNotLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return andWhereNotLike(field, value);
  }

  @Override
  public ICondition andFilterWhereNotLeftLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return andWhereNotLeftLike(field, value);
  }

  @Override
  public ICondition andFilterWhereNotRightLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return andWhereNotRightLike(field, value);
  }

  @Override
  public ICondition orWhereRaw(String where) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    wrapper.where = new Where(wrapper.where, logic, new Node(where));
    return this;
  }

  @Override
  public ICondition orWhere(Condition condition) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    wrapper.where = new Where(wrapper.where, logic, new Node(condition.getWhere()));
    return this;
  }

  @Override
  public ICondition orWhere(String field, Operator opt, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    wrapper.where = new Where(wrapper.where, logic, new Node(whereFormat(field, opt, value)));
    return this;
  }

  @Override
  public ICondition orWhereField(String field1, Operator opt, String field2) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = String.format("%s %s %s", field1, opt.getValue(), field2);
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereIn(String field, Collection<?> value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.IN, value.toArray());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereIn(String field, IQuery value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = String.format("%s in (%s)", field, value.toSql());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orExists(IQuery value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = String.format("%s (%s)", Operator.EXISTS, value.toSql());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orNotExists(IQuery value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = String.format("%s (%s)", Operator.NOT_EXISTS, value.toSql());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereNotIn(String field, Collection<?> value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.NOT_IN, value.toArray());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereNotIn(String field, IQuery value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = String.format("%s not in (%s)", field, value.toSql());
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereBetween(String field, Object value1, Object value2) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    wrapper.where = new Where(wrapper.where, logic, new Node(whereFormat(field, Operator.BETWEEN, value1, value2)));
    return this;
  }

  @Override
  public ICondition orWhereNotBetween(String field, Object value1, Object value2) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    wrapper.where = new Where(wrapper.where, logic, new Node(whereFormat(field, Operator.NOT_BETWEEN, value1, value2)));
    return this;
  }

  @Override
  public ICondition orWhereNull(String field) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.ISNULL);
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereNotNull(String field) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.NOTNULL);
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.LIKE, String.format("%%%s%%", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereLeftLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.LIKE, String.format("%%%s", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereRightLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.LIKE, String.format("%s%%", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereNotLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%%%s%%", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereNotLeftLike(String field, Object value) {
    Keyword logic = wrapper.where == null ? null : Keyword.OR;
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%%%s", value));
    wrapper.where = new Where(wrapper.where, logic, new Node(v));
    return this;
  }

  @Override
  public ICondition orWhereNotRightLike(String field, Object value) {
    String v = whereFormat(field, Operator.NOT_LIKE, String.format("%s%%", value));
    wrapper.where = new Where(wrapper.where, Keyword.OR, new Node(v));
    return this;
  }

  @Override
  public ICondition orFilterWhere(boolean condition, String field, Operator opt, Object value) {
    if (!condition) {
      return this;
    }
    return orWhere(field, opt, value);
  }

  @Override
  public ICondition orFilterWhere(Predicate<Object> filter, String field, Operator opt, Object value) {
    if (!filter.test(value)) {
      return this;
    }
    return orWhere(field, opt, value);
  }

  @Override
  public ICondition orFilterWhereIn(boolean condition, String field, Collection<?> value) {
    if (!condition) {
      return this;
    }
    return orWhereIn(field, value);
  }

  @Override
  public ICondition orFilterWhereIn(boolean condition, String field, IQuery value) {
    if (!condition) {
      return this;
    }
    return orWhereIn(field, value);
  }

  @Override
  public ICondition orFilterWhereNotIn(boolean condition, String field, Collection<?> value) {
    if (!condition) {
      return this;
    }
    return orWhereNotIn(field, value);
  }

  @Override
  public ICondition orFilterWhereNotIn(boolean condition, String field, IQuery value) {
    if (!condition) {
      return this;
    }
    return orWhereNotIn(field, value);
  }

  @Override
  public ICondition orFilterWhereBetween(boolean condition, String field, Object value1, Object value2) {
    if (!condition) {
      return this;
    }
    return orWhereBetween(field, value1, value2);
  }

  @Override
  public ICondition orFilterWhereNotBetween(boolean condition, String field, Object value1, Object value2) {
    if (!condition) {
      return this;
    }
    return orWhereNotBetween(field, value1, value2);
  }

  @Override
  public ICondition orFilterWhereNull(boolean condition, String field) {
    if (!condition) {
      return this;
    }
    return orWhereNull(field);
  }

  @Override
  public ICondition orFilterWhereNotNull(boolean condition, String field) {
    if (!condition) {
      return this;
    }
    return orWhereNotNull(field);
  }

  @Override
  public ICondition orFilterWhereLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orWhereLike(field, value);
  }

  @Override
  public ICondition orFilterWhereLeftLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orWhereLeftLike(field, value);
  }

  @Override
  public ICondition orFilterWhereRightLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orWhereRightLike(field, value);
  }

  @Override
  public ICondition orFilterWhereNotLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orWhereNotLike(field, value);
  }

  @Override
  public ICondition orFilterWhereNotLeftLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orWhereNotLeftLike(field, value);
  }

  @Override
  public ICondition orFilterWhereNotRightLike(boolean condition, String field, Object value) {
    if (!condition) {
      return this;
    }
    return orWhereNotRightLike(field, value);
  }

  @Override
  public Where getWhere() {
    return wrapper.where;
  }

  @Override
  public void setWrapper(IWrapper wrapper) {
    this.wrapper = (Wrapper) wrapper;
  }

  public String toStatement() {
    return wrapper.whereToSql();
  }

  @Override
  public String toString() {
    return toStatement();
  }
}
