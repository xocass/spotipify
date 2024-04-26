package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.Contenido;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class cContenidoMod {
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
        ArrayList<Contenido> resultado;
        if (!fieldBuscar.getText().isEmpty()) {
            resultado = fa.buscarContenidoMod(fieldBuscar.getText());
            for (Contenido aux : resultado) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("templateContenidoEntrar.fxml"));
                vboxBuscar.getChildren().add(loader.load());

                cTemplateContenidoEntrar controller = loader.getController();

                controller.setLabelNombre(aux.getNombre());
                controller.setLabelTipo(aux.getTipo());
                for (int i = 0; i < aux.getCreador().size(); i++) {
                    if (i != 0) controller.setLabelCreadores(", ");
                    controller.setLabelCreadores(aux.getCreador().get(i));
                }

                controller.setContenido(aux);
                controller.setFachadas(this.fgui,this.fa);
            }
        }
    }

    public void clickEntrar(Contenido contenido) throws IOException{
        vboxBuscar.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("templateContenidoEliminar.fxml"));
        vboxBuscar.getChildren().add(loader.load());

        cTemplateContenidoEliminar controller = loader.getController();

        controller.setLabelNombre(contenido.getNombre());
        if(contenido.getTipo()>2){
            controller.setLabelDuracion(contenido.getDuracion().toString());
            controller.setTickExplicito(contenido.getExplicito());
        }else{
            controller.ocultarExplicito();
        }
        controller.setFachadas(this.fgui,this.fa);
    }
}
