package controller;

import business.BOFactory;
import business.BOType;
import business.custom.AdminBO;
import business.custom.LecturerBO;
import business.custom.UserBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.AdminTM;
import util.LecturerTM;

import java.io.IOException;

public class AdminAccountFormController {
    public AnchorPane root;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtUserName;
    public JFXPasswordField txtNewPassword;
    public JFXPasswordField txtNewPassword2;
    public JFXPasswordField txtPasswordHide;
    public TextField txtPasswordShow;
    public ImageView imgPasswordHide;
    public ImageView imgPasswordShow;

    private AdminBO adminBO = BOFactory.getInstance().getBO(BOType.ADMIN);
    private UserBO userBO = BOFactory.getInstance().getBO(BOType.USER);

    public void initialize(){
        txtPasswordShow.setVisible(false);
        imgPasswordShow.setVisible(false);

        try {
            AdminTM adminDetails = adminBO.getAdmin(LoginFormController.loginId);
            txtId.setEditable(false);
            txtId.setText(adminDetails.getId());
            txtName.setText(adminDetails.getName());
            txtContact.setText(adminDetails.getContact());


            //TODO: Substitute lecturer Id
            txtUserName.setText(userBO.getUser(adminBO.getUserId(LoginFormController.loginId)).getUsername());
            txtPasswordShow.setText(userBO.getUser(adminBO.getUserId(LoginFormController.loginId)).getPassword());


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

    public void btnStudents_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminStudentForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void btnLecturers_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminLecturerForm.fxml"));
        Scene mainScene =  new Scene(root);
        Stage mainStage = (Stage)this.root.getScene().getWindow();
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    public void imgPasswordShow_OnMouseClicked(MouseEvent mouseEvent) {
        imgPasswordShow.setVisible(false);
        txtPasswordShow.setVisible(false);
        txtPasswordHide.setVisible(true);
        imgPasswordHide.setVisible(true);

        txtPasswordHide.requestFocus();
        txtPasswordHide.deselect();
        txtPasswordHide.positionCaret(txtPasswordHide.getLength());
    }

    public void imgPasswordHide_OnMouseClicked(MouseEvent mouseEvent) {
        txtPasswordHide.setVisible(false);
        imgPasswordHide.setVisible(false);
        imgPasswordShow.setVisible(true);
        txtPasswordShow.setVisible(true);

        txtPasswordShow.requestFocus();
        txtPasswordShow.deselect();
        txtPasswordShow.positionCaret(txtPasswordShow.getLength());
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        try {
            AdminTM adminId = adminBO.getAdmin(LoginFormController.loginId);
            String userId = adminBO.getUserId(LoginFormController.loginId);
            String userRole = userBO.getUser(userId).getUserRole();

            userBO.update(userId,txtUserName.getText(),txtNewPassword2.getText(),userRole);

            adminBO.updateAdmin(txtName.getText(),txtContact.getText()
                    ,txtId.getText(), userId);

            new Alert(Alert.AlertType.INFORMATION,"Admin updated successfully", ButtonType.OK).showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
