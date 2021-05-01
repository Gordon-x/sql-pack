package cn.voiceads.sql.sql.pgsql;

import cn.voiceads.sql.sql.IHaving;
import cn.voiceads.sql.common.pgsql.Keyword;
import cn.voiceads.sql.common.pgsql.Operator;
import cn.voiceads.sql.common.pgsql.Where;
import cn.voiceads.sql.common.util.EscapeUtil;
import cn.voiceads.sql.sql.ICondition;
import cn.voiceads.sql.sql.IQuery;
import cn.voiceads.sql.sql.IRelation;

import java.util.*;

import static cn.voiceads.sql.common.pgsql.Operator.*;

/**
 * sql主体
 */
public class Query implements IQuery {
  protected Wrapper wrapper;

  /**
   * sql对象存储
   */
  public static class Wrapper implements IQuery.IWrapper {
    public Query query;
    public Collection<String> fieldCollection;
    /**
     * 条件树对象
     */
    public Where where;
    public Where having;


    public List<String> join = new ArrayList<>();
    public final ArrayList<String> group = new ArrayList<>();
    public final ArrayList<String> order = new ArrayList<>();
    public Integer offset;
    public Integer limit;

    /**
     * 驱动表｜关联关系
     */
    public Table table;
    /**
     * 条件对象承载类
     */
    public Condition condition;
    public Having havingCondition;

    private Stack<String> whereElem;
    private Stack<Keyword> logic;

    @Override
    public String whereToSql() {
      return condition(where);
    }

    @Override
    public String havingToSql() {
      return condition(having);
    }

    @Override
    public String relationToSql() {
      return String.join(" ", join);
    }

    /**
     * where & having 条件树解析
     * @param w 条件对象（having 或 where）
     * @return 条件字符串
     */
    private String condition(Where w) {
      logic = new Stack<>();
      whereElem = new Stack<>();
      Stack<Where> left = new Stack<>();
      while (w != null) {
        if (w.logic != null) {
          logic.push(w.logic);
        }
        if (w.right.value != null) {
          whereElem.push(w.right.value);
        }

        if (w.right.nodes != null) {
          //暂存左节点
          left.push(w.left);
          w = w.right.nodes;
          //当出现优先级高的节点，增加空运算符标记
          logic.push(Keyword.NULL);
        } else if (w.left != null) {
          w = w.left;
        } else {
          construct();
          if (!left.isEmpty()) {
            w = left.pop();
          } else {
            w = null;
          }

          if (w == null) {
            construct();
          }
        }
      }
      return whereElem.pop();
    }

    private void construct() {
      while (!logic.isEmpty()) {
        Keyword logicSymbol = logic.pop();
        if (Keyword.NULL.equals(logicSymbol)) {
          break;
        }
        if (!logic.isEmpty() && Keyword.NULL.equals(logic.peek())) {
          whereElem.push(String.format("(%s %s %s)", whereElem.pop(), logicSymbol.getValue(), whereElem.pop()));
        } else {
          whereElem.push(String.format("%s %s %s", whereElem.pop(), logicSymbol.getValue(), whereElem.pop()));
        }
      }
    }
  }

  protected Query() {}

  public static Query find() {
    Query query = new Query();
    query.wrapper = new Wrapper();
    query.wrapper.query = query;
    return query;
  }

  @Override
  public IQuery selectRaw(String fields) {
    wrapper.fieldCollection = Arrays.asList(fields.split(","));
    return this;
  }

  @Override
  public Query select(String... fields) {
    wrapper.fieldCollection = Arrays.asList(fields);
    return this;
  }

  @Override
  public Query select(Collection<String> fields) {
    wrapper.fieldCollection = fields;
    return this;
  }

  @Override
  public IRelation from(String tableName, String alias) {
    wrapper.table = new Table(tableName, alias);
    wrapper.table.setWrapper(wrapper);
    return wrapper.table;
  }

  @Override
  public IRelation from(IQuery query, String alias) {
    wrapper.table = new Table(query, alias, false);
    wrapper.table.setWrapper(wrapper);
    return wrapper.table;
  }

  @Override
  public IRelation with(IQuery query, String alias) {
    wrapper.table = new Table(query, alias, true);
    wrapper.table.setWrapper(wrapper);
    return wrapper.table;
  }

  @Override
  public ICondition whereRaw(String where) {
    if (wrapper.condition == null) {
      wrapper.condition = new Condition();
      wrapper.condition.setWrapper(wrapper);
    }
    return wrapper.condition.andWhereRaw(where);
  }

  @Override
  public ICondition where(String field, Operator opt, Object value) {
    return whereRaw(whereFormat(field, opt, value));
  }

  @Override
  public ICondition whereField(String field1, Operator opt, String field2) {
    return whereRaw(String.format("%s %s %s", field1, opt.getValue(), field2));
  }

  @Override
  public ICondition whereIn(String field, Collection<?> value) {
    return whereIn(field, value, false);
  }

  @Override
  public ICondition whereIn(String field, IQuery value) {
    return whereRaw(String.format("%s %s (%s)", field, IN.getValue(), value.toSql()));
  }

  @Override
  public ICondition whereNotIn(String field, Collection<?> value) {
    return whereIn(field, value, true);
  }

  @Override
  public ICondition whereNotIn(String field, IQuery value) {
    return whereRaw(String.format("%s not %s (%s)", field, IN.getValue(), value.toSql()));
  }

  @Override
  public ICondition whereBetween(String field, Object value1, Object value2) {
    return whereBetween(field, value1, value2, false);
  }

  @Override
  public ICondition whereNotBetween(String field, Object value1, Object value2) {
    return whereBetween(field, value1, value2, true);
  }

  @Override
  public ICondition whereNull(String field) {
    return whereRaw(whereFormat(field, ISNULL));
  }

  @Override
  public ICondition whereNotNull(String field) {
    return whereRaw(whereFormat(field, NOTNULL));
  }

  @Override
  public ICondition whereExists(IQuery query) {
    if (wrapper.condition == null) {
      wrapper.condition = new Condition();
      wrapper.condition.setWrapper(wrapper);
    }
    return wrapper.condition.andExists(query);
  }

  @Override
  public ICondition whereNotExists(IQuery query) {
    if (wrapper.condition == null) {
      wrapper.condition = new Condition();
      wrapper.condition.setWrapper(wrapper);
    }
    return wrapper.condition.andNotExists(query);
  }

  @Override
  public ICondition whereLike(String field, Object value) {
    return where(field, Operator.LIKE, String.format("%%%s%%", value));
  }

  @Override
  public ICondition whereLeftLike(String field, Object value) {
    return where(field, Operator.LIKE, String.format("%%%s", value));
  }

  @Override
  public ICondition whereRightLike(String field, Object value) {
    return where(field, Operator.LIKE, String.format("%s%%", value));
  }

  @Override
  public ICondition whereNotLike(String field, Object value) {
    return where(field, Operator.NOT_LIKE, String.format("%%%s%%", value));
  }

  @Override
  public ICondition whereNotLeftLike(String field, Object value) {
    return where(field, Operator.NOT_LIKE, String.format("%%%s", value));
  }

  @Override
  public ICondition whereNotRightLike(String field, Object value) {
    return where(field, Operator.NOT_LIKE, String.format("%s%%", value));
  }

  @Override
  public IHaving groupBy(String... columns) {
    wrapper.group.addAll(Arrays.asList(columns));
    return having();
  }

  @Override
  public IHaving groupBy(String column) {
    wrapper.group.add(column);
    return having();
  }

  private IHaving having() {
    if (wrapper.havingCondition == null) {
      wrapper.havingCondition = new Having();
      wrapper.havingCondition.setWrapper(wrapper);
    }
    return wrapper.havingCondition;
  }

  @Override
  public IQuery orderBy(String... columns) {
    wrapper.order.addAll(Arrays.asList(columns));
    return this;
  }

  @Override
  public IQuery orderBy(String columns, Keyword order) {
    wrapper.order.add(String.format("%s %s", columns, order));
    return this;
  }

  @Override
  public IQuery orderBy(int columnIndex, Keyword order) {
    wrapper.order.add(String.format("%d %s", columnIndex, order));
    return this;
  }

  @Override
  public IQuery offset(int offset) {
    wrapper.offset = offset;
    return this;
  }

  @Override
  public IQuery limit(int limit) {
    wrapper.limit = limit;
    return this;
  }

  @Override
  public ICondition where(ICondition condition) {
    return whereRaw(String.format("(%s)", condition.toStatement()));
  }

  @Override
  public String whereFormat(String field, Operator opt, Object... value) {
    switch (opt) {
      case NOT_IN:
      case IN:
        StringJoiner joiner = new StringJoiner(",");
        Arrays.stream(value).forEach(v -> {
          if (v instanceof CharSequence) {
            String escapeStr = EscapeUtil.escapeQuoteForPgSQL(String.valueOf(v));
            joiner.add(String.format("'%s'", escapeStr)) ;
          } else {
            joiner.add(String.valueOf(v));
          }
        });
        return String.format("%s %s (%s)", field, opt.getValue(), joiner);
      case NOT_BETWEEN:
      case BETWEEN:
        Object value1 = value[0];
        Object value2 = value[1];
        if (value1 instanceof CharSequence) {
          value1 = String.format("'%s'", EscapeUtil.escapeQuoteForPgSQL(String.valueOf(value1)));
        }
        if (value2 instanceof CharSequence) {
          value2 = String.format("'%s'", EscapeUtil.escapeQuoteForPgSQL(String.valueOf(value2)));
        }
        return String.format("%s %s %s and %s", field, opt.getValue(), value1, value2);
      case NOT_LIKE:
      case LIKE:
        return String.format("%s %s '%s'", field, opt.getValue(), EscapeUtil.escapeQuoteForPgSQL(String.valueOf(value[0])));
      case ISNULL:
      case NOTNULL:
        return String.format("%s %s", field, opt.getValue());
      default:
        if (value[0] instanceof CharSequence) {
          return String.format("%s %s '%s'", field, opt.getValue(), EscapeUtil.escapeQuoteForPgSQL(String.valueOf(value[0])));
        }
        return String.format("%s %s %s", field, opt.getValue(), EscapeUtil.escapeQuoteForPgSQL(String.valueOf(value[0])));
    }
  }

  protected ICondition whereBetween(String field, Object value1, Object value2, boolean not) {
    Operator between = not ? NOT_BETWEEN : BETWEEN;
    return whereRaw(whereFormat(field, between, value1, value2));
  }

  protected ICondition whereIn(String field, Object value, boolean not) {
    Operator n = not ? NOT_IN : IN;

    if (value instanceof Collection) {
      return whereRaw(whereFormat(field, n, ((Collection<?>) value).toArray()));
    } else if (value.getClass().isArray() || value instanceof CharSequence) {
      return whereRaw(whereFormat(field, n, value));
    }
    return wrapper.condition;
  }

  @Override
  public String toSql() {
    StringJoiner joiner = new StringJoiner(" ");

    if (wrapper.table.isWith()) {
      joiner.add(String.format("%s %s as (%s)", Keyword.WITH.getValue(), wrapper.table.getAlias(), wrapper.table.getDataSet().toSql()));
    }
    joiner.add(Keyword.SELECT.getValue());

    StringJoiner fieldJoiner = new StringJoiner(",");
    if (wrapper.fieldCollection != null) {
      for (String field : wrapper.fieldCollection) {
        fieldJoiner.add(field);
      }
    } else {
      fieldJoiner.add("*");
    }
    joiner.add(fieldJoiner.toString());
    joiner.add(Keyword.FROM.getValue());

    if (wrapper.table.isWith()) {
      joiner.add(wrapper.table.getAlias());
    } else {
      if (wrapper.table.getName() != null) {
        joiner.add(String.format("%s as %s", wrapper.table.getName(), wrapper.table.getAlias()));
      } else {
        joiner.add(String.format("(%s) as %s", wrapper.table.getDataSet().toSql(), wrapper.table.getAlias()));
      }
    }

    String relation = wrapper.table.toStatement();
    if (relation.length() > 0) {
      joiner.add(relation);
    }

    if (wrapper.condition != null) {
      String where = wrapper.condition.toStatement();
      if (where.length() > 0) {
        joiner.add(String.format("%s %s", Keyword.WHERE.getValue(), where));
      }
    }
    if (wrapper.group.size() > 0) {
      joiner.add(String.format("%s %s", Keyword.GROUP_BY.getValue(), String.join(",", wrapper.group)));
      if (wrapper.having != null) {
        String having = wrapper.havingCondition.toStatement();
        if (having.length() > 0) {
          joiner.add(String.format("%s %s", Keyword.HAVING, having));
        }
      }
    }

    if (wrapper.order.size() > 0) {
      joiner.add(String.format("%s %s", Keyword.ORDER_BY.getValue(), String.join(",", wrapper.order)));
    }

    if (wrapper.offset != null) {
      joiner.add(String.format("%s %d", Keyword.OFFSET.getValue(), wrapper.offset));
    }

    if (wrapper.limit != null) {
      joiner.add(String.format("%s %d", Keyword.LIMIT.getValue(), wrapper.limit));
    }

    return joiner.toString();
  }

  @Override
  public String toString() {
    return toSql();
  }
}