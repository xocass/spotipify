package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class cTemplateArtistaInicio {
    @FXML
    private Label labelUsuario;

    public void setLabelUsuario(String text){
        labelUsuario.setText(text);
    }
}
