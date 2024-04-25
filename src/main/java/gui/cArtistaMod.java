package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.Artista;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class cArtistaMod {
    private FachadaGui fgui;
    private FachadaAplicacion fa;
    @FXML
    private ImageView btnAtras;
    @FXML
    private VBox vboxBuscar;
    @FXML
    private TextField fieldBuscar;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }

    @FXML
    public void irAtras() throws IOException {
        fgui.irAtrasMod();
    }
    @FXML
    public void clickBuscar() throws IOException {
        vboxBuscar.getChildren().clear();
        ArrayList<Artista> resultado;
        if (!fieldBuscar.getText().isEmpty()) {
            resultado = fa.buscarArtista(fieldBuscar.getText());
            for (Artista aux : resultado) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateArtistaEliminar.fxml"));
                vboxBuscar.getChildren().add(loader.load());

                cTemplateUsuarioEliminar controller = loader.getController();

                controller.setLabelNombre(aux.getNombre());
                controller.setLabelEmail(aux.getContrasena());

                controller.setFachadas(this.fgui,this.fa);
            }
        }
    }
}
