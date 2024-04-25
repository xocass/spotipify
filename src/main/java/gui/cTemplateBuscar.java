package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class cTemplateBuscar {
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelTipo;
    @FXML
    private Label labelArtista;

    public void setLabelNombre(String nombre){
        labelNombre.setText(nombre);
    }
    public void setLabelTipo(String tipo){
        labelTipo.setText(tipo);
    }
    public void setLabelArtista(String artista){
        labelArtista.setText(labelArtista.getText() + artista);
    }
}
