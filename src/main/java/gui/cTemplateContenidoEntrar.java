package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class cTemplateContenidoEntrar {
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelTipo;
    @FXML
    private Label labelDuracion;
    @FXML
    private CheckBox tickExplicito;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setLabelNombre(String nombre){labelNombre.setText(nombre);}
    public void setLabelTipo(String tipo){labelTipo.setText(tipo);}
    public void setLabelDuracion(String duracion){labelDuracion.setText(duracion);}
    public void setTickExplicito(Boolean explicito){tickExplicito.setSelected(explicito);}
}
