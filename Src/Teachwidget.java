//Widget for teachers
//Used to comment on applications, verify applications
//And view applications under each state



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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory; 
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class Teachwidget extends Application{
	//Attributes for multi-window communictaion
	public Connector con;
	public String id;
	String statusChosen = "pending";
	String typeChosen = "pending";
	public void start(Stage primarystage){
		//Basic components of GUI and their settings
		Pane pane = new Pane();
		pane.setMinSize(1280,720);
		Button commentButton = new Button("Comment");
		Button processButton = new Button("Verify");
		Button viewButton = new Button("View");
		Button refreshButton = new Button("Refresh");
		TextField comnumber = new TextField();
		TextField comcontent = new TextField();
		TextField appnumber = new TextField();
		String[] status = {"passed","refused"};
		String[] status2 = {"passed","refused","pending","cancelled"};
		ChoiceBox<String> verifyChoice = new ChoiceBox<String>(FXCollections.observableArrayList(status));
		ChoiceBox<String> viewChoice = new ChoiceBox<String>(FXCollections.observableArrayList(status2));
		Label numberLabel = new Label("Number: ");
		Label commentLabel = new Label("Comment: ");
		Label verifyLabel = new Label("Number: ");
		Label statusLabel = new Label("Status: ");
		Label viewLabel = new Label("Status Choice: ");
		ObservableList<stuApplication> list = FXCollections.observableArrayList();
		list.add(new stuApplication(1,"2021-6-25 14:13:00","2021-6-25 19:13:00",
			"pending","2019011404","2019011404","NO"));
		TableView data = new TableView();
		data.setEditable(false);
		TableColumn idCol = new TableColumn("ID");
		idCol.setPrefWidth(60);
		idCol.setCellValueFactory(
			new PropertyValueFactory<stuApplication,String>("ID")
		);
		TableColumn statCol = new TableColumn("stat");
		statCol.setPrefWidth(190);
		statCol.setCellValueFactory(
			new PropertyValueFactory<stuApplication,String>("stat")
		);
		TableColumn endCol = new TableColumn("end");
		endCol.setPrefWidth(190);
		endCol.setCellValueFactory(
			new PropertyValueFactory<stuApplication,String>("end")
		);
		TableColumn durationCol = new TableColumn("duration");
		durationCol.setPrefWidth(190);
		durationCol.setCellValueFactory(
			new PropertyValueFactory<stuApplication,Integer>("duration")
		);
		TableColumn statusCol = new TableColumn("status");
		statusCol.setPrefWidth(190);
		statusCol.setCellValueFactory(
			new PropertyValueFactory<stuApplication,String>("status")
		);
		TableColumn reasonCol = new TableColumn("reason");
		reasonCol.setPrefWidth(190);
		reasonCol.setCellValueFactory(
			new PropertyValueFactory<stuApplication,String>("reason")
		);
		TableColumn initiatorCol = new TableColumn("initiator");
		initiatorCol.setPrefWidth(190);
		initiatorCol.setCellValueFactory(
			new PropertyValueFactory<stuApplication,String>("initiator")
		);
		data.setItems(list);
		data.getColumns().addAll(idCol,statCol,endCol,durationCol,statusCol,reasonCol,initiatorCol);
		data.setMinSize(1200,300);
		data.setMaxSize(1200,300);
		data.setLayoutX(40);
		data.setLayoutY(50);
		data.setStyle("-fx-font-size:16");
		numberLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		commentLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		verifyLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		statusLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		viewLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		numberLabel.setLayoutY(400);
		numberLabel.setLayoutX(50);
		comnumber.setLayoutY(400);
		comnumber.setLayoutX(200);
		comnumber.setMinSize(200,40);
		commentLabel.setLayoutX(425);
		commentLabel.setLayoutY(400);
		comcontent.setLayoutX(550);
		comcontent.setLayoutY(400);
		comcontent.setMinSize(550,40);
		commentButton.setLayoutX(1150);
		commentButton.setLayoutY(400);
		commentButton.setMinSize(120,40);
		commentButton.setStyle("-fx-font-size:16");
		verifyLabel.setLayoutX(50);
		verifyLabel.setLayoutY(500);
		appnumber.setLayoutX(200);
		appnumber.setLayoutY(500);
		appnumber.setMinSize(200,40);
		statusLabel.setLayoutX(500);
		statusLabel.setLayoutY(500);
		verifyChoice.setStyle("-fx-font-size:16");
		verifyChoice.setMinSize(300,40);
		verifyChoice.setLayoutX(630);
		verifyChoice.setLayoutY(500);
		processButton.setMinSize(120,40);
		processButton.setStyle("-fx-font-size:16");
		processButton.setLayoutX(1000);
		processButton.setLayoutY(500);
		viewLabel.setLayoutX(50);
		viewLabel.setLayoutY(600);
		viewChoice.setMinSize(300,40);
		viewChoice.setStyle("-fx-font-size:16");
		viewChoice.setLayoutX(300);
		viewChoice.setLayoutY(600);
		viewButton.setMinSize(120,40);
		viewButton.setStyle("-fx-font-size:16");
		viewButton.setLayoutX(700);
		viewButton.setLayoutY(600);
		refreshButton.setMinSize(150,40);
		refreshButton.setStyle("-fx-font-size:18");
		refreshButton.setLayoutX(1020);
		refreshButton.setLayoutY(600);
		verifyChoice.getSelectionModel().selectedIndexProperty().addListener(
			(ObservableValue<? extends Number> ov, Number old_val, Number new_val)->{
				statusChosen = status[new_val.intValue()];
			}
		);
		viewChoice.getSelectionModel().selectedIndexProperty().addListener(
			(ObservableValue<? extends Number> ov, Number old_val, Number new_val)->{
				typeChosen = status2[new_val.intValue()];
			}
		);


		//Operations done when refreshbutton is clicked
		refreshButton.setOnAction( e -> {
			//Call the method and get the application of the department 
			//Under all status
			//Show on the table and refresh the page
			ArrayList<stuApplication> temp = con.teacherView(id);
			stuApplication[] resultList = temp.toArray(new stuApplication[temp.size()]);
			data.setItems(FXCollections.observableArrayList(resultList));
		});
		//Operations done when commentbutton is clicked
		commentButton.setOnAction( e -> {
			//Get the information input
			int comid = Integer.parseInt(comnumber.getText().trim());
			String content = comcontent.getText();
			//Call the method
			int temp = con.makeComment(id,comid,content);
			//temp == 1 for normal
			//Use information popup
			if(temp == 1){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The comment is successfully submitted.");
                alert.showAndWait();
			}
			//For abnormal , use error popup
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Unknown ERROR occurs!");
                alert.showAndWait();
			}
		});
		//Operations done when verify button is clicked
		processButton.setOnAction( e -> {
			//Get the input and call the method
			int tempid = Integer.parseInt(appnumber.getText().trim());
			int temp = con.verify(tempid,statusChosen,id);
			if(temp == 1){
				//temp == -1 for nomal
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The application is successfully verified.");
                alert.showAndWait();
			}
			//temp == -2 for verify a processed or cancelled application
			else if (temp == -2){
			    Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The application has already been cancelled or processed!");
                alert.showAndWait();
			}
			//Error
		    else{
			    Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("Unknown ERROR occurs!");
                alert.showAndWait();
			}
		});
		//Operations done when viewbutton is clicked
		viewButton.setOnAction( e -> {
			//Call the method and show on the table, with a given status
			ArrayList<stuApplication> temp = con.selectiveTeacherView(id,typeChosen);
			stuApplication[] resultList = temp.toArray(new stuApplication[temp.size()]);
			data.setItems(FXCollections.observableArrayList(resultList));
		});






		//Add the components to the pane
		pane.getChildren().add(commentButton);
		pane.getChildren().add(processButton);
		pane.getChildren().add(viewButton);
		pane.getChildren().add(refreshButton);
		pane.getChildren().add(comnumber);
		pane.getChildren().add(commentLabel);
		pane.getChildren().add(numberLabel);
		pane.getChildren().add(verifyLabel);
		pane.getChildren().add(statusLabel);
		pane.getChildren().add(viewLabel);
		pane.getChildren().add(data);
		pane.getChildren().add(comcontent);
		pane.getChildren().add(verifyChoice);
		pane.getChildren().add(appnumber);
		pane.getChildren().add(viewChoice);

		Scene sc = new Scene(pane);
		primarystage.setTitle("Mainwidget");
		primarystage.setScene(sc);
		primarystage.show();
	}
}