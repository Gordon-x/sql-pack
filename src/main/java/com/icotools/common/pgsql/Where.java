/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

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
