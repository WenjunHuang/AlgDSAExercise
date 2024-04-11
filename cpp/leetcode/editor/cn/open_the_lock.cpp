#include <utility>
#include <vector>
#include <string>
#include <set>
#include <queue>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
public:
    int openLock(vector<string> &deadends, string target) {
        return doubleBfs(deadends, std::move(target));
    }

    int bfs(vector<string> &deadends, string target) {
        set<string> visited; // 记录已经访问过的密码，防止走回头路
        set<string> deads(deadends.begin(), deadends.end());

        int step = 0;
        queue<string> q;
        q.emplace("0000");
        visited.insert("0000");

        while (!q.empty()) {
            auto sz = q.size();
            // 将当前队列中的所有节点向周围扩散
            for (auto i = 0; i < sz; i++) {
                auto cur = q.front();
                q.pop();

                // 判断是否到达终点
                if (deads.contains(cur)) continue;
                if (cur == target) return step;

                // 将一个节点的未遍历相邻节点加入队列
                for (auto j = 0; j < 4; j++) {
                    auto up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.push(up);
                        visited.insert(up);
                    }

                    auto down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.push(down);
                        visited.insert(down);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    int doubleBfs(vector<string> &deadends, string target) {
        set<string> deads(deadends.begin(), deadends.end());
        set<string> visited;

        set<string> q1;
        set<string> q2;

        int step = 0;
        q1.insert("0000");
        q2.insert(target);
        while (!q1.empty() && !q2.empty()) {
            if (q1.size() > q2.size()) {
                // 选择一个小的集合进行扩散，这样的好处是遍历的节点少，减少了搜索的时间
                swap(q1, q2);
            }

            set<string> temp;
            for (auto &cur: q1) {
                if (deads.contains(cur)) continue;
                if (q2.contains(cur)) return step;

                visited.insert(cur);

                for (auto i = 0; i < 4; i++) {
                    auto up = plusOne(cur, i);
                    if (!visited.contains(up)) {
                        temp.insert(up);
                    }

                    auto down = minusOne(cur, i);
                    if (!visited.contains(down)) {
                        temp.insert(down);
                    }
                }
            }
            step++;
            q1 = std::move(q2);
            q2 = std::move(temp);
        }

        return -1;
    }

    string plusOne(string s, int i) {
        if (s[i] == '9')
            s[i] = '0';
        else
            s[i] += 1;
        return s;
    }

    string minusOne(string s, int i) {
        if (s[i] == '0')
            s[i] = '9';
        else
            s[i] -= 1;
        return s;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}