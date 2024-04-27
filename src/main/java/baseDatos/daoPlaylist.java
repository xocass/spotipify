package baseDatos;

import aplicacion.Playlist;
import aplicacion.Contenido;
import aplicacion.FachadaAplicacion;
import aplicacion.Playlist;

import java.sql.*;
import java.util.ArrayList;

public class daoPlaylist extends AbstractDAO{
    private Connection connexion;
    private FachadaAplicacion fa;
    public daoPlaylist(Connection conexion, FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Playlist> playlistDefecto(){
        ArrayList<Playlist> resultado=new ArrayList<>();
        Connection con;
        String aux="spotify";
        PreparedStatement stmPlaylist=null;
        ResultSet rsPlaylist;

        con=this.getConexion();

        try {
            stmPlaylist=con.prepareStatement("select * "+
                    "from playlist "+
                    "where idoyente=?");
            stmPlaylist.setString(1,aux);
            rsPlaylist=stmPlaylist.executeQuery();
            while (rsPlaylist.next())
            {
                resultado.add(new Playlist(rsPlaylist.getString("nombreplaylist"),rsPlaylist.getString("idoyente"),rsPlaylist.getInt("idplaylist")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPlaylist.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public void crearFavoritos(String user){
        Connection con;
        PreparedStatement stmPlaylist=null;
        ResultSet rsPlaylist;
        String nombrePl = "Canciones que te gustan";

        con=this.getConexion();

        int id=getIdMax()+1;

        try {
            stmPlaylist=con.prepareStatement("insert into  playlist "+
                    "values (?,?,now(),?)");
            stmPlaylist.setInt(1,id);
            stmPlaylist.setString(2,nombrePl);
            stmPlaylist.setString(3,user);
            rsPlaylist=stmPlaylist.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPlaylist.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public int getIdMax() {
        ResultSet consulta;
        int resultado=0;
        Connection con;
        PreparedStatement stmPlaylist=null;

        con=this.getConexion();

        try {
            stmPlaylist=con.prepareStatement("select max(idplaylist) as idmax "+
                    "from playlist");
            consulta = stmPlaylist.executeQuery();
            if(consulta.next())
                resultado=consulta.getInt("idmax");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPlaylist.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public ArrayList<Playlist> tusPlaylist(String nombre){
        ArrayList<Playlist> resultado=new ArrayList<>();
        Connection con;
        PreparedStatement stmPlaylist=null;
        ResultSet rsPlaylist;

        con=this.getConexion();

        try {
            stmPlaylist=con.prepareStatement("select * "+
                    "from playlist "+
                    "where idoyente=?");
            stmPlaylist.setString(1,nombre);
            rsPlaylist=stmPlaylist.executeQuery();
            while (rsPlaylist.next())
            {
                resultado.add(new Playlist(rsPlaylist.getString("nombreplaylist"),rsPlaylist.getString("idoyente"),rsPlaylist.getInt("idplaylist")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPlaylist.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public Playlist getPlaylistID(int idPlaylist){
        Playlist resultado=null;

        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select idplaylist, nombreplaylist, idoyente "+
                    "from playlist  "+
                    "where idplaylist = ?  ");
            stmUsuario.setInt(1, idPlaylist);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Playlist(rsUsuario.getString("nombreplaylist"),rsUsuario.getString("idoyente"),
                        rsUsuario.getInt("idplaylist"));

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public Time getDuracionPlaylist(int id){
        Connection con;
        PreparedStatement stmPlaylist=null;
        ResultSet rsPlaylist;
        Time tiempo= null;

        con=this.getConexion();
        try {
            stmPlaylist=con.prepareStatement("select sum(c.duracion) as tiempo "+
                    "from cancion c, formarparte f where c.idcancion=f.idcancion and f.idplaylist = ? ");
            stmPlaylist.setInt(1, id);
            rsPlaylist=stmPlaylist.executeQuery();
            if (rsPlaylist.next())
            {
                tiempo= rsPlaylist.getTime("tiempo");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPlaylist.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return tiempo;
    }
    public int nCancionesPlaylist(int id){
        Connection con;
        PreparedStatement stmPlaylist=null;
        ResultSet rsPlaylist;
        int cuenta= 0;

        con=this.getConexion();
        try {
            stmPlaylist=con.prepareStatement("select count(*) as cuenta "+
                    "from cancion c, formarparte f where c.idcancion=f.idcancion and f.idplaylist = ? ");
            stmPlaylist.setInt(1, id);
            rsPlaylist=stmPlaylist.executeQuery();
            if (rsPlaylist.next())
            {
                cuenta= rsPlaylist.getInt("tiempo");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmPlaylist.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return cuenta;
    }
    public ArrayList<String> generoPlaylist(int id){
        ArrayList<String> resultado=new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select distinct c.nombregenero as genero "+
                    "from formarparte f, cancion c "+
                    "where f.idplaylist=? ");
            stmUsuario.setInt(1, id);
            rsUsuario=stmUsuario.executeQuery();
            while (rsUsuario.next())
            {
                resultado.add(rsUsuario.getString("genero"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
