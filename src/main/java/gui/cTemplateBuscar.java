package gui;

import aplicacion.FachadaAplicacion;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class cTemplateBuscar {
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelTipo;
    @FXML
    private Label labelArtista;

    private FachadaGui fgui;
    private FachadaAplicacion fa;

    private int tipo; //ARTISTA= 0;
                        //ALBUM = 1;
                        //PODCAST = 2;

    public void setTipo(int tipo){
        this.tipo = tipo;
    }

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa) {
        this.fgui = fgui;
        this.fa = fa;
    }

    public void setLabelNombre(String nombre){
        labelNombre.setText(nombre);
    }
    public void setLabelTipo(String tipo){
        labelTipo.setText(tipo);
    }
    public void setLabelArtista(String artista){
        labelArtista.setText(labelArtista.getText() + artista);
    }
    @FXML
    public void clickEntrar(){
        switch (this.tipo){
            case 0: //ARTISTA

                break;
            case 1: //ALBUM

                break;

            case 2: //PODCAST

                break;



        }
    }
}

