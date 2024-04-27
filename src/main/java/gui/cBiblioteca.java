package gui;

import aplicacion.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public class cBiblioteca {
    private FachadaGui fgui;
    private FachadaAplicacion fa;

    @FXML
    private HBox boxArtista;
    @FXML
    private HBox boxPlaylist;
    @FXML
    private HBox boxSiguiendo;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }

    @FXML
    public void clickInicio() throws IOException {
        fgui.principal();
    }

    @FXML
    public void clickBuscar() throws IOException{
        fgui.showBuscar();
    }
    @FXML
    public void clickUsuario() throws IOException{
        fgui.showUsuario();
    }
    @FXML
    public void clickAjustes() throws IOException{
        fgui.showAjustes();
    }
    public void iniciar() throws IOException {
        ArrayList<String> aux;
        ArrayList<Playlist> auxc;
        aux = fa.siguiendo(fgui.getActual().getNombre());
        if(!aux.isEmpty()) {
            for (int i = 0; i < aux.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateArtistaInicio.fxml"));
                boxSiguiendo.getChildren().add(loader.load());
                cTemplateArtistaInicio controller = loader.getController();
                controller.setLabelUsuario(aux.get(i));
                controller.setFachadas(this.fgui, this.fa);
            }
        }
        Artista artistaAux = null;
        aux=fa.siguiendoArtistaID(fgui.getActual().getNombre());
        if (!aux.isEmpty()) {
            for (int i = 0; i < aux.size();i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateArtistaInicio.fxml"));
                boxArtista.getChildren().add(loader.load());
                cTemplateArtistaInicio controller = loader.getController();
                controller.setTam(200,230);
                artistaAux = fa.getArtistaId(aux.get(i));
                controller.setLabelUsuario(artistaAux.getNombreArtistico());
                controller.setFachadas(this.fgui, this.fa);
                controller.setIdArtista(artistaAux.getNombre());
            }
        }
        auxc=fa.tusPlaylist(fgui.getActual().getNombre());
        for(Playlist playlist: auxc){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("templateCancion.fxml"));
            boxPlaylist.getChildren().add(loader.load());
            cTemplateCancion controller = loader.getController();
            controller.setLabelUsuario(playlist.getNombre());
            controller.setIdplaylist(playlist.getId());
            controller.setFachadas(this.fgui, this.fa);
        }
    }
}
