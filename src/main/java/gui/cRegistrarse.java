package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.Oyente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Text;

import java.io.IOException;

public class cRegistrarse {
    private FachadaAplicacion fa;
    private FachadaGui fgui;

    @FXML
    private ImageView btnAtras;
    @FXML
    private TextField fieldUsuario;
    @FXML
    private TextField fieldContrasena;
    @FXML
    private TextField fieldCorreo;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private Label labelVacio;


    public void setFgui(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa=fa;
    }
    @FXML
    public void atras() throws IOException {
        fgui.iniciarSesion();
    }
    @FXML
    public void registrarUsuario() {

        if (fieldUsuario.getText().isEmpty() || fieldContrasena.getText().isEmpty() || fieldCorreo.getText().isEmpty() || fechaNacimiento.getValue() == null) {
            labelVacio.setText("Debes rellenar todos los campos");
        } else {
            if(fa.comprobarNombre(fieldUsuario.getText()))
                fa.registrarUsuario(fieldUsuario.getText(), fieldContrasena.getText(), fieldCorreo.getText(), fechaNacimiento.getValue().toString());
            else
                labelVacio.setText("Ya existe un usuario con ese nombre");
        }
        }
    }

