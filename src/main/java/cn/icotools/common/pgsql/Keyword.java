/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.icotools.common.pgsql;

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
