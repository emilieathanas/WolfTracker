/**
 * IO package since we're utilizing a file to write from
 */
package edu.ncsu.csc216.wolf_tracker.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import edu.ncsu.csc216.wolf_tracker.model.log.AbstractTaskLog;
import edu.ncsu.csc216.wolf_tracker.model.log.CategoryLog;

import edu.ncsu.csc216.wolf_tracker.model.project.Project;

/**
 * ProjectWriter class is responsible for writing out projects onto 
 * given files
 * @author Dawn Pancholi
 * @author Emilie Athanasenas
 */
public class ProjectWriter {
	
	/**
	 * Writes a given project onto a given file
	 * @param file to export to 
	 * @param project to write out 
	 * @throws IllegalArgumentException if there are any errors with writing the file
	 */
	public static void writeProjectFile(File file, Project project) {
		 if (file == null || project == null) {
	            throw new IllegalArgumentException("Invalid file or project.");
	        }

	        try (PrintWriter writer = new PrintWriter(file)) {
	         
	            writer.println("! " + project.getProjectName());

	    
	            String[] categoryNames = project.getCategoryNames();
	            
	            
	            for (int i = 1; i < categoryNames.length; i++) {
	                writer.println("# " + categoryNames[i]);
	            }
	            
	            String[] names = project.getCategoryNames();
	            for(int i = 1; i < names.length; i++) {
	            	project.setCurrentTaskLog(names[i]);

	            	for(int j = 0; j < project.getCurrentLog().getTaskCount(); j++) {
	            		writer.println(project.getCurrentLog().getTask(j));
	            	}
	            	
	            }
	        } catch (FileNotFoundException e) {
	            throw new IllegalArgumentException("Unable to write to file.");
	        }
	        
	}
	
	/**
	 * Writes the statistics of a project onto a given file
	 * @param file to export to
	 * @param project statistics to write out
	 * @throws IllegalArgumentException if there is a problem saving the file
	 */
	public static void writeStatsFile(File file, Project project) {
	    try (PrintWriter writer = new PrintWriter(file)) {
	    	
	    	String[] categoryNames = project.getCategoryNames();
	    	writer.println("Category,Count,Min,Max,Average");
	    	
	    	for(String name: categoryNames) {
	    		if(!"All Tasks".equals(name)) {
	    			project.setCurrentTaskLog(name);
		    		CategoryLog log = (CategoryLog) project.getCurrentLog();
		    		if(log.getName().equals(name)) {
		    			writer.println(log.toString());
		    		}
	    		}
	    		
	    	}
	    	project.setCurrentTaskLog("All Tasks");
	    	AbstractTaskLog allTasksLog = project.getCurrentLog();
	    	writer.println(allTasksLog.toString());	
	    	
	    } catch (FileNotFoundException e) {
	    	throw new IllegalArgumentException("Unable to save file");
	    }
    
	}

}
