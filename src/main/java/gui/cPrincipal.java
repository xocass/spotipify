package gui;

import aplicacion.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public class cPrincipal {
    private FachadaGui fgui;
    private FachadaAplicacion fa;

    @FXML
    private HBox boxBuscar;
    @FXML
    private HBox boxBiblioteca;
    @FXML
    private HBox boxUsuario;
    @FXML
    private HBox boxAjustes;
    @FXML
    private HBox playlistBox;
    @FXML
    private HBox artistaBox;
    @FXML
    private HBox cancionBox;


    public void setFachadas (FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }
    @FXML
    public void clickUsuario() throws IOException {
        fgui.showUsuario();
    }
    @FXML
    public void clickBuscar() throws IOException{
        fgui.showBuscar();
    }
    @FXML
    public void clickBiblioteca() throws IOException{
        fgui.showBiblioteca();
    }
    @FXML
    public void clickAjustes() throws IOException{
        fgui.showAjustes();
    }
   public void iniciar() throws IOException {
       ArrayList<Playlist> defecto;
       defecto = fa.playlistDefecto();
       for (Playlist aux : defecto) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("templateCancion.fxml"));
           playlistBox.getChildren().add(loader.load());
           cTemplateCancion controller = loader.getController();
           controller.setLabelUsuario(aux.getNombre());
           controller.setFachadas(this.fgui, this.fa);
       }
       ArrayList<Artista> verificado;
       verificado = fa.verificados();
       for (Artista aux : verificado) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("templateArtistaInicio.fxml"));
           artistaBox.getChildren().add(loader.load());
           cTemplateArtistaInicio controller = loader.getController();
           controller.setLabelUsuario(aux.getNombreArtistico());
           controller.setFachadas(this.fgui, this.fa);
       }
       ArrayList<Cancion> topCanciones;
       topCanciones = fa.topCanciones();
       for (Cancion aux : topCanciones) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("templateCancion.fxml"));
           cancionBox.getChildren().add(loader.load());
           cTemplateCancion controller = loader.getController();
           controller.setLabelUsuario(aux.getNombre());
           controller.setFachadas(this.fgui, this.fa);
       }
   }
}
