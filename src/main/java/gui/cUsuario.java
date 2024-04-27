package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.Oyente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private HBox btnPlan;
    @FXML
    private TextField fieldContraseña;
    @FXML
    private TextField fieldCorreo;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private Label labelNombreUsuario;
    @FXML
    private Label labelError;
    @FXML
    private Label labelArtista;
    @FXML
    private Label labelMinutos;


    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa, Oyente oyente){
        this.fgui=fgui;
        this.fa=fa;
        fieldContraseña.setText(oyente.getContrasena());
        fieldCorreo.setText(oyente.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fechaNacimiento.setValue(LocalDate.parse(oyente.getFechanac(), formatter));
        labelNombreUsuario.setText(fgui.getActual().getNombre());
        labelError.setText("");
    }


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
        if(fieldContraseña.getText().isEmpty()||fieldCorreo.getText().isEmpty()||fechaNacimiento.getValue()==null){
            labelError.setText("¡INTRODUZCA TODOS LOS CAMPOS!");
        }else{
            labelError.setText("");
            fa.actualizarUsuario(fgui.getActual().getNombre(),fieldContraseña.getText(),fieldCorreo.getText(),fechaNacimiento.getValue().toString());
        }

    }
    @FXML
    public void clickActualizarPlan() throws IOException{
        fgui.showActualizarPlan();
    }
}
