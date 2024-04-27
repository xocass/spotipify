package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;

import java.sql.Time;
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
    public boolean cambiarExplicito(Contenido contenido){return fbd.cambiarExplicito(contenido);}
    public ArrayList<Playlist> playlistDefecto(){return fbd.playlistDefecto();}
    public ArrayList<Cancion> topCanciones(){return fbd.topCanciones();}
    public void crearFavoritos(String user){fbd.crearFavoritos(user);}
    public ArrayList<Playlist> tusPlaylist(String nombre){return fbd.tusPlaylist(nombre);}
    public ArrayList<Contenido> buscarContenidoArtista(String id){return fbd.buscarContenidoArtista(id);}
    public Playlist getPlaylistId(int id){return fbd.getPlaylistId(id);}
    public Album getAlbumId(int id){return fbd.getAlbumId(id);}
    public Podcast getPodcastId(int id){return fbd.getPodcastId(id);}
    public Time getDuracionPlaylist(int id){return fbd.getDuracionPlaylist(id);}
    public Time getDuracionAlbum(int id){return fbd.getDuracionAlbum(id);}
    public Time getDuracionPodcast(int id){return fbd.getDuracionPodcast(id);}
    public int nCancionesPlaylist(int id){return fbd.nCancionesPlaylist(id);}
    public int nCancionesAlbum(int id){return fbd.nCancionesAlbum(id);}
    public int nCapitulosPodcast(int id){return fbd.nCapitulosPodcast(id);}
    public ArrayList<String> generoAlbum(int id){return fbd.generoAlbum(id);}
    public ArrayList<String> generoPlaylist(int id){return fbd.generoPlaylist(id);}
    public ArrayList<Cancion> getCancionesAP(int id, char opcion){return fbd.getCancionesAP(id,opcion);}
    public ArrayList<Capitulo> getCapitulosPodcast(int id){return fbd.getCapitulosPodcast(id);}

}
