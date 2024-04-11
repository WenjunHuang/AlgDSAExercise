#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <memory>
#include <unordered_map>

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
    ListNode *deleteDuplicatesUnsorted(ListNode *head) {
        unordered_map<int, int> m;
        auto p = head;
        while (p != nullptr) {
            m[p->val]++;
            p = p->next;
        }

        auto dummy = make_unique<ListNode>(-1);
        dummy->next = head;
        p = dummy.get();
        while (p != nullptr) {
            auto unique = p->next;
            while (unique != nullptr && m[unique->val] > 1) {
                unique = unique->next;
            }

            p->next = unique;
            p = p->next;
        }

        return dummy->next;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
}