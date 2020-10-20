package controller;

import business.BOFactory;
import business.BOType;
import business.custom.ContentBO;
import business.custom.LecturerBO;
import business.custom.ModuleBO;
import business.custom.impl.ContentBOImpl;
import business.custom.impl.LecturerBOImpl;
import business.custom.impl.ModuleBOImpl;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ContentTM;
import util.CourseTM;
import util.ModuleTM;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LecturerModuleContentController {
    public AnchorPane root;
    public JFXComboBox<CourseTM> cmbCourses;
    public ListView lstContent;
    public ComboBox<ModuleTM> cmbModuleId;
    public JFXTextField txtDate;
    public JFXButton btnSave;
    public JFXTextField txtTitle;

    private LecturerBO lecturerBO = BOFactory.getInstance().getBO(BOType.LECTURER);
    private ModuleBO moduleBO= BOFactory.getInstance().getBO(BOType.MODULE);
    private ContentBO contentBO = BOFactory.getInstance().getBO(BOType.CONTENT);

    public void initialize() throws Exception {
        loadAllCoursesOfLecturerInFaculty(LoginFormController.loginId,"F001");
//        loadAllCourseModules("C001");
//        loadAllModuleContent("M001");
        cmbModuleId.setVisible(false);
        LocalDate today = LocalDate.now();
        txtDate.setText(today.toString());
        txtTitle.setVisible(false);
        btnSave.setVisible(false);

        cmbCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM selectedCourse) {
                if (selectedCourse==null) {
                    return;
                }
                String courseId  = selectedCourse.getId();
                try {
                    loadAllCourseModules(courseId);
                    cmbModuleId.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cmbModuleId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModuleTM>() {
            @Override
            public void changed(ObservableValue<? extends ModuleTM> observable, ModuleTM oldValue, ModuleTM selectedModule) {
                if(selectedModule==null){
                    return;
                }
                String moduleId = selectedModule.getId();
                try {
                    loadAllModuleContent(moduleId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void btnCourses_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerCoursesForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnModules_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerModulesForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnAccount_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerAccountForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }
    public void loadAllCoursesOfLecturerInFaculty(String lecturerId, String facultyId) throws Exception {
        cmbCourses.getItems().clear();
        List<CourseTM> lecturerFacultyCourses = lecturerBO.getLecturerFacultyCourses(lecturerId, facultyId);
        ObservableList<CourseTM> courseTMS = FXCollections.observableArrayList(lecturerFacultyCourses);
        cmbCourses.setItems(courseTMS);
    }

    public void loadAllCourseModules(String courseId) throws Exception {
        cmbModuleId.getItems().clear();
        List<ModuleTM> courseModules = moduleBO.getCourseModules(courseId);
        ObservableList<ModuleTM> moduleTMS = FXCollections.observableArrayList(courseModules);
        cmbModuleId.setItems(moduleTMS);
    }

    public void loadAllModuleContent(String moduleId) throws Exception {
        lstContent.getItems().clear();
        List<ContentTM> allContent = contentBO.getModuleContent(moduleId);
        ObservableList<ContentTM> contentTMS = FXCollections.observableArrayList(allContent);
        lstContent.setItems(contentTMS);
    }

    public void btnAddContent_OnAction(ActionEvent actionEvent) throws Exception {
        btnSave.setVisible(true);
        txtTitle.setVisible(true);

    }

    public void btnSave_OnAction(ActionEvent actionEvent) throws Exception {
        String moduleId = cmbModuleId.getSelectionModel().getSelectedItem().getId();
        String newContentId = contentBO.getNewContentId();
        contentBO.saveContent(newContentId,txtTitle.getText(),Date.valueOf(txtDate.getText()),LoginFormController.loginId,moduleId);
        loadAllModuleContent(moduleId);
        btnSave.setVisible(false);
        txtTitle.setVisible(false);
    }
}
