/**
 * util package as it is the list object for our tasks, a utility we'll need for the project
 */
package edu.ncsu.csc216.wolf_tracker.model.util;

/**
 * List implementation from ILogList interface to create a list
 * of logs made from a generic element 
 * @author Dawn Pancholi
 * @param <E> generic element
 */
public class LogList<E> implements ILogList<E> {

	/** Array we're using as a backing for our LogList */
	private E[] list;
	/** Size of list */
	private int size;
	/** Initial Capacity of our list when we construct the object */
	private static final int INIT_CAP = 10;
	
	/**
	 * Constructor to create LogList Objects
	 */
	@SuppressWarnings("unchecked")
	public LogList(){
		list = (E[]) new Object[INIT_CAP];
		size = 0;		
	}
	
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added
	 */
	@Override
	public void addLog(E element) {
		if(element == null)
		{
			throw new NullPointerException("Cannot add null element");
		}
		if(size == list.length)
		{
			ensureCapacity(list.length * 2);
		}
		list[size] = element;
		size++;
	}

	
	/**
	 * Set the log at the given index to the given element.
	 * @param idx index of the log to edit
	 * @param element element to replace at the index
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * 		for the list
	 */
	@Override
	public void setLog(int idx, E element) {
		if(element == null)
		{
			throw new NullPointerException("Cannot add null element");
		}
		if(idx < 0 || idx >= size)
		{
			throw new IndexOutOfBoundsException("Invalid index");
		}
		list[idx] = element;
	}

	/**
	 * Removes the log at the given index.
	 * @param idx index of log to remove
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * 		for the list
	 */
	@Override
	public E removeLog(int idx) {
		if(idx < 0 || idx >= size)
		{
			throw new IndexOutOfBoundsException("Invalid index");
		}
		E removedItem = getLog(idx);
		for(int i = idx; i < size - 1; i++)
		{
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return removedItem;
	}

	
	/**
	 * Returns the log at the given index.
	 * @param idx index of log to get
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * 		for the list
	 */
	@Override
	public E getLog(int idx) {
		if(idx < 0 || idx >= size)
		{
			
			throw new IndexOutOfBoundsException("Invalid index");
		}
		return list[idx];
	}

	
	/**
	 * Returns the number of logged elements.
	 * @return number of logged elements
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * sees the size of the log list and sees if we need to expand it
	 * @param capacity figuring out if the list is up to size
	 */
	private void ensureCapacity(int capacity){
		@SuppressWarnings("unchecked")
		E[] newList = (E[]) new Object[capacity];
		for(int i = 0; i < size; i++)
		{
			newList[i] = list[i];
		}
		list = newList;
	}
}
