package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.fxgraph.graph.CellType;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.base.Layout;
import com.fxgraph.layout.random.RandomLayout;
import sample.*;
import sample.Main;

import java.util.ArrayList;
import java.util.Collections;

public class Money {

    ArrayList<Activity> listActivity = Controller.listActivity;

    public Money() {
        fillTable();

    }

    //////////////////////////////////////////////////////////////////////////////

    private void fillTable() {
        for(int i=0;i<listActivity.size();i++) {
            int max = 0;
            String temp;
            if(listActivity.get(i).dependencies.size()==0) {
                listActivity.get(i).earliestFinish = listActivity.get(i).activityDuration;
                listActivity.get(i).startTime = listActivity.get(i).earliestFinish-listActivity.get(i).activityDuration ;
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
                listActivity.get(i).startTime = listActivity.get(i).earliestFinish-listActivity.get(i).activityDuration;
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
        int count=1;
        while(visited.get(0)==0) {
            for(int i=0;i<listActivity.size();i++) {
                int min;
                if(visited.get(i)==0 && isTrue(i, visited)) {
                    System.out.println("hello");
                    min = findMin(i);
                    listActivity.get(i).latestFinish = min;
                    visited.set(i, 1);
                    count++;
                }
            }
        }
        for(int i=0;i<listActivity.size();i++) {
            listActivity.get(i).slack = listActivity.get(i).latestFinish-listActivity.get(i).earliestFinish;
            if(listActivity.get(i).slack == 0) {
                listActivity.get(i).critical_path=true;
            }
            else listActivity.get(i).critical_path=false;
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
        for (int i = 0; i < listActivity.size(); i++) {
            for (int j = 0; j < listActivity.get(i).dependencies.size(); j++) {
                String temp = listActivity.get(i).dependencies.get(j);
                for (int k = 0; k < listActivity.size(); k++) {
                    if (listActivity.get(k).activityName == temp) {
                        listActivity.get(k).parentOf.add(listActivity.get(i).activityName);
                        break;
                    }
                }
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////

    Graph graph = new Graph();

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        graph = new Graph();

        root.setCenter(graph.getScrollPane());

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);

        primaryStage.setTitle("Pert Graph");
        primaryStage.show();

        addGraphComponents();

        Layout layout = new RandomLayout(graph);
        layout.execute();

    }

    private void addGraphComponents() {

        Model model = graph.getModel();

        graph.beginUpdate();

        graph.getModel();

        //model.addCell("Cell A", CellType.RECTANGLE);
       // model.addCell("Cell B", CellType.RECTANGLE);
      //  model.addCell("Cell C", CellType.RECTANGLE);
      //model.addCell("Cell D", CellType.TRIANGLE);
        //model.addCell("Cell E", CellType.TRIANGLE);
      //  model.addCell("Cell F", CellType.RECTANGLE);

        // model.addEdge("Cell A", "Cell B");
        // model.addEdge("Cell A", "Cell C");
        // model.addEdge("Cell B", "Cell C");
        // model.addEdge("Cell C", "Cell D");
        //model.addEdge("Cell A", "Cell E");
        // model.addEdge("Cell D", "Cell F");
        //  model.addEdge("Cell D", "Cell G")
        // ;

        ArrayList<Activity> listActivity = Controller.listActivity;
        for(int i=0; i< listActivity.size();i++) {
            if(listActivity.get(i).critical_path) {
                model.addCell(listActivity.get(i).activityName, CellType.TRIANGLE);
            }
            else {
                model.addCell(listActivity.get(i).activityName, CellType.RECTANGLE);
            }
            System.out.println(listActivity.get(i).parentOf);
        }

        for(int i=0;i<listActivity.size();i++) {
            for(int j=0;j<listActivity.get(i).parentOf.size();j++) {
                //  System.out.println("yummy");
               // model.addCell(listActivity.get(i).activityName+"->"+listActivity.get(i).parentOf.get(j), CellType.LABEL);
                model.addEdge(listActivity.get(i).activityName, listActivity.get(i).parentOf.get(j));
                //model.addEdge(listActivity.get(i).activityName, listActivity.get(i).parentOf.get(j));
            }
        }
        graph.endUpdate();

    }
}