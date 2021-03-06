package org.qainfolabs.behaviour.model;

import org.apache.commons.lang.StringUtils;
import org.qainfolabs.behaviour.selenium.utils.ReadFileInBuffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Feature {
    private File file;
    private String featureTitle;

    public Feature(File file) {
        this.file = file;
    }

    public List<Scenario> getAllScenarios() {
        BufferedReader bf = getBufferedReader(getFile());
        String step;
        List<Step> scenarioSteps = null;
        List<Scenario> scenarios = new ArrayList<Scenario>();
        Scenario scn = null;
        boolean foundScenarioStart = false;
        try {
            while ((step = bf.readLine()) != null) {
                String line = StringUtils.trim(step);
                if(line.startsWith("Feature:")) {
                    featureTitle = line;
                }

                if (line.startsWith("Scenario")) {
                    foundScenarioStart = true;
                    scn = new Scenario();
                    scn.setTitle(line);
                    scn.setFeatureFileName(featureFileName());
                    scenarioSteps = new ArrayList<Step>();
                }
                if (foundScenarioStart && line.contains("End Scenario")) {
                    scn.readSteps(scenarioSteps);
                    scenarios.add(scn);
                    System.out.println("All steps" + scn.allSteps().toString());
                    foundScenarioStart = false;
                }

                if (foundScenarioStart && !(line.contains("Scenario:"))) {
                    scenarioSteps.add(new Step(line));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return scenarios;
    }

    private static BufferedReader getBufferedReader(File file) {
        ReadFileInBuffer rf = new ReadFileInBuffer();
        return rf.readFile(file);
    }


    public String getFeatureTitle() {
        return featureTitle;
    }

    private File getFile() {
        return file;
    }

    public String featureFileName() {
        return file.getName();
    }
}
