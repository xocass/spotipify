package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class cTemplateUsuarioEliminar {
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelEmail;
    @FXML
    private Button btnEliminar;


    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setLabelNombre(String nombre){labelNombre.setText(nombre);}
    public void setLabelEmail(String email){labelEmail.setText(email);}
    @FXML
    public void clickEliminar() throws IOException {
        fa.eliminarOyente(labelNombre.getText());
        fgui.showOyenteMod();
    }

}
