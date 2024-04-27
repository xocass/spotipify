package gui;

import aplicacion.FachadaAplicacion;
import javafx.application.HostServices;
import javafx.fxml.FXML;

import java.io.IOException;

public class cActualizarPlan {
    FachadaGui fgui;
    FachadaAplicacion fa;

    private HostServices hostServices;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
    public HostServices getHostServices(){
        return this.hostServices;
    }
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
    public void clickUsuario() throws IOException {
        fgui.showUsuario();
    }
    @FXML
    public void clickAjustes() throws IOException{
        fgui.showAjustes();
    }

    @FXML
    protected void clickInfo() {
        String url = "https://www.youtube.com/shorts/16uJ-jxcKHo"; // MONDONGO
        getHostServices().showDocument(url);

    }
    @FXML
    public void clickNormal() throws IOException{
        fgui.showTransaccionEnCurso(0);
    }

    @FXML
    public void clickPremium() throws IOException{
        fgui.showTransaccionEnCurso(1);
    }
    @FXML
    public void clickEstudiante() throws IOException{
        fgui.showTransaccionEnCurso(2);
    }
    @FXML
    public void clickAnual() throws IOException{
        fgui.showTransaccionEnCurso(3);
    }


}


