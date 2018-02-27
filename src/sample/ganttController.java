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
                Main.globalstage.setTitle("Hello");
                Main.globalstage.setScene(new Scene(root, 600, 400));
               // Main.globalstage.show();
                System.out.println("ok");
            }
            catch(Exception e){
                System.out.println("new error");
            }
        }
        if(event.getSource()==generateChart) {
            stackedBarChart.setData(getChartData());
        }
    }

    private ObservableList<XYChart.Series<Integer, String>> getChartData() {
        ObservableList<Activity> data = FXCollections.observableArrayList();
        return null;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stackedBarChart.setTitle("Gantt Chart");
    }
}
