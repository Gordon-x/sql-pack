/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.icotools.common.util;

import com.icotools.common.pgsql.SpecialChar;

public class EscapeUtil {
  /**
   * 处理数据中的引号
   * @param values 待处理字符串
   * @return 转义字符串
   */
  public static String escapeQuoteForPgSQL(String values) {
    return values.replace(SpecialChar.SINGLE_QUOTE.getValue(), "''");
  }
}
