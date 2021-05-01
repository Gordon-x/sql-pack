package cn.voiceads.sql.common.pgsql;

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
