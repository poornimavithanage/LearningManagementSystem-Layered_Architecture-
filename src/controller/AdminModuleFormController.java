package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CourseBO;
import business.custom.ModuleBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ModuleDAO;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CourseTM;
import util.ModuleTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AdminModuleFormController {
    public AnchorPane root;
    public Label lblCourseName;
    public JFXButton btnDashboard;
    public JFXButton btnCourses;
    public JFXButton btnModules;
    public JFXButton btnAccount;
    public JFXButton btnStudent;
    public JFXButton btnLecturer;
    public ListView<ModuleTM> lstModule;
    public JFXTextField txtModuleId;
    public JFXTextField txtModuleName;
    public JFXTextField txtDuration;
    public JFXTextField txtCredits;
    public JFXComboBox<CourseTM> cmbCourses;
    public JFXButton btnAddModule;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    private ModuleBO moduleBO = BOFactory.getInstance().getBO(BOType.MODULE);
    private CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    public void initialize() throws Exception {
        loadAllCourses();
        btnAddModule.setDisable(true);
        txtModuleId.setDisable(true);
        txtModuleId.setEditable(false);
        txtModuleName.setDisable(true);
        txtDuration.setDisable(true);
        txtCredits.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
        lstModule.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModuleTM>() {
            @Override
            public void changed(ObservableValue<? extends ModuleTM> observable, ModuleTM oldValue, ModuleTM newValue) {
                ModuleTM selectedModule = lstModule.getSelectionModel().getSelectedItem();

                if (selectedModule == null) {
                    btnSave.setText("Save");
                    btnDelete.setDisable(true);
                    txtModuleId.clear();
                    txtModuleName.clear();
                    txtDuration.clear();
                    txtCredits.clear();
                    return;
                }
                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
                txtModuleId.setDisable(false);
                txtModuleName.setDisable(false);
                txtDuration.setDisable(false);
                txtCredits.setDisable(false);

                txtModuleId.setText(selectedModule.getId());
                txtModuleName.setText(selectedModule.getTitle());
                txtDuration.setText(selectedModule.getDuration());
                txtCredits.setText(selectedModule.getCredits());
            }
        });
        cmbCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM newValue) {
                CourseTM selectedItem = cmbCourses.getSelectionModel().getSelectedItem();
                if (selectedItem == null) {
                    btnAddModule.setDisable(true);
                    lstModule.getItems().clear();
                    return;
                }
                addModulesToListView();
                btnAddModule.setDisable(false);
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

    public void btnAddModule_OnAction(ActionEvent actionEvent) {
        try {
            txtCredits.clear();
            txtDuration.clear();
            txtModuleName.clear();
            lstModule.getSelectionModel().clearSelection();
            txtModuleId.setText(moduleBO.getNewModuleId());
            txtModuleName.setDisable(false);
            txtModuleId.setDisable(false);
            txtDuration.setDisable(false);
            txtCredits.setDisable(false);

            btnSave.setDisable(false);
            btnDelete.setDisable(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equals("Save")) {
            try {
                moduleBO.saveModule(txtModuleId.getText(), txtModuleName.getText(), txtDuration.getText(), txtCredits.getText(), cmbCourses.getSelectionModel().getSelectedItem().getId());
                txtCredits.clear();
                txtDuration.clear();
                txtModuleName.clear();
                txtModuleId.clear();
                cmbCourses.getSelectionModel().clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                moduleBO.updateModule(txtModuleName.getText(),txtDuration.getText(),txtCredits.getText(),cmbCourses.getSelectionModel().getSelectedItem().getId(),txtModuleId.getText());
                txtCredits.clear();
                txtDuration.clear();
                txtModuleName.clear();
                txtModuleId.clear();
                cmbCourses.getSelectionModel().clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                ModuleTM selectedItem = lstModule.getSelectionModel().getSelectedItem();
                moduleBO.deleteModule(lstModule.getSelectionModel().getSelectedItem().getId());
                txtCredits.clear();
                txtDuration.clear();
                txtModuleName.clear();
                txtModuleId.clear();
                btnAddModule.requestFocus();
                lstModule.getSelectionModel().clearSelection();
                lstModule.getItems().remove(selectedItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            lstModule.getSelectionModel().clearSelection();
            cmbCourses.getSelectionModel().clearSelection();
        }
    }

    private void loadAllCourses() {
        try {
            cmbCourses.getItems().clear();
            List<CourseTM> allCourses = courseBO.getAllCourses();
            ObservableList<CourseTM> courses = FXCollections.observableList(allCourses);
            cmbCourses.setItems(courses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addModulesToListView() {
        try {
            List<ModuleTM> modules = moduleBO.getCourseModules(cmbCourses.getSelectionModel().getSelectedItem().getId());
            ObservableList<ModuleTM> moduleTM = FXCollections.observableArrayList(modules);
            lstModule.setItems(moduleTM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
