package com.example.asn3;

import javafx.scene.layout.BorderPane;

/**
 * A view that contains and lays out the toolbar and the drawing surface
 */
public class MainUI extends BorderPane {
    public MainUI() {
        // create view components
        ToolPalette toolPaletteView = new ToolPalette();
        DiagramView diagramView = new DiagramView();

        // create model components
        SMModel model = new SMModel();
//        SMStateNode smStateNode = new SMStateNode();
        SMTransitionLink transitionLinkModel = new SMTransitionLink();

        // create interaction model component
        InteractionModel iModel = new InteractionModel();

        // create controller component
        AppController controller = new AppController();


        // connect MVC components
        toolPaletteView.setInteractionModel(iModel);
        toolPaletteView.setController(controller);

//        smStateNode.setModel(model);
//        smStateNode.setInteractionModel(iModel);
//        smStateNode.setController(controller);

//        model.addSubscriber(smStateNode);
        iModel.addSubscriber(toolPaletteView);
        iModel.addSubscriber(diagramView);

        controller.setModel(model);
        controller.setInteractionModel(iModel);


        // set up View layout
        this.setLeft(toolPaletteView);
        this.setCenter(diagramView);

        // set up initial state
        toolPaletteView.init();

    }
}
