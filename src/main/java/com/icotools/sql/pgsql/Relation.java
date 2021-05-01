package com.icotools.sql.pgsql;

import com.icotools.sql.ICondition;
import com.icotools.common.pgsql.Keyword;
import com.icotools.sql.IQuery;
import com.icotools.sql.IRelation;

import java.util.List;

public class Relation extends Query implements IRelation {

  @Override
  public IRelation joinRaw(String joinStr) {
    wrapper.join.add(String.format("%s %s", Keyword.JOIN.getValue(), joinStr));
    return this;
  }

  @Override
  public IRelation join(String table, String alias, String on) throws Exception {
    wrapper.join.add(joinFormat(Keyword.JOIN, table, alias, on));
    return this;
  }

  @Override
  public IRelation join(IQuery query, String alias, String on) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.JOIN, String.format("(%s)", query.toSql()), alias, on));
    return this;
  }

  @Override
  public IRelation join(String table, String alias, ICondition condition) throws Exception {
    wrapper.join.add(joinFormat(Keyword.JOIN, table, alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation join(IQuery query, String alias, ICondition condition) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.JOIN, String.format("(%s)", query.toSql()), alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation leftJoinRaw(String joinStr) {
    wrapper.join.add(String.format("%s %s", Keyword.LEFT_JOIN.getValue(), joinStr));
    return this;
  }

  @Override
  public IRelation leftJoin(String table, String alias, String on) throws Exception {
    wrapper.join.add(joinFormat(Keyword.LEFT_JOIN, table, alias, on));
    return this;
  }

  @Override
  public IRelation leftJoin(IQuery query, String alias, String on) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.LEFT_JOIN, String.format("(%s)", query.toSql()), alias, on));
    return this;
  }

  @Override
  public IRelation leftJoin(String table, String alias, ICondition condition) throws Exception {
    wrapper.join.add(joinFormat(Keyword.LEFT_JOIN, table, alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation leftJoin(IQuery query, String alias, ICondition condition) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.LEFT_JOIN, String.format("(%s)", query.toSql()), alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation rightJoinRaw(String joinStr) {
    wrapper.join.add(String.format("%s %s", Keyword.RIGHT_JOIN.getValue(), joinStr));
    return this;
  }

  @Override
  public IRelation rightJoin(String table, String alias, String on) throws Exception {
    wrapper.join.add(joinFormat(Keyword.RIGHT_JOIN, table, alias, on));
    return this;
  }

  @Override
  public IRelation rightJoin(IQuery query, String alias, String on) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.LEFT_JOIN, String.format("(%s)", query.toSql()), alias, on));
    return this;
  }

  @Override
  public IRelation rightJoin(String table, String alias, ICondition condition) throws Exception {
    wrapper.join.add(joinFormat(Keyword.RIGHT_JOIN, table, alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation rightJoin(IQuery query, String alias, ICondition condition) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.RIGHT_JOIN, String.format("(%s)", query.toSql()), alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation innerJoinRaw(String joinStr) {
    wrapper.join.add(String.format("%s %s", Keyword.INNER_JOIN.getValue(), joinStr));
    return this;
  }

  @Override
  public IRelation innerJoin(String table, String alias, String on) throws Exception {
    wrapper.join.add(joinFormat(Keyword.INNER_JOIN, table, alias, on));
    return this;
  }

  @Override
  public IRelation innerJoin(IQuery query, String alias, String on) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.INNER_JOIN, String.format("(%s)", query.toSql()), alias, on));
    return this;
  }

  @Override
  public IRelation innerJoin(String table, String alias, ICondition condition) throws Exception {
    wrapper.join.add(joinFormat(Keyword.INNER_JOIN, table, alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation innerJoin(IQuery query, String alias, ICondition condition) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.INNER_JOIN, String.format("(%s)", query.toSql()), alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation fullJoinRaw(String joinStr) {
    wrapper.join.add(String.format("%s %s", Keyword.FULL_JOIN.getValue(), joinStr));
    return this;
  }

  @Override
  public IRelation fullJoin(String table, String alias, String on) throws Exception {
    wrapper.join.add(joinFormat(Keyword.FULL_JOIN, table, alias, on));
    return this;
  }

  @Override
  public IRelation fullJoin(IQuery query, String alias, String on) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.FULL_JOIN, String.format("(%s)", query.toSql()), alias, on));
    return this;
  }

  @Override
  public IRelation fullJoin(String table, String alias, ICondition condition) throws Exception {
    wrapper.join.add(joinFormat(Keyword.FULL_JOIN, table, alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation fullJoin(IQuery query, String alias, ICondition condition) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.FULL_JOIN, String.format("(%s)", query.toSql()), alias, condition.toStatement()));
    return this;
  }

  @Override
  public IRelation crossJoinRaw(String joinStr) {
    wrapper.join.add(String.format("%s %s", Keyword.CROSS_JOIN.getValue(), joinStr));
    return this;
  }

  @Override
  public IRelation crossJoin(String table, String alias) throws Exception {
    wrapper.join.add(joinFormat(Keyword.CROSS_JOIN, table, alias, null));
    return this;
  }

  @Override
  public IRelation crossJoin(IQuery query, String alias) throws Exception {
    this.wrapper.join.add(joinFormat(Keyword.CROSS_JOIN, String.format("(%s)", query.toSql()), alias, null));
    return this;
  }

  @Override
  public IRelation joinUsing(String table, String alias, String... columns) throws Exception {
    wrapper.join.add(joinUsingFormat(Keyword.JOIN, table, alias, columns));
    return this;
  }

  @Override
  public IRelation joinUsing(IQuery query, String alias, String... columns) throws Exception {
    this.wrapper.join.add(joinUsingFormat(Keyword.JOIN, String.format("(%s)", query.toSql()), alias, columns));
    return this;
  }

  @Override
  public IRelation leftJoinUsing(String table, String alias, String... columns) throws Exception {
    wrapper.join.add(joinUsingFormat(Keyword.LEFT_JOIN, table, alias, columns));
    return this;
  }

  @Override
  public IRelation leftJoinUsing(IQuery query, String alias, String... columns) throws Exception {
    this.wrapper.join.add(joinUsingFormat(Keyword.LEFT_JOIN, String.format("(%s)", query.toSql()), alias, columns));
    return this;
  }

  @Override
  public IRelation rightJoinUsing(String table, String alias, String... columns) throws Exception {
    wrapper.join.add(joinUsingFormat(Keyword.RIGHT_JOIN, table, alias, columns));
    return this;
  }

  @Override
  public IRelation rightJoinUsing(IQuery query, String alias, String... columns) throws Exception {
    this.wrapper.join.add(joinUsingFormat(Keyword.RIGHT_JOIN, String.format("(%s)", query.toSql()), alias, columns));
    return this;
  }

  @Override
  public IRelation fullJoinUsing(String table, String alias, String... columns) throws Exception {
    wrapper.join.add(joinUsingFormat(Keyword.FULL_JOIN, table, alias, columns));
    return this;
  }

  @Override
  public IRelation fullJoinUsing(IQuery query, String alias, String... columns) throws Exception {
    this.wrapper.join.add(joinUsingFormat(Keyword.FULL_JOIN, String.format("(%s)", query.toSql()), alias, columns));
    return this;
  }

  @Override
  public IRelation filterJoinRaw(boolean filter, String joinStr) {
    if (!filter) {
      return this;
    }
    return joinRaw(joinStr);
  }

  @Override
  public IRelation filterJoin(boolean filter, String table, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return join(table, alias, on);
  }

  @Override
  public IRelation filterJoin(boolean filter, IQuery query, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return join(query, alias, on);
  }

  @Override
  public IRelation filterJoin(boolean filter, String table, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return join(table, alias, condition);
  }

  @Override
  public IRelation filterJoin(boolean filter, IQuery query, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return join(query, alias, condition);
  }

  @Override
  public IRelation filterLeftJoinRaw(boolean filter, String joinStr) {
    if (!filter) {
      return this;
    }
    return leftJoinRaw(joinStr);
  }

  @Override
  public IRelation filterLeftJoin(boolean filter, String table, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return leftJoin(table, alias, on);
  }

  @Override
  public IRelation filterLeftJoin(boolean filter, IQuery query, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return leftJoin(query, alias, on);
  }

  @Override
  public IRelation filterLeftJoin(boolean filter, String table, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return leftJoin(table, alias, condition);
  }

  @Override
  public IRelation filterLeftJoin(boolean filter, IQuery query, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return leftJoin(query, alias, condition);
  }

  @Override
  public IRelation filterRightJoinRaw(boolean filter, String joinStr) {
    if (!filter) {
      return this;
    }
    return rightJoinRaw(joinStr);
  }

  @Override
  public IRelation filterRightJoin(boolean filter, String table, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return rightJoin(table, alias, on);
  }

  @Override
  public IRelation filterRightJoin(boolean filter, IQuery query, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return rightJoin(query, alias, on);
  }

  @Override
  public IRelation filterRightJoin(boolean filter, String table, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return rightJoin(table, alias, condition);
  }

  @Override
  public IRelation filterRightJoin(boolean filter, IQuery query, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return rightJoin(query, alias, condition);
  }

  @Override
  public IRelation filterInnerJoinRaw(boolean filter, String joinStr) {
    if (!filter) {
      return this;
    }
    return innerJoinRaw(joinStr);
  }

  @Override
  public IRelation filterInnerJoin(boolean filter, String table, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return innerJoin(table, alias, on);
  }

  @Override
  public IRelation filterInnerJoin(boolean filter, IQuery query, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return innerJoin(query, alias, on);
  }

  @Override
  public IRelation filterInnerJoin(boolean filter, String table, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return innerJoin(table, alias, condition);
  }

  @Override
  public IRelation filterInnerJoin(boolean filter, IQuery query, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return innerJoin(query, alias, condition);
  }

  @Override
  public IRelation filterFullJoinRaw(boolean filter, String joinStr) {
    if (!filter) {
      return this;
    }
    return fullJoinRaw(joinStr);
  }

  @Override
  public IRelation filterFullJoin(boolean filter, String table, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return fullJoin(table, alias, on);
  }

  @Override
  public IRelation filterFullJoin(boolean filter, IQuery query, String alias, String on) throws Exception {
    if (!filter) {
      return this;
    }
    return fullJoin(query, alias, on);
  }

  @Override
  public IRelation filterFullJoin(boolean filter, String table, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return fullJoin(table, alias, condition);
  }

  @Override
  public IRelation filterFullJoin(boolean filter, IQuery query, String alias, ICondition condition) throws Exception {
    if (!filter) {
      return this;
    }
    return fullJoin(query, alias, condition);
  }

  @Override
  public IRelation filterCrossJoinRaw(boolean filter, String joinStr) {
    if (!filter) {
      return this;
    }
    return crossJoinRaw(joinStr);
  }

  @Override
  public IRelation filterCrossJoin(boolean filter, String table, String alias) throws Exception {
    if (!filter) {
      return this;
    }
    return crossJoin(table, alias);
  }

  @Override
  public IRelation filterCrossJoin(boolean filter, IQuery query, String alias) throws Exception {
    if (!filter) {
      return this;
    }
    return crossJoin(query, alias);
  }

  @Override
  public IRelation filterJoinUsing(boolean filter, String table, String alias, String... columns) throws Exception {
    if (!filter) {
      return this;
    }
    return joinUsing(table, alias, columns);
  }

  @Override
  public IRelation filterJoinUsing(boolean filter, IQuery query, String alias, String... columns) throws Exception {
    if (!filter) {
      return this;
    }
    return joinUsing(query, alias, columns);
  }

  @Override
  public IRelation filterLeftJoinUsing(boolean filter, String table, String alias, String... columns) throws Exception {
    if (!filter) {
      return this;
    }
    return leftJoinUsing(table, alias, columns);
  }

  @Override
  public IRelation filterLeftJoinUsing(boolean filter, IQuery query, String alias, String... columns) throws Exception {
    if (!filter) {
      return this;
    }
    return leftJoinUsing(query, alias, columns);
  }

  @Override
  public IRelation filterRightJoinUsing(boolean filter, String table, String alias, String... columns) throws Exception {
    if (!filter) {
      return this;
    }
    return rightJoinUsing(table, alias, columns);
  }

  @Override
  public IRelation filterRightJoinUsing(boolean filter, IQuery query, String alias, String... columns) throws Exception {
    if (!filter) {
      return this;
    }
    return rightJoinUsing(query, alias, columns);
  }

  @Override
  public IRelation filterFullJoinUsing(boolean filter, String table, String alias, String... columns) throws Exception {
    if (!filter) {
      return this;
    }
    return fullJoinUsing(table, alias, columns);
  }

  @Override
  public IRelation filterFullJoinUsing(boolean filter, IQuery query, String alias, String... columns) throws Exception {
    if (!filter) {
      return this;
    }
    return fullJoinUsing(query, alias, columns);
  }

  @Override
  public List<String> getJoinList() {
    return wrapper.join;
  }

  @Override
  public void setWrapper(IWrapper wrapper) {
    this.wrapper = (Wrapper) wrapper;
  }

  @Override
  public String toStatement() {
    return wrapper.relationToSql();
  }

  @Override
  public String toString() {
    return toStatement();
  }

  private String joinFormat(Keyword relation, String table, String alias, String on) throws Exception {
    if (alias == null) {
      throw new Exception("请填写数据表别名");
    }
    if (Keyword.CROSS_JOIN.equals(relation)) {
      return String.format("%s %s as %s", relation.getValue(), table, alias);
    }
    return String.format("%s %s as %s on %s", relation.getValue(), table, alias, on);
  }

  private String joinUsingFormat(Keyword relation, String table, String alias, String... columns) throws Exception {
    if (alias == null) {
      throw new Exception("请填写数据表别名");
    }

    String cols = String.join(",", columns);
    return String.format("%s %s as %s using (%s)", relation.getValue(), table, alias, cols);
  }
}
