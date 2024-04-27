package baseDatos;

import aplicacion.Contenido;
import aplicacion.Album;
import aplicacion.Oyente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoAlbumes extends AbstractDAO{
    public daoAlbumes(Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Contenido> buscar(String busqueda){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmAlbum=null;
        ResultSet rsAlbum;

        con=this.getConexion();

        try {
            stmAlbum=con.prepareStatement("select al.nombre as nombre, al.idalbum as idalbum, al.tipo as tipo, ar.nombreartistico as creador, ar.nombre as idartista "+
                    "from album al, componer c, artista ar "+
                    "where al.nombre like ? and al.idalbum=c.idalbum and c.idartista=ar.nombre");
            stmAlbum.setString(1, "%"+busqueda+"%");
            rsAlbum=stmAlbum.executeQuery();
            while (rsAlbum.next())
            {
                resultado.add(new Album(rsAlbum.getString("nombre"), rsAlbum.getString("creador"),
                        rsAlbum.getString("idartista"), rsAlbum.getInt("idalbum"), rsAlbum.getString("tipo")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmAlbum.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void eliminar(int eliminar){
        Connection con;
        PreparedStatement stmAlbum=null;

        con=this.getConexion();
        try {
            stmAlbum=con.prepareStatement("delete from album where idalbum = ? ");
            stmAlbum.setInt(1, eliminar);
            stmAlbum.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmAlbum.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void checkAlbumesVacios(int album){
        Connection con;
        PreparedStatement stmAlbum=null;
        ResultSet rsAlbum;
        int numCancionesRestantes= 0;

        con=this.getConexion();
        try {
            stmAlbum=con.prepareStatement("select count(*) as numCanciones "+
                    "from cancion where idalbum = ? ");
            stmAlbum.setInt(1, album);
            rsAlbum=stmAlbum.executeQuery();
            while (rsAlbum.next())
            {
                numCancionesRestantes= rsAlbum.getInt("numCanciones");
            }
            if (numCancionesRestantes == 0) {
                eliminar(album);
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmAlbum.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public ArrayList<Contenido> getAlbumesArtista(String id){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmAlbum=null;
        ResultSet rsAlbum;

        con=this.getConexion();

        try {
            stmAlbum=con.prepareStatement("select al.nombre as nombre, al.idalbum as idalbum, c.idartista as idartista "+
                    "from album al, componer c "+
                    "where al.idalbum= c.idalbum and c.idartista=?");
            stmAlbum.setString(1, id);
            rsAlbum=stmAlbum.executeQuery();
            while (rsAlbum.next())
            {
                resultado.add(new Album(rsAlbum.getString("nombre"), null,
                        rsAlbum.getString("idartista"), rsAlbum.getInt("idalbum"),null));
                System.out.println(rsAlbum.getString("nombre"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmAlbum.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public ArrayList<Album> getAlbumID(int idAlbum){
        ArrayList<Album> resultado=new ArrayList<>();

        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select a.nombre as nombreAlbum,ar.nombreartistico as nombreArtista, ar.nombre as idArtista, a.idalbum, a.tipo, a.añolanzamiento, a.iddiscografica "+
                    "from album a, componer c, artista ar "+
                    "where a.idalbum = ? and c.idartista= ar.nombre and c.idalbum=a.idalbum ");
            stmUsuario.setInt(1, idAlbum);
            rsUsuario=stmUsuario.executeQuery();
            while (rsUsuario.next())
            {
                resultado.add( new Album(rsUsuario.getString("nombreAlbum"),rsUsuario.getString("nombreArtistico"),
                        rsUsuario.getString("idArtista"),rsUsuario.getInt("a.idalbum"),
                        rsUsuario.getString("a.tipo"),rsUsuario.getInt("a.añolanzamiento"),
                        rsUsuario.getInt("a.iddiscografica")));

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}






