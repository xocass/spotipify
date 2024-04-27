package gui;

import aplicacion.FachadaAplicacion;
import javafx.application.HostServices;
import javafx.fxml.FXML;

public class cTransaccionCurso {

    FachadaGui fgui;
    FachadaAplicacion fa;

    int tipo;

    public void setTipo(int tipo){
        this.tipo = tipo;
    }

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }
    @FXML
    public void cambiarPlan(){

        String plan;

        switch (this.tipo){
            case 0:
                plan = "Básico";
                break;
            case 1:
                plan = "Premium";
                break;
            case 2:
                plan = "Estudiante";
                break;
            case 3:
                plan = "Anual";
                break;
            default:
                plan = "Básico";
                break;
        }

        //enviar plan y tipo
        //luego cerrar ventana
        fa.actualizarPlanUsuario(fgui.getActual().getNombre(),plan,this.tipo);
        fgui.cerrarTransaccionEnCurso();
    }
    @FXML
    public void clickCancelar(){
        fgui.cerrarTransaccionEnCurso();
    }
}
