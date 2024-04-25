package aplicacion;

import baseDatos.*;
import gui.*;

import java.util.ArrayList;

public class FachadaAplicacion {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    GestionUsuarios gu;
    GestionContenido gc;

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
    }
    public ArrayList<Playlist> playlistDefecto(){return gc.playlistDefecto();}
    public ArrayList<Contenido> buscar(String buscar){
        return gc.buscar(buscar);
    }
    public ArrayList<Oyente> buscarUsuario(String buscar){return gu.buscar(buscar);}
    //public ArrayList<Playlist> playlistDefecto(){return gc.playlistDefecto();}
}
