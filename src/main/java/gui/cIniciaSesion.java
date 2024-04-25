package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.Oyente;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Scene;


public class cIniciaSesion {
    FachadaGui fgui;
    FachadaAplicacion fa;

    @FXML
    private Button btnEntrar;
    @FXML
    private Label labelNoExiste;
    @FXML
    private TextField fieldUsuario;
    @FXML
    private PasswordField fieldContrasena;
    @FXML
    private Label labelOlvidado;

    private HostServices hostServices;
    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui; this.fa=fa;
    }
    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
    public HostServices getHostServices(){
        return this.hostServices;
    }


    @FXML
    protected void clickEntrar() throws IOException {
        Oyente aux = fa.validarUsuario(fieldUsuario.getText(),fieldContrasena.getText());
        if (aux!=null)
            fgui.cargando(aux);
        else{
            aux=fa.validarAdmin(fieldUsuario.getText(),fieldContrasena.getText());
            if(aux!=null)
                fgui.showAdmin(aux);
            else labelNoExiste.setText("El usuario y/o contraseña no existe");
        }
    }
    @FXML
    protected void clickRegistrar() throws IOException {
        fgui.registrar();
    }

    @FXML
    protected void clickOlvidona() {
        String url = "https://www.youtube.com/shorts/16uJ-jxcKHo"; // MONDONGO
        getHostServices().showDocument(url);

    }

}