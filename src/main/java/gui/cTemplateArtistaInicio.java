package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class cTemplateArtistaInicio {
    @FXML
    private Label labelUsuario;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setLabelUsuario(String text){
        labelUsuario.setText(text);
    }
    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa=fa;
    }
}
