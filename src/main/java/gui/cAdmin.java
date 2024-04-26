package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane boxUsuario;
    @FXML
    private AnchorPane boxArtistas;
    @FXML
    private AnchorPane boxContenido;


    @FXML
    public void clickUsuario() throws IOException {
        fgui.showOyenteMod();
    }
    @FXML
    public void clickArtistas() throws IOException{
        fgui.showArtistasMod();
    }
    @FXML
    public void clickContenido() throws IOException{
        fgui.showContenidoMod(null);
    }


}
