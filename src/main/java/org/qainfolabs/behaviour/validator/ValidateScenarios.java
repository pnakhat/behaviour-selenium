package org.qainfolabs.behaviour.validator;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.qainfolabs.behaviour.selenium.utils.ReadFileInBuffer;

import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pankajnakhat
 * Date: 02/03/2013
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class ValidateScenarios {

    private File story;
    private final List<String> allContent;

    public ValidateScenarios(File story) throws IOException {
       this.story = story;
        FileInputStream fs = new FileInputStream(story);
        allContent = IOUtils.readLines(fs);
    }

    public boolean validateScenarioSchema() throws IOException {


        for (String line : allContent) {
            boolean foundScenarioStart = false;
            if (StringUtils.startsWithIgnoreCase(line,"Scenario:")) {
                foundScenarioStart = true;
            }

            if (foundScenarioStart && StringUtils.startsWithIgnoreCase(line,"Scenario")) {
                  throw new IllegalArgumentException("Scenario doesnt have end scenario" + line);
            }

            if (!foundScenarioStart && StringUtils.startsWithIgnoreCase(line,"Scenario")) {
                throw new IllegalArgumentException("Scenario doesnt have end scenario" + line + allContent.indexOf(line));
            }



        }

        return false;
    }

}
