package com.davidjlynn.codekata.kata21;

public class DoubleLinkedList implements KataList {

  private DoubleLinkedNode firstNode;

  @Override
  public KataNode find(String value) {
    if (firstNode != null) {
      DoubleLinkedNode currentNode = firstNode;
      while (currentNode != null) {
        if (currentNode.getValue().equals(value)) {
          return currentNode;
        }
        currentNode = currentNode.getNextNode();
      }
    }
    return null;
  }

  @Override
  public String[] values() {
    int count = size();
    String[] valuesArray = new String[count];

    DoubleLinkedNode currentNode = firstNode;
    int index = 0;
    while (currentNode != null) {
      valuesArray[index] = currentNode.getValue();

      index++;
      currentNode = currentNode.getNextNode();
    }

    return valuesArray;
  }

  @Override
  public void add(String value) {
    DoubleLinkedNode node = new DoubleLinkedNode(value);
    if (firstNode == null) {
      firstNode = node;
    } else {
      DoubleLinkedNode lastNode = getLastNode();
      lastNode.setNextNode(node);
      node.setPreviousNode(lastNode);
    }
  }

  @Override
  public void delete(KataNode node) {
    if (firstNode == null) {
      return;
    }

    if (firstNode == node) {
      firstNode = firstNode.getNextNode();
      if (firstNode != null) {
        firstNode.setPreviousNode(null);
      }
      return;
    }

    DoubleLinkedNode previousNode = firstNode;
    DoubleLinkedNode currentNode = firstNode.getNextNode();
    while (currentNode != null) {
      if (currentNode == node) {
        DoubleLinkedNode nextNode = currentNode.getNextNode();
        if (nextNode != null) {
          nextNode.setPreviousNode(previousNode);
        }
        previousNode.setNextNode(nextNode);
        return;
      }

      previousNode = currentNode;
      currentNode = currentNode.getNextNode();
    }
  }

  public DoubleLinkedNode getLastNode() {
    if (firstNode == null) {
      return null;
    } else {
      DoubleLinkedNode currentNode = firstNode;
      while (currentNode != null) {
        DoubleLinkedNode nextNode = currentNode.getNextNode();
        if (nextNode == null) {
          return currentNode;
        }
        currentNode = currentNode.getNextNode();
      }
      throw new IllegalStateException("There should have been a last node");
    }
  }

  public int size() {
    if (firstNode == null) {
      return 0;
    } else {
      int count = 0;
      DoubleLinkedNode currentNode = firstNode;
      while (currentNode != null) {
        count++;
        currentNode = currentNode.getNextNode();
      }
      return count;
    }
  }
}
