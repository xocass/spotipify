package baseDatos;

import aplicacion.Contenido;
import aplicacion.Album;
import aplicacion.Oyente;

import java.sql.*;
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

    public Album getAlbumId(int idAlbum){
        Album resultado=null;
        int i=0;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select a.nombre as nombreAlbum, ar.nombreartistico as nombreArtistico, ar.nombre as idArtista, a.idalbum as id, a.tipo as tipo "+
                    "from album a, componer c, artista ar "+
                    "where a.idalbum = ? and c.idartista= ar.nombre and c.idalbum=a.idalbum ");
            stmUsuario.setInt(1, idAlbum);
            rsUsuario=stmUsuario.executeQuery();
            while (rsUsuario.next())
            {
                if(i==0)resultado= new Album(rsUsuario.getString("nombreAlbum"),rsUsuario.getString("nombreArtistico"),
                        rsUsuario.getString("idArtista"),rsUsuario.getInt("id"),
                        rsUsuario.getString("tipo"),-1,
                        -1);
                else resultado.getCreador().add(rsUsuario.getString("nombreArtistico"));
                i++;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
   public Time getDuracionAlbum(int id){
       Connection con;
       PreparedStatement stmAlbum=null;
       ResultSet rsAlbum;
       Time tiempo= null;

       con=this.getConexion();
       try {
           stmAlbum=con.prepareStatement("select sum(duracion) as tiempo "+
                   "from cancion where idalbum = ? ");
           stmAlbum.setInt(1, id);
           rsAlbum=stmAlbum.executeQuery();
           if (rsAlbum.next())
           {
               tiempo= rsAlbum.getTime("tiempo");
           }
       } catch (SQLException e){
           System.out.println(e.getMessage());
       }finally{
           try {stmAlbum.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
       }
       return tiempo;
   }
}






