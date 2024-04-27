package baseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import aplicacion.*;

public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private daoUsuarios daoUsuarios;
    private daoArtista daoArtista;
    private daoAlbumes daoAlbumes;
    private daoPodcast daoPodcast;
    private daoPlaylist daoPlaylist;
    private daoCanciones daoCanciones;
    private daoCapitulos daoCapitulos;
    private daoSponsor daoSponsor;

    public FachadaBaseDatos (aplicacion.FachadaAplicacion fa){
        Properties configuracion = new Properties();
        this.fa=fa;
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();


            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                            configuracion.getProperty("servidor")+":"+
                            configuracion.getProperty("puerto")+"/"+
                            configuracion.getProperty("baseDatos"),
                    usuario);

            this.daoUsuarios = new daoUsuarios(conexion, fa);
            this.daoArtista = new daoArtista(conexion,fa);
            this.daoAlbumes = new daoAlbumes(conexion,fa);
            this.daoPodcast = new daoPodcast(conexion,fa);
            this.daoPlaylist = new daoPlaylist(conexion,fa);
            this.daoCanciones = new daoCanciones(conexion,fa);
            this.daoCapitulos= new daoCapitulos(conexion, fa);
            this.daoSponsor= new daoSponsor(conexion, fa);

        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Oyente validarUsuario(String user, String pass){return daoUsuarios.validarUsuario(user,pass);}
    public Oyente validarAdmin(String user, String pass){return daoUsuarios.validarAdmin(user,pass);}
    public boolean comprobarNombre(String user){
        return (daoUsuarios.comprobarAdmin(user) == null) && (daoUsuarios.comprobarArtista(user) == null);
    }

    public void registrarUsuario(String usuario, String contrasena, String email, String nacimiento) {
        daoUsuarios.registrarUsuario(usuario, contrasena, email, nacimiento);
    }

    public ArrayList<Contenido> buscar(String buscar){
        ArrayList<Contenido> resultado = new ArrayList<>();
        ArrayList<Contenido> aux;
        aux=daoAlbumes.buscar(buscar);
        if(!aux.isEmpty()) {
            resultado.addAll(aux);
        }
        aux= daoPodcast.buscar(buscar);
        if(!aux.isEmpty()){
            resultado.addAll(aux);
        }
        int tam=resultado.size();
        for (int i=0;i<tam;i++) {
            for(int j=i+1;j<tam;j++){
                if(resultado.get(i).getNombre().equals(resultado.get(j).getNombre())){
                    resultado.get(i).getCreador().add(resultado.get(j).getCreador().get(0));
                    resultado.remove(j);
                    tam--;
                    j--;
                }
            }
        }
        return resultado;
    }
    public ArrayList<Usuario> buscarU(String buscar){
        ArrayList<Usuario> resultado = new ArrayList<>();
        resultado.addAll(daoArtista.buscar(buscar));
        resultado.addAll(daoUsuarios.buscar(buscar));
        return resultado;
    }
    public ArrayList<Contenido> buscarMod(String buscar){
        ArrayList<Contenido> resultado = new ArrayList<>();
        ArrayList<Contenido> aux;
        aux=daoAlbumes.buscar(buscar);
        if(!aux.isEmpty()) {
            resultado.addAll(aux);
        }
        aux= daoPodcast.buscar(buscar);
        if(!aux.isEmpty()){
            resultado.addAll(aux);
        }
        aux = daoCanciones.buscar(buscar);
        if (!aux.isEmpty()){
            resultado.addAll(aux);
        }
        aux = daoCapitulos.buscar(buscar);
        if (!aux.isEmpty()){
            resultado.addAll(aux);
        }
        int tam=resultado.size();
        for (int i=0;i<tam;i++) {
            for(int j=i+1;j<tam;j++){
                if(resultado.get(i).getNombre().equals(resultado.get(j).getNombre()) && !resultado.get(i).getCreador().contains(resultado.get(j).getCreador().get(0))){
                    resultado.get(i).getCreador().add(resultado.get(j).getCreador().get(0));
                    resultado.remove(j);
                    tam--;
                    j--;
                }
            }
        }

        return resultado;
    }

    /*public Contenido unificarContenido(int id, int tipo){
        System.out.println(((Integer)id).toString());
        ArrayList<Contenido> resultado = new ArrayList<>();
        ArrayList<Contenido> aux;
        switch (tipo){

            case 0: //ALBUM
                resultado.addAll(daoAlbumes.getAlbumID(id));
                break;
            case 1: //PODCAST
                resultado.addAll(daoPodcast.getPodcastID(id));
                break;
        }

        int tam=resultado.size();
        for (int i=1;i<tam;i++) {
                if(resultado.get(0).getNombre().equals(resultado.get(i).getNombre())){
                    resultado.get(0).getCreador().add(resultado.get(i).getCreador().get(0));
                }
        }

        return resultado.get(0);
    }*/
    public void eliminarOyente(String nombre){daoUsuarios.eliminar(nombre);}
    public void eliminarArtista(String nombre){daoArtista.eliminar(nombre);}
    public void eliminarContenido(Contenido contenido){
        if(contenido instanceof Album album) {
            if(album instanceof Cancion cancion){
                daoCanciones.eliminar(cancion.getIdCancion());
                daoAlbumes.checkAlbumesVacios(cancion.getIdAlbum());
            }else {
                daoAlbumes.eliminar(album.getIdAlbum());
            }
            daoArtista.checkGeneros(contenido.getIdartista().get(0));
        }else{
            if(contenido instanceof Capitulo capitulo){
                daoCapitulos.eliminar(capitulo.getIdCapitulo());
            }else{
                Podcast podcast= (Podcast)contenido;
                daoPodcast.eliminar(podcast.getIdPodcast());
            }
        }

    }
    public ArrayList<Oyente> buscarOyente(String buscar){
        ArrayList<Oyente> resultado = new ArrayList<>();
        ArrayList<Oyente> aux;
        aux=daoUsuarios.buscar(buscar);
        if(!aux.isEmpty()) {
            resultado.addAll(aux);
        }
        return resultado;
    }
    public ArrayList<Artista> buscarArtista(String buscar){
        ArrayList<Artista> resultado = new ArrayList<>();
        ArrayList<Artista> aux;
        aux=daoArtista.buscar(buscar);
        if(!aux.isEmpty()) {
            resultado.addAll(aux);
        }
        return resultado;
    }
    public boolean cambiarVerificado(String nombre){return daoArtista.cambiarVerificado(nombre);}
    public boolean cambiarExplicito(Contenido contenido) {
        boolean explicito= false;
        if (contenido instanceof Album album && album instanceof Cancion cancion) {
            explicito= daoCanciones.cambiarExplicito(cancion.getIdCancion());
        } else if (contenido instanceof Podcast podcast && podcast instanceof Capitulo capitulo) {
            explicito= daoCapitulos.cambiarExplicito(capitulo.getIdCapitulo());
        }
        return explicito;
    }
    public ArrayList<Playlist> playlistDefecto(){return daoPlaylist.playlistDefecto();}
    public ArrayList<Artista> verificados(){return daoArtista.verificados();}
    public ArrayList<Cancion> topCanciones(){return daoCanciones.topCanciones();}
    public ArrayList<String> siguiendo(String nombre){return daoUsuarios.siguiendo(nombre);}
    public ArrayList<String> seguidores(String nombre){return daoUsuarios.seguidores(nombre);}

    public void crearFavoritos(String user){daoPlaylist.crearFavoritos(user);}
    public ArrayList<String> siguiendoArtista(String nombre){return daoArtista.siguiendoArtista(nombre);}
    public ArrayList<Playlist> tusPlaylist(String nombre){return daoPlaylist.tusPlaylist(nombre);}
    public ArrayList<String> getGeneros(String nombre){return daoArtista.getGeneros(nombre);}
    public int getnSeguidores(String nombre){return daoArtista.getnSeguidores(nombre);}
    public int getnSeguidoresU(String nombre){return daoUsuarios.getnSeguidoresU(nombre);}
    public int getnSeguidosU(String nombre){return daoUsuarios.getnSeguidosU(nombre);}
    public void seguirArtista(String artista, String oyente){daoArtista.seguirArtista(artista,oyente);}
    public void seguirPerfil(String seguido, String seguidor){daoUsuarios.seguirPerfil(seguido,seguidor);}


    public ArrayList<Contenido> buscarContenidoArtista(String id){
        ArrayList<Contenido> resultado=new ArrayList<>(), aux;
        aux=daoPodcast.getPodcastArtista(id);
        if(!aux.isEmpty())
            resultado.addAll(aux);
        aux=daoAlbumes.getAlbumesArtista(id);
        if(!aux.isEmpty())
            resultado.addAll(aux);
        return resultado;
    }
    public ArrayList<String> buscarSponsors(String id){
        return daoSponsor.getSponsor(id);
    }

    public void actualizarPlanUsuario(String usuario, String plan, int tipo){daoUsuarios.actualizarPlanUsuario(usuario, plan, tipo);}
    public String getPlan(String id){return daoUsuarios.getPlan(id);}

    public Oyente actualizarUsuario(String usuario, String contrasena, String email, String nacimiento){return daoUsuarios.actualizarUsuario(usuario, contrasena, email, nacimiento);}

    public void actualizarEscuchando(String usuario, Contenido contenido){daoUsuarios.actualizarEscuchando(usuario, contenido);}

    public void noEscuchando(String usuario){daoUsuarios.noEscuchando(usuario);}

    public Playlist getPlaylistId(int id){return daoPlaylist.getPlaylistID(id);}
    public Album getAlbumId(int id){return daoAlbumes.getAlbumId(id);}
    public Podcast getPodcastId(int id){return (Podcast) daoPodcast.getPodcastId(id);}
}
