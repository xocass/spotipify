package gui;

import aplicacion.Contenido;
import aplicacion.FachadaAplicacion;
import aplicacion.Playlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public class cPrincipal {
    FachadaGui fgui;
    FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
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
    private HBox playlistBox;

    @FXML
    public void clickUsuario() throws IOException {
        fgui.showUsuario();
    }

    @FXML
    public void clickBuscar() throws IOException {
        fgui.showBuscar();
    }

    @FXML
    public void clickBiblioteca() throws IOException {
        fgui.showBiblioteca();
    }

    @FXML
    public void clickAjustes() throws IOException {
        fgui.showAjustes();
    }

    public void iniciar() throws IOException {
        ArrayList<Playlist> defecto = new ArrayList<>();
        defecto = fa.playlistDefecto();
        for (Playlist aux : defecto) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("templateCancion.fxml"));
            playlistBox.getChildren().add(loader.load());
            cTemplateCancion controller = loader.getController();
            controller.setLabelUsuario(aux.getNombre());
            controller.setFachadas(this.fgui, this.fa);
        }
    }
}
