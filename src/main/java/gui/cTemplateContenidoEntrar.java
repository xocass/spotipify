package gui;

import aplicacion.Contenido;
import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
public class cTemplateContenidoEntrar {
    private Contenido contenido;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelTipo;
    @FXML
    private Label labelCreadores;
    @FXML
    private Button btnEntrar;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setLabelNombre(String nombre){labelNombre.setText(nombre);}
    public void setLabelTipo(int tipo){
        if(tipo==1){
            labelTipo.setText("Álbum");
        }else if(tipo==2){
            labelTipo.setText("Podcast");
        }else if(tipo==3){
            labelTipo.setText("Canción");
        }else{
            labelTipo.setText("Capítulo");
        }
    }
    public void setLabelCreadores(String creadores){labelCreadores.setText(labelCreadores.getText() + creadores);}

    public void setContenido(Contenido contenido){
        this.contenido=contenido;}
    public void clickEntrar() throws IOException {
         fgui.showContenidoMod(contenido);
    }
}
