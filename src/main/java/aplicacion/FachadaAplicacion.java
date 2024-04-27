package aplicacion;

import baseDatos.*;
import gui.*;

import java.sql.Time;
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
    public ArrayList<Usuario> buscarU(String buscar){
        return gu.buscarU(buscar);
    }
    public ArrayList<Contenido> buscarContenidoMod(String buscar){return gc.buscarMod(buscar);}
    public ArrayList<Oyente> buscarOyente(String buscar){return gu.buscarOyente(buscar);}
    public ArrayList<Artista> buscarArtista(String buscar){return gu.buscarArtista(buscar);}
    public void eliminarOyente(String nombre){gu.eliminarOyente(nombre);}
    public void eliminarArtista(String nombre){gu.eliminarArtista(nombre);}
    public void eliminarContenido(Contenido contenido){gc.eliminar(contenido);}
    public boolean cambiarVerificado(String nombre){return gu.cambiarVerificado(nombre);}
    public boolean cambiarExplicito(Contenido contenido){return gc.cambiarExplicito(contenido);}
    public ArrayList<Artista> verificados(){return gu.verificados();}
    public ArrayList<Cancion> topCanciones(){return gc.topCanciones();}
    public ArrayList<String> siguiendo(String nombre){return gu.siguiendo(nombre);}
    public ArrayList<String> seguidores(String nombre){return gu.seguidores(nombre);}

    public ArrayList<String> siguiendoArtista(String nombre){return gu.siguiendoArtista(nombre);}
    public ArrayList<Playlist> tusPlaylist(String nombre){return gc.tusPlaylist(nombre);}
    public ArrayList<String> getGeneros(String nombre){return gu.getGeneros(nombre);}
    public int getnSeguidores(String nombre){return gu.getnSeguidores(nombre);}
    public int getnSeguidoresU(String nombre){return gu.getnSeguidoresU(nombre);}
    public int getnSeguidosU(String nombre){return gu.getnSeguidosU(nombre);}

    public void seguirArtista(String artista, String oyente){gu.seguirArtista(artista,oyente);}
    public void seguirPerfil(String seguido, String seguidor){gu.seguirPerfil(seguido,seguidor);}

    public ArrayList<Contenido> buscarContenidoArtista(String id){return gc.buscarContenidoArtista(id);}
    public ArrayList<String> buscarSponsors(String id){return gu.buscarSponsors(id);}

    public void actualizarPlanUsuario(String usuario, String plan, int tipo){gu.actualizarPlanUsuario(usuario, plan, tipo);}
    public String getPlan(String id){return gu.getPlan(id);}

    public Oyente actualizarUsuario(String usuario, String contrasena, String email, String nacimiento){return gu.actualizarUsuario(usuario, contrasena, email, nacimiento);}

    public void actualizarEscuchando(String usuario, Contenido contenido){gu.actualizarEscuchando(usuario, contenido);}

    public void noEscuchando(String usuario){gu.noEscuchando(usuario);}

    public Playlist getPlaylistId(int id){return gc.getPlaylistId(id);}
    public Album getAlbumId(int id){return gc.getAlbumId(id);}
    public Podcast getPodcastId(int id){return gc.getPodcastId(id);}
    public Time getDuracionPlaylist(int id){return gc.getDuracionPlaylist(id);}
    public Time getDuracionAlbum(int id){return gc.getDuracionAlbum(id);}
    public Time getDuracionPodcast(int id){return gc.getDuracionPodcast(id);}
}
