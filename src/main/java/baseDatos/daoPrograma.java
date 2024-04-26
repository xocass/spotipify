package baseDatos;
import aplicacion.Contenido;
import aplicacion.Podcast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoPrograma extends AbstractDAO{
    public daoPrograma(Connection conexion, aplicacion.FachadaAplicacion fa){
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
            stmPrograma=con.prepareStatement("select p.nombre as nombre, a.nombreartistico as creador, p.idpodcast as idpodcast "+
                    "from podcast p, participarpodcast pp, artista a "+
                    "where p.nombre like ? and p.idpodcast = pp.idpodcast and pp.idartista = a.nombre");
            stmPrograma.setString(1, "%"+busqueda+"%");
            rsPrograma=stmPrograma.executeQuery();
            while (rsPrograma.next())
            {
                resultado.add(new Podcast(rsPrograma.getString("nombre"), rsPrograma.getString("creador"),rsPrograma.getInt("idpodcast")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmPrograma.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void eliminar(Podcast podcast){

    }
}
