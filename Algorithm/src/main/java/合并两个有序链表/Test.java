package 合并两个有序链表;

/**
 * 合并两个有序链表, 合并之后依旧有序
 * 例 {1,3,5},{2,4,6}    =>  {1,2,3,4,5,6}
 */
public class Test {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);
        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(4);
        list2.next.next = new ListNode(6);
        Merge(list1, list2);
    }

    /**
     * 思路: 使用一个新链表, 逐个对比list1和2的大小, 谁小谁放, 当一个list放完之后, 直接将剩下的list放到新链表中
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode newHead = new ListNode(0);
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                newHead.next = list1;
                list1 = list1.next;
            } else {
                newHead.next = list2;
                list2 = list2.next;
            }
            newHead = newHead.next;
        }
        ListNode restList = list1 == null ? list2 : list1;
        newHead.next = restList;
        return newHead.next;
    }
}
