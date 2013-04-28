package org.qainfolabs.behaviour.selenium.utils;

import org.qainfolabs.behaviour.model.Feature;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FeatureReader {

	protected String storyLocation;
	protected List<Feature> storyFiles;
	public FeatureReader(String path) {
		this.storyLocation = path;
	}
	
	public List<Feature> getAllFeatures(){
		storyFiles = new ArrayList<Feature>();
		File dir = new File(storyLocation);
		File[] files = dir.listFiles();
		Iterator<File> fileList = Arrays.asList(files).iterator();
		while(fileList.hasNext()){
			File file = fileList.next();
			String fileName = file.getName();
			String fileNameExtension = fileName .substring(fileName.lastIndexOf("."), fileName.length());
			
			if(fileNameExtension.equals(".story")){
				storyFiles.add(new Feature(file));
			}
		}
		return storyFiles;
	}

	

}
