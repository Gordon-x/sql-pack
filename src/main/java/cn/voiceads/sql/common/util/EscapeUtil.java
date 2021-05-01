package cn.voiceads.sql.common.util;

import cn.voiceads.sql.common.pgsql.SpecialChar;

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
