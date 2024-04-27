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
}
