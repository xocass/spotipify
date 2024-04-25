package baseDatos;

import aplicacion.Contenido;
import aplicacion.FachadaAplicacion;
import aplicacion.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                resultado.add(new Playlist(rsPlaylist.getString("nombre"),rsPlaylist.getString("idoyente")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmPlaylist.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }


}
