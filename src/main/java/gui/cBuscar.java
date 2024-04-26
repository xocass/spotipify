package gui;

import aplicacion.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class cBuscar {
    private FachadaGui fgui;
    private FachadaAplicacion fa;

    @FXML
    private ImageView btnBuscar;
    @FXML
    private TextField fieldBuscar;
    @FXML
    private HBox boxInicio;
    @FXML
    private HBox boxBiblioteca;
    @FXML
    private HBox boxUsuario;
    @FXML
    private HBox boxAjustes;
    @FXML
    private VBox vboxBuscar;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }
    @FXML
    public void clickInicio() throws IOException {
        fgui.principal();
    }
    @FXML
    public void clickUsuario() throws IOException {
        fgui.showUsuario();
    }
    @FXML
    public void clickBiblioteca() throws IOException{
        fgui.showBiblioteca();
    }
    @FXML
    public void clickAjustes() throws IOException{
        fgui.showAjustes();
    }
    @FXML
    public void clickBuscar() throws IOException {
        vboxBuscar.getChildren().clear();
        ArrayList<Contenido> resultado;
        if (!fieldBuscar.getText().isEmpty()) {
            resultado = fa.buscar(fieldBuscar.getText());
            if(!resultado.isEmpty()) {
                for (Contenido aux : resultado) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("templateBuscar.fxml"));
                    vboxBuscar.getChildren().add(loader.load());
                    cTemplateBuscar controller = loader.getController();

                    controller.setLabelNombre(aux.getNombre());
                    if(aux instanceof Podcast){
                        controller.setLabelTipo("Podcast");
                        controller.setContenido(((Podcast) aux).getIdPodcast(),'d');
                    }
                    else {
                        controller.setLabelTipo(((Album) aux).getTipoA());
                        controller.setContenido(((Album) aux).getIdAlbum(),'c');
                    }

                    controller.setFachadas(this.fgui, this.fa);


                    for (int i = 0; i < aux.getCreador().size(); i++) {
                        if (i != 0) controller.setLabelArtista(", ");
                        controller.setLabelArtista(aux.getCreador().get(i));
                    }

                }
            }
            ArrayList<Usuario> resultado1 = fa.buscarU(fieldBuscar.getText());
            if(!resultado1.isEmpty()) {
                for (Usuario aux : resultado1) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("templateBuscar.fxml"));
                    vboxBuscar.getChildren().add(loader.load());
                    cTemplateBuscar controller = loader.getController();


                    if(aux instanceof Oyente){
                        controller.setLabelNombre(aux.getNombre());
                        controller.setLabelTipo("");
                        controller.setUsuario(aux.getNombre(),'b');
                    }
                    else {
                        controller.setLabelNombre(((Artista)aux).getNombreArtistico());
                        controller.setLabelTipo(((Artista)aux).getPaisNacimiento());
                        controller.setUsuario(aux.getNombre(),'a');
                        if(((Artista)aux).getVerificado())controller.setVerified(true);
                    }
                    controller.setImagen();
                    controller.setFachadas(this.fgui, this.fa);

                }
            }

        }

    }

}
