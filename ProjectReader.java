/**
 * IO package since we're utilizing a file to read from
 */
package edu.ncsu.csc216.wolf_tracker.model.io;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tracker.model.log.AllTasksLog;
import edu.ncsu.csc216.wolf_tracker.model.log.CategoryLog;
import edu.ncsu.csc216.wolf_tracker.model.project.Project;
import edu.ncsu.csc216.wolf_tracker.model.task.Task;

/**
 * ProjectReader class is responsible for reading in files and 
 * outputting them as a Project
 * @author Dawn Pancholi
 * @author Emilie Athanasenas
 */
public class ProjectReader {
	
	/**
	 * Takes in a file and reads it and outputs it as a Project 
	 * object,
	 * @param file to be read in
	 * @return Project object
	 * @throws IllegalArgumentException if the file cannot be loaded or any other issues occur
	 */
	public static Project readProjectFile(File file) {
		if(file == null || !file.exists()) {
			throw new IllegalArgumentException("Unable to load file");
		}
		
		try(Scanner scanner = new Scanner(file)){
			StringBuilder fileContent = new StringBuilder();
			while(scanner.hasNextLine()) {
				fileContent.append(scanner.nextLine()).append("\n");
			}
			
			if(fileContent.length() == 0 || fileContent.charAt(0) != '!') {
				throw new IllegalArgumentException("Unable to load file.");
			}
			
			String[] tokens = fileContent.toString().split("\\r?\\n?[*]");
			
			String[] lines = tokens[0].split("\\r?\\n");
			String projectName = lines[0].substring(1).trim();
			
			
			Project project = new Project(projectName);
			
			String[] categoryLines = tokens[0].split("\\r?\\n");
			
			for(String line: categoryLines) {
				if(line.startsWith("#")) {
					String categoryName = line.substring(1).trim();
					if(!categoryName.isEmpty()) {
						try {
							CategoryLog category = new CategoryLog(categoryName);
							project.addCategoryLog(category.getName());
									
						} catch(IllegalArgumentException e) {
							throw new IllegalArgumentException("Invalid categories.");
						}
					}
				}
			}
			
			
			for(int i = 1; i < tokens.length; i++) {
				String taskToken = tokens[i].trim();
				if(!taskToken.isEmpty()) {
					processTask(project, taskToken);
				}
			}
			
			project.setCurrentTaskLog(AllTasksLog.ALL_TASKS_NAME);	
			return project;
		
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file");
		}
	}
	
	/**
	 * Helper method that processes an individual task 
	 * @param project that contains tasks
	 * @param task to be processed
	 */
	private static void processTask(Project project, String task) {
		String[] taskLines = task.split("\\r?\\n");
		String[] taskInfo = taskLines[0].split(",");
		
		if(taskInfo.length != 3) {
			return;
		}
		
		String taskName = taskInfo[0].trim();
		String taskDurationStr = taskInfo[1].trim();
		String taskCategory = taskInfo[2].trim();
		project.setCurrentTaskLog(taskCategory);
		
		int taskDuration;
		try {
			taskDuration = Integer.parseInt(taskDurationStr);
		} catch (NumberFormatException e) {
			return;
		}
		
		StringBuilder description = new StringBuilder();
		for(int i = 1; i < taskLines.length; i++) {
			description.append(taskLines[i].trim()).append("\n");
		}
		
		try {
			Task newTask = new Task(taskName, taskDuration, description.toString().trim());
			 Object possibleCategoryLog = project.getCurrentLog();
			if(possibleCategoryLog instanceof CategoryLog) {
				project.addTask(newTask);
			}
		} catch (IllegalArgumentException e) {
			//Do Nothing
		}
	}
}