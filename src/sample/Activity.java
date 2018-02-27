package sample;

import java.util.ArrayList;

public class Activity {
    protected String activityName;
    protected int activityDuration;
    protected int startTime;
    protected boolean critical_path;
    protected int earliestFinish;
    protected int latestFinish;
    protected int slack;
    protected String dynamicString;
    protected ArrayList <String> dependencies = new ArrayList<String>();
    protected ArrayList <String> parentOf = new ArrayList<String>();

    public String getActivityName() {
        return activityName;
    }
    public int getActivityDuration(){
        return activityDuration;
    }

    public String getDynamicString() {
        return dynamicString;
    }

}
