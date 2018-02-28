package com.fxgraph.cells;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import com.fxgraph.graph.Cell;
import javafx.scene.text.Text;
import sample.Controller;

public class TriangleCell extends Cell {

    public TriangleCell( String id) {
        super( id);

        double width = 50;
        double height = 50;

        Polygon view = new Polygon( width / 2, 0, width, height, 0, height);

        view.setStroke(Color.RED);
        view.setFill(Color.RED);
        StackPane stack = new StackPane();



        String temp=new String();
        for(int i = 0; i< Controller.listActivity.size(); i++) {
            if(Controller.listActivity.get(i).activityName.equals(id)) {
                temp = Controller.listActivity.get(i).dependencies.toString();
                break;
            }
        }

        id = id+temp;

        Text text = new Text(id);
        stack.getChildren().addAll(view,text);


        setView(stack);

    }

}