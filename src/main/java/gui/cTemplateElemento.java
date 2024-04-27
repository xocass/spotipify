package gui;

import aplicacion.FachadaAplicacion;

public class cTemplateElemento {
    private char opcion; //a cancion        b capitulo
    private FachadaGui fgui;
    private FachadaAplicacion fa;

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }
}
