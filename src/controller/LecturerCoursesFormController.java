package controller;

import business.BOFactory;
import business.BOType;
import business.custom.AnnouncementBO;
import business.custom.LecturerBO;
import business.custom.StudentBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.dc.pr.PRError;
import util.AnnouncementTM;
import util.CourseTM;
import util.FacultyTM;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LecturerCoursesFormController {

    public AnchorPane root;
    public Label lblTitle;
    public Label lblType;
    public JFXComboBox<CourseTM> cmbCourses;
    public JFXComboBox<FacultyTM> cmbFaculty;
    public Label lblLecturerId;
    public VBox vBoxAnnouncements;
    public JFXTextField txtAnnouncement;

    private LecturerBO lecturerBO = BOFactory.getInstance().getBO(BOType.LECTURER);
    private AnnouncementBO announcementBO = BOFactory.getInstance().getBO(BOType.ANNOUNCEMENT);

    public void setLecturerId(String id){
        lblLecturerId.setText(id);
        loadAllFaculties();
    }

    public void initialize() {
        cmbCourses.setVisible(false);

        cmbFaculty.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FacultyTM>() {
            @Override
            public void changed(ObservableValue<? extends FacultyTM> observable, FacultyTM oldValue, FacultyTM selectedFaculty) {
                if (selectedFaculty==null) {
                    return;
                }
                cmbCourses.setVisible(true);
                loadAllCourses();
            }
        });

        cmbCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseTM>() {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM newValue) {
                if (newValue == null) {
                    lblTitle.setText("");
                    lblType.setText("");
                    return;
                }
                lblTitle.setText(newValue.getTitle());
                lblType.setText(newValue.getType());

                try {
                    vBoxAnnouncements.getChildren().clear();
                    List<AnnouncementTM> announcements = lecturerBO.getAnnouncements(newValue.getId());
                    for (AnnouncementTM announcement : announcements) {
                        Label label = new Label(announcement.toString());
                        vBoxAnnouncements.getChildren().add(label);
                        label.setPrefWidth(806);
                        label.setPrefHeight(52);
                        label.setFont(new Font(18));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
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

    private void loadAllFaculties() {
        try {
            cmbFaculty.getItems().clear();
            cmbFaculty.setItems(FXCollections.observableArrayList(lecturerBO.getLecturerFaculties(LoginFormController.loginId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllCourses() {
        try {
            String facultyId = cmbFaculty.getSelectionModel().getSelectedItem().toString();
            cmbCourses.getItems().clear();
            cmbCourses.setItems(FXCollections.observableArrayList(lecturerBO.getLecturerFacultyCourses(LoginFormController.loginId,facultyId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSubmit_OnAction(ActionEvent actionEvent) {
        lblTitle.setText("");
        lblType.setText("");
        String announcement = txtAnnouncement.getText();

        if (announcement.trim().length() == 0 || lblTitle.getText().trim().length() == 0 || lblType.getText().trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Select a course!", ButtonType.OK).show();
            return;
        }
        //vBoxAnnouncements.getChildren().clear();
        Label label = new Label(announcement);
        vBoxAnnouncements.getChildren().add(0,label);
        label.setPrefWidth(806);
        label.setPrefHeight(52);
        label.setFont(new Font(18));

        try {
            boolean result = announcementBO.saveAnnouncement(cmbCourses.getSelectionModel().getSelectedItem().getId(),
                    lblLecturerId.getText(), Date.valueOf(LocalDate.now()),announcement);
            if (!result) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the announcement", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCancel_OnAction(ActionEvent actionEvent) {
        txtAnnouncement.setText("");
    }
}
