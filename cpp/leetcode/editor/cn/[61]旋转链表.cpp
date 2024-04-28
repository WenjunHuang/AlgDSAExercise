#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <utility>
#include <memory>

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
    ListNode *rotateRight(ListNode *head, int k) {
        if (head == nullptr) return nullptr;

        auto size = listSize(head);
        k = k % size;
        if (k == 0) return head;

        auto [reversed, _] = reverseList(head, std::numeric_limits<int>::max());
        auto [newHead, next] = reverseList(reversed, k);
        if (next != nullptr) {
            auto [second,_] = reverseList(next, size - k);

            auto p = newHead;
            while (p->next != nullptr) p = p->next;
            p->next = second;
        }
        return newHead;

    }

private:
    static pair<ListNode *, ListNode *> reverseList(ListNode *head, int n, ListNode *acc = nullptr) {
        if (head == nullptr || n == 0)
            return make_pair(acc, head);
        else {
            auto next = head->next;
            head->next = acc;
            return reverseList(next, n - 1, head);
        }
    }

    static size_t listSize(ListNode *head) {
        size_t res = 0;
        auto p = head;
        while (p != nullptr) {
            ++res;
            p = p->next;
        }
        return res;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
    Solution s;
    auto head = new ListNode(1);
    s.rotateRight(head, 0);
}