/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import cn.icotools.common.pgsql.Keyword;
import cn.icotools.common.pgsql.Operator;
import cn.icotools.sql.ICondition;
import cn.icotools.sql.IQuery;
import cn.icotools.sql.pgsql.Condition;
import cn.icotools.sql.pgsql.Query;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UnitTest {
  @Test
  public void complexSql() throws Exception {
    ICondition cond = Query.find().from("t_customer_order", "tco")
        .where("tco.tenant_id", Operator.EQ, 1);

    ICondition joinQuery = Query.find().from("t_customer", "t")
        .select("distinct t.cid", "coalesce(t.source, '') as alias_source")
        .where("t.tenant_id", Operator.EQ, 1)
        .andWhere("t.is_deleted", Operator.EQ, 0)
        .andWhere("t.create_source", Operator.EQ, "source@channel");

    IQuery group = Query.find().with(cond, "t")
        .filterJoin(true, joinQuery, "c", "t.cid=c.cid")
        .select("sum(t.amount_total) as count", "c.alias_source")
        .groupBy("c.alias_source");

    String q = Query.find().with(group, "t")
        .select("t.alias_source", "count::numeric")
        .orderBy("t.alias_source")
        .limit(1000)
        .toSql();

    assert "with t as (with t as (select * from t_customer_order as tco where tco.tenant_id = 1) select sum(t.amount_total) as count,c.alias_source from t join (select distinct t.cid,coalesce(t.source, '') as alias_source from t_customer as t where t.tenant_id = 1 and t.is_deleted = 0 and t.create_source = 'source@channel') as c on t.cid=c.cid group by c.alias_source) select t.alias_source,count::numeric from t order by t.alias_source limit 1000".equals(q);
  }

  @Test
  public void selectAll() {
    String sql = Query.find().from("test", "t").toSql();
    assert "select * from test as t".equals(sql);
  }

  @Test
  public void selectFieldAndNoWhere() {
    String sql = Query.find()
        .from("test", "t")
        .select("t.id", "t.name")
        .toSql();
    assert "select t.id,t.name from test as t".equals(sql);

    Collection<String> fields = new ArrayList<String>(){{
      add("t.id");
      add("t.name");
    }};
    String sql2 = Query.find()
        .from("test", "t")
        .select(fields)
        .toSql();
    assert "select t.id,t.name from test as t".equals(sql2);
  }

  @Test
  public void normalWhere() {
    ICondition cond = Query.find().from("t_customer_order", "tco")
        .where("tco.tenant_id", Operator.EQ, 1);

    assert "select * from t_customer_order as tco where tco.tenant_id = 1".equals(cond.toSql());

    ICondition cond1 = Query.find().from("t_customer_order", "tco")
        .where("tco.tenant_id", Operator.EQ, 1)
        .orWhere("tco.is_refund", Operator.EQ, "23'-");

    assert "select * from t_customer_order as tco where tco.tenant_id = 1 or tco.is_refund = '23''-'".equals(cond1.toSql());
  }

  @Test
  public void filterWhere() {
    ICondition cond = Query.find().from("t_customer_order", "tco")
        .where("tco.tenant_id", Operator.EQ, 1);

    assert "select * from t_customer_order as tco where tco.tenant_id = 1".equals(cond.toSql());
  }

  @Test
  public void bracketWhere() {
    ICondition cond = new Condition()
        .where("t.id", Operator.EQ, 1)
        .orWhere("t.name", Operator.NE, "gordon");

    String sql = Query.find()
        .from("test1", "t1")
        .whereLike("t.name", "abc")
        .andWhere(cond)
        .toSql();

    assert "select * from test1 as t1 where t.name like '%abc%' and (t.id = 1 or t.name != 'gordon')".equals(sql);
  }

  @Test
  public void whereIn() {
    List<Integer> values = new ArrayList<Integer>(){{
      add(1);
      add(2);
    }};

    IQuery cond = Query.find()
        .from("test1", "t1");

    String sql = Query.find()
        .from("test", "t")
        .whereIn("t.id", values)
        .andWhereIn("t.name", cond)
        .toSql();

    assert "select * from test as t where t.id in (1,2) and t.name in (select * from test1 as t1)".equals(sql);
  }

  @Test
  public void leftJoin() throws Exception {
    IQuery group = Query.find()
        .from("test", "t")
        .leftJoin("test1", "c", "t.id=c.id")
        .select("sum(t.amount_total) as count")
        .groupBy("c.alias_source");

    assert "select sum(t.amount_total) as count from test as t left join test1 as c on t.id=c.id group by c.alias_source".equals(group.toSql());
  }

  @Test
  public void subQuery() {
    IQuery table = Query.find()
        .from("test", "t")
        .select("t.id");

    String sql = Query.find()
        .from(table, "tt")
        .whereBetween("tt.id", 1, 10)
        .toSql();

    assert "select * from (select t.id from test as t) as tt where tt.id between 1 and 10".equals(sql);
  }

  @Test
  public void exist() {
    IQuery query = Query.find()
        .from("test", "t")
        .select("t.id");

    IQuery query1 = Query.find()
        .from("test1", "t1")
        .select("t1.id");

    String sql = Query.find()
        .from("test", "t")
        .whereRightLike("t.name", "苏子")
        .whereExists(query)
        .orNotExists(query1)
        .andWhere("t.id", Operator.GT, 2)
        .toSql();

    assert "select * from test as t where t.name like '苏子%' and exists (select t.id from test as t) or not exists (select t1.id from test1 as t1) and t.id > 2".equals(sql);
  }

  @Test
  public void multiJoin() throws Exception {
    String s = Query.find()
        .from("test", "t")
        .leftJoin("test1", "t1", "t1.id = t.id")
        .leftJoin("test2", "t2", "t2.id = t1.t2id")
        .toSql();

    assert "select * from test as t left join test1 as t1 on t1.id = t.id left join test2 as t2 on t2.id = t1.t2id".equals(s);
  }

  @Test
  public void withSubQuery() {
    IQuery withTable = Query.find()
        .from("test", "t")
        .where("t.id", Operator.IN, 1);

    String s = Query.find()
        .with(withTable, "tt")
        .orderBy("t.id", Keyword.DESC)
        .orderBy("t.name", Keyword.ASC)
        .limit(10)
        .toSql();

    assert "with tt as (select * from test as t where t.id in (1)) select * from tt order by t.id desc,t.name asc limit 10".equals(s);
  }
}
