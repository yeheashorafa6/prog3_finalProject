/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.Booked_Appointment;
import Model.User;
import View.PatientPage;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AdminDashboardPageController implements Initializable {

    @FXML
    private TableView<User> patientsTableView;
    @FXML
    private TableColumn<User, Integer> tvID;
    @FXML
    private TableColumn<User, String> tvUserName;
    @FXML
    private TableColumn<User, String> tvFirstName;
    @FXML
    private TableColumn<User, String> tvLastName;
    @FXML
    private TableColumn<User, Integer> tvAge;
    @FXML
    private TableColumn<User, String> tvEmail;
    @FXML
    private TableColumn<User, String> tvPhone;
    @FXML
    private TableColumn<User, String> tvGender;
    @FXML
    private TableColumn<User, String> tvRole;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnShowAllBookedAppointments;
    @FXML
    private Button btnDelelte;
    @FXML
    private Button btnShowAllFreeAppointment;
    @FXML
    private Button btnShowallRegisterdPatientsInTheSystem;
    @FXML
    private TextField txtSearchInPatients;
    @FXML
    private Button btnSearchInPatients;
    @FXML
    private Button btnSearchInBookedAppointments;
    @FXML
    private TextField txtSearchinbookedappointments;
    @FXML
    private Button btnCreateNewPatient;
    @FXML
    private Button btnUpdataPatient;
    @FXML
    private TableView<Appointment> freeTableView;
    @FXML
    private TableColumn<Appointment, Integer> idCol;
    @FXML
    private TableColumn<Appointment, String> appDateCol;
    @FXML
    private TableColumn<Appointment, String> appDayCol;
    @FXML
    private TableColumn<Appointment, String> appTimeCol;
    @FXML
    private TableColumn<Appointment, String> statusCol;
    @FXML
    private Button btnCreatNewAppointment;
    @FXML
    private Button btnUpdateAppointment;
    @FXML
    private Button btnDeleteAppointment;
    @FXML
    private TableView<Booked_Appointment> bookedTableView;
    @FXML
    private TableColumn<Booked_Appointment, Integer> idBookedCol;
    @FXML
    private TableColumn<Booked_Appointment, Integer> userIdCol;
    @FXML
    private TableColumn<Booked_Appointment, Integer> appIdCol;
    @FXML
    private TableColumn<Booked_Appointment, String> statusBookedCol;
    @FXML
    private TableColumn<Booked_Appointment, String> docCommentCol;
    @FXML
    private Button btnLeaveComment;
    @FXML
    private Button btnMode;
    private boolean isLightMode=true;
    @FXML
    private AnchorPane parent;
    @FXML
    private ImageView imgMode;
    @FXML
    private Label mode;
    @FXML
    private Button btnLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // To make Maping between (patientsTableView column's) and (User) object attributes
        tvID.setCellValueFactory(new PropertyValueFactory("id"));
        tvUserName.setCellValueFactory(new PropertyValueFactory("username"));
        tvFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        tvLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tvAge.setCellValueFactory(new PropertyValueFactory("age"));
        tvEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tvPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        tvGender.setCellValueFactory(new PropertyValueFactory("gender"));
        tvRole.setCellValueFactory(new PropertyValueFactory("role"));
        
        // To make Maping between (freeTableView column's) and (Appointment) object attributes
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        appDateCol.setCellValueFactory(new PropertyValueFactory("appointment_date"));
        appDayCol.setCellValueFactory(new PropertyValueFactory("appointment_day"));
        appTimeCol.setCellValueFactory(new PropertyValueFactory("appointment_time"));
        statusCol.setCellValueFactory(new PropertyValueFactory("status"));
        
        // To make Maping between (bookedTableView column's) and (Booked_Appointment) object attributes
        idBookedCol.setCellValueFactory(new PropertyValueFactory("id"));
        userIdCol.setCellValueFactory(new PropertyValueFactory("user_id"));
        appIdCol.setCellValueFactory(new PropertyValueFactory("appointment_id"));
        statusBookedCol.setCellValueFactory(new PropertyValueFactory("status"));
        docCommentCol.setCellValueFactory(new PropertyValueFactory("doctor_commnet"));
    }

    @FXML
    private void ShowAllBookedAppointments(ActionEvent event) throws SQLException, ClassNotFoundException {
        ArrayList<Booked_Appointment> allBookedAppointments = Booked_Appointment.getAllBookedAppointments();
        
        ObservableList<Booked_Appointment> bookedAppointmentList = 
                FXCollections.observableArrayList(allBookedAppointments);
        bookedTableView.setItems(bookedAppointmentList);
    }

    @FXML
    private void ShowAllFreeAppointment(ActionEvent event) throws SQLException, ClassNotFoundException {
        ArrayList<Appointment> freeAppointments = new ArrayList();
        ArrayList<Appointment> allAppointments = Appointment.getAllAppointments();
        for(Appointment a: allAppointments){
            if(a.getStatus().equals("free")){
                freeAppointments.add(a);
            }
        }
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList(freeAppointments);
        freeTableView.setItems(appointmentList);
    }

    @FXML
    private void ShowallRegisterdPatientsInTheSystem(ActionEvent event) throws SQLException, ClassNotFoundException {
        ArrayList<User> allUsers = User.getAllUsers();
        
        ArrayList<User> allPatientsOnly = new ArrayList<>();
        for(User u: allUsers){
            if(u.getRole().equals("patient"))
                allPatientsOnly.add(u);
        }
        ObservableList<User> allPatientsToView = FXCollections.observableArrayList(allPatientsOnly);
        patientsTableView.setItems(allPatientsToView);
    }

    @FXML
    private void Searchinbookedappointments(ActionEvent event) {
    }

    @FXML
    private void createNewPatient(ActionEvent event) {
    }

    @FXML
    private void updatePatient(ActionEvent event) {
    }

    @FXML
    private void deleltePatient(ActionEvent event) {
    }

    @FXML
    private void searchInPatients(ActionEvent event) throws SQLException, ClassNotFoundException {
        String patientToSearch = txtSearchInPatients.getText();  // patient first name to be searched in patients table
        if(patientToSearch.equals("") || patientToSearch == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setContentText("You should enter the first name of patient you would search in !!");
            alert.showAndWait();
        }else{
            ObservableList<User> patientList
                    = FXCollections.observableArrayList(User.searchInPatientsByFirstName(patientToSearch));
            if(patientList.size() > 0)
                patientsTableView.setItems(patientList);
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Not Found");
                alert.setContentText("Patient you search in not found");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void searchInBookedAppointments(ActionEvent event) throws SQLException, ClassNotFoundException {
        String patientFirstName = txtSearchinbookedappointments.getText();  // patient first name to be searched in booked_Appointments table
        if(patientFirstName.equals("") || patientFirstName == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setContentText("You should enter the first name of patient you would search in !!");
            alert.showAndWait();
        }else{
//            ArrayList<User> allPatientsByFirstName = User.searchInPatientsByFirstName(patientFirstName);
            ObservableList<Booked_Appointment> bookedAppointmentList
            = FXCollections.observableArrayList(Booked_Appointment.searchInBookedAppointmentsByPatientFirstName(patientFirstName));
            if(bookedAppointmentList.size() > 0)
                bookedTableView.setItems(bookedAppointmentList);
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Not Found");
                alert.setContentText("This patient '" + patientFirstName + "' has no booked appointments");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void creatNewAppointment(ActionEvent event) {
    }

    @FXML
    private void updateAppointment(ActionEvent event) {
    }

    @FXML
    private void deleteAppointment(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAdminLoginPage();
    }

    @FXML
    private void leaveCommentAndFinish(ActionEvent event) throws SQLException, ClassNotFoundException {
        Booked_Appointment selectedBookedAppointment = bookedTableView.getSelectionModel().getSelectedItem();
        if(selectedBookedAppointment != null){ 
            if(selectedBookedAppointment.getStatus().equals("waiting")){  // if status of booked-appointment is 'waiting' do the following
                TextInputDialog inputdialog = new TextInputDialog("no-comment"); // no-comment is a defualt value of inputdialog
                inputdialog.setHeaderText("Please give some comments to this patient");
                inputdialog.setContentText("leave your Comment: ");
                boolean isPressedOk = inputdialog.showAndWait().isPresent(); // if user press ok will return 'true' overwise will return 'false'

                if(isPressedOk){
                    String drComment = inputdialog.getEditor().getText();

                    selectedBookedAppointment.setDoctor_commnet(drComment);
                    selectedBookedAppointment.setStatus("finished");
                    selectedBookedAppointment.update();
                }else{
                    System.out.println("Operation is canceled");
                }
                ShowAllBookedAppointments(event); //ShowAllBookedAppointments(new ActionEvent());
            }else{   // if status of booked-appointment is 'finished' do the following
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Selection Error");
                alert.setContentText("This booked-appointment is already finished and has doctor comment");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Error");
            alert.setContentText("You should select a booked appointment to leave your comment !!");
            alert.showAndWait();
        }  
    }

 
    
    private void setLightMode(){
        parent.getStylesheets().remove("/css/admindashboardPageDarkMode.css");
        parent.getStylesheets().add("/css/admindashboardPageLightMode.css");
        Image image=new Image("/image/dark.png");
        imgMode.setImage(image);
        mode.setText("Dark Mode");

        isLightMode=true;
                

    }
    private void setDarkMode(){
        parent.getStylesheets().remove("/css/admindashboardPageLightMode.css");
        parent.getStylesheets().add("/css/admindashboardPageDarkMode.css");
        Image image=new Image("/image/light.png");
        imgMode.setImage(image);
                mode.setText("Light Mode");

        isLightMode=false;
    }

    @FXML
    private void showBtnMode(ActionEvent event) {
        isLightMode=!isLightMode;
        if (isLightMode) {
            setLightMode();
        }else{
            setDarkMode();
        }
    }

    @FXML
    private void showBtnlogin(ActionEvent event) {
    }

}
