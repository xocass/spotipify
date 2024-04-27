package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.sql.Time;

public class cTemplateElemento {
    private char opcion; //a cancion        b capitulo
    private FachadaGui fgui;
    private FachadaAplicacion fa;
    @FXML
    private ImageView megusta;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelTiempo;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }

    public void setLabelNombre(String nombre) {
        labelNombre.setText(nombre);
    }
    public void setLabelTiempo(Time tiempo){
        labelTiempo.setText(tiempo.toString());
    }
}
