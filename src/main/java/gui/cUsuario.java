package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class cUsuario {
    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }

    @FXML
    private HBox boxInicio;
    @FXML
    private HBox boxBuscar;
    @FXML
    private HBox boxBiblioteca;
    @FXML
    private HBox boxAjustes;
    @FXML
    public void clickInicio() throws IOException {
        fgui.principal();
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
