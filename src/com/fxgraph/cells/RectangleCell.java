package com.fxgraph.cells;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import com.fxgraph.graph.Cell;
import javafx.scene.text.Text;
import sample.Controller;

public class RectangleCell extends Cell {

    public RectangleCell( String id) {
        super( id);

        Rectangle view = new Rectangle( 50,50);
        StackPane stack = new StackPane();

        view.setStroke(Color.DODGERBLUE);
        view.setFill(Color.DODGERBLUE);


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

        setView( stack);

    }

}