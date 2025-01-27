/**
 * Log Package since we're using this as an abstract way to store tasks
 */
package edu.ncsu.csc216.wolf_tracker.model.log;

import edu.ncsu.csc216.wolf_tracker.model.task.Task;
import edu.ncsu.csc216.wolf_tracker.model.util.ILogList;
import edu.ncsu.csc216.wolf_tracker.model.util.LogList;

/**
 *  Represents a log of tasks in the Wolf Tracker system
 *  @author Emilie Athanasenas
 */
public abstract class AbstractTaskLog {
	
	/** Name of the task log */
	private String taskLogName;

	/** Instance of ILogList */
	private ILogList<Task> tasks;
	
	/**
	 * Constructor to prepare for concrete class creation
	 * @param taskListName name of the tasklog
	 * @throws IllegalArgumentException if the taskListName is null or 
	 * empty string
	 */
	public AbstractTaskLog(String taskListName) {
		if(taskListName == null || taskListName.isEmpty()) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.taskLogName = taskListName;
		tasks = new LogList<Task>();
	}
	
	/**
	 * Gets the name of the task log
	 * @return name of the task log
	 */
	public String getName() {
		return taskLogName;
		
	}
	
	/**
	 * Sets the name of the task given a name
	 * @param name of the task 
	 */
	public void setTaskLogName(String name) {
		this.taskLogName = name;
		
	}
	
	/**
	 * Gets the list of tasks
	 * @return list of tasks
	 */
	public ILogList<Task> getTasks(){
		return tasks;
	}
	
	/**
	 * Adds the task to the end of the log.
	 * @param task to be added
	 * @throws NullPointerException if the task is null
	 */
	public void addTask(Task task) {
		if(task == null)
		{
			throw new NullPointerException();
		}
		tasks.addLog(task);
		
	}
	
	/**
	 * Sets the task at a given index
	 * @param index of list 
	 * @param task to be set in list
	 * @throws NullPointerException if the task is null
	 * @throws IndexOutOfBoundsException if the index if out of bounds
	 */
	public void setTask(int index, Task task) {
		if(task == null)
		{
			throw new NullPointerException();
		}
		if(index >= tasks.size())
		{
			throw new IndexOutOfBoundsException();
		}
		tasks.setLog(index, task);		
	}
	
	/**
	 * Removes the task at the given index and returns it
	 * @param index of task to be removed
	 * @return task at given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public Task removeTask(int index) {
		return tasks.removeLog(index);
	}
	
	/**
	 * Gets the task at a given index
	 * @param index of task to be returned
	 * @return task at given index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public Task getTask(int index) {
		if(index >= tasks.size())
		{
			throw new IndexOutOfBoundsException();
		}
		return tasks.getLog(index);
	}
	
	/**
	 * Returns the number of tasks in the list
	 * @return number of tasks in the list
	 */
	public int getTaskCount() {
		return tasks.size();
	}
	
	/**
	 * Gets the minimum duration spent on a task
	 * @return minimum duration on a task
	 */
	public int getMinDuration() {
		if(tasks.size() != 0) {
			int minimum = tasks.getLog(0).getTaskDuration();
			for(int i = 0; i < tasks.size() - 1; i++) {
				if(tasks.getLog(i + 1).getTaskDuration() < minimum) {
					minimum = tasks.getLog(i + 1).getTaskDuration();
					
				}
			}
			return minimum;
		}
		return 0;
		
	}
	
	/**
	 * Gets the maximum duration spent on a task
	 * @return max duration on a task
	 */
	public int getMaxDuration() {
		if(tasks.size() != 0) {
			int maximum = tasks.getLog(0).getTaskDuration();
			for(int i = 0; i < tasks.size() - 1; i++) {
				if(tasks.getLog(i + 1).getTaskDuration() > maximum) {
					maximum = tasks.getLog(i + 1).getTaskDuration();
					
				}
			}
			return maximum;
		}
		return 0;
	}
	
	/**
	 * Gets the average duration spent on a task
	 * @return average time it takes to do a task
	 */
	public double getAvgDuration() {
		double sum = 0;
		for(int i = 0; i < tasks.size(); i++) {
			sum += tasks.getLog(i).getTaskDuration();
			
		}
		double average = sum / tasks.size();
		double roundedAvg = Math.round(average * 10) / 10.0;
		return roundedAvg;
	}
		
	/**
	 * Gets the tasks in a 2D array of strings
	 * @return list of tasks in a 2D array
	 */
	public String[][] getTasksAsArray(){
		String[][] taskArray = new String[tasks.size()][3];
		for(int i = 0; i < tasks.size(); i++) {
			taskArray[i][0] = tasks.getLog(i).getTaskTitle();
			taskArray[i][1] = "" + tasks.getLog(i).getTaskDuration();
			taskArray[i][2] = tasks.getLog(i).getCategoryName();
		}
		return taskArray;
	}
	
	/**
	 * Returns a string representation of the summary statistics 
	 * @return String representation of a Task
	 */
	public String toString() {
		if(this.getTaskCount() == 0) {
			return this.getName() + "," + 0 + "," + "," + ",";
		}
		return this.getName() + "," + this.getTaskCount() + "," + this.getMinDuration() + "," + this.getMaxDuration() + "," + this.getAvgDuration();
	}

}
