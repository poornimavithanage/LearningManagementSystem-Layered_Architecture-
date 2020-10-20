package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CourseBO;
import business.custom.FacultyBO;
import business.custom.StudentBO;
import business.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CourseTM;
import util.FacultyTM;
import util.StudentTM;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AdminStudentFormController {
    public AnchorPane root;
    public JFXButton btnDashboard;
    public JFXButton btnCourses;
    public JFXButton btnModules;
    public JFXButton btnAccount;
    public JFXButton btnStudent;
    public JFXButton btnLecturer;
    public JFXButton btnAdd;
    public JFXTextField txtId;
    public JFXTextField txtTel;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtPassword;
    public JFXTextField txtUsername;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public JFXTextField txtEmail;
    public TableView<StudentTM> tblAdminStudent;
    public JFXComboBox<FacultyTM> cmbFacultyId;
    public JFXTextField txtFacultyName;
    public JFXTextField txtNIC;
    private boolean readOnly;

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    private FacultyBO facultyBO = BOFactory.getInstance().getBO(BOType.FACULTY);
    private UserBO userBO= BOFactory.getInstance().getBO(BOType.USER);

    public void initialize(){

        readOnly = false;

        //Basic Initialization
        disableFields();

        //load all students
        loadAllStudents();


        //load all faculties
        loadAllFaculties();

        //when admin select facultyId
        cmbFacultyId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FacultyTM>() {
            @Override
            public void changed(ObservableValue<? extends FacultyTM> observable, FacultyTM oldValue, FacultyTM newValue) {
                if(newValue==null){
                    txtFacultyName.clear();
                    return;
                }
                txtFacultyName.setText(newValue.getName());
                btnSave.setDisable(false);
            }
        });

        tblAdminStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblAdminStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAdminStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblAdminStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblAdminStudent.getColumns().get(0).setStyle("-fx-alignment: center");
        tblAdminStudent.getColumns().get(1).setStyle("-fx-alignment: center");
        tblAdminStudent.getColumns().get(2).setStyle("-fx-alignment: center");
        tblAdminStudent.getColumns().get(3).setStyle("-fx-alignment: center");

        btnSave.setDisable(true);

        tblAdminStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentTM>() {
            @Override
            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM newValue) {
                StudentTM selectedStudentDetails = tblAdminStudent.getSelectionModel().getSelectedItem();

                if(selectedStudentDetails==null){
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    txtId.clear();
                    txtName.clear();
                    txtAddress.clear();
                    txtTel.clear();
                    txtEmail.clear();
                    txtPassword.clear();
                    txtUsername.clear();
                    txtNIC.clear();
                    cmbFacultyId.getSelectionModel().clearSelection();
                    return;
                }

                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                cmbFacultyId.setDisable(false);
                txtFacultyName.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtNIC.setDisable(false);
                txtTel.setDisable(false);
                txtEmail.setDisable(false);
                txtUsername.setDisable(false);
                txtPassword.setDisable(true);
                txtId.setText(selectedStudentDetails.getId());
                txtName.setText(selectedStudentDetails.getName());
                txtAddress.setText(selectedStudentDetails.getAddress());
                txtNIC.setText(selectedStudentDetails.getNic());
                txtTel.setText(selectedStudentDetails.getContact());
                txtEmail.setText(selectedStudentDetails.getEmail());

                try {
                    txtPassword.setText(userBO.getUser(studentBO.getUserId(selectedStudentDetails.getId())).getPassword());
                    txtUsername.setText(userBO.getUser(studentBO.getUserId(selectedStudentDetails.getId())).getUsername());
                } catch (Exception e) {
                    e.printStackTrace();
                }



//                txtPassword.setText(selectedStudentDetails.getPassword());
//                txtUsername.setText(selectedStudentDetails.getUsername());

                String selectedFacultyId = selectedStudentDetails.getFacultyId();
                ObservableList<FacultyTM>faculties = cmbFacultyId.getItems();
                for (FacultyTM faculty :faculties) {
                    if(faculty.getId().equals(selectedFacultyId)){
                        cmbFacultyId.getSelectionModel().select(faculty);
                        txtFacultyName.setText(faculty.getName());
                        if(!readOnly){
                            btnSave.setText("Update");
                        }
                        if(readOnly){
                            btnSave.setDisable(true);
                        }
                        cmbFacultyId.setDisable(false);
                        Platform.runLater(()->{
                            txtName.requestFocus();
                        });
                        break;
                    }
                }
            }
        });

        txtUsername.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                txtPassword.setText(generatePassword(8));
            }
        });
    }

    public void btnDashboard_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminDashboard.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnCourses_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminCoursesForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnModules_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminModuleForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnAccount_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminAccountForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnStudent_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminStudentForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnLecturer_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminLecturerForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    private void loadAllFaculties() {
        cmbFacultyId.getItems().clear();
        try {
            cmbFacultyId.setItems(FXCollections.observableArrayList(facultyBO.getAllFaculties()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudents() {

        tblAdminStudent.getItems().clear();
        List<StudentTM>allStudents = null;
        try {
            allStudents = studentBO.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<StudentTM>students = FXCollections.observableArrayList(allStudents);
        tblAdminStudent.setItems(students);

    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this student?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            StudentTM selectedItem = tblAdminStudent.getSelectionModel().getSelectedItem();

            boolean result = false;
            try {
                result = studentBO.deleteStudent(selectedItem.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result){
                new Alert(Alert.AlertType.ERROR, "Failed to delete the student", ButtonType.OK).show();
            }else{
                tblAdminStudent.getItems().remove(selectedItem);
                tblAdminStudent.getSelectionModel().clearSelection();
            }
        }
    }

    public void btnSave_OnAction(ActionEvent event) throws Exception {

        String facultyId = String.valueOf(cmbFacultyId.getSelectionModel().getSelectedItem());
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtTel.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String nic = txtNIC.getText();
        String email = txtEmail.getText();

        //validation
        if(facultyId.trim().length()==0 ||
        name.trim().length()==0 || address.trim().length()==0 ||
        contact.trim().length()==0|| username.trim().length()==0 ||
                password.trim().length()==0 || nic.trim().length()==0 || email.trim().length()==0){
            new Alert(Alert.AlertType.ERROR,"Can not be empty values", ButtonType.OK).show();
            return;
        }

        StudentTM selectedStudent = tblAdminStudent.getSelectionModel().getSelectedItem();
        if(btnSave.getText().equals("Save")){
            try {
                userBO.save(studentBO.getUserId(selectedStudent.getId()),txtUsername.getText(),txtPassword.getText(),"Student");

                studentBO.saveStudent(txtId.getText(),
                        String.valueOf(cmbFacultyId.getSelectionModel().getSelectedItem()),
                        txtName.getText(),txtAddress.getText(),
                        txtTel.getText(),txtNIC.getText(),txtEmail.getText(),studentBO.getUserId(selectedStudent.getId()));
                new Alert(Alert.AlertType.INFORMATION,"Student saved successfully",ButtonType.OK).showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            btnAdd_OnAction(event);
        }else{
            userBO.update(studentBO.getUserId(selectedStudent.getId()),txtUsername.getText(),txtPassword.getText(),"Student");
            boolean result = false;
            try {
                result = studentBO.updateStudent(String.valueOf(cmbFacultyId.getSelectionModel().getSelectedItem()),
                        txtName.getText(),txtAddress.getText(),txtTel.getText(),txtUsername.getText(),
                        txtPassword.getText(),txtNIC.getText(),txtEmail.getText(),selectedStudent.getId(),studentBO.getUserId(selectedStudent.getId()));

                new Alert(Alert.AlertType.INFORMATION,"User updated successfully",ButtonType.OK).showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(!result){
                new Alert(Alert.AlertType.ERROR, "Failed to update the student form", ButtonType.OK).show();
            }
            tblAdminStudent.refresh();
            btnAdd_OnAction(event);
        }
        loadAllStudents();


    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {

        clearFields();
        enableFields();
        cmbFacultyId.getSelectionModel().clearSelection();
        tblAdminStudent.getSelectionModel().clearSelection();

        // Generate a new id
        try {
            txtId.setText(studentBO.getNewStudentId());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void enableFields() {
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        cmbFacultyId.setDisable(false);
        txtNIC.setDisable(false);
        txtEmail.setDisable(false);
        txtTel.setDisable(false);
        txtUsername.setDisable(false);
        txtPassword.setDisable(false);
        txtFacultyName.setDisable(false);
        btnSave.setDisable(false);
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtEmail.clear();
        txtTel.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtFacultyName.clear();
    }

    private void disableFields() {
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtTel.setDisable(true);
        txtEmail.setDisable(true);
        txtUsername.setDisable(true);
        txtPassword.setDisable(true);
        txtNIC.setDisable(true);
        cmbFacultyId.setDisable(true);
        txtFacultyName.setDisable(true);
    }


    //generate password
    private static String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return String.valueOf(password);
    }
}
