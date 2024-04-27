package baseDatos;
import aplicacion.Podcast;
import aplicacion.Contenido;
import aplicacion.Podcast;

import java.sql.*;
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
        PreparedStatement stmPodcast=null;
        ResultSet rsPodcast;

        con=this.getConexion();

        try {
            stmPodcast=con.prepareStatement("select p.nombre as nombre, p.idpodcast as idpodcast, pp.idartista as idartista "+
                    "from podcast p, participarpodcast pp "+
                    "where p.idpodcast=pp.idpodcast and pp.idartista=?");
            stmPodcast.setString(1, id);
            rsPodcast=stmPodcast.executeQuery();
            while (rsPodcast.next())
            {
                resultado.add(new Podcast(rsPodcast.getString("nombre"), null, rsPodcast.getString("idartista"),
                        rsPodcast.getInt("idpodcast")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPodcast.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public Podcast getPodcastId(int idPodcast){
        Podcast resultado=null;
        int i=0;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select p.nombre as nombrePodcast, a.nombreartistico as nombreArtistico, a.nombre as idArtista, p.idpodcast as idpodcast "+
                    "from podcast p,  participarpodcast pp, artista a "+
                    "where p.idpodcast = ? and pp.idartista= a.nombre and pp.idpodcast=p.idpodcast ");
            stmUsuario.setInt(1, idPodcast);
            rsUsuario=stmUsuario.executeQuery();
            while (rsUsuario.next())
            {
                if(i==0)resultado = new Podcast(rsUsuario.getString("nombrePodcast"),rsUsuario.getString("nombreArtistico"),
                        rsUsuario.getString("idArtista"),rsUsuario.getInt("idpodcast"));
                else resultado.getCreador().add(rsUsuario.getString("nombreArtistico"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public Time getDuracionPodcast(int id){
        Connection con;
        PreparedStatement stmPodcast=null;
        ResultSet rsPodcast;
        Time tiempo= null;

        con=this.getConexion();
        try {
            stmPodcast=con.prepareStatement("select sum(duracion) as tiempo "+
                    "from capitulo where idpodcast = ? ");
            stmPodcast.setInt(1, id);
            rsPodcast=stmPodcast.executeQuery();
            if (rsPodcast.next())
            {
                tiempo= rsPodcast.getTime("tiempo");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPodcast.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return tiempo;
    }
    public int nCapitulosPodcast(int id){
        Connection con;
        PreparedStatement stmPodcast=null;
        ResultSet rsPodcast;
        int cuenta= 0;

        con=this.getConexion();
        try {
            stmPodcast=con.prepareStatement("select count(*) as cuenta "+
                    "from capitulo where idpodcast = ? ");
            stmPodcast.setInt(1, id);
            rsPodcast=stmPodcast.executeQuery();
            if (rsPodcast.next())
            {
                cuenta= rsPodcast.getInt("cuenta");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPodcast.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return cuenta;
    }
}
