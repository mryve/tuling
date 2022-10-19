package 合并两个有序链表;

public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        String s1 = new String("voi") + new String("d");
        String s2 = s1.intern();
        String s3 = "a" + "b";
        System.out.println(s1 == s2);
    }
}