package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class cTemplateBuscar {
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelTipo;
    @FXML
    private Label labelArtista;
    @FXML
    private ImageView imagen;

    private FachadaGui fgui;
    private FachadaAplicacion fa;


    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setLabelNombre(String nombre){
        labelNombre.setText(nombre);
    }
    public void setLabelTipo(String tipo){labelTipo.setText(tipo);}
    public void setLabelArtista(String artista){
        labelArtista.setText(labelArtista.getText() + artista);
    }

    public void setImagen() {
        Image userImage = new Image(getClass().getResource("/spoti/icons8-usuario-90.png").toExternalForm());
        imagen.setImage(userImage);
    }

}

