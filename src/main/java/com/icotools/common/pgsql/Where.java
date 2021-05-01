package com.icotools.common.pgsql;

/**
 * 条件树对象（where & having)
 */
public class Where {
  public Where left;
  public Keyword logic;
  public Node right;

  public Where(Where left, Keyword logic, Node right) {
    this.left = left;
    this.logic = logic;
    this.right = right;
  }
}
