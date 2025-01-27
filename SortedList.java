/**
 * util package as it is the list object for our categories, a utility we'll need for the project
 */
package edu.ncsu.csc216.wolf_tracker.model.util;

/**
 * List architecture for creating SortedLists to allow us to
 * sort projects in certain order
 * @author Dawn Pancholi
 * @author Emilie Athanasenas
 * @param <E> generic element
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	/** Size of our list */
	private int size;
	/** front of the listnode */
	private ListNode front;
	
	/**
	 * Constructor to make a SortedList object
	 */
	public SortedList() {
		front = null;
		size = 0;
		
	}

	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if(contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		
		ListNode newNode = new ListNode(element, null);
		
		if(front == null || element.compareTo(front.data) < 0) {
			newNode.next = front;
			front = newNode;
		} else {
			ListNode current = front;
			while(current.next != null && element.compareTo(current.next.data) > 0) {
				current = current.next;
			}
			
			newNode.next = current.next;
			current.next = newNode;
		}
		size++;
		
	}

	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
			
		}
		
		E removedElement;
		if(idx == 0) {
			removedElement = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
		
			removedElement = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return removedElement;
	}


	/**
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found, false if not
	 */
	@Override
	public boolean contains(E element) {
		ListNode current = front;
		while(current != null) {
			if(current.data.compareTo(element) == 0) {
				return true;
			}
			current = current.next; 
		}
		return false;
	}

	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int idx) {

		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		ListNode current = front;
		for(int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Nodes that make up our SortedList
	 */
	private class ListNode {
		/** Data the ListNode consists of */
		public E data;
		/** Instance of ListNode */
		public ListNode next;
		
		/**
		 * Constructor to make a ListNode object
		 * @param data contents of the ListNode
		 * @param next the connecting ListNode 
		 */
		public ListNode(E data, ListNode next){
			this.data = data;
			this.next = next;
		}
	}

}
