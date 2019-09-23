package com.davidjlynn.codekata.kata02;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;

public class BinarySearchTree {
  public static int chop(int searchValue, int[] searchList) {
    if (searchList.length == 0) {
      return -1;
    }
    TreeStructure tree = buildTree(searchList, 0);
    return find(searchValue, tree);
  }

  private static int find(int searchValue, TreeStructure tree) {
    if (searchValue == tree.currentValue) {
      return tree.currentIndex;
    } else if (searchValue < tree.currentValue && tree.leftTree != null) {
      return find(searchValue, tree.leftTree);
    } else if (searchValue > tree.currentValue && tree.rightTree != null) {
      return find(searchValue, tree.rightTree);
    } else {
      return -1;
    }
  }

  private static TreeStructure buildTree(int[] searchList, int currentOffset) {
    int firstIndex = indexer(searchList.length);
    TreeStructure treeStructure = new TreeStructure();
    treeStructure.currentValue = searchList[firstIndex];
    treeStructure.currentIndex = currentOffset + firstIndex;
    if (firstIndex != 0) {
      treeStructure.leftTree = buildTree(Arrays.copyOfRange(searchList, 0, firstIndex), 0);
    }
    if (firstIndex != searchList.length - 1) {
      treeStructure.rightTree =
          buildTree(
              Arrays.copyOfRange(searchList, firstIndex + 1, searchList.length),
              currentOffset + firstIndex + 1);
    }
    return treeStructure;
  }

  /**
   * A small function to find the target given the bounds.

   * @return the target next comparison.
   */
  private static int indexer(int items) {
    if (items % 2 == 0) {
      return (items / 2) - 1;
    } else {
      return ((items + 1) / 2) - 1;
    }
  }

  @Getter
  @Setter
  private static class TreeStructure {
    private TreeStructure leftTree;
    private int currentValue;
    private int currentIndex;
    private TreeStructure rightTree;
  }
}
