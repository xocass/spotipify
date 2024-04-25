package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class cPrincipal {
    FachadaGui fgui;
    FachadaAplicacion fa;

    public void setFachadas (FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }

    @FXML
    private HBox boxBuscar;
    @FXML
    private HBox boxBiblioteca;
    @FXML
    private HBox boxUsuario;
    @FXML
    private HBox boxAjustes;

    @FXML
    public void clickUsuario() throws IOException {
        fgui.showUsuario();
    }
    @FXML
    public void clickBuscar() throws IOException{
        fgui.showBuscar();
    }
    @FXML
    public void clickBiblioteca() throws IOException{
        fgui.showBiblioteca();
    }
    @FXML
    public void clickAjustes() throws IOException{
        fgui.showAjustes();
    }
}
