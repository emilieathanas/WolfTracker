/**
 * task package as this is the class for a task object
 */
package edu.ncsu.csc216.wolf_tracker.model.task;

import edu.ncsu.csc216.wolf_tracker.model.log.CategoryLog;

/**
 * Task class contains the information about each individual task including
 * title, duration, and details. It represents a task in the WolfTracker system
 * @author Dawn Pancholi
 * @author Emilie Athanasenas
 */
public class Task {
	/** String field for title of task */
	private String taskTitle;
	/** Int field for the duration of a task */
	private int taskDuration;
	/** String field for the details of a task */
	private String taskDetails;
	/** Instance of CategoryLog */
	private CategoryLog category;
	
	/**
	 * Constructs a Task object consisting of its name, duration, and 
	 * the task details
	 * @param title of task
	 * @param duration of task
	 * @param details of task 
	 */
	public Task(String title, int duration, String details) {
		setTaskTitle(title);
		setTaskDuration(duration);
		setTaskDetails(details);
		category = null;
		
	}
	
	/**
	 * Gets the title of the task
	 * @return task title
	 */
	public String getTaskTitle() {
		return taskTitle;
	}
	
	/**
	 * Sets the title of the task to the specified title
	 * @param title to be set as the title
	 * @throws IllegalArgumentException if title is null or empty string
	 */
	public void setTaskTitle(String title) {
		if(title == null)
		{
			throw new IllegalArgumentException("Incomplete task information.");
		}
		if(title.length() == 0)
		{
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskTitle = title;
	}
	
	/**
	 * Gets the duration of the task
	 * @return duration of task
	 */
	public int getTaskDuration() {
		return taskDuration;		
	}
	
	/**
	 * Sets the duration of the task to the specified duration
	 * @param duration to be set as the duration
	 * @throws IllegalArgumentException if the duration is not at least 1
	 */
	public void setTaskDuration(int duration) {
		if(duration < 1)
		{
			throw new IllegalArgumentException("Incomplete task information.");
		}
		taskDuration = duration;
	}
	
	/**
	 * Gets the details of the task
	 * @return details of task
	 */
	public String getTaskDetails() {
		return taskDetails;
	}
	
	/**
	 * Sets the details of the task to the specified details
	 * @param details to be set as the details
	 * @throws IllegalArgumentException if the details string is null or 
	 * empty
	 */
	public void setTaskDetails(String details) {
		if(details == null)
		{
			throw new IllegalArgumentException("Incomplete task information.");
		}
		if(details.length() == 0)
		{
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskDetails = details;
	}
	
	/**
	 * Adds a specified category to the task
	 * @param c represents the CategoryLog to be added
	 * @throws IllegalArgumentException if the parameter is null or there
	 * already is an assigned category
	 */
	public void addCategory(CategoryLog c) {
		if(c == null)
		{
			throw new IllegalArgumentException("Incomplete task information.");
		}
		if(category != null)
		{
			throw new IllegalArgumentException("Incomplete task information.");
		}
		category = c;
	}
	
	/**
	 * Gets the name of the category
	 * @return name of category
	 */
	public String getCategoryName() {
		if(category == null)
		{
			return "";
		}
		return category.getName();
	}
	
	/**
	 * Represents a task as a String
	 * @return task in the form of a String
	 */
	public String toString() {
		return "* " + taskTitle + "," + taskDuration + "," + getCategoryName() + "\n" + taskDetails;
	}
}