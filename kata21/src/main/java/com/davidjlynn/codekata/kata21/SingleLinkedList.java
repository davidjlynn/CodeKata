package com.davidjlynn.codekata.kata21;

import lombok.NonNull;

public class SingleLinkedList implements KataList {

  private SingleLinkedNode firstNode;

  @Override
  public KataNode find(String value) {
    if (firstNode != null) {
      SingleLinkedNode currentNode = firstNode;
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

    SingleLinkedNode currentNode = firstNode;
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
    SingleLinkedNode node = new SingleLinkedNode(value);
    if (firstNode == null) {
      firstNode = node;
    } else {
      SingleLinkedNode lastNode = getLastNode();
      lastNode.setNextNode(node);
    }
  }

  @Override
  public void delete(@NonNull KataNode node) {
    if (firstNode == null) {
      return;
    }

    if (firstNode == node) {
      firstNode = firstNode.getNextNode();
      return;
    }

    SingleLinkedNode previousNode = firstNode;
    SingleLinkedNode currentNode = firstNode.getNextNode();
    while (currentNode != null) {
      if (currentNode == node) {
        previousNode.setNextNode(currentNode.getNextNode());
        return;
      }

      previousNode = currentNode;
      currentNode = currentNode.getNextNode();
    }
  }

  public SingleLinkedNode getLastNode() {
    if (firstNode == null) {
      return null;
    } else {
      SingleLinkedNode currentNode = firstNode;
      while (currentNode != null) {
        SingleLinkedNode nextNode = currentNode.getNextNode();
        if (nextNode == null){
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
      SingleLinkedNode currentNode = firstNode;
      while (currentNode != null) {
        count++;
        currentNode = currentNode.getNextNode();
      }
      return count;
    }
  }
}
