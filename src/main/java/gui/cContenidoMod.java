package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class cContenidoMod {
    private FachadaGui fgui;
    private FachadaAplicacion fa;
    @FXML
    private ImageView btnAtras;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }

    @FXML
    public void irAtras() throws IOException {
        fgui.irAtrasMod();
    }
}
