package 链表.反转链表;

public class Test {
    public ListNode ReverseList(ListNode head) {
        ListNode newHead = new ListNode(0);
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead.next;
            newHead.next = head;
            head = next;
        }
        return newHead.next;

    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
