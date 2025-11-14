package utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsSortedArray extends TypeSafeMatcher<Integer[]> {
    
    @Override
    protected boolean matchesSafely(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void describeTo(Description description) {
        description.appendText("a sorted array");
    }
    
    @Override
    protected void describeMismatchSafely(Integer[] array, Description mismatchDescription) {
        // Find the first unsorted element
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                mismatchDescription.appendText("element at index ")
                        .appendValue(i)
                        .appendText(" was ")
                        .appendValue(array[i])
                        .appendText(", which is greater than element at index ")
                        .appendValue(i + 1)
                        .appendText(" which was ")
                        .appendValue(array[i + 1]);
                return;
            }
        }
    }
    
    public static IsSortedArray sortedArray() {
        return new IsSortedArray();
    }
}