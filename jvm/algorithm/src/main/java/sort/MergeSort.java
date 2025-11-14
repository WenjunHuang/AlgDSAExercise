package sort;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import utils.AbstractTestDataGenerator;

import java.lang.reflect.Array;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.IsSortedArray.sortedArray;

public class MergeSort extends AbstractTestDataGenerator {

    @Property
    public void testRandomArrays(@ForAll("intArrayGen") int[] arr) {

        // 将 int[] 转换为 Integer[] 以匹配 mergeSort 的签名
        Integer[] integerArr = IntStream.of(arr).boxed().toArray(Integer[]::new);

        // 执行排序
        mergeSort(integerArr);

        // 验证数组已排序
        assertThat(integerArr, sortedArray());
    }

    @Property
    public void testLargeArrays(@ForAll("largeArrayGen") int[] arr) {

        // 将 int[] 转换为 Integer[] 以匹配 mergeSort 的签名
        Integer[] integerArr = IntStream.of(arr).boxed().toArray(Integer[]::new);

        // 执行排序
        mergeSort(integerArr);

        // 验证数组已排序
        assertThat(integerArr, sortedArray());
    }

    @Property
    public void testSortedArrays(@ForAll("sortedArrayGen") int[] arr) {
        Integer[] integerArr = IntStream.of(arr).boxed().toArray(Integer[]::new);
        mergeSort(integerArr);
        assertThat(integerArr, sortedArray());
    }

    @Property
    public void testDuplicateArrays(@ForAll("duplicateArrayGen") int[] arr) {
        Integer[] integerArr = IntStream.of(arr).boxed().toArray(Integer[]::new);
        mergeSort(integerArr);
        assertThat(integerArr, sortedArray());
    }


    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        mergeSortImpl(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSortImpl(T[] arr, int left, int right) {
        if (left >= right) return;
        var middle = left + (right - left) / 2;
        mergeSortImpl(arr, left, middle);
        mergeSortImpl(arr, middle + 1, right);
        merge(arr, left, right, middle);
    }

    public static <T extends Comparable<T>> void merge(T[] arr, int left, int right, int middle) {
        var temp = (T[]) Array.newInstance(arr[0].getClass(), right - left + 1);
        var p1 = left;
        var p2 = middle + 1;
        var index = 0;
        while (p1 <= middle && p2 <= right) {
            if (arr[p1].compareTo(arr[p2]) <= 0) {
                temp[index++] = arr[p1++];
            } else {
                temp[index++] = arr[p2++];
            }
        }

        if (p1 <= middle) {
            while (p1 <= middle)
                temp[index++] = arr[p1++];
        }
        if (p2 <= right) {
            while (p2 <= right) {
                temp[index++] = arr[p2++];
            }
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
