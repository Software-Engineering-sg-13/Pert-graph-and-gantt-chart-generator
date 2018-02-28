package com.fxgraph.cells;

import javafx.scene.control.Label;

import com.fxgraph.graph.Cell;

public class LabelCell extends Cell {

    public LabelCell(String id) {
        super(id);

        Label view = new Label(id);

        setView(view);

    }

}