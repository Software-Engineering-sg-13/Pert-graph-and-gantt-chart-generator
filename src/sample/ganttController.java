package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ganttController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private Button generateChart;
    @FXML
    private StackedBarChart<Integer, String> stackedBarChart;
    @FXML
    private CategoryAxis activityNames;
    @FXML
    private NumberAxis duration;
    @FXML
    void handle(ActionEvent event){
        if(event.getSource()==home){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Main.globalstage.setTitle("Gantt Chart & Pert Graph Generator");
                Main.globalstage.setScene(new Scene(root, 600, 400));
               // Money.globalstage.show();
                System.out.println("ok");
            }
            catch(Exception e){
                System.out.println("new error");
            }
        }
        if(event.getSource()==generateChart) {

        }
    }

    private ObservableList<XYChart.Series<Integer, String>> getChartData() {
        //ObservableList<Activity> data = FXCollections.observableArrayList();

        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        ArrayList<Activity>pumi =Controller.listActivity;
        for(int i=0;i<Controller.listActivity.size();i++) {
            for(int j=1;j<Controller.listActivity.size()-i;j++) {
                if(Controller.listActivity.get(j-1).startTime > Controller.listActivity.get(j).startTime) {
                    Activity temp;
                    temp = Controller.listActivity.get(j-1);
                        pumi.get(j-1) = pumi.get(j);

                    Controller.listActivity.get(j) = temp;
                    String pent;
                    pent= Controller.listNames.get(j-1);
                    Controller.listNames.get(j-1) = Controller.listNames.get(j);
                    Controller.listNames.get(j) = pent;

                }


            }
        }

        */

        Collections.sort(Controller.listActivity, new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                if(o1.startTime!=o2.startTime) {
                    return Integer.valueOf(o2.startTime).compareTo(o1.startTime);
                }
                else {
                    return String.valueOf(o2.activityName).compareTo(o1.activityName);
                }
            }
        });



        stackedBarChart.setTitle("Gantt Chart");
            ObservableList<String> data = FXCollections.observableArrayList();
            for(int i=0;i<Controller.listActivity.size();i++) {
                data.add(Controller.listActivity.get(i).activityName);
            }
            activityNames.setCategories(data);
        duration.setLabel("Days");
        XYChart.Series<Integer, String> series1 = new XYChart.Series<>();
        series1.setName("Not Started");
        XYChart.Series<Integer, String> series2 = new XYChart.Series<>();
        series2.setName("In Progress");
        XYChart.Series<Integer, String> series3 = new XYChart.Series<>();
        series3.setName("Slack");



        for(int i=Controller.listNames.size()-1;i>=0;i--) {
            series1.getData().add(new XYChart.Data<>(Controller.listActivity.get(i).startTime, Controller.listActivity.get(i).activityName));
            series2.getData().add(new XYChart.Data<>(Controller.listActivity.get(i).activityDuration, Controller.listActivity.get(i).activityName));
            series3.getData().add(new XYChart.Data<>(Controller.listActivity.get(i).slack, Controller.listActivity.get(i).activityName));
        }
        stackedBarChart.getData().addAll(series1, series2, series3);
    }
}
