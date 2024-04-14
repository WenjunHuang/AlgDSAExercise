//
// Created by rick on 2024/4/14.
//

#include <string>
#include <cstring>
#include <cinttypes>
#include <iostream>

using namespace std;
using Pos = pair<int64_t, int64_t>;
constexpr int N = 4000000;
Pos lightHouses[N];
int n;
int64_t count = 0;

void merge(int p, int q, int r) {
    auto x = q - p + 1;
    auto y = r - q;

    if (lightHouses[q].second <= lightHouses[q + 1].second) {
        count += x * y;
        return;
    }
    auto b = new Pos[x];
    auto c = new Pos[y];

    memcpy(b, lightHouses + p, x * sizeof(Pos));
    memcpy(c, lightHouses + q + 1, y * sizeof(Pos));

    auto i = 0;
    auto j = 0;
    for (auto k = p; k <= r; k++) {
        if (i == x) {
            lightHouses[k] = c[j++];
        } else if (j == y) {
            lightHouses[k] = b[i++];
        } else if (b[i].second > c[j].second) {
            lightHouses[k] = c[j++];
        } else {
            lightHouses[k] = b[i++];
            count += y - j;
        }
    }

    delete[] c;
    delete[] b;
}

void mergeSort(int p, int r) {
    if (p < r) {
        auto mid = p + (r - p) / 2;
        mergeSort(p, mid);
        mergeSort(mid + 1, r);
        merge(p, mid, r);
    }
}

void go() {
    qsort(lightHouses, n, sizeof(Pos), [](const void *x, const void *y) {
        auto a = (Pos *) x;
        auto b = (Pos *) y;
        return (int) (a->first - b->first);
    });

    mergeSort(0, n - 1);
    cout << count << endl;
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

int main() {
//    test();
    judge();

}