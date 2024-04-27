package aplicacion;

import aplicacion.FachadaAplicacion;
import aplicacion.Oyente;
import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;

import java.util.ArrayList;

public class GestionUsuarios {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd){
        this.fgui=fgui;
        this.fbd=fbd;
    }

    public Oyente validarUsuario(String user, String pass){
       return fbd.validarUsuario(user,pass);
    }
    public Oyente validarAdmin(String user, String pass){
        return fbd.validarAdmin(user,pass);
    }
    public boolean comprobarNombre(String user){return fbd.comprobarNombre(user);}
    public void registrarUsuario(String usuario, String contrasena, String email, String nacimiento){
        fbd.registrarUsuario(usuario,contrasena,email,nacimiento);
    }
    public ArrayList<Oyente> buscarOyente(String buscar){return fbd.buscarOyente(buscar);}
    public ArrayList<Artista> buscarArtista(String buscar){return fbd.buscarArtista(buscar);}
    public void eliminarOyente(String nombre){fbd.eliminarOyente(nombre);}
    public void eliminarArtista(String nombre){fbd.eliminarArtista(nombre);}
    public boolean cambiarVerificado(String nombre){return fbd.cambiarVerificado(nombre);}
    public ArrayList<Artista> verificados(){return fbd.verificados();}
    public ArrayList<String> siguiendo(String nombre){return fbd.siguiendo(nombre);}
    public ArrayList<String> siguiendoArtista(String nombre){return fbd.siguiendoArtista(nombre);}
    public ArrayList<String> getGeneros(String nombre){return fbd.getGeneros(nombre);}
    public int getSeguidores(String nombre){return fbd.getSeguidores(nombre);}
    public int getSeguidoresU(String nombre){return fbd.getSeguidoresU(nombre);}

    public ArrayList<Usuario> buscarU(String buscar){
        return fbd.buscarU(buscar);
    }

    public void seguirArtista(String artista, String oyente){fbd.seguirArtista(artista,oyente);}
    public ArrayList<String> buscarSponsors(String id){return fbd.buscarSponsors(id);}

    public void actualizarPlanUsuario(String usuario, String plan, int tipo){fbd.actualizarPlanUsuario(usuario, plan, tipo);}
}
