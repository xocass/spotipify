package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;

import java.util.ArrayList;

public class GestionContenido {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    public GestionContenido(FachadaGui fgui, FachadaBaseDatos fbd){
        this.fgui=fgui;
        this.fbd=fbd;
    }

    public ArrayList<Contenido> buscar(String buscar){
        return fbd.buscar(buscar);
    }
    public ArrayList<Playlist> playlistDefecto(){return fbd.playlistDefecto();}
    public ArrayList<Cancion> topCanciones(){return fbd.topCanciones();}

}
