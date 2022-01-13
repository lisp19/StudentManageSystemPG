//Widget of configuration for local setup
//You need to configure your port, username and password of PG
//This is not the login pane


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
public class Configurepane extends Application{
	//Attributes used to communicate betweenn widgets 
	private String port;
	private String user;
	private String password;
	public Connector con;
	public void start(Stage primaryStage){
		//Default parameters
		port = "5433";
		user = "postgres";
		password = "0000";

		//Components of GUI and their setup
		Pane pane = new Pane();
		pane.setMinSize(1280,720);
		Label idLabel = new Label("    User: ");
		Label pwLabel = new Label("Password: ");
		Label ptLabel = new Label("    Port: ");
		TextField idInput = new TextField();
		Text tex = new Text("This page is for local configuration.\r\n\r\n"+
			"Plz enter your local port, User name and password.\r\n\r\n" + 
			"If connection is established successfully, you can login then.");
		tex.setTextAlignment(TextAlignment.CENTER);
		PasswordField pwInput = new PasswordField();
		TextField ptInput = new TextField();
		Button matButton = new Button("Configure ");
		tex.setFont(Font.font("Microsoft Ya Hei", FontWeight.BOLD, FontPosture.ITALIC, 28));
		tex.setLayoutY(325);
		tex.setLayoutX(225);
		idLabel.setLayoutX(400);
		idLabel.setLayoutY(50);
		pwLabel.setLayoutX(400);
		pwLabel.setLayoutY(120);
		ptLabel.setLayoutX(400);
		ptLabel.setLayoutY(190);
		idLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		pwLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		ptLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		idInput.setLayoutX(700);
		idInput.setLayoutY(50);
		idInput.setMinSize(200,50);
		idInput.setPromptText("Default: postgres");
		pwInput.setMinSize(200,50);
		pwInput.setLayoutX(700);
		pwInput.setLayoutY(120);
		ptInput.setLayoutX(700);
		ptInput.setLayoutY(190);
		ptInput.setMinSize(200,50);
		ptInput.setPromptText("Default: 5433");
		matButton.setMinSize(200,100);
		matButton.setLayoutX(535);
		matButton.setLayoutY(600);
		matButton.setFont(Font.font("Microsoft Ya Hei", FontWeight.BOLD, FontPosture.REGULAR, 23));

		//Operations done when configure button is clicked
		matButton.setOnAction(e -> {
			//Get the information input
			//If it is null, use the default
			if(!ptInput.getText().trim().equals("")){
				port = ptInput.getText();
			}
			if(!idInput.getText().trim().equals("")){
				user = idInput.getText();
			}
			if(!pwInput.getText().trim().equals("")){
				password = pwInput.getText();
			}
			//Constructe a connector and pass through the next widget
			con = new Connector(port,user,password);
			Platform.runLater(()->{
				Stage stage = (Stage)matButton.getScene().getWindow();
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

		//Add the components to the widget
		pane.getChildren().add(idLabel);
		pane.getChildren().add(pwLabel);
		pane.getChildren().add(idInput);
		pane.getChildren().add(pwInput);
		pane.getChildren().add(matButton);
		pane.getChildren().add(ptLabel);
		pane.getChildren().add(ptInput);
		pane.getChildren().add(tex);
		Scene sc = new Scene(pane);
		primaryStage.setScene(sc);
		primaryStage.setTitle("Configuration");
		primaryStage.show();
	} 
}