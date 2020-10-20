package controller;

import business.BOFactory;
import business.BOType;
import business.custom.ContentBO;
import business.custom.CourseBO;
import business.custom.ModuleBO;
import business.custom.StudentBO;
import business.custom.impl.ContentBOImpl;
import business.custom.impl.CourseBOImpl;
import business.custom.impl.ModuleBOImpl;
import business.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CourseTM;
import util.ModuleTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentModuleFormController {
    public AnchorPane root;
    public JFXButton btnDashboard;
    public JFXButton btnCourses;
    public JFXButton btnModules;
    public JFXButton btnAccount;
    public Label lblCourseName;
    public Label lblDescription;
    public Label lblModuleDescription;
    public Label lblDuration;
    public JFXComboBox<ModuleTM> cmbModules;
    public Label lblCredits;
    public Label lblModuleTitle;
    public JFXComboBox<CourseTM> cmbCourses;
    public Hyperlink hyprlnkCount;
    public static String moduleId;


    private ContentBO contentBO = BOFactory.getInstance().getBO(BOType.CONTENT);
    private ModuleBO moduleBO = BOFactory.getInstance().getBO(BOType.MODULE);

    public void setStudentId(String id) throws Exception {
//        lblStudentId.setText(id);
        loadAllCoursesOfStudent(LoginFormController.loginId);

    }

    public void initialize() throws Exception {
        loadAllCoursesOfStudent(LoginFormController.loginId);
//        loadAllCourseModules("C001");
//        getModuleDetails("M001");
        lblCourseName.setVisible(false);

        cmbModules.setVisible(false);
        cmbCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM selectedCourse) {
                if(selectedCourse==null){
                    return;
                }
                cmbModules.setVisible(true);
                lblCourseName.setVisible(true);
                String id = selectedCourse.getId();
                try {
                    loadAllCourseModules(id);
                    lblCourseName.setText(selectedCourse.getTitle());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cmbModules.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ModuleTM>() {
            @Override
            public void changed(ObservableValue<? extends ModuleTM> observable, ModuleTM oldValue, ModuleTM selectedModule) {
                if(selectedModule==null){
                    return;
                }
                String id = selectedModule.getId();
                moduleId = id;
                try {
                    getModuleDetails(id);
                    String moduleContentCount = contentBO.findModuleContentCount(id);
                    hyprlnkCount.setText(moduleContentCount + "files");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void btnCourses_OnAction(ActionEvent actionEvent) throws IOException {
        loadView("/view/StudentCoursesForm.fxml");
    }

    public void btnModules_OnAction(ActionEvent actionEvent) throws IOException {
        loadView("/view/StudentModuleForm.fxml");
    }

    public void btnAccount_OnAction(ActionEvent actionEvent) throws IOException {
        loadView("/view/StudentAccountForm.fxml");
    }

    public void loadView(String location) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource(location));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void loadAllCoursesOfStudent(String studentId) throws Exception {
        cmbCourses.getItems().clear();
        StudentBO studentBO = new StudentBOImpl();
        List<CourseTM> courses = studentBO.getStudentCourses(studentId);
        ObservableList<CourseTM> courseTM = FXCollections.observableArrayList(courses);
        cmbCourses.setItems(courseTM);
    }

    public void loadAllCourseModules(String courseId) throws Exception {
        cmbModules.getItems().clear();
        List<ModuleTM> modules = moduleBO.getCourseModules(courseId);
        ObservableList<ModuleTM> moduleTMS = FXCollections.observableArrayList(modules);
        cmbModules.setItems(moduleTMS);
    }

    //TODO: add description to the module table!
    public void getModuleDetails(String moduleId) throws Exception {
        ModuleTM module = moduleBO.getModule(moduleId);
        lblCredits.setText(module.getCredits());
        lblDuration.setText(module.getDuration());
        lblDescription.setText("Introduction");
        lblModuleTitle.setText(module.getTitle());
    }

    public void hyprlnkCount_OnAction(ActionEvent actionEvent) throws IOException {
        loadView("/view/StudentModuleContent.fxml");
    }
}
