package cn.voiceads.sql.sql.pgsql;

import cn.voiceads.sql.sql.IQuery;
import lombok.Getter;

@Getter
public class Table extends Relation {
  private String name;
  private final String alias;
  private IQuery dataSet;
  private boolean with = false;

  public Table(String name, String alias) {
    this.name = name;
    this.alias = alias;
  }
  public Table(IQuery dataSet, String alias, boolean with) {
    this.dataSet = dataSet;
    this.alias = alias;
    this.with = with;
  }
}
