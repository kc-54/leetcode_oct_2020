package com.leet.leetcode_oct_2020;

import java.util.HashSet;
import java.util.Set;

public class Linked_List_Cycle_II {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(3);
		head.next = new ListNode(2);
		head.next.next = new ListNode(0);
		head.next.next.next = new ListNode(-4);
		head.next.next.next.next = head.next;
		System.out.println("loop in " + detectCycle_1(head).val);
	}
	
	//original solution
	//store the each listnode in a set and 
	//check if the current listnode.next existed in listnode set
	//if there is, return the node
	//if the node.next is null, it means the list node doesn;t loop
	//Runtime 4 ms, Memory Usage 40.1 MB
	public static ListNode detectCycle_1(ListNode head) {
        
        ListNode node = head;
        Set<ListNode> set = new HashSet<>();
        
        while(node.next != null){
            if(set.contains(node.next))return node.next;
            set.add(node);
            node = node.next;
            
        }
        return null;
    }
	
	//Runtime 0 ms, Memory Usage 39 MB
	//solution from discussion
	public static ListNode detectCycle_2(ListNode head) {
		
		ListNode slow = head;
        ListNode fast = head;
        boolean cycle = false;
        
        //use fast to check for cycle faster
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            
            //if fast next is null, it means node have no cycle and ended, return null
            if (fast.next == null) return null;
            fast = fast.next.next;
            if(slow == fast) 
            {
                cycle = true;
                break;
            }
        }
        
        //if no cycle, return null
        if(!cycle) return null;;
        
        //if have cycle, set slow to head and start looping with fast from last position
        //when slow==fast, that is the looping point start
       
        slow = head;
        while(slow!=fast)
        {
            slow = slow.next;
            fast =fast.next;
        }
        
        return slow;
	}
	
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}

		
	}
}
