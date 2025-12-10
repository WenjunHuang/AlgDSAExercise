#include <vector>
#include "util.h"
#include <map>
#include <string>

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
    ListNode *partition(ListNode *head, int x) {
        auto *dummy1 = new ListNode(-1);
        auto *dummy2 = new ListNode(-1);
        auto *p1 = dummy1;
        auto *p2 = dummy2;

        auto *p = head;
        while (p != nullptr) {
            if (p->val < x) {
                p1->next = p;
                p1 = p1->next;
            } else {
                p2->next = p;
                p2 = p2->next;
            }
            auto temp = p->next;
            p->next = nullptr;
            p = temp;
        }

        p1->next = dummy2->next;
        return dummy1->next;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}