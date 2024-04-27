package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;

import java.util.ArrayList;

public class GestionContenido {
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;

    public GestionContenido(FachadaGui fgui, FachadaBaseDatos fbd){
        this.fgui=fgui;
        this.fbd=fbd;
    }

    public ArrayList<Contenido> buscar(String buscar){
        return fbd.buscar(buscar);
    }
    public ArrayList<Contenido> buscarMod(String buscar){
        return fbd.buscarMod(buscar);
    }
    public void eliminar(Contenido contenido){fbd.eliminarContenido(contenido);}
    public ArrayList<Playlist> playlistDefecto(){return fbd.playlistDefecto();}
    public ArrayList<Cancion> topCanciones(){return fbd.topCanciones();}
    public void crearFavoritos(String user){fbd.crearFavoritos(user);}
    public ArrayList<Playlist> tusPlaylist(String nombre){return fbd.tusPlaylist(nombre);}
    public ArrayList<Contenido> buscarContenidoArtista(String id){return fbd.buscarContenidoArtista(id);}

}
