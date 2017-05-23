/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */
package simple;


import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {
	
		
    /**
     * @param args the command line arguments
     * @throws IOException 
     * @throws SecurityException 
     */
    public static void main(String[] args) throws SecurityException, IOException {
        Application.launch(Main.class, (java.lang.String[])null);
     
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //StackPane page = (StackPane) FXMLLoader.load(Main.class.getClassLoader().getResource("simple.fxml"));
           // AnchorPane page  = (AnchorPane) FXMLLoader.load(Main.class.getClassLoader().getResource("Crawler.fxml"));
            Pane page  = (Pane) FXMLLoader.load(Main.class.getClassLoader().getResource("Crawler.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("CareLink Crawler");
           
            primaryStage.show();
        	
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
