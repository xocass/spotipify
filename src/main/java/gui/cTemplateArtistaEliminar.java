package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class cTemplateArtistaEliminar {
    @FXML
    private Label labelNombreArtistico;
    @FXML
    private Label labelPaisNacimiento;
    @FXML
    private CheckBox tickVerificado;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setLabelNombreArtistico(String nombre){labelNombreArtistico.setText(nombre);}
    public void setLabelPaisNacimiento(String paisNacimiento){labelPaisNacimiento.setText(paisNacimiento);}
    public void setTickVerificado(Boolean verificado){tickVerificado.setSelected(verificado);}
}
