package gui;

import aplicacion.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.sql.Time;
import java.io.IOException;
import java.util.ArrayList;

public class cTemplateContenidoEliminar {
    private Contenido contenido;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelDuracion;
    @FXML
    private CheckBox tickExplicito;
    @FXML
    private Button btnEliminar;
    @FXML
    private Label labelTipo;
    @FXML
    private Label labelCreadores;

    private FachadaGui fgui;
    private FachadaAplicacion fa;
    private cTemplateContenidoEntrar ctce;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa, cTemplateContenidoEntrar ctce) {
        this.fgui = fgui;
        this.fa = fa;
        this.ctce= ctce;
    }

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

    public void setContenido(Contenido contenido){this.contenido=contenido;}
    public void setLabelNombre(String nombre){labelNombre.setText(nombre);}
    public void setLabelDuracion(String duracion){labelDuracion.setText(duracion);}
    public void setTickExplicito(boolean explicito){tickExplicito.setSelected(explicito);}
    public void ocultarExplicito(){tickExplicito.setVisible(false);}
    @FXML
    public void cambiarExplicito() throws IOException{
        if(tickExplicito.isSelected()){
            tickExplicito.setSelected(false);
        }else{
            tickExplicito.setSelected(true);
        }
        fa.cambiarExplicito(contenido);
        fgui.showContenidoMod();
    }
    @FXML
    public void clickEliminar() throws IOException {
        fa.eliminarContenido(this.contenido);
        fgui.showContenidoMod();
    }
}
