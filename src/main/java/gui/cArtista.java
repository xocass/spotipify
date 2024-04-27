package gui;

import aplicacion.Album;
import aplicacion.Artista;
import aplicacion.Contenido;
import aplicacion.FachadaAplicacion;
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
    private FachadaAplicacion fa;
    @FXML
    private Label labelArtista;
    @FXML
    private Label labelGeneros;
    @FXML
    private Label labelPais;
    @FXML
    private Label labelSeguidores;
    @FXML
    private ImageView verified;
    @FXML
    private VBox boxAlbum;
    @FXML
    private VBox boxPodcast;
    @FXML
    private VBox boxSponsor;
    private String id;
    private boolean isVerified;
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

    public void setId(String id,boolean verified) {
        this.id = id;
        isVerified=verified;
        if(verified)
            this.verified.setVisible(true);
    }

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }
    public void setLabelArtista(String nombre){
        this.labelArtista.setText(nombre);
    }
    public void setLabelGeneros(ArrayList<String> generos){
        for(int i=0;i<generos.size();i++){
            if(i!=0)labelGeneros.setText(labelGeneros.getText()+", ");
            labelGeneros.setText(labelGeneros.getText()+generos.get(i));
        }
    }
    public void setLabelPais(String pais){
        labelPais.setText(pais);
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
        fa.seguirArtista(id,fgui.getActual().getNombre());
        fgui.showArtista(new Artista(id,labelArtista.getText(),isVerified,labelPais.getText()));
    }
}
