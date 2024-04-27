package gui;

import aplicacion.FachadaAplicacion;
import javafx.application.HostServices;

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

        }

        //enviar plan y tipo
        //luego cerrar ventana
    }

    public void clickCancelar(){
        //cerrar ventana
    }
}
