/**
 * Log Package since we're using this as a concrete way to store tasks
 */
package edu.ncsu.csc216.wolf_tracker.model.log;

/**
 * AllTasksLog class extends the AbstractTaskLog class and
 * overrides behavior for setting the log's name.
 * @author Emilie Athanasenas
 */
public class AllTasksLog extends AbstractTaskLog {
	/** Constant name for representing all the tasks */
	public static final String ALL_TASKS_NAME = "All Tasks";
	
	/**
	 * Constructs an object that uses the AbstractTaskLog constructor
	 * and sets the task log name to "All Tasks"
	 */
	public AllTasksLog() {
		super(ALL_TASKS_NAME);
	}
	
	/**
	 * Sets the task log's name to a specified name
	 * @throws IllegalArgumentException if the parameter value does not match 
	 * the expected name
	 * @param name of the tasklog
	 */
	public void setTaskLogName(String name) {
		if(!ALL_TASKS_NAME.equals(name)) {
			throw new IllegalArgumentException("The All Tasks log may not be edited.");
		}
		super.setTaskLogName(name);
		
	}

}
