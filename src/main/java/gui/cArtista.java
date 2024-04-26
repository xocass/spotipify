package gui;

import aplicacion.Artista;
import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class cArtista {
    private Artista artista;
    private FachadaGui fgui;
    private FachadaAplicacion fa;
    @FXML
    private Label labelArtista;
    @FXML
    private Label labelGeneros;


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
}
