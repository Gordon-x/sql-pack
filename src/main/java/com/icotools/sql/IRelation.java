package com.icotools.sql;

import java.util.List;

/**
 * 表关联
 */
public interface IRelation extends IQuery {
  IRelation joinRaw(String joinStr);
  IRelation join(String table, String alias, String on) throws Exception;
  IRelation join(IQuery query, String alias, String on) throws Exception;
  IRelation join(String table, String alias, ICondition condition) throws Exception;
  IRelation join(IQuery query, String alias, ICondition condition) throws Exception;

  IRelation leftJoinRaw(String joinStr);
  IRelation leftJoin(String table, String alias, String on) throws Exception;
  IRelation leftJoin(IQuery query, String alias, String on) throws Exception;
  IRelation leftJoin(String table, String alias, ICondition condition) throws Exception;
  IRelation leftJoin(IQuery query, String alias, ICondition condition)  throws Exception;

  IRelation rightJoinRaw(String joinStr);
  IRelation rightJoin(String table, String alias, String on)  throws Exception;
  IRelation rightJoin(IQuery query, String alias, String on)  throws Exception;
  IRelation rightJoin(String table, String alias, ICondition condition)  throws Exception;
  IRelation rightJoin(IQuery query, String alias, ICondition condition)  throws Exception;

  IRelation innerJoinRaw(String joinStr);
  IRelation innerJoin(String table, String alias, String on)  throws Exception;
  IRelation innerJoin(IQuery query, String alias, String on)  throws Exception;
  IRelation innerJoin(String table, String alias, ICondition condition)  throws Exception;
  IRelation innerJoin(IQuery query, String alias, ICondition condition)  throws Exception;

  IRelation fullJoinRaw(String joinStr);
  IRelation fullJoin(String table, String alias, String on)  throws Exception;
  IRelation fullJoin(IQuery query, String alias, String on)  throws Exception;
  IRelation fullJoin(String table, String alias, ICondition condition)  throws Exception;
  IRelation fullJoin(IQuery query, String alias, ICondition condition)  throws Exception;

  IRelation crossJoinRaw(String joinStr);
  IRelation crossJoin(String table, String alias)  throws Exception;
  IRelation crossJoin(IQuery query, String alias)  throws Exception;

  IRelation joinUsing(String table, String alias, String... columns)  throws Exception;
  IRelation joinUsing(IQuery query, String alias, String... columns)  throws Exception;
  IRelation leftJoinUsing(String table, String alias, String... columns)  throws Exception;
  IRelation leftJoinUsing(IQuery query, String alias, String... columns)  throws Exception;
  IRelation rightJoinUsing(String table, String alias, String... columns)  throws Exception;
  IRelation rightJoinUsing(IQuery query, String alias, String... columns)  throws Exception;
  IRelation fullJoinUsing(String table, String alias, String... columns)  throws Exception;
  IRelation fullJoinUsing(IQuery query, String alias, String... columns)  throws Exception;


  IRelation filterJoinRaw(boolean filter, String joinStr);
  IRelation filterJoin(boolean filter, String table, String alias, String on)  throws Exception;
  IRelation filterJoin(boolean filter, IQuery query, String alias, String on)  throws Exception;
  IRelation filterJoin(boolean filter, String table, String alias, ICondition condition)  throws Exception;
  IRelation filterJoin(boolean filter, IQuery query, String alias, ICondition condition)  throws Exception;

  IRelation filterLeftJoinRaw(boolean filter, String joinStr);
  IRelation filterLeftJoin(boolean filter, String table, String alias, String on) throws Exception;
  IRelation filterLeftJoin(boolean filter, IQuery query, String alias, String on) throws Exception;
  IRelation filterLeftJoin(boolean filter, String table, String alias, ICondition condition) throws Exception;
  IRelation filterLeftJoin(boolean filter, IQuery query, String alias, ICondition condition) throws Exception;

  IRelation filterRightJoinRaw(boolean filter, String joinStr);
  IRelation filterRightJoin(boolean filter, String table, String alias, String on) throws Exception;
  IRelation filterRightJoin(boolean filter, IQuery query, String alias, String on) throws Exception;
  IRelation filterRightJoin(boolean filter, String table, String alias, ICondition condition) throws Exception;
  IRelation filterRightJoin(boolean filter, IQuery query, String alias, ICondition condition) throws Exception;

  IRelation filterInnerJoinRaw(boolean filter, String joinStr);
  IRelation filterInnerJoin(boolean filter, String table, String alias, String on) throws Exception;
  IRelation filterInnerJoin(boolean filter, IQuery query, String alias, String on) throws Exception;
  IRelation filterInnerJoin(boolean filter, String table, String alias, ICondition condition) throws Exception;
  IRelation filterInnerJoin(boolean filter, IQuery query, String alias, ICondition condition) throws Exception;

  IRelation filterFullJoinRaw(boolean filter, String joinStr);
  IRelation filterFullJoin(boolean filter, String table, String alias, String on)  throws Exception;
  IRelation filterFullJoin(boolean filter, IQuery query, String alias, String on)  throws Exception;
  IRelation filterFullJoin(boolean filter, String table, String alias, ICondition condition)  throws Exception;
  IRelation filterFullJoin(boolean filter, IQuery query, String alias, ICondition condition)  throws Exception;

  IRelation filterCrossJoinRaw(boolean filter, String joinStr);
  IRelation filterCrossJoin(boolean filter, String table, String alias) throws Exception;
  IRelation filterCrossJoin(boolean filter, IQuery query, String alias) throws Exception;

  IRelation filterJoinUsing(boolean filter, String table, String alias, String... columns) throws Exception;
  IRelation filterJoinUsing(boolean filter, IQuery query, String alias, String... columns) throws Exception;
  IRelation filterLeftJoinUsing(boolean filter, String table, String alias, String... columns) throws Exception;
  IRelation filterLeftJoinUsing(boolean filter, IQuery query, String alias, String... columns) throws Exception;
  IRelation filterRightJoinUsing(boolean filter, String table, String alias, String... columns) throws Exception;
  IRelation filterRightJoinUsing(boolean filter, IQuery query, String alias, String... columns) throws Exception;
  IRelation filterFullJoinUsing(boolean filter, String table, String alias, String... columns) throws Exception;
  IRelation filterFullJoinUsing(boolean filter, IQuery query, String alias, String... columns) throws Exception;

  List<String> getJoinList();
  void setWrapper(IWrapper wrapper);
  String toStatement();
}
