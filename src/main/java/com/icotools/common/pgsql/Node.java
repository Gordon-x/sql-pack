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
