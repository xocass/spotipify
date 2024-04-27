package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class cTemplateAnhadirArtista {
    private FachadaAplicacion fa;
    private FachadaGui fgui;
    private char opcion;
    @FXML
    private Label labelTexto;

    public void setLabelTexto(String texto){
        labelTexto.setText(texto);
    }

}
