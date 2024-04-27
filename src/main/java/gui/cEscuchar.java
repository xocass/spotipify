package gui;

import aplicacion.Contenido;
import aplicacion.FachadaAplicacion;
import aplicacion.Oyente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class cEscuchar {

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    @FXML
    private Label labelArtista;
    @FXML
    private Label labelContenido;
    @FXML
    private Button btnAtras;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa, Contenido contenido){
        this.fgui=fgui;
        this.fa=fa;
        String artistas = "";
        for(int i = 0; i<contenido.getCreador().size(); i++ ){
            if(i!=0)artistas = artistas+", "+contenido.getCreador().get(i);
            else artistas = artistas + contenido.getCreador().get(i);
        }
        labelArtista.setText(artistas);
        labelContenido.setText(contenido.getNombre());

    }
}
