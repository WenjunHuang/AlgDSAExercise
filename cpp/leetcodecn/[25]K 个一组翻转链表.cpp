#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>
#include <ranges>
#include <algorithm>

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
class Solution {
public:
    ListNode *reverseKGroup(ListNode *head, int k) {
        if (head == nullptr) return nullptr;

        auto dummy = make_unique<ListNode>(-1, head);
        auto curr = head;
        auto prev = dummy.get();
        auto count = nodeCount(head);
        for (auto i = 0; i < count / k; i++) {
            auto [newHead, newTail] = reserveN(curr, k);
            prev->next = newHead;
            curr->next = newTail;
            prev = curr;
            curr = newTail;
        }

        return dummy->next;
    }

    int nodeCount(ListNode *node, size_t acc = 0) {
        if (node == nullptr) return acc;
        else return nodeCount(node->next, acc + 1);
    }

    pair<ListNode *, ListNode *> reserveN(ListNode *head, int n, ListNode *acc = nullptr) {
        if (head == nullptr || n == 0)
            return make_pair(acc, head);
        else {
            auto next = head->next;
            head->next = acc;
            return reserveN(next, n - 1, head);
        }
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}