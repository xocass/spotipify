package gui;

import aplicacion.Artista;
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
    @FXML
    private ImageView verified;
    char tipoResultado; //a=artista b=perfil c=album d=podcast
    String idUser;
    int idContenido;
    private FachadaGui fgui;
    private FachadaAplicacion fa;


    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }
    public void setVerified(boolean option){
        verified.setVisible(option);
    }

    public void setLabelNombre(String nombre){
        labelNombre.setText(nombre);
    }
    public void setLabelTipo(String tipo){labelTipo.setText(tipo);}
    public void setLabelArtista(String artista){labelArtista.setText(labelArtista.getText() + artista);}
    public void setContenido(int id, char tipo){this.idContenido=id;this.tipoResultado=tipo;}
    public void setUsuario(String id, char tipo){this.idUser=id;this.tipoResultado=tipo;}

    public void setImagen() {
        Image userImage = new Image(getClass().getResource("/spoti/icons8-usuario-90.png").toExternalForm());
        imagen.setImage(userImage);
        verified.setVisible(false);
    }
    @FXML
    public void clickEntrar() throws IOException {
        System.out.println(this.tipoResultado);
        switch(this.tipoResultado){
            case 'a':
                fgui.showArtista(new Artista(this.idUser,this.labelNombre.getText(),this.verified.isVisible(),this.labelTipo.getText()));
                break;
        }
    }

}

