package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class cTemplateAnhadirArtista {
    @FXML
    private Label labelTexto;

    public void setLabelTexto(String texto){
        labelTexto.setText(texto);
    }
}
