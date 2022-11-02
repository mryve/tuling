package 链表.回文链表;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false
 * 例: head = [1,2,2,1]   true
 */
public class Test {
    public static void main(String[] args) {

    }

    /**
     * 思路: 设置快慢指针, 只要快指针没到头就让快指针一直走两次, 慢指针也跟着走一次, 如果总数是双数快指针此时在null, 慢指针在二段链表第一;
     * 如果总数是单数快指针此时在null的前一位, 慢指针也在二段链表前一位, 让慢指针前进一位;
     * 之后反转二段链表, 并让快指针回到开始进行对比, 知道慢指针到null
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val == slow.val) {
                fast = fast.next;
                slow = slow.next;
            } else {
                return false;
            }
        }
        return true;

    }

    public ListNode reverse(ListNode head) {
        ListNode end = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = end;
            end = head;
            head = next;
        }
        return end;
    }

}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
