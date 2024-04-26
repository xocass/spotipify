package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class cTemplateArtistaEliminar {
    private String nombre;
    @FXML
    private Label labelNombreArtistico;
    @FXML
    private Label labelPaisNacimiento;
    @FXML
    private CheckBox tickVerificado;
    @FXML
    private Button btnEliminar;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }
    public void setNombre(String nombre){this.nombre= nombre;}
    public void setLabelNombreArtistico(String nombre){labelNombreArtistico.setText(nombre);}
    public void setLabelPaisNacimiento(String paisNacimiento){labelPaisNacimiento.setText(paisNacimiento);}
    public void setTickVerificado(Boolean verificado){tickVerificado.setSelected(verificado);}

    @FXML
    public void clickEliminar() throws IOException {
        fa.eliminarArtista(this.nombre);
        fgui.showArtistasMod();
    }
}
