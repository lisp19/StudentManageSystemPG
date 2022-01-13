//Mainwidget of student 
//Used for apply, revise, cancel the application
//Also for check the comments


import java.time.LocalDate;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
public class Studentwidget extends Application{
	//Attributes to communicate between pages
	public Connector con;
	public String id;
	private String statusChosen;
	public void start(Stage primarystage){
		//Components of GUI
		Pane pane = new Pane();
		pane.setMinSize(1280,720);
		Button applyButton = new Button("Apply");
		Button reviseButton = new Button("Revise");
		Button viewButton = new Button("View");
		Button refreshButton = new Button("Refresh");
		Button cancelButton = new Button("Cancel");
		Button commentButton = new Button("checkcomment");
		TextField statInput = new TextField();
		TextField endInput = new TextField();
		TextField appnumber = new TextField();
		TextField reasonInput = new TextField();
		String[] status = {"passed","refused","pending","cancelled"};		
		ChoiceBox<String> viewChoice = new ChoiceBox<String>(FXCollections.observableArrayList(status));
		Label statLabel = new Label("STAT: ");
		Label endLabel = new Label("END: ");
		Label reasonLabel = new Label("Reason: ");
		Label reviseLabel = new Label("Number: ");
		Label statusLabel = new Label("Operations: ");
		Label viewLabel = new Label("Status Choice: ");
		DatePicker statDatePicker = new DatePicker();
		DatePicker endDatePicker = new DatePicker();
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
		TableColumn processorCol = new TableColumn("processor");
		processorCol.setPrefWidth(190);
		processorCol.setCellValueFactory(
			new PropertyValueFactory<stuApplication,String>("processor")
		);
		data.setItems(list);
		data.getColumns().addAll(idCol,statCol,endCol,durationCol,statusCol,reasonCol,processorCol);
		data.setMinSize(1200,300);
		data.setMaxSize(1200,300);
		data.setLayoutX(40);
		data.setLayoutY(50);
		data.setStyle("-fx-font-size:16");
		statLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		endLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		reviseLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		statusLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		viewLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		reasonLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		statLabel.setLayoutY(400);
		statLabel.setLayoutX(50);
		statInput.setLayoutY(400);
		statInput.setLayoutX(375);
		statInput.setMaxSize(50,50);
		statInput.setMinSize(50,40);
		statInput.setPromptText("time");
		endLabel.setLayoutX(475);
		endLabel.setLayoutY(400);
		endInput.setLayoutX(800);
		endInput.setLayoutY(400);
		endInput.setMaxSize(50,50);
		endInput.setMinSize(50,40);
		endInput.setPromptText("time");
		reasonLabel.setLayoutX(900);
		reasonLabel.setLayoutY(400);
		reasonInput.setMinSize(200,40);
		reasonInput.setLayoutX(1025);
		reasonInput.setLayoutY(400);
		applyButton.setLayoutX(925);
		applyButton.setLayoutY(500);
		applyButton.setMinSize(120,40);
		applyButton.setStyle("-fx-font-size:16");
		commentButton.setMinSize(180,40);
		commentButton.setLayoutX(1075);
		commentButton.setLayoutY(500);
		commentButton.setStyle("-fx-font-size:15");
		reviseLabel.setLayoutX(50);
		reviseLabel.setLayoutY(500);
		appnumber.setLayoutX(200);
		appnumber.setLayoutY(500);
		appnumber.setMinSize(200,40);
		statusLabel.setLayoutX(450);
		statusLabel.setLayoutY(500);
		cancelButton.setMinSize(120,40);
		cancelButton.setLayoutX(600);
		cancelButton.setLayoutY(500);
		cancelButton.setStyle("-fx-font-size:16");
		reviseButton.setMinSize(120,40);
		reviseButton.setStyle("-fx-font-size:16");
		reviseButton.setLayoutX(760);
		reviseButton.setLayoutY(500);
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
		statDatePicker.setMinSize(150,40);
		statDatePicker.setLayoutX(150);
		statDatePicker.setLayoutY(400);
		endDatePicker.setMinSize(150,40);
		endDatePicker.setLayoutX(575);
		endDatePicker.setLayoutY(400);
		viewChoice.getSelectionModel().selectedIndexProperty().addListener(
			(ObservableValue<? extends Number> ov, Number old_val, Number new_val)->{
				statusChosen = status[new_val.intValue()];
			}
		);


		//Operations done when viewbutton is clicked
		viewButton.setOnAction(e -> {
			//Call the selectiveview method of connector to show 
			//the applications under given status
			//Display applications on table
			ArrayList<stuApplication> temp = con.selectiveStudentView(id,statusChosen);
			stuApplication[] resultList = temp.toArray(new stuApplication[temp.size()]);
			data.setItems(FXCollections.observableArrayList(resultList));
		});
		//Operations done when refreshbutton is clicked
		refreshButton.setOnAction(e -> {
			//Call the view method of connector
			//Show the applications under all status and refresh the page
			ArrayList<stuApplication> temp = con.studentView(id);
			stuApplication[] resultList = temp.toArray(new stuApplication[temp.size()]);
			data.setItems(FXCollections.observableArrayList(resultList));
		});
		//Operations done when cancel button is clicked
		cancelButton.setOnAction( e -> {
			//Get the information input and call the method
			int tempid = Integer.parseInt(appnumber.getText().trim());
			int temp = con.cancelApp(tempid);
			//temp==1 means successfully cancelled
			//Use a popup to show
			if(temp == 1){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The application is successfully cancelled.");
                alert.showAndWait();
			}
			//Fail to cancel
			//Use ERROR popup
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The application isn't successfully cancelled.");
                alert.showAndWait();
			}
		});
		//Operations done when applybutton is clicked
		applyButton.setOnAction( e -> {
			//Get the input information and normalization
			String stat = statDatePicker.getValue().toString();
			String hour1 = statInput.getText().trim();
			stat = stat + " " + hour1 + ":00:00";
			String end = endDatePicker.getValue().toString();
			String hour2 = endInput.getText().trim();
			end = end + " " + hour2 + ":00:00";
			String reason = reasonInput.getText();
			//Call the method
			int temp = con.apply(id,stat,end,reason);
			if(temp == 1){
				//temp==1 for success
				//Give popup to show
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The application isn successfully submitted.");
                alert.showAndWait();
			}
			//temp == -1 for more than 3 times per week
			//Give a warning popup
			else if(temp == -1){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("You can only leave 3 times per week!");
                alert.showAndWait();
			}
			//temp == -2 for time overlap
			//Give a warning popup
			else if(temp == -2){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Applications should not overlap!");
                alert.showAndWait();
			}
			//temp == -3 for duration longer than 48 hours or shorter than 0
			//Give a warning popup
			else if(temp == -3){
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Plz check that your application duration is between 1 to 47 hours.");
                alert.showAndWait();
			}
			//For unknown error
			//Give an error popup
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Unkown Error occurs!");
                alert.showAndWait();
			}
		});
		//Operations done when revisebutton is clicked
		reviseButton.setOnAction( e -> {
			//Get the input information
			int appid = Integer.parseInt(appnumber.getText().trim());
			String stat = statDatePicker.getValue().toString();
			String hour1 = statInput.getText().trim();
			stat = stat + " " + hour1 + ":00:00";
			String end = endDatePicker.getValue().toString();
			String hour2 = endInput.getText().trim();
			end = end + " " + hour2 + ":00:00";
			String reason = reasonInput.getText();
			//Call the method
			int temp = con.revise(appid,stat,end,reason);
			//temp == 1 for normal
			if(temp == 1){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("SUCCESS");
                alert.setHeaderText(null);
                alert.setContentText("You successfully revised your application.");
                alert.showAndWait();
			}
			else if(temp == -1){
				//temp == -1 for revise unprocessed application
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("You can only revise application passed or refused!");
                alert.showAndWait();
			}
			else{
				//For unknown error
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("WARNING");
                alert.setHeaderText(null);
                alert.setContentText("Unkown Error occurs!");
                alert.showAndWait();
			}
		});
		//Operations done when commentbutton is clicked
		commentButton.setOnAction( e -> {
			//Get the input information
			int appid = Integer.parseInt(appnumber.getText().trim());
			String result = con.checkComment(appid);
			//Use a popup to show the comments
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Comment");
			alert.setHeaderText(null);
			alert.setContentText(null);
			TextArea texa = new TextArea(result);
			texa.setEditable(false);
			texa.setWrapText(true);
			Pane pane1 = new Pane();
			pane1.getChildren().add(texa);
			alert.getDialogPane().setExpandableContent(pane1);
			alert.showAndWait();
		});






		//Add the components to the pane
		pane.getChildren().add(applyButton);
		pane.getChildren().add(reviseButton);
		pane.getChildren().add(viewButton);
		pane.getChildren().add(refreshButton);
		pane.getChildren().add(statInput);
		pane.getChildren().add(endLabel);
		pane.getChildren().add(statLabel);
		pane.getChildren().add(reviseLabel);
		pane.getChildren().add(statusLabel);
		pane.getChildren().add(viewLabel);
		pane.getChildren().add(data);
		pane.getChildren().add(endInput);
		pane.getChildren().add(cancelButton);
		pane.getChildren().add(appnumber);
		pane.getChildren().add(viewChoice);
		pane.getChildren().add(reasonLabel);
		pane.getChildren().add(reasonInput);
		pane.getChildren().add(statDatePicker);
		pane.getChildren().add(endDatePicker);
		pane.getChildren().add(commentButton);

		Scene sc = new Scene(pane);
		primarystage.setTitle("Mainwidget");
		primarystage.setScene(sc);
		primarystage.show();
	}
}