package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class UserLogin extends Application{

    public static void main(String[] args) {
        Application.launch(UserLogin.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/admin/fxml/UserLogin.fxml"));
        
        stage.setTitle("User Login");
        stage.setScene(new Scene(root, 410, 275));
        stage.show();
    }
}
