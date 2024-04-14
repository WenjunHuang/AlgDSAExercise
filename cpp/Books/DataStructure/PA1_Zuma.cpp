//
// Created by rick on 2024/4/14.
//

#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>
#include <iostream>

using namespace std;
constexpr int SZ = 1 << 20;
char inbuf[SZ];
char outbuf[SZ];


class Zuma {
public:
    explicit Zuma(string &&balls, size_t limit = 3) : _balls(std::move(balls)), _limit(limit) {}

    void insetNewBallAt(int idx, char ball) {
        _balls.insert(idx, 1, ball);
        cancelAt(idx);
    }

    const string &getBalls() const {
        return _balls;
    }


private:
    string _balls;
    size_t _limit;

    void collapseRange(int from, int to) {
        _balls.erase(_balls.begin() + from, _balls.begin() + to + 1);
    }

    /**在idx位置消去相同颜色的球
     * 所用到的区间均为闭区间
     **/
    void cancelAt(int idx) {
        auto left = idx;
        auto right = idx;

        // 先向左右两边扩展，直到不能再消去为止
        auto range = tryExpandCancelRange(left, right);

        // 继续检查外延的球是否在range被消去后也能被消去
        auto expandedRange = range;
        while (expandedRange.first != -1) {
            range = expandedRange;
            expandedRange = tryExpandCancelRange(range.first - 1, range.second + 1);
        }

        if (range.first != -1) {
            collapseRange(range.first, range.second);
        }
    }

    pair<int, int> tryExpandCancelRange(int fromLeft, int fromRight) {
        char ball = _balls[fromLeft];
        int p1 = fromLeft;
        int p2 = fromRight;
        int count = 0;

        // 如果left与right相等，那么p2往后移动一位，以免重复计算count
        if (fromLeft == fromRight) p2 += 1;

        while (p1 >= 0 && _balls[p1] == ball) {
            fromLeft = p1;
            count++;
            p1--;
        }
        while (p2 < _balls.length() && _balls[p2] == ball) {
            fromRight = p2;
            count++;
            p2++;
        }

        if (count >= _limit)
            return {fromLeft, fromRight};
        else
            return {-1, -1};

    }
};

void judge() {
    setvbuf(stdin, inbuf, _IOFBF, SZ);
    setvbuf(stdout, outbuf, _IOFBF, SZ);

    string str;
    // 读入行用c++的getline，如果用c的scanf，会读入空格，引起错误
    getline(cin,str);
    Zuma zuma(std::move(str));

    int m;
    scanf("%d", &m);

    for (auto i = 0; i < m; i++) {
        int k;
        char c;
        scanf("%d %c", &k, &c);
        zuma.insetNewBallAt(k, c);
        const string &ball = zuma.getBalls();
        if (ball.empty())
            printf("%s\n", "-");
        else
            printf("%s\n", zuma.getBalls().data());
    }
}

void test3() {
    Zuma zuma("AACCA");
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(5, 'C');
    printf("%s\n", zuma.getBalls().data());
}

void test1() {
    Zuma zuma("ACCBA");
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(5, 'B');
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(1, 'B');
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(0, 'A');
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(2, 'B');
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(4, 'C');
    printf("%s\n", zuma.getBalls().data());
}

void test2() {
    Zuma zuma("");
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(0, 'B');
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(1, 'B');
    printf("%s\n", zuma.getBalls().data());
    zuma.insetNewBallAt(1, 'A');
    printf("%s\n", zuma.getBalls().data());
}

int main() {
    judge();
//    test2();
}