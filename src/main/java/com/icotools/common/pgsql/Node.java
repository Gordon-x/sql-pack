/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.icotools.common.pgsql;

/**
 * 条件树节点
 */
public class Node {
  public Where nodes;
  public String value;

  public Node(Where nodes) {
    this.nodes = nodes;
  }
  public Node(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public Where getNodes() {
    return nodes;
  }
}
