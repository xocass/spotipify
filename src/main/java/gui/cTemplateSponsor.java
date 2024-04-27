package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class cTemplateSponsor {
    @FXML
    private Label text;
    @FXML
    private ImageView imagen;

    public void setText(String text) {
        this.text.setText(text);
    }
    public void setImagen() {
        Image userImage=null;

        userImage= new Image(getClass().getResource("/spoti/usuario-de-perfil.png").toExternalForm());

        imagen.setImage(userImage);
    }
}
