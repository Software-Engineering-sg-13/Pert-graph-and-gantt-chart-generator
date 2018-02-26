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
import java.util.ResourceBundle;

public class Controller implements Initializable {
    static protected ObservableList<Activity> product = FXCollections.observableArrayList();
    static protected ArrayList<Activity> listActivity= new ArrayList<Activity>();
   static private ArrayList <String> tempPrecedence = new ArrayList<String>();
   static private ArrayList <String> listNames = new ArrayList<String>();
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
     private ComboBox <String> comboBoxprecedence;
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
            try{
                System.out.println("coodlw");
               Parent root = FXMLLoader.load(getClass().getResource("gantt.fxml"));
                Main.globalstage.setTitle("Hello");
                Main.globalstage.setScene(new Scene(root, 600, 400));
                Main.globalstage.show();
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
                Main.globalstage.show();*/
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
                Main.globalstage.show();*/
            }
            catch(Exception e){
                System.out.println("cool");
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
