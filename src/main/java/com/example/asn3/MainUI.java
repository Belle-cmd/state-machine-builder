package com.example.asn3;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * A view that contains and lays out the toolbar and the drawing surface
 */
public class MainUI extends StackPane {
    public MainUI() {
        BorderPane root = new BorderPane();

        // create view components
        ToolPalette toolPaletteView = new ToolPalette();
        DiagramView diagramView = new DiagramView();
        NodePropertiesView nodePropertiesView = new NodePropertiesView();
        LinkPropertiesView linkPropertiesView = new LinkPropertiesView();

        // create model components
        SMModel model = new SMModel();

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

        nodePropertiesView.setInteractionModel(iModel);
        nodePropertiesView.setController(controller);  // has to be after the iModel bc controller needs info from iModel

        controller.setModel(model);
        controller.setInteractionModel(iModel);


        // set up View layout
        root.setLeft(toolPaletteView);
        root.setRight(nodePropertiesView);
        root.setCenter(diagramView);

        this.getChildren().add(root);
    }
}
