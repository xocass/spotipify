package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class cTemplateArtistaEliminar {
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelEmail;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setLabelNombre(String nombre){labelNombre.setText(nombre);}
    public void setLabelEmail(String email){labelEmail.setText(email);}
}
