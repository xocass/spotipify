package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class cTemplateArtistaInicio {
    @FXML
    private Label labelUsuario;
    @FXML
    private VBox vbox;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setLabelUsuario(String text){
        labelUsuario.setText(text);
    }
    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa=fa;
    }
    public void setTam(int ancho, int alto){
        vbox.setMaxSize(ancho,alto);
    }
}
