package 链表.倒数第k个节点;

import java.util.*;


class ListNode {
  int val;
  ListNode next = null;
  public ListNode(int val) {
    this.val = val;
  }
}


public class Test {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pHead ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
        ListNode temp = pHead;
        int index = size(temp) - k;
        if (index < 0) {
            return null;
        }
        int i = 0;
        while (i < index) {
            pHead = pHead.next;
            i++;
        }
        return pHead;
    }

    private int size(ListNode head){
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

}
