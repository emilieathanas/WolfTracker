/**
 * project package as this is the central hub of the other classes to be used for the GUI
 */
package edu.ncsu.csc216.wolf_tracker.model.project;
import java.io.File;
import edu.ncsu.csc216.wolf_tracker.model.io.ProjectWriter;
import edu.ncsu.csc216.wolf_tracker.model.log.AbstractTaskLog;
import edu.ncsu.csc216.wolf_tracker.model.log.AllTasksLog;
import edu.ncsu.csc216.wolf_tracker.model.log.CategoryLog;
import edu.ncsu.csc216.wolf_tracker.model.task.Task;
import edu.ncsu.csc216.wolf_tracker.model.util.ILogList;
import edu.ncsu.csc216.wolf_tracker.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tracker.model.util.SortedList;
/**
 * A way to hold a log of tasks that one project object has.
 * @author Dawn Pancholi
 * @author Emilie Athanasenas
 */
public class Project {
	/** Name of the project */
	private String projectName;
	/** boolean to see if there's a change done to the project */
	private boolean isChanged;
	/** a list of categories for different types of tasks */
	private ISortedList<CategoryLog> categories;
	/**	a log of tasks we're currently on */
	private AbstractTaskLog currentLog;
	/** All the different logs for different types of tasks */
	private AllTasksLog allTasksLog;
	
	/**
	 * Project constructor to make a project that'll belong in a SortedList
	 * @param projectName name of this project
	 * @throws IllegalArugmentException if the name is null, empty string, or equal to "All Tasks"
	 */
	public Project(String projectName){
		if(projectName == null || projectName.isEmpty() || projectName.equals(AllTasksLog.ALL_TASKS_NAME))
		{
			//throw new IllegalArgumentException();
			throw new IllegalArgumentException("Invalid name.");
		}
		setProjectName(projectName);

		allTasksLog = new AllTasksLog();
		currentLog = allTasksLog;
		categories = new SortedList<CategoryLog>();
		isChanged = true;
	}
	
	/**
	 * Saves project to a given file
	 * @param aFile the file we're saving the project to
	 */
	public void saveProject(File aFile)
	{
		ProjectWriter.writeProjectFile(aFile, this);
		isChanged = false;
	}
	
	/**
	 * Saves project statistics to a given file
	 * @param aFile the file we're saving the stats to
	 */
	public void saveStats(File aFile)
	{
		ProjectWriter.writeStatsFile(aFile, this);
	}
	
	/**
	 * Getter for the projectName field
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * Setter for the projectName filed
	 * @param projectName the projectName to set
	 */
	private void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * Getter for the isChanged field
	 * @return if a project has been changed
	 */
	public boolean isChanged() {
		return isChanged;
	}
	/**
	 * Setter for the isChanged field
	 * @param isChanged the isChanged to set
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	/**
	 * Add a Log to a certain category 
	 * @param category we're adding a log to
	 * @throws IllegalArgumentException if name is null, empty string, or equal to "All Tasks"
	 */
	public void addCategoryLog(String category)
	{
		if(category == null || category.isEmpty() || category.equals(AllTasksLog.ALL_TASKS_NAME))
		{
			throw new IllegalArgumentException("Invalid name.");
		}
		for(int i = 0; i < categories.size(); i++)
		{
			if(categories.get(i).getName().equals(category))
			{
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		CategoryLog aLog = new CategoryLog(category);
		currentLog = aLog;
		categories.add(aLog);
		currentLog = aLog;
		isChanged = true;
	}
	
	/**
	 * Getter for all the category names of the logs
	 * @return an Array of all the category names in our logs
	 */
	public String[] getCategoryNames()
	{
		String[] categoryNames = new String[categories.size() + 1];
		categoryNames[0] = AllTasksLog.ALL_TASKS_NAME;
		for(int i = 1; i < categories.size() + 1; i++)
		{
			categoryNames[i] = categories.get(i - 1).getName();
		}
		return categoryNames;
	}
	
	/**
	 * Setter for the currentTaskLog to change the task log we're in
	 * @param currentTaskLog the Task Log we're within
	 */
	public void setCurrentTaskLog(String currentTaskLog)
	{
		//CategoryLog aLog = new CategoryLog(currentTaskLog);
		boolean flag = false;
		for(int i = 0; i < categories.size(); i++)
		{
			if(categories.get(i).getName().equals(currentTaskLog))
			{
				currentLog = categories.get(i);
				flag = true;
			}
		}
		if(!flag)
		{
			currentLog = allTasksLog;
		}
	}
	
	/**
	 * Getter for the Task Log we're currently in
	 * @return the Task Log we're currently in
	 */
	public AbstractTaskLog getCurrentLog()
	{
		return currentLog;
	}
	
	/**
	 * Method to allow us to rename a Project's Category Log
	 * @param categoryName the log Category we're changing to
	 * @throws IllegalArgumentException if @param is null or equal to ALL_TASKS_NAME or a duplicate of it exists
	 */
	public void editCategoryLogName(String categoryName)
	{
		if(categoryName == null || categoryName.isEmpty() || categoryName.equals(AllTasksLog.ALL_TASKS_NAME))
		{
			throw new IllegalArgumentException("Invalid name.");
		}
		for(int i = 0; i < categories.size(); i++)
		{
			if(categories.get(i).getName().equalsIgnoreCase(categoryName))
			{
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		if(currentLog instanceof AllTasksLog)
		{
			throw new IllegalArgumentException("The All Tasks log may not be edited.");
		}
		
		
		for(int i = 0; i < categories.size(); i++)
		{
			if(categories.get(i).equals(currentLog))
			{
				CategoryLog removedLog = categories.remove(i);
				removedLog.setTaskLogName(categoryName);
				categories.add(removedLog);
				isChanged = true;
				break;
			}
		}
	}
	
	/**
	 * Method to delete a Log from a certain category
	 * @throws IllegalArgumentException if user attempts to delte all tasks
	 */
	public void removeCategoryLog()
	{
		if(currentLog instanceof AllTasksLog)
		{
			throw new IllegalArgumentException("The All Tasks log may not be deleted.");
		}
		for(int i = 0; i < categories.size(); i++)
		{
			if(categories.get(i).getName().equals(currentLog.getName()))
			{
				categories.remove(i);
				break;
			}
		}
		ILogList<Task> newListOfTasks = allTasksLog.getTasks();
		String currentLogName = currentLog.getName();
		for(int j = 0; j < newListOfTasks.size(); j++)
		{
			if(newListOfTasks.getLog(j).getCategoryName().equals(currentLogName))
			{
				newListOfTasks.removeLog(j);
				j--;
			}
		}
		currentLog = allTasksLog;
		isChanged = true;
	}
	
	/**
	 * Method to add a task to our project
	 * @param addedTask the task being added
	 */
	public void addTask(Task addedTask)
	{
		if(currentLog instanceof CategoryLog)
		{
			currentLog.addTask(addedTask);
			allTasksLog.addTask(addedTask);
			isChanged = true;
		}
		
	}
	
	/**
	 * method to edit a task at a certain index by creating a new task to take its place
	 * @param index index of the task we're modifying
	 * @param title title of our edited task
	 * @param duration duration of our edited task
	 * @param details details of our edited task
	 */
	public void editTask(int index, String title, int duration, String details)
	{
		Task editedTask = currentLog.getTask(index);
		editedTask.setTaskTitle(title);
		editedTask.setTaskDuration(duration);
		editedTask.setTaskDetails(details);
		isChanged = true;
	}
	
	/**
	 * Method to remove a task from our logs given a certain index
	 * It also modifies the isChanged variable to true assuming everything is done right
	 * @param index index of the task we're removing
	 */
	public void removeTask(int index)
	{
		//categories.remove(index);
		if(currentLog instanceof CategoryLog)
		{
			Task removedTask = currentLog.removeTask(index);
			for(int i = 0; i < allTasksLog.getTaskCount(); i++)
			{
				if(allTasksLog.getTask(i).equals(removedTask))
				{
					allTasksLog.removeTask(i);
				}
			}				
		}
		else
		{
			Task removedTask = allTasksLog.removeTask(index);

			String removedTaskCategoryName = removedTask.getCategoryName();
			setCurrentTaskLog(removedTaskCategoryName);

			for(int i = 0; i < currentLog.getTaskCount(); i++)
			{
				if(currentLog.getTask(i).equals(removedTask))
				{
					currentLog.removeTask(i);
				}
			}
			currentLog = allTasksLog;
		}
		isChanged = true;
	}
	
	/**
	 * Method to get the Most recent tasks from our currentLog object
	 * @return a 2D array of information about recent tasks
	 */
	public String[][] getMostRecentTasks()
	{
		String[][] mostRecentTasks = new String[categories.size()][3];
		for(int i = 0; i < categories.size(); i++)
		{
			
			if(categories.get(i).getTaskCount() == 0)
			{
				mostRecentTasks[i][0] = "None";
				mostRecentTasks[i][1] = "";
				mostRecentTasks[i][2] = categories.get(i).getName();
			}
			else
			{
				mostRecentTasks[i][0] = categories.get(i).getTask(categories.get(i).getTaskCount() - 1).getTaskTitle();
				mostRecentTasks[i][1] = Integer.toString(categories.get(i).getTask(categories.get(i).getTaskCount() - 1).getTaskDuration());
				mostRecentTasks[i][2] = categories.get(i).getName();
			}
		}
		return mostRecentTasks;
	}
}