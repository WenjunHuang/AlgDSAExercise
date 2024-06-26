#include <vector>
#include "util.h"

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
    ListNode *mergeTwoLists(ListNode *list1, ListNode *list2) {
        ListNode *dummy = new ListNode(-1);
        ListNode *p = dummy;

        while (list1 != nullptr && list2 != nullptr) {
            if (list1->val > list2->val) {
                p->next = list2;
                list2 = list2->next;
            } else {
                p->next = list1;
                list1 = list1->next;
            }
            p = p->next;
        }

        if (list1 != nullptr)
            p->next = list1;
        if (list2 != nullptr)
            p->next = list2;

        return dummy->next;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}