package org.qainfolabs.behaviour.selenium.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadFileInBuffer {
	
	public BufferedReader readFile(File file){
        //StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
                      
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		return reader;
    }
}


