#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <queue>

using namespace std;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
struct ListNodeCmp {
    constexpr bool operator()(ListNode *a, ListNode *b) {
        return a->val > b->val;
    }

};

class Solution {
public:
    ListNode *mergeKLists(vector<ListNode *> &lists) {
        if (lists.empty()) return nullptr;

        auto dummy = new ListNode(-1);
        auto p = dummy;
        priority_queue<ListNode *, vector<ListNode *>, ListNodeCmp> pq;

        for (auto list : lists) {
            if (list != nullptr) {
                pq.push(list);
            }
        }

        while (!pq.empty()) {
            auto node = pq.top();
            pq.pop();
            p->next = node;
            if (node->next != nullptr) {
                pq.push(node->next);
                node->next = nullptr;
            }

            p = p->next;
        }


        return dummy->next;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}