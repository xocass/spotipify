package baseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import aplicacion.Cancion;
import aplicacion.Contenido;
import aplicacion.Oyente;
import aplicacion.Artista;
import aplicacion.Playlist;

public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private daoUsuarios daoUsuarios;
    private daoArtista daoArtista;
    private daoAlbumes daoAlbumes;
    private daoPrograma daoPrograma;
    private daoPlaylist daoPlaylist;
    private daoCanciones daoCanciones;
    private daoCapitulos daoCapitulos;

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
            this.daoPrograma = new daoPrograma(conexion,fa);
            this.daoPlaylist = new daoPlaylist(conexion,fa);
            this.daoCanciones = new daoCanciones(conexion,fa);
            this.daoCapitulos= new daoCapitulos(conexion, fa);

        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
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
        aux=daoPrograma.buscar(buscar);
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
        aux = daoArtista.buscar(buscar);
        if (!aux.isEmpty()){
            resultado.addAll(aux);
        }
        return resultado;
    }
    public ArrayList<Contenido> buscarMod(String buscar){
        ArrayList<Contenido> resultado = new ArrayList<>();
        ArrayList<Contenido> aux;
        aux=daoAlbumes.buscar(buscar);
        if(!aux.isEmpty()) {
            resultado.addAll(aux);
        }
        aux=daoPrograma.buscar(buscar);
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
        aux=daoArtista.buscarMod(buscar);
        if(!aux.isEmpty()) {
            resultado.addAll(aux);
        }
        return resultado;
    }
    public ArrayList<Playlist> playlistDefecto(){return daoPlaylist.playlistDefecto();}
    public ArrayList<Oyente> verificados(){return daoArtista.verificados();}
    public ArrayList<Cancion> topCanciones(){return daoCanciones.topCanciones();}
    public ArrayList<String> siguiendo(String nombre){return daoUsuarios.siguiendo(nombre);}
    public void crearFavoritos(String user){daoPlaylist.crearFavoritos(user);}
    public ArrayList<String> siguiendoArtista(String nombre){return daoArtista.siguiendoArtista(nombre);}
    public ArrayList<Playlist> tusPlaylist(String nombre){return daoPlaylist.tusPlaylist(nombre);}

}
