package gui;

import aplicacion.Artista;
import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class cTemplateArtistaInicio {
    @FXML
    private Label labelUsuario;
    @FXML
    private VBox vbox;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    private String idArtista;

    public void setLabelUsuario(String text){
        labelUsuario.setText(text);
    }
    public void setIdArtista(String id){this.idArtista=id;}
    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa=fa;
    }
    public void setTam(int ancho, int alto){
        vbox.setMaxSize(ancho,alto);
    }

    public void clickEntrar() throws IOException {
        Artista artista = null;
        artista = fa.getArtistaId(this.idArtista);
        fgui.showArtista(artista);
    }
}
