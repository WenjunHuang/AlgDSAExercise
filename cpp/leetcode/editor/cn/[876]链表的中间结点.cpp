#include <vector>
#include "util.h"
#include <map>
#include <string>
#include <iostream>


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
    ListNode *middleNode(ListNode *head) {
        auto fast = head;
        auto slow = head;

        while (fast != nullptr && fast->next != nullptr) {
            slow = slow->next;
            fast = fast->next->next;
        }
        return slow;
    }
};
//leetcode submit region end(Prohibit modification and deletion)


int main(int argv, char *argc[]) {
    Solution solution;
    auto result = solution.middleNode(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5,new ListNode(6)))))));
    cout << result->val << endl;
}