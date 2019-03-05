package com.meng.algo.demo.algotest.algomodel.v1.map.adjacency;

/**
 * Created by jjp on 2017/12/26.
 */
public class Graph {

  class EdgeNode {
    //邻接点
    int adjvex;
    EdgeNode nextEdge;
  }

  class VexNode {

    String data;
    EdgeNode firstEdge;
    boolean isVisted;

    public boolean isVisted() {
      return isVisted;
    }

    public void setVisted(boolean isVisted) {
      this.isVisted = isVisted;
    }

  }

  VexNode[] vexsarray;
  int[] visited = new int[100];
  boolean[] isVisited = new boolean[100];

  //连接下一个节点
  public void linkLast(EdgeNode target, EdgeNode node) {
    while (target.nextEdge != null) {
      target = target.nextEdge;
    }
    target.nextEdge = node;
  }

  //得到与数据对应的位置
  public int getPosition(String data) {
    for (int i = 0; i < vexsarray.length; i++) {
      if (data == vexsarray[i].data) {
        return i;
      }
    }
    return -1;
  }


  public void buildGraph(Object[] vexs, String[][] edges) {
    int vLen = vexs.length;
    int eLen = edges.length;
    vexsarray = new VexNode[vLen];

    for (int i = 0; i < vLen; i++) {
      vexsarray[i] = new VexNode();
      vexsarray[i].data = String.valueOf(vexs[i]);
      //邻接表的第一个邻接边结点
      vexsarray[i].firstEdge = null;
    }

    for (int i = 0; i < eLen; i++) {

      String a = edges[i][0];
      String b = edges[i][1];

      int start = getPosition(a);
      int end = getPosition(b);

      EdgeNode edgeNode = new EdgeNode();
      edgeNode.adjvex = end;

      if (vexsarray[start].firstEdge == null) {
        vexsarray[start].firstEdge = edgeNode;
      } else {
        linkLast(vexsarray[start].firstEdge, edgeNode);
      }
    }
  }

  public void printGraph() {
    for (int i = 0; i < vexsarray.length; i++) {
      System.out.printf("%d(%s): ", i, vexsarray[i].data);
//      System.out.printf("%d--", vexsarray[i].data);
      EdgeNode node = vexsarray[i].firstEdge;
      while (node != null) {
        System.out.printf("%d(%s) ", node.adjvex, vexsarray[node.adjvex].data);
        //    System.out.printf("%d(%d)--", node.adjvex, vexsarray[node.adjvex].data);
        node = node.nextEdge;
      }
      System.out.println("\n");
    }
  }
}
