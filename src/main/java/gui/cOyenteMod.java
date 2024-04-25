package gui;

import aplicacion.Oyente;
import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class cOyenteMod {
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
    public void irAtras() throws IOException{
        fgui.irAtrasMod();
    }
    @FXML
    public void clickBuscar() throws IOException {
        vboxBuscar.getChildren().clear();
        ArrayList<Oyente> resultado;
        if (!fieldBuscar.getText().isEmpty()) {
            resultado = fa.buscarUsuario(fieldBuscar.getText());
            for (Oyente aux : resultado) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateUsuarioEliminar.fxml"));
                vboxBuscar.getChildren().add(loader.load());

                cTemplateUsuarioEliminar controller = loader.getController();

                controller.setLabelNombre(aux.getNombre());
                controller.setLabelEmail(aux.getEmail());

                controller.setFachadas(this.fgui,this.fa);
            }
        }
    }

}
