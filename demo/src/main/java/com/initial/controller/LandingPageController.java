// package com.initial.controller;


// import java.io.IOException;

// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import javafx.scene.Node;  // Add this import

// public class LandingPageController {

//  @FXML
//     public void handleLoginButton(ActionEvent event) {
//         try {
//             // Load the login page FXML
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/initial/view/login_page.fxml"));
//             Parent loginPage = loader.load();
            
//             // Get the stage from the event source
//             Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            
//             // Create the new scene with the login page
//             Scene scene = new Scene(loginPage);
            
//             // Set the scene on the stage and show it
//             stage.setScene(scene);
//             stage.show();
            
//         } catch (IOException e) {
//             e.printStackTrace();
//             System.out.println("Could not load login page: " + e.getMessage());
//         }
//     }
// }