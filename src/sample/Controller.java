package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    static protected ObservableList<Activity> product = FXCollections.observableArrayList();
    static protected ArrayList<Activity> listActivity= new ArrayList<Activity>();
   static private ArrayList <String> tempPrecedence = new ArrayList<String>();
   static private ArrayList <String> listNames = new ArrayList<>();
    @FXML
    protected TableView <Activity> activityTable;
    @FXML
    protected TableColumn<Activity,String> eventNameColumn;
    @FXML
   protected TableColumn<Activity,Integer> eventDurationColumn;
    @FXML
     protected TableColumn<Activity,String> eventDependenciesColumn;
    @FXML
    private Button resetPrecedences;
    @FXML
    private TextField dynamicPrecedence;
    @FXML
    private TextField activityName;
    @FXML
    private TextField activityDuration;
    @FXML
     private ComboBox <String> comboBoxprecedence = new ComboBox<>();
    @FXML
    private Button precedenceAdder;
    @FXML
    private Button activityAdder;
    @FXML
    private Button GeneratePert;
    @FXML
    private Button GenerateGantt;
    @FXML
    private Button deleteActivity;
   @FXML
    void handleActivityInput(ActionEvent event){
        if(event.getSource() == resetPrecedences){
            dynamicPrecedence.setText("");
            tempPrecedence = new ArrayList<String>();
        }
        if(event.getSource() ==activityAdder){
            Activity temp = new Activity();
            if(!listNames.contains(activityName.getText())){

                //***************************************
                //
                //Reset Precedence
                //***************************************
                try {
                    temp.activityDuration = Integer.parseInt(activityDuration.getText());
                    temp.activityName = activityName.getText();
                    activityName.setText("");
                    temp.dynamicString=dynamicPrecedence.getText();
                    listNames.add(temp.activityName);
                    temp.dependencies=tempPrecedence;
                    listActivity.add(temp);
                    comboBoxprecedence.getItems().add(temp.activityName);
                    tempPrecedence = new ArrayList<String>();
                    activityTable.getColumns().clear();

                    product.add(temp);
                    eventNameColumn.setCellValueFactory(new PropertyValueFactory<Activity,String>("activityName"));
                    eventDurationColumn.setCellValueFactory(new PropertyValueFactory<Activity,Integer>("activityDuration"));
                    eventDependenciesColumn.setCellValueFactory(new PropertyValueFactory<Activity,String>("dynamicString"));
                    activityTable.setItems(product);
                    activityTable.getColumns().addAll(eventNameColumn,eventDurationColumn,eventDependenciesColumn);
                    activityDuration.setText("");
                    dynamicPrecedence.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Integer to string");
                }
            }
        }
        if(event.getSource()==precedenceAdder&&comboBoxprecedence.getValue()!=null){
            String checker = comboBoxprecedence.getValue();
            if (!tempPrecedence.contains(checker)){
                tempPrecedence.add(checker);
                dynamicPrecedence.setText(dynamicPrecedence.getText()+" "+checker);
            }
        }


    }
    @FXML
    void handleSceneAction(ActionEvent event) {
        System.out.println("coolw");
        if(event.getSource()==GenerateGantt){
            fillTable();
            for(int i=0;i<listActivity.size();i++) {
                System.out.println("Earliest Finishes: " + listActivity.get(i).earliestFinish);
            }
            for(int i=0;i<listActivity.size();i++) {
                System.out.println("Latest Finishes: " + listActivity.get(i).latestFinish);
            }
            try{
                System.out.println("coodlw");
               Parent root = FXMLLoader.load(getClass().getResource("gantt.fxml"));
                Main.globalstage.setTitle("Hello");
                Main.globalstage.setScene(new Scene(root, 600, 400));
                //Main.globalstage.show();
                System.out.println("ok");
            }
            catch(Exception e){
                System.out.println("bad error");
            }
        }
        else if(event.getSource()==GeneratePert){
            try{
                System.out.println("coodlw");
               /* Parent root = FXMLLoader.load(getClass().getResource("gantt.fxml"));
                Main.globalstage.setTitle("Hello");
                Main.globalstage.setScene(new Scene(root, 600, 400));
                */
            }
            catch(Exception e){
                System.out.println("cool");
            }
        }
        else if(event.getSource()==deleteActivity){
            try{
                System.out.println("coodlw");
                /*Parent root = FXMLLoader.load(getClass().getResource("gantt.fxml"));
                Main.globalstage.setTitle("Hello");
                Main.globalstage.setScene(new Scene(root, 600, 400));
                */
            }
            catch(Exception e){
                System.out.println("cool");
            }
        }
    }

    private void fillTable() {
       for(int i=0;i<listActivity.size();i++) {
           int max = 0;
           String temp;
           if(listActivity.get(i).dependencies.size()==0) {
               listActivity.get(i).earliestFinish = listActivity.get(i).activityDuration;
           }
           else {
               for(int j=0;j<listActivity.get(i).dependencies.size();j++) {
                   temp = listActivity.get(i).dependencies.get(j);
                   for(int k=0;k<listActivity.size();k++) {
                       if(listActivity.get(k).activityName==temp) {
                            if(max < listActivity.get(k).earliestFinish) {
                                System.out.println("***"+max);
                                max = listActivity.get(k).earliestFinish;
                            }
                           break;
                       }
                   }
               }
               listActivity.get(i).earliestFinish = max+listActivity.get(i).activityDuration;
           }

       }

       ArrayList<Integer> visited = new ArrayList<>(Collections.nCopies(listActivity.size(), 0));
       int max=0;
       int maxIndex=0;
        for(int i=0;i<listActivity.size();i++) {
            if(max < listActivity.get(i).earliestFinish) {
                max = listActivity.get(i).earliestFinish;
                maxIndex = i;
            }
        }
        System.out.println("max EF: " + listActivity.get(maxIndex).activityName + " "+ listActivity.get(maxIndex).earliestFinish);
        listActivity.get(maxIndex).latestFinish = listActivity.get(maxIndex).earliestFinish;
        visited.set(maxIndex, 1);
        System.out.println("visited List:" + visited);
        fillParentOf();
        for(int z=0;z<listActivity.size();z++) {
            System.out.println("parent list: "+listActivity.get(z).parentOf);
        }
       while(visited.get(0)==0) {
            for(int i=0;i<listActivity.size();i++) {
                int min;
                if(visited.get(i)==0 && isTrue(i, visited)) {
                    System.out.println("hello");
                    min = findMin(i);
                    listActivity.get(i).latestFinish = min;
                    visited.set(i, 1);
                }
            }
       }
    }

    private int findMin(int i) {
       int min = Integer.MAX_VALUE;
       for(int j=0;j<listActivity.get(i).parentOf.size();j++) {
           String temp = listActivity.get(i).parentOf.get(j);
           for(int k=0;k<listActivity.size();k++) {
               if(listActivity.get(k).activityName == temp) {
                   if(min > (listActivity.get(k).latestFinish-listActivity.get(k).activityDuration)) {
                       min = (listActivity.get(k).latestFinish-listActivity.get(k).activityDuration);
                   }
               }
           }
       }
       return  min;
    }

    private boolean isTrue(int i, ArrayList<Integer> visited) {
       System.out.println(visited);
       for(int j=0;j<listActivity.get(i).parentOf.size();j++) {
           String temp = listActivity.get(i).parentOf.get(j);
           for(int k=0;k<listActivity.size();k++) {
               if(listActivity.get(k).activityName == temp) {
                   if(visited.get(k)==0) return false;
                   break;
               }
           }
       }
        return true;
    }

    private void fillParentOf() {
       for(int i=0;i<listActivity.size();i++) {
           for(int j=0;j<listActivity.get(i).dependencies.size();j++) {
               String temp = listActivity.get(i).dependencies.get(j);
               for(int k=0;k<listActivity.size();k++) {
                   if(listActivity.get(k).activityName==temp) {
                       listActivity.get(k).parentOf.add(listActivity.get(i).activityName);
                       break;
                   }
               }
           }
       }
       for(int i=0;i<listActivity.size();i++) {
           System.out.println(listActivity.get(i).parentOf);
       }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(comboBoxprecedence.getSelectionModel().isEmpty()){
            System.out.println("returning to scene1");
            ObservableList<String> bent = FXCollections.observableArrayList();
            bent.addAll(listNames);
            comboBoxprecedence.setItems(bent);
            if(comboBoxprecedence.getSelectionModel().isEmpty()){
                System.out.println(listNames);
                System.out.println("yesreturn");
            }
        }
        try{
            activityTable.getColumns().clear();
            eventNameColumn.setCellValueFactory(new PropertyValueFactory<Activity,String>("activityName"));
            eventDurationColumn.setCellValueFactory(new PropertyValueFactory<Activity,Integer>("activityDuration"));
            eventDependenciesColumn.setCellValueFactory(new PropertyValueFactory<Activity,String>("dynamicString"));
            activityTable.setItems(product);
            activityTable.getColumns().addAll(eventNameColumn,eventDurationColumn,eventDependenciesColumn);
        }
        catch (Exception e){
            System.out.println("yeah");
        }
    }
}
