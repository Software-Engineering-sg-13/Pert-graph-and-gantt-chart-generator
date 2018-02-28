package sample;

import java.util.ArrayList;
import java.util.Comparator;

public  class Activity {
    public String activityName;
    public int activityDuration;
    public int startTime;
    public boolean critical_path;
    public int earliestFinish;
    public int latestFinish;
    public int slack;
    public String dynamicString;
    public ArrayList <String> dependencies = new ArrayList<String>();
    public ArrayList <String> parentOf = new ArrayList<String>();

    public String getActivityName() {
        return activityName;
    }
    public int getActivityDuration(){
        return activityDuration;
    }

    public String getDynamicString() {
        return dynamicString;
    }

    public int getStartTime() {
        return startTime;
    }


}
