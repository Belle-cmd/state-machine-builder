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
        model.addSubscriber(diagramView);
        iModel.addCanvasSubscriber(diagramView);
        iModel.addToolSubscriber(toolPaletteView);

        // set up initial state; have to be declared here in order for cursor changes to be enabled
        // initializing buttons before the controller is set causes buttons to be null -> buttons need to be
        // initialized before the controller is set since the scene is retrieved through the tool buttons
        toolPaletteView.init();
        toolPaletteView.setController(controller);

        toolPaletteView.setInteractionModel(iModel);
        diagramView.setIModel(iModel);
        diagramView.setModel(model);
        diagramView.setController(controller);

//        smStateNode.setModel(model);
//        smStateNode.setInteractionModel(iModel);
//        smStateNode.setController(controller);


        controller.setModel(model);
        controller.setInteractionModel(iModel);


        // set up View layout
        this.setLeft(toolPaletteView);
        this.setCenter(diagramView);
    }
}
