package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class cAdmin {
    FachadaGui fgui;
    FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }
    @FXML
    private HBox boxUsuario;
    @FXML
    private HBox boxArtistas;
    @FXML
    private HBox boxContenido;

    @FXML
    public void clickUsuario() throws IOException {
        fgui.showUsuarioMod();
    }
    @FXML
    public void clickArtistas() throws IOException{
        fgui.showArtistasMod();
    }
    @FXML
    public void clickContenidoMod() throws IOException{
        fgui.showContenidoMod();
    }

}
