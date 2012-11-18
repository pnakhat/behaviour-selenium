package org.qainfolabs.behaviour.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: pankaj
 * Date: 18/11/12
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */


public class FileWriterUtil {
    private static File file;

    public synchronized static  String writeFile(String fileName, String content) {
        String text = "";
        File theDir = new File("target/behaviour-selenium/scenarios");
        if (!theDir.exists()) theDir.mkdir();
        try {
            file = new File(theDir +"/" + fileName.replaceAll(":",""));
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(content);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return file.getName();
    }
}

