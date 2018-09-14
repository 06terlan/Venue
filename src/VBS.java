import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class VBS extends Application{

    public static void main(String[] args) {
        Application.launch(VBS.class, args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/admin/fxml/userLogin.fxml"));
        
        stage.setTitle("User Login");
        stage.setScene(new Scene(root, 410, 275));
        stage.show();
    }
}
