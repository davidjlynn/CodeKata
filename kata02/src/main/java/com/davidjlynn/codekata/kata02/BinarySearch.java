package com.davidjlynn.codekata.kata02;

public class BinarySearch {

    /**
     * Simple solution using recursion.
     *
     * @param searchValue int value to search for, not null.
     * @param searchList  List of int to search, not null, can be empty.
     * @return Position of search value, as int, -1 if not found.
     */
    public static int chop(int searchValue, int[] searchList) {
        if (searchList.length == 0) {
            return -1;
        }
        int firstIndex = indexer(0, searchList.length - 1);
        return evaluateGroup(searchValue, searchList, firstIndex, 0, searchList.length - 1); // Returning failure value.
    }

    /**
     * The comparator, compares values and works out if the item is not in the list.
     * <p>
     * Uses recursion to get through list.
     *
     * @param searchValue  the original search value
     * @param searchList   the full search list
     * @param currentIndex the current index that is being checked
     * @param lowerBound   the lower bound which has yet to be checked
     * @param upperBound   the upper bound which has yet to be checked
     * @return -1 for not found, or the index.
     */
    private static int evaluateGroup(int searchValue, int[] searchList, int currentIndex, int lowerBound, int upperBound) {
        if (searchValue == searchList[currentIndex]) {
            return currentIndex;
        } else if (searchValue < searchList[currentIndex]) {
            if (currentIndex == lowerBound) {
                return -1;
            } else {
                return evaluateGroup(searchValue, searchList, indexer(lowerBound, currentIndex - 1), lowerBound, currentIndex - 1);
            }
        } else { // searchValue > searchList[currentIndex]
            if (currentIndex == upperBound) {
                return -1;
            } else {
                return evaluateGroup(searchValue, searchList, indexer(currentIndex + 1, upperBound), currentIndex + 1, upperBound);
            }
        }
    }

    /**
     * A small function to find the target given the bounds.
     *
     * @param lowerBound the low level of our search
     * @param upperBound the high level of our search
     * @return the target next comparison.
     */
    private static int indexer(int lowerBound, int upperBound) {
        int items = upperBound - lowerBound + 1;
        if (items % 2 == 0) {
            return lowerBound + (items / 2) - 1;
        } else {
            return lowerBound + ((items + 1) / 2) - 1;
        }
    }
}
