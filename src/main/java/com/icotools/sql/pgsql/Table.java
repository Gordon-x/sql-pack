/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.icotools.sql.pgsql;

import com.icotools.sql.IQuery;
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
