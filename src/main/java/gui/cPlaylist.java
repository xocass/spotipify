package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;

import java.io.IOException;

public class cPlaylist {
    boolean album;
    private FachadaGui fgui;
    private FachadaAplicacion fa;
    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }
    @FXML
    public void clickInicio() throws IOException {
        fgui.principal();
    }
    @FXML
    public void clickUsuario() throws IOException {
        fgui.showUsuario();
    }
    @FXML
    public void clickBiblioteca() throws IOException{
        fgui.showBiblioteca();
    }
    @FXML
    public void clickAjustes() throws IOException{
        fgui.showAjustes();
    }
    @FXML
    public void clickBuscar() throws IOException {
        fgui.showBuscar();
    }
}
