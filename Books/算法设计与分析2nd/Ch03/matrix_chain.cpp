//
// Created by rick on 2024/4/1.
//

#include <vector>
#include <iostream>


std::pair<int, int> matrixChain(const std::vector<int> &p) {
    int n = p.size() - 1;
    std::vector<std::vector<std::pair<int, int>>> m(n+1, std::vector<std::pair<int, int>>(n+1, {std::numeric_limits<int>::max(), 0}));
    for (int i = 1; i <= n; i++) {
        m[i][i] = {0, 0};
    }
    for (int r = 2; r <= n; r++) {
        for (int i = 1; i <= n - r + 1; i++) {
            int j = i + r - 1;
            m[i][j] = {m[i + 1][j].first + p[i - 1] * p[i] * p[j], i};
            for (int k = i + 1; k < j; k++) {
                int t = m[i][k].first + m[k + 1][j].first + p[i - 1] * p[k] * p[j];
                if (t < m[i][j].first) {
                    m[i][j] = {t, k};
                }
            }
        }
    }
    return m[1][n];
}

int main(int argc, char *argv[]) {
    auto result = matrixChain({30, 35, 15, 5, 10, 20});
    std::cout << result.first << " " << result.second << std::endl;

}