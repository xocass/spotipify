package gui;

import aplicacion.Album;
import aplicacion.Contenido;
import aplicacion.Cancion;
import aplicacion.Podcast;
import aplicacion.Capitulo;
import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private Label labelGenero;
    @FXML
    private Button btnEntrar;

    private FachadaGui fgui;
    private FachadaAplicacion fa;
    private cContenidoMod ccm;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa, cContenidoMod ccm) {
        this.fgui = fgui;
        this.fa = fa;
        this.ccm=ccm;
    }

    public void setLabelNombre(String nombre){labelNombre.setText(nombre);}
    public void setLabelGenero(String genero){labelGenero.setText(genero);}
    public void setLabelTipo(Contenido contenido){
        if(contenido instanceof Album album){
            if(album instanceof Cancion){
                labelTipo.setText("Canción");
            }else{
                labelTipo.setText("Álbum");
            }
        }else if(contenido instanceof Podcast podcast){
            if(podcast instanceof Capitulo){
                labelTipo.setText("Capítulo");
            }else{
                labelTipo.setText("Podcast");
            }

        }
    }
    public void setLabelCreadores(String creadores){labelCreadores.setText(labelCreadores.getText() + creadores);}

    public void setContenido(Contenido contenido){
        this.contenido=contenido;}
    @FXML
    public void clickEntrar() throws IOException {
        ccm.clickEntrar(contenido);
    }
}
