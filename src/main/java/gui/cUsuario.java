package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.Oyente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.w3c.dom.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;

public class cUsuario {
    private FachadaGui fgui;
    private FachadaAplicacion fa;

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

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa, Oyente oyente){
        this.fgui=fgui;
        this.fa=fa;
        fieldContraseña.setText(oyente.getContrasena());
        fieldCorreo.setText(oyente.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fechaNacimiento.setValue(LocalDate.parse(oyente.getFechanac(), formatter));
    }


    @FXML
    public void clickInicio() throws IOException {

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
