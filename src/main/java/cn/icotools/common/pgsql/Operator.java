/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.icotools.common.pgsql;

public enum Operator {
  EQ("="),
  NE("!="),
  NE2("<>"),
  GT(">"),
  LT("<"),
  LTE("<="),
  GTE(">="),
  IN("in"),
  BETWEEN("between"),
  LIKE("like"),
  ISNULL("isnull"),
  NOTNULL("notnull"),
  NOT_LIKE("not like"),
  NOT_BETWEEN("not between"),
  EXISTS("exists"),
  NOT_EXISTS("not exists"),
  NOT_IN("not in");

  private final String value;
  Operator(String value) {
    this.value = value;
  }
  public String getValue() {
    return value;
  }

  public String toString() {
    return getValue();
  }
}
