/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.icotools.common.pgsql;

public enum SpecialChar {
  SINGLE_QUOTE("'"),
  DOUBLE_QUOTE("\""),
  REVERSE_QUOTE("`"),
  LEFT_BRACKET("("),
  RIGHT_BRACKET(")"),
  SEMI(";"),
  HASH("#"),
  COMMENT("--");

  private final String value;
  SpecialChar(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
