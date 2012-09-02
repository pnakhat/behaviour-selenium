package org.qainfolabs.behaviour.selenium.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StoryReader {

	protected String storyLocation;
	protected List<File> storyFiles;
	public StoryReader(String path) {
		this.storyLocation = path;
	}
	
	public List<File> getAllStories(){
		storyFiles = new ArrayList<File>();
		File dir = new File(storyLocation);
		File[] files = dir.listFiles();
		Iterator<File> fileList = Arrays.asList(files).iterator();
		while(fileList.hasNext()){
			File file = fileList.next();
			String fileName = file.getName();
			String fileNameExtension = fileName .substring(fileName.lastIndexOf("."), fileName.length());
			
			if(fileNameExtension.equals(".story")){
				storyFiles.add(file);
			}
		}
		return storyFiles;
	}

	

}
