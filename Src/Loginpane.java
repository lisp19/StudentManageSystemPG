//Widget used as login pane
//Users can login, go to reset password widget, goto register pane


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.beans.value.ObservableValue;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class Loginpane extends Application{
	//Attributes used for multi-window communication
	private String id;
	private String pw;
	private String type;
	public Connector con;
	public void start(Stage primaryStage){
		//Components of GUI and their settings
		Pane pane = new Pane();
		pane.setMinSize(1280,720);
		Label idLabel = new Label("      ID: ");
		Label pwLabel = new Label("Password: ");
		Label typeLabel = new Label("    Type: ");
		TextField idInput = new TextField();
		PasswordField pwInput = new PasswordField();
		Button logButton = new Button("Login");
		Button regButton = new Button("Register");
		Button matButton = new Button("Forget Password");
		String[] alternatives = {"Teacher","Student"};
		ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(alternatives));
		idLabel.setLayoutX(400);
		idLabel.setLayoutY(50);
		pwLabel.setLayoutX(400);
		pwLabel.setLayoutY(140);
		typeLabel.setLayoutX(400);
		typeLabel.setLayoutY(230);
		idLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		pwLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		typeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		idInput.setLayoutX(700);
		idInput.setLayoutY(50);
		idInput.setMinSize(200,50);
		pwInput.setMinSize(200,50);
		pwInput.setLayoutX(700);
		pwInput.setLayoutY(120);
		logButton.setMinSize(150,75);
		regButton.setMinSize(150,75);
		matButton.setMinSize(200,100);
		logButton.setLayoutX(375);
		logButton.setLayoutY(500);
		regButton.setLayoutX(775);
		regButton.setLayoutY(500);
		matButton.setLayoutX(535);
		matButton.setLayoutY(600);
		logButton.setFont(Font.font("Microsoft Ya Hei", FontWeight.BOLD, FontPosture.REGULAR, 20));
		regButton.setFont(Font.font("Microsoft Ya Hei", FontWeight.BOLD, FontPosture.REGULAR, 20));
		matButton.setFont(Font.font("Microsoft Ya Hei", FontWeight.BOLD, FontPosture.REGULAR, 23));
		cb.setMinSize(200,50);
		cb.setLayoutX(700);
		cb.setLayoutY(200);
		cb.setStyle("-fx-font-size:16");
		cb.getSelectionModel().selectedIndexProperty().addListener(
			(ObservableValue<? extends Number> ov, Number old_val, Number new_val)->{
				type = alternatives[new_val.intValue()];
			}
		);


		//Operations done when registerbutton is clicked
		regButton.setOnAction( e -> {
			//Go to register page and pass through the connector
			Platform.runLater(()->{
				Stage stage = (Stage)regButton.getScene().getWindow();
				stage.hide();
				try{
					Registerpane rp = new Registerpane();
					rp.con = con;
					rp.start(stage);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			});
		});
		//Operations done when maintainbutton is clicked
		matButton.setOnAction( e ->{
			//Go to the reset password page and pass through the connector
			Platform.runLater(()->{
				Stage stage = (Stage)matButton.getScene().getWindow();
				stage.hide();
				try{
					Resetpane rp = new Resetpane();
					rp.con = con;
					rp.start(stage);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			});
		});
		//Operations done when loginbutton is clicked
		logButton.setOnAction( e ->{
			//Get the input id and password
			id = idInput.getText();
			pw = pwInput.getText();
			//Check the correction of input
			boolean check = con.login(type,id,pw);
			if(check){
				//Jump to the corresponding pages of students and teachers 
				Platform.runLater(()->{
				    Stage stage = (Stage)logButton.getScene().getWindow();
				    stage.hide();
				    try{
				    	if(type.equals("Teacher")){
				    		Teachwidget mt = new Teachwidget();
				    		mt.con = con;
					        mt.id=id;
					        mt.start(stage);
				    	}
				    	else{
				    		Studentwidget mt = new Studentwidget();
				    		mt.con = con;
					        mt.id=id;
					        mt.start(stage);
				    	}
				    }catch(Exception ex){
					    ex.printStackTrace();
				    }
			    });
			}
			else{
				//Check failed, set the prompttext
				//Give alert 
				idInput.clear();
				idInput.setPromptText("Failed to login");
				pwInput.clear();
				pwInput.setPromptText("Failed to login");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Fail to login!");
                alert.showAndWait();
			}
		});
		
		//Add the conponents to the pane
		pane.getChildren().add(idLabel);
		pane.getChildren().add(pwLabel);
		pane.getChildren().add(idInput);
		pane.getChildren().add(pwInput);
		pane.getChildren().add(logButton);
		pane.getChildren().add(regButton);
		pane.getChildren().add(typeLabel);
		pane.getChildren().add(cb);
		pane.getChildren().add(matButton);
		Scene sc = new Scene(pane);
		primaryStage.setScene(sc);
		primaryStage.setTitle("Log in");
		primaryStage.show();
	} 
}