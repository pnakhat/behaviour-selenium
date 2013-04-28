package org.qainfolabs.behaviour.validator;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.qainfolabs.behaviour.selenium.utils.ReadFileInBuffer;

import java.io.*;
import java.util.List;


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
            int currentLine = allContent.indexOf(line);
            int scenarioStartLine = 0;


            boolean foundScenarioStart = false;
            if (StringUtils.startsWithIgnoreCase(line, "Scenario:")) {
                foundScenarioStart = true;
                scenarioStartLine = allContent.indexOf(line);
            }

            if (StringUtils.startsWithIgnoreCase(line, "End Scenario:")) {
                foundScenarioStart = false;
            }

            if ((scenarioStartLine != currentLine) && foundScenarioStart && StringUtils.startsWithIgnoreCase(line, "Scenario")) {
                throw new IllegalArgumentException("Scenario doesn't have end scenario: " + line + ":Line " + currentLine);
            }

            if (!foundScenarioStart && StringUtils.startsWithIgnoreCase(line, "End Scenario")) {
                throw new IllegalArgumentException("Scenario doesn't have start scenario: " + line + " :at line number: " + currentLine);
            }

        }

        return false;
    }

}
