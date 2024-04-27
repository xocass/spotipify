package baseDatos;
import aplicacion.Album;
import aplicacion.Contenido;
import aplicacion.Podcast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoPodcast extends AbstractDAO{
    public daoPodcast(Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Contenido> buscar(String busqueda){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmPrograma=null;
        ResultSet rsPrograma;

        con=this.getConexion();

        try {
            stmPrograma=con.prepareStatement("select p.nombre as nombre, a.nombreartistico as creador, p.idpodcast as idpodcast, a.nombre as idartista "+
                    "from podcast p, participarpodcast pp, artista a "+
                    "where p.nombre like ? and p.idpodcast = pp.idpodcast and pp.idartista = a.nombre");
            stmPrograma.setString(1, "%"+busqueda+"%");
            rsPrograma=stmPrograma.executeQuery();
            while (rsPrograma.next())
            {
                resultado.add(new Podcast(rsPrograma.getString("nombre"), rsPrograma.getString("creador"),
                        rsPrograma.getString("idartista"), rsPrograma.getInt("idpodcast")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPrograma.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void eliminar(int eliminar){
        Connection con;
        PreparedStatement stmPodcast=null;

        con=this.getConexion();
        try {
            stmPodcast=con.prepareStatement("delete from podcast where idpodcast = ? ");
            stmPodcast.setInt(1, eliminar);
            stmPodcast.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPodcast.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public ArrayList<Contenido> getPodcastArtista(String id){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmAlbum=null;
        ResultSet rsAlbum;

        con=this.getConexion();

        try {
            stmAlbum=con.prepareStatement("select p.nombre as nombre, p.idpodcast as idpodcast, pp.idartista as idartista "+
                    "from podcast p, participarpodcast pp "+
                    "where p.idpodcast=pp.idpodcast and pp.idartista=?");
            stmAlbum.setString(1, id);
            rsAlbum=stmAlbum.executeQuery();
            while (rsAlbum.next())
            {
                resultado.add(new Podcast(rsAlbum.getString("nombre"), null, rsAlbum.getString("idartista"),
                        rsAlbum.getInt("idpodcast")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmAlbum.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
