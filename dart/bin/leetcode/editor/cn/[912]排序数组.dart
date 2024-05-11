//leetcode submit region begin(Prohibit modification and deletion)
class MergeSort {
  final List<int> nums;
  final List<int> temp;

  MergeSort(this.nums) : temp = List<int>.filled(nums.length, 0);

  List<int> sort() {
    sortImpl(0, nums.length - 1);
    return nums;
  }

  void sortImpl(int low, int high) {
    if (low >= high) return;

    int mid = (low + (high - low) / 2).toInt();
    sortImpl(low, mid);
    sortImpl(mid + 1, high);
    merge(low, mid, high);
  }

  void merge(int low, int mid, int high) {
    for (int i = low; i <= high; i++) {
      temp[i] = nums[i];
    }

    int i = low;
    int j = mid + 1;
    for (int p = low; p <= high; p++) {
      if (i == mid + 1) {
        nums[p] = temp[j++];
      } else if (j == high + 1) {
        nums[p] = temp[i++];
      } else if (temp[i] > temp[j]) {
        nums[p] = temp[j++];
      } else {
        nums[p] = temp[i++];
      }
    }
  }
}

class Solution {
  List<int> sortArray(List<int> nums) {
    return MergeSort(nums).sort();
  }
}
//leetcode submit region end(Prohibit modification and deletion)
