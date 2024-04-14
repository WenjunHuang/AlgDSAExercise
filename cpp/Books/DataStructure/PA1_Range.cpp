#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <utility>
#include <iostream>

using namespace std;

int *searchLeft(int *nums, size_t length, int target) {
    auto left = nums;
    auto right = nums + length - 1;

    while (left <= right) {
        auto mid = left + (right - left) / 2;
        if (*mid == target) {
            right = mid - 1;
        } else if (*mid < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return left;
}

int *searchRight(int *nums, size_t length, int target) {
    auto left = nums;
    auto right = nums + length - 1;

    while (left <= right) {
        auto mid = left + (right - left) / 2;
        if (*mid == target) {
            left = mid + 1;
        } else if (*mid < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return right;
}

constexpr int SZ = 1 << 20;
char inbuf[SZ];
char outbuf[SZ];
int nums[500000 + 2];

void normal() {
    int n, m;

    // 读取n和m
    scanf("%d %d", &n, &m);

    // 读取n个整数
    for (auto i = 0; i < n; i++) {
        int temp;
        scanf("%d", &temp);
        nums[i] = temp;
    }

    // 排序
    qsort(nums, n, sizeof(int), [](const void *x, const void *y) {
        return *(int *) x - *(int *) y;
    });

    // 进行m次判断
    for (auto k = 0; k < m; k++) {
        int a, b;
        scanf("%d %d", &a, &b);
        if (a > b || a > nums[n - 1] || b < nums[0]) {
            printf("%d\n", 0);
            continue;
        }

        const int *left;
        if (a < nums[0]) {
            left = nums;
        } else {
            left = searchLeft(nums, n, a);
        }
        const int *right;
        if (b > nums[n - 1]) {
            right = nums + n - 1;
        } else {
            right = searchRight(nums, n, b);
        }
        auto r = distance(left, right) + 1;
        if (r < 0)
            r = 0;
        printf("%d\n", r);
    }
}

int main(int argc, char *argv[]) {
    setvbuf(stdin, inbuf, _IOFBF, SZ);
    setvbuf(stdout, outbuf, _IOFBF, SZ);
    normal();
}