package gui;

import aplicacion.Artista;
import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public class cArtista {
    private FachadaGui fgui;
    private FachadaAplicacion fa;
    @FXML
    private HBox boxInicio;
    @FXML
    private HBox boxBiblioteca;
    @FXML
    private HBox boxBuscar;
    @FXML
    private HBox boxUsuario;
    @FXML
    private HBox boxAjustes;
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
    private String id;
    private boolean isVerified;

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
