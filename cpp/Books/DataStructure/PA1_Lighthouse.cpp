//
// Created by rick on 2024/4/14.
//

#include <cstring>
#include <cinttypes>
#include <cstdio>
#include <cstdlib>
#include <utility>

using namespace std;
constexpr int SZ = 1 << 20;
char inbuf[SZ];
char outbuf[SZ];

using Pos = pair<int64_t, int64_t>;
constexpr int N = 4000000;
Pos lightHouses[N];
int n;
Pos *temp;
int64_t amount = 0;

void merge(int low, int mid, int high) {
    auto x = mid - low + 1;
    auto y = high - mid;

    if (lightHouses[mid].second <= lightHouses[mid + 1].second) {
        amount += x * y;
        return;
    }
    memcpy(temp + low, lightHouses + low, (high - low + 1) * sizeof(Pos));

    auto i = low;
    auto j = mid + 1;
    for (auto k = low; k <= high; k++) {
        if (i == mid + 1) {
            lightHouses[k] = temp[j++];
        } else if (j == high + 1) {
            lightHouses[k] = temp[i++];
        } else if (temp[i].second > temp[j].second) {
            lightHouses[k] = temp[j++];
        } else {
            lightHouses[k] = temp[i++];
            amount += high - j + 1;
        }
    }
}

void sort(int low, int high) {
    if (low < high) {
        auto mid = low + (high - low) / 2;
        sort(low, mid);
        sort(mid + 1, high);
        merge(low, mid, high);
    }
}

void go() {
    temp = new Pos[n];
    qsort(lightHouses, n, sizeof(Pos), [](const void *x, const void *y) {
        auto a = (Pos *) x;
        auto b = (Pos *) y;
        return (int) (a->first - b->first);
    });

    sort(0, n - 1);
    printf("%" PRId64 "\n",amount);
    delete[] temp;
}

void test() {
    lightHouses[0] = {2, 2};
    lightHouses[1] = {4, 3};
    lightHouses[2] = {5, 1};
    n = 3;
    go();
}

void judge() {
    scanf("%d", &n);
    for (auto i = 0; i < n; i++) {
        int x, y;
        scanf("%d %d\n", &x, &y);
        lightHouses[i].first = x;
        lightHouses[i].second = y;
    }
    go();
}
#include <cstdio>
#include <cstring>
#include <cstdlib>
#include <utility>
#include <iostream>
int main() {
    setvbuf(stdin, inbuf, _IOFBF, SZ);
    setvbuf(stdout, outbuf, _IOFBF, SZ);
//    test();
    judge();
}