/**
 * Log Package since we're using this as a way to store Categories
 */
package edu.ncsu.csc216.wolf_tracker.model.log;

import edu.ncsu.csc216.wolf_tracker.model.task.Task;

/**
 * CategoryLog class extends the AbstractTaskLog class and
 * overrides behavior when adding and setting a task in the log
 * @author Emilie Athanasenas
 */
public class CategoryLog extends AbstractTaskLog implements Comparable<CategoryLog> {
	/**
	 * Constructs an objects that uses the AbstractTaskLog constructor
	 * and sets the category log's name to a specified title
	 * @param name of our category
	 */
	public CategoryLog(String name) {
		super(name);
	}
	
	/**
	 * Sets the given index of the log to the 
	 * specified task.
	 * Overrides the setTask() method in the parent class
	 * @throws NullPointerException if Task is null
	 * @param idx index of task we're changing
	 * @param task task we're replacing in our AbstractTaskLog's setTask()
	 */
	@Override
	public void setTask(int idx, Task task) {
		if(task == null) {
			throw new NullPointerException("Task cannot be null");
		}
		super.setTask(idx, task);
		task.addCategory(this);
		
	}
	
	/**
	 * Adds a given task to the end of the list
	 * Overrides the addTask() method in the parent class
	 * @throws NullPointerException if Task is null
	 * @param task task we're adding to our AbstractTaskLog addTask()
	 */
	@Override
	public void addTask(Task task) {
		if(task == null) {
			throw new NullPointerException("Task cannot be null");
		}
		super.addTask(task);
		task.addCategory(this);
		
	}

	/**
	 * Compares the names of the CategoryLog's ignoring case
	 * @param c CategoryLog to be compared
	 * @return int representing the comparison
	 */
	public int compareTo(CategoryLog c) {
		return this.getName().compareToIgnoreCase(c.getName());
	}

}
