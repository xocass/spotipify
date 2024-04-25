package baseDatos;

import aplicacion.Contenido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoArtista extends AbstractDAO{
    public daoArtista(Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Contenido> buscar(String busqueda){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmArtista=null;
        ResultSet rsArtista;

        con=this.getConexion();

        try {
            stmArtista=con.prepareStatement("select nombreartistico, paisnacimiento "+
                    "from Artista "+
                    "where nombreartistico like ?");
            stmArtista.setString(1, "%"+busqueda+"%");
            rsArtista=stmArtista.executeQuery();
            while (rsArtista.next())
            {
                resultado.add(new Contenido(rsArtista.getString("nombreartistico"), rsArtista.getString("paisnacimiento"),null,0));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmArtista.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
