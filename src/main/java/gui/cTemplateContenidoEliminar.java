package gui;

import aplicacion.Contenido;
import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.sql.Time;
import java.io.IOException;
import java.util.ArrayList;

public class cTemplateContenidoEliminar {
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelDuracion;
    @FXML
    private CheckBox tickExplicito;
    @FXML
    private Button btnEliminar;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setLabelNombre(String nombre){labelNombre.setText(nombre);}
    public void setLabelDuracion(String duracion){labelDuracion.setText(duracion);}
    public void setTickExplicito(boolean explicito){tickExplicito.setSelected(explicito);}
    public void cambiarExplicito(){

    }

    public void clickEliminar() throws IOException {

    }
}
