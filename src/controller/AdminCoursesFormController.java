package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CourseBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CourseTM;

import java.util.List;
import java.util.Optional;

public class AdminCoursesFormController {
    public AnchorPane root;
    public ListView<CourseTM> lstCourses;
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtDuration;
    public JFXComboBox cmbType;
    public JFXButton btnSave;

    private CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    public void initialize(){
        loadComboType();
        loadAllCourses();
        disableFields();

        lstCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM newValue) {
                enableFields();
                btnSave.setText("Update");
                txtId.setText(newValue.getId());
                txtTitle.setText(newValue.getTitle());
                cmbType.setPromptText(newValue.getType());
                txtDuration.setText(newValue.getDuration());
            }
        });
    }

    public void btnDashboard_OnAction(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminDashboard.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnCourses_OnAction(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminCoursesForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnModules_OnAction(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminModuleForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnAccount_OnAction(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminAccountForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnStudents_OnAction(ActionEvent actionEvent) throws Exception {
            Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminStudentForm.fxml"));
            Scene mainScene =  new Scene(root);
            Stage mainStage = (Stage)this.root.getScene().getWindow();
            mainStage.setScene(mainScene);
            mainStage.centerOnScreen();
    }

    public void btnLecturers_OnAction(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminLecturerForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String title = txtTitle.getText();
        String type = String.valueOf(cmbType.getSelectionModel().getSelectedItem());
        String duration = txtDuration.getText();

        // Validation
        if (title.trim().length() == 0 || type.trim().length() == 0 || duration.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Course title, type, duration can't be empty", ButtonType.OK).show();
            return;
        }

        try {
            if (btnSave.getText().equals("Save")) {
                courseBO.saveCourseDetails(txtId.getText(),txtTitle.getText(),
                        String.valueOf(cmbType.getSelectionModel().getSelectedItem()),
                        txtDuration.getText());
                new Alert(Alert.AlertType.INFORMATION,"Course Details saved successfully",ButtonType.OK).showAndWait();
                clear();
                loadAllCourses();
            } else {
                CourseTM selectedItem = lstCourses.getSelectionModel().getSelectedItem();
                boolean result = courseBO.updateCourse(selectedItem.getId(),
                        String.valueOf(cmbType.getSelectionModel().getSelectedItem()),
                        txtDuration.getText(), txtId.getText());
                if  (!result) {
                    new Alert(Alert.AlertType.ERROR, "Failed to update the course", ButtonType.OK).show();
                }
                clear();
                loadAllCourses();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure whether you want to delete this course?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                CourseTM selectedItem = lstCourses.getSelectionModel().getSelectedItem();

                boolean result = courseBO.deleteCourse(selectedItem.getId());
                if (!result){
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the customer", ButtonType.OK).show();
                }else{
                    lstCourses.getItems().remove(selectedItem);
                    lstCourses.getSelectionModel().clearSelection();
                    clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllCourses(){
        try {
            lstCourses.getItems().clear();
            List<CourseTM> allCourses = courseBO.getAllCourses();
            ObservableList<CourseTM> courseTMS = FXCollections.observableArrayList(allCourses);
            lstCourses.setItems(courseTMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddCourse_OnAction(ActionEvent actionEvent) {
        txtTitle.setText("");
        cmbType.getSelectionModel().clearSelection();
        cmbType.setPromptText("");
        txtDuration.setText("");
        btnSave.setText("Save");
        enableFields();

        try {
            txtId.setText(courseBO.getNewCourseId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void clear(){
        txtId.setText("id");
        txtTitle.setText("title");
        cmbType.getSelectionModel().clearSelection();
        txtDuration.setText("duration");
    }

    public void loadComboType(){
        cmbType.getItems().add(0,"Diploma");
        cmbType.getItems().add(1,"Bachelor");
        cmbType.getItems().add(2,"Post-Graduate");

    }

    private void disableFields() {
        txtId.setDisable(true);
        txtTitle.setDisable(true);
        cmbType.setDisable(true);
        txtDuration.setDisable(true);
    }

    private void enableFields() {
        txtId.setDisable(false);
        txtTitle.setDisable(false);
        cmbType.setDisable(false);
        txtDuration.setDisable(false);
        btnSave.setDisable(false);
    }

}
