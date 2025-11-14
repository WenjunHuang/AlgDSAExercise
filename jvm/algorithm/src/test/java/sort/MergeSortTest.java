package sort;

import net.jqwik.api.Example;

import static org.assertj.core.api.Assertions.assertThat;

public class MergeSortTest {
    
    @Example
    void testSimpleSort() {
        Integer[] arr = {5, 2, 8, 1, 9};
        MergeSort.mergeSort(arr);
        assertThat(arr).containsExactly(1, 2, 5, 8, 9);
    }
}