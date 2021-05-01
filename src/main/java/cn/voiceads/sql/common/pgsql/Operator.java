package cn.voiceads.sql.common.pgsql;

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
