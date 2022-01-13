//Widget for user to register


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
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.application.Platform;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.application.Platform;
public class Registerpane extends Application{
	//Attributs used for multi-window communication
	private int department;
	private String id;
	private String password;
	private String type;
	public Connector con;
	public void start(Stage primaryStage) throws Exception{
		//Basic components of GUI and settings
		Pane pane = new Pane();
		pane.setMinSize(1280,720);
		Label idLabel = new Label("      ID: ");
		Label pwLabel = new Label("Password: ");
		Label dpLabel = new Label("   Dept.: ");
		Label tpLabel = new Label("    Type: ");
		TextField idInput = new TextField();
		Text tx = new Text("Status");
		PasswordField pwInput = new PasswordField();
		String[] alternatives = {"Industrial Engineering","Department of Automation",
		"School of Software","Computer Science",
		"School of Economics and Management","Chemistry",
		"XinYa College"};
		ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(alternatives));
		String[] types = {"Teacher","Student"};
		ChoiceBox<String> cb2 = new ChoiceBox<String>(FXCollections.observableArrayList(types));
		Button matButton = new Button("Register ");
		Button backButton = new Button("Back to Login");
		backButton.setMinSize(200,50);
		backButton.setLayoutX(150);
		backButton.setLayoutY(550);
		backButton.setStyle("-fx-font-size:16");
		tx.setFont(Font.font("Microsoft Ya Hei", FontWeight.BOLD, FontPosture.ITALIC, 44));
		tx.setLayoutY(425);
		tx.setLayoutX(550);
		tx.setFill(Color.rgb(102,8,116));
		idLabel.setLayoutX(400);
		idLabel.setLayoutY(50);
		pwLabel.setLayoutX(400);
		pwLabel.setLayoutY(120);
		dpLabel.setLayoutX(400);
		dpLabel.setLayoutY(190);
		tpLabel.setLayoutX(400);
		tpLabel.setLayoutY(260);
		idLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		pwLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		dpLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		tpLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		idInput.setLayoutX(700);
		idInput.setLayoutY(50);
		idInput.setMinSize(200,50);
		pwInput.setMinSize(200,50);
		pwInput.setLayoutX(700);
		pwInput.setLayoutY(120);
		matButton.setMinSize(200,100);
		matButton.setLayoutX(535);
		matButton.setLayoutY(550);
		matButton.setFont(Font.font("Microsoft Ya Hei", FontWeight.BOLD, FontPosture.REGULAR, 23));
		cb.setMinSize(200,50);
		cb.setLayoutX(700);
		cb.setLayoutY(190);
		cb.setStyle("-fx-font-size:16");
		cb.getSelectionModel().selectedIndexProperty().addListener(
			(ObservableValue<? extends Number> ov, Number old_val, Number new_val)->{
				department = new_val.intValue();
			}
		);
		cb2.setMinSize(200,50);
		cb2.setLayoutX(700);
		cb2.setLayoutY(260);
		cb2.setStyle("-fx-font-size:16");
		cb2.getSelectionModel().selectedIndexProperty().addListener(
			(ObservableValue<? extends Number> ov, Number old_val, Number new_val)->{
				type = types[new_val.intValue()];
			}
		);


		//Operations done when registerbutton is clicked
		matButton.setOnAction(e -> {
			//Get the ipnput content
			id = idInput.getText();
			password = pwInput.getText();
			try{
				//Register the new user to the system
				//Use text to represent the status of system
				int temp = con.register(type,id,department,password);
			    if(temp == 1){
			    	//Temp == 1 for success, vice versa
				    tx.setText("Success");
				    tx.setFill(Color.rgb(0,255,0));
			    }
			    else{
				    tx.setText("Failed");
				    tx.setFill(Color.rgb(255,0,0));
			    }
			} catch(Exception ex){
				ex.printStackTrace();
			}	
		});
		//Operations done when backbutton is clicked
		backButton.setOnAction( e-> {
			//Jump back to login page and pass through the connector
			Platform.runLater(()->{
				Stage stage = (Stage)backButton.getScene().getWindow();
				stage.hide();
				try{
					Loginpane lp = new Loginpane();
					lp.con = con;
					lp.start(stage);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			});
		});


		
		//Add the components to the pane
		pane.getChildren().add(idLabel);
		pane.getChildren().add(pwLabel);
		pane.getChildren().add(idInput);
		pane.getChildren().add(pwInput);
		pane.getChildren().add(matButton);
		pane.getChildren().add(dpLabel);
		pane.getChildren().add(cb);
		pane.getChildren().add(cb2);
		pane.getChildren().add(tpLabel);
		pane.getChildren().add(tx);
		pane.getChildren().add(backButton);
		Scene sc = new Scene(pane);
		primaryStage.setScene(sc);
		primaryStage.setTitle("Configuration");
		primaryStage.show();
	} 
}