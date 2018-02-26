package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;
import java.util.ResourceBundle;

public class ganttController implements Initializable {
    @FXML
    public Label testLabel;
    @FXML
    public Button testButton;
    @FXML
    void handle(ActionEvent event){
        if(event.getSource()==testButton){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Main.globalstage.setTitle("Hello");
                Main.globalstage.setScene(new Scene(root, 600, 400));
                Main.globalstage.show();
                System.out.println("ok");
            }
            catch(Exception e){
                System.out.println("new error");
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
