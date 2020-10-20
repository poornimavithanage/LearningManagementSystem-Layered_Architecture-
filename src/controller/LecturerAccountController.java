package controller;

import business.BOFactory;
import business.BOType;
import business.custom.LecturerBO;
import business.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.LecturerTM;

import java.io.IOException;

public class LecturerAccountController {
    public AnchorPane root;
    public JFXButton btnDashboard;
    public JFXButton btnCourses;
    public JFXButton btnModules;
    public JFXButton btnAccount;
    public AnchorPane subAnchorPane;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtContact;
    public Button btnUpdate;
    public JFXTextField txtUserName;
    public JFXPasswordField txtNewPassword2;
    public JFXPasswordField txtNewPassword;
    public JFXTextField txtCourseId;
    public TextField txtPasswordShow;
    public ImageView imgPasswordHide;
    public ImageView imgPasswordShow;
    public JFXPasswordField txtPasswordHide;

    private LecturerBO lecturerBO = BOFactory.getInstance().getBO(BOType.LECTURER);
    private UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);


    public void initialize(){
        txtPasswordShow.setVisible(false);
        imgPasswordShow.setVisible(false);

        try {
            LecturerTM lecturerDetails = lecturerBO.getLecturer(LoginFormController.loginId);
            txtId.setEditable(false);
            txtCourseId.setEditable(false);
            txtId.setText(lecturerDetails.getId());
            txtCourseId.setText(lecturerDetails.getCourseId());
            txtName.setText(lecturerDetails.getName());
            txtAddress.setText(lecturerDetails.getAddress());
            txtContact.setText(lecturerDetails.getContact());
            txtNIC.setText(lecturerDetails.getNic());
            txtEmail.setText(lecturerDetails.getEmail());


            //TODO: Substitute lecturer Id
            txtUserName.setText(userBO.getUser(lecturerBO.getUserId("L001")).getUsername());
            txtPasswordShow.setText(userBO.getUser(lecturerBO.getUserId("L001")).getPassword());


        } catch (Exception e) {
            e.printStackTrace();
        }

        txtPasswordHide.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txtPasswordShow.setText(newValue);
            }
        });

        txtPasswordShow.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                txtPasswordHide.setText(newValue);
            }
        });
    }

    public void btnCourses_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerCoursesForm.fxml"));
        Scene mainScene = new Scene(root);
        Stage mainStage = (Stage) this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnModules_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerModulesForm.fxml"));
        Scene mainScene = new Scene(root);
        Stage mainStage = (Stage) this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnAccount_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LecturerAccountForm.fxml"));
        Scene mainScene = new Scene(root);
        Stage mainStage = (Stage) this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {

        try {
            LecturerTM lecturerId = lecturerBO.getLecturer("L001");
            String userId = lecturerBO.getUserId("L001");
            String userRole = userBO.getUser(userId).getUserRole();

            userBO.update(userId,txtUserName.getText(),txtNewPassword2.getText(),userRole);

            lecturerBO.updateLecturer(txtCourseId.getText(),
                    txtName.getText(),txtAddress.getText(),txtContact.getText(),txtNIC.getText(),
                    txtEmail.getText(),txtId.getText(),
                    userId);


            new Alert(Alert.AlertType.INFORMATION,"Lecturer updated successfully", ButtonType.OK).showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //hide the password
    public void imgPasswordHide_OnMouseClicked(MouseEvent event) {

        txtPasswordHide.setVisible(false);
        imgPasswordHide.setVisible(false);
        imgPasswordShow.setVisible(true);
        txtPasswordShow.setVisible(true);

        txtPasswordShow.requestFocus();
        txtPasswordShow.deselect();
        txtPasswordShow.positionCaret(txtPasswordShow.getLength());

    }

    //show the password
    public void imgPasswordShow_OnMouseClicked(MouseEvent event) {
        imgPasswordShow.setVisible(false);
        txtPasswordShow.setVisible(false);
        txtPasswordHide.setVisible(true);
        imgPasswordHide.setVisible(true);

        txtPasswordHide.requestFocus();
        txtPasswordHide.deselect();
        txtPasswordHide.positionCaret(txtPasswordHide.getLength());
    }



}

