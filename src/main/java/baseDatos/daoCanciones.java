package baseDatos;

import aplicacion.Cancion;
import baseDatos.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoCanciones extends AbstractDAO {
    public daoCanciones(Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    public ArrayList<Cancion> topCanciones(){
        ArrayList<Cancion> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmCancion=null;
        ResultSet rsCancion;

        con=this.getConexion();

        try {
            stmCancion=con.prepareStatement("select nombre, idcancion "+
                    "from cancion "+
                    "order by visualizaciones limit 10");
            rsCancion=stmCancion.executeQuery();
            while (rsCancion.next())
            {
                resultado.add(new Cancion(rsCancion.getInt("idcancion"),rsCancion.getString("nombre")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmCancion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
