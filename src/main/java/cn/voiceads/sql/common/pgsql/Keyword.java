package cn.voiceads.sql.common.pgsql;

public enum Keyword {
  SELECT("select"),
  WITH("with"),
  AS("as"),
  FROM("from"),
  WHERE("where"),
  GROUP_BY("group by"),
  ORDER_BY("order by"),
  OFFSET("offset"),
  LIMIT("limit"),
  LEFT_JOIN("left join"),
  RIGHT_JOIN("right join"),
  JOIN("join"),
  CROSS_JOIN("cross join"),
  FULL_JOIN("full join"),
  INNER_JOIN("inner join"),
  ON("on"),
  IN("in"),
  EXISTS("exists"),
  NOT_EXISTS("not exists"),
  NULL("null"),
  ISNULL("isnull"),
  NOTNULL("notnull"),
  NOT("not"),
  LIKE("like"),
  BETWEEN("between"),
  DISTINCT("distinct"),
  HAVING("having"),
  ASC("asc"),
  DESC("desc"),
  NULLS_FIRST("nulls first"),
  NULLS_LAST("nulls last"),
  AND("and"),
  OR("or");

  private final String value;
  Keyword(String value) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return getValue();
  }
}
