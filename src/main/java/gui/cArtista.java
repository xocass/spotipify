package gui;

import aplicacion.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class cArtista {
    private FachadaGui fgui;
    private boolean esArtista;
    private FachadaAplicacion fa;
    @FXML
    private Label labelArtista;
    @FXML
    private Label labelGeneros_Plan;
    @FXML
    private Label labelPais_Seguidos;
    @FXML
    private Label labelSeguidores;
    @FXML
    private ImageView verified;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private VBox boxAlbum;
    @FXML
    private VBox boxPodcast;
    @FXML
    private VBox boxSponsor;
    private String id;
    private boolean isVerified;


    public void setId(String id,boolean verified) {
        this.id = id;
        isVerified=verified;
        if(verified)
            this.verified.setVisible(true);
    }

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa, boolean artista){
        this.fgui=fgui;
        this.fa=fa;
        esArtista=artista;
    }
    public void setLabelArtista(String nombre){
        this.labelArtista.setText(nombre);
    }
    public void setLabelGeneros_Plan(ArrayList<String> generos){
        if(esArtista){
            for (int i = 0; i < generos.size(); i++) {
                if (i != 0) labelGeneros_Plan.setText(labelGeneros_Plan.getText() + ", ");
                labelGeneros_Plan.setText(labelGeneros_Plan.getText() + generos.get(i));
            }
        }else{
            labelGeneros_Plan.setText(generos.get(0));
        }
    }
    public void setLabelPais_Seguidos(String pais){
        labelPais_Seguidos.setText(pais);
    }
    public void setLabelSeguidores(int nseguidores){
        labelSeguidores.setText(((Integer)nseguidores).toString());
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
        fgui.showBuscar();
    }
    @FXML
    public void seguir() throws IOException {
        if (esArtista) {
            fa.seguirArtista(id, fgui.getActual().getNombre());
            fgui.showArtista(new Artista(id, labelArtista.getText(), isVerified, labelPais_Seguidos.getText()));
        }
        else{
            fa.seguirPerfil(id,fgui.getActual().getNombre());
            fgui.showPerfil(id);
        }
    }
    public void iniciar() throws IOException {
        boxAlbum.getChildren().clear();
        boxPodcast.getChildren().clear();
        boxSponsor.getChildren().clear();

        ArrayList<Contenido> resultado1;
        ArrayList<String> resultado2;
        resultado1=fa.buscarContenidoArtista(id);
        resultado2=fa.buscarSponsors(id);
        if(!resultado1.isEmpty()){
            for (Contenido aux:resultado1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateAnhadirArtista.fxml"));
                if(aux instanceof Album){
                    boxAlbum.getChildren().add(loader.load());
                }else{
                    boxPodcast.getChildren().add((loader.load()));
                }
                cTemplateAnhadirArtista controller = loader.getController();
                controller.setLabelTexto(aux.getNombre());
            }
        }if(!resultado2.isEmpty()){
            for(int i=0;i<resultado2.size();i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateSponsor.fxml"));
                boxSponsor.getChildren().add(loader.load());
                cTemplateSponsor controller = loader.getController();
                controller.setText(resultado2.get(i));
            }
        }
    }
    public void iniciarP() throws IOException {
        label1.setText("Plan:");
        label2.setText("Seguidos:");
        label4.setText("Sus playlist:");
        label5.setText("Sus seguidos:");
        label6.setText("Sus seguidores:");
        ArrayList<String> siguiendo =fa.siguiendo(id);
        ArrayList<String> seguidores = fa.seguidores(id);
        ArrayList<Playlist> playlist=fa.tusPlaylist(id);
        if(!playlist.isEmpty()) {
            for (Playlist aux : playlist) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateAnhadirArtista.fxml"));
                boxAlbum.getChildren().add(loader.load());
                cTemplateAnhadirArtista controller = loader.getController();
                controller.setLabelTexto(aux.getNombre());
            }
        }
        if(!siguiendo.isEmpty()) {
            for (int i = 0; i < siguiendo.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateSponsor.fxml"));
                boxPodcast.getChildren().add(loader.load());
                cTemplateSponsor controller = loader.getController();
                controller.setText(siguiendo.get(i));
                controller.setImagen();
            }
        }
        if(!seguidores.isEmpty()) {
            for (int i = 0; i < siguiendo.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateSponsor.fxml"));
                boxSponsor.getChildren().add(loader.load());
                cTemplateSponsor controller = loader.getController();
                controller.setText(seguidores.get(i));
                controller.setImagen();
            }
        }
    }
}
