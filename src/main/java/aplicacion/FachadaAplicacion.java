package aplicacion;

import baseDatos.*;
import gui.*;

import java.util.ArrayList;

public class FachadaAplicacion {
    private FachadaGui fgui;
    private FachadaBaseDatos fbd;
    private  GestionUsuarios gu;
    private GestionContenido gc;

    public FachadaAplicacion(FachadaGui fgui){
        this.fgui=fgui;
        fbd = new FachadaBaseDatos(this);
        gu=new GestionUsuarios(fgui,fbd);
        gc= new GestionContenido(fgui,fbd);
    }
    public void muestraExcepcion(String e){
        fgui.muestraExcepcion(e);
    }

    public Oyente validarUsuario(String user, String pass) {
        return gu.validarUsuario(user,pass);
    }
    public Oyente validarAdmin(String user, String pass) {
        return gu.validarAdmin(user,pass);
    }
    public boolean comprobarNombre(String user){return gu.comprobarNombre(user);}

    public void registrarUsuario(String usuario, String contrasena, String email, String nacimiento){
        gu.registrarUsuario(usuario,contrasena,email,nacimiento);
        gc.crearFavoritos(usuario);
    }
    public ArrayList<Playlist> playlistDefecto(){return gc.playlistDefecto();}
    public ArrayList<Contenido> buscar(String buscar){
        return gc.buscar(buscar);
    }
    public ArrayList<Contenido> buscarContenidoMod(String buscar){return gc.buscarMod(buscar);}
    public ArrayList<Oyente> buscarOyente(String buscar){return gu.buscarOyente(buscar);}
    public ArrayList<Artista> buscarArtista(String buscar){return gu.buscarArtista(buscar);}
    public void eliminarOyente(String nombre){gu.eliminarOyente(nombre);}
    public void eliminarArtista(String nombre){gu.eliminarArtista(nombre);}
    public void eliminarContenido(Contenido contenido){gc.eliminar(contenido);}
    public ArrayList<Oyente> verificados(){return gu.verificados();}
    public ArrayList<Cancion> topCanciones(){return gc.topCanciones();}
    public ArrayList<String> siguiendo(String nombre){return gu.siguiendo(nombre);}
    public ArrayList<String> siguiendoArtista(String nombre){return gu.siguiendoArtista(nombre);}
    public ArrayList<Playlist> tusPlaylist(String nombre){return gc.tusPlaylist(nombre);}
    public ArrayList<String> getGeneros(String nombre){return gu.getGeneros(nombre);}

}
