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
    ListNode *removeNthFromEnd(ListNode *head, int n) {
        ListNode dummy(-1);
        dummy.next = head;

        auto x = findFromEnd(&dummy, n + 1);
        x->next = x->next->next;
        return dummy.next;
    }

    ListNode *findFromEnd(ListNode *head, int k) {
        auto p1 = head;
        for (auto i = 0; i < k; i++) p1 = p1->next;

        auto p2 = head;
        while (p1 != nullptr) {
            p2 = p2->next;
            p1 = p1->next;
        }
        return p2;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}