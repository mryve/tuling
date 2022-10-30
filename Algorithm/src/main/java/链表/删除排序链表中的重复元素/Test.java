package 链表.删除排序链表中的重复元素;

/**
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表 1->2->3->3->4->4->5  处理后为 1->2->5
 */

  class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Test {
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode newHead = new ListNode(0);
        ListNode slow = newHead;
        ListNode fast = pHead;

        while (fast != null && fast.next != null) {
            if (fast.next.val == fast.val) {
                while (fast.next != null && fast.next.val == fast.val) {
                    fast = fast.next;
                }
                fast = fast.next;
                slow.next = fast;
            } else {
                slow.next = fast;
                slow = slow.next;
                fast = fast.next;
            }
        }
        return newHead.next;

    }
}
