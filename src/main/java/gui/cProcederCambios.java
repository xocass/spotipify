package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class cProcederCambios {
    @FXML
    private Button btnSi;
    @FXML
    private javafx.scene.control.Button btnNo;
    private String nombre;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setNombre(String nombre) {this.nombre = nombre;}

    @FXML
    public void clickSi() throws IOException {
        fa.eliminarOyente(nombre);
        fgui.showOyenteMod();
    }
    @FXML
    public void clickNo() throws IOException{
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.close();

    }
}
