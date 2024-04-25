package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.w3c.dom.Text;

import java.io.IOException;

public class cUsuario {
    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }

    @FXML
    private HBox boxInicio;
    @FXML
    private HBox boxBuscar;
    @FXML
    private HBox boxBiblioteca;
    @FXML
    private HBox boxAjustes;
    @FXML
    private Button btnActualizar;
    @FXML
    private TextField fieldContraseña;
    @FXML
    private TextField fieldCorreo;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    public void clickInicio() throws IOException {
        fgui.principal();
    }
    public void iniciar(){
        fieldCorreo.setText("");
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
    public void clickAjustes() throws IOException{
        fgui.showAjustes();
    }
    @FXML
    public void clickActualizar(){
        if(fieldContraseña.getText().isEmpty()||fieldCorreo.getText().isEmpty()||fechaNacimiento.getValue()==null);

    }
}
