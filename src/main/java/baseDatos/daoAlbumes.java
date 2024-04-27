package baseDatos;

import aplicacion.Contenido;
import aplicacion.Album;

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
            stmAlbum=con.prepareStatement("select al.nombre as nombre, al.idalbum as idalbum, al.tipo as tipo, ar.nombreartistico as creador "+
                    "from album al, componer c, artista ar "+
                    "where al.nombre like ? and al.idalbum=c.idalbum and c.idartista=ar.nombre");
            stmAlbum.setString(1, "%"+busqueda+"%");
            rsAlbum=stmAlbum.executeQuery();
            while (rsAlbum.next())
            {
                resultado.add(new Album(rsAlbum.getString("nombre"), rsAlbum.getString("creador"),
                        rsAlbum.getInt("idalbum"), rsAlbum.getString("tipo")));
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
    public ArrayList<Contenido> getAlbumesArtista(String id){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmAlbum=null;
        ResultSet rsAlbum;

        con=this.getConexion();

        try {
            stmAlbum=con.prepareStatement("select al.nombre as nombre, al.idalbum as idalbum "+
                    "from album al, componer c "+
                    "where al.idalbum=c.idalbum and c.idartista=?");
            stmAlbum.setString(1, id);
            rsAlbum=stmAlbum.executeQuery();
            while (rsAlbum.next())
            {
                resultado.add(new Album(rsAlbum.getString("nombre"), null,
                        rsAlbum.getInt("idalbum"),null));
                System.out.println(rsAlbum.getString("nombre"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmAlbum.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}






