package baseDatos;

import aplicacion.Cancion;
import aplicacion.Contenido;
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
            stmCancion=con.prepareStatement("select nombre, idcancion, idalbum "+
                    "from cancion "+
                    "order by visualizaciones limit 10");
            rsCancion=stmCancion.executeQuery();
            while (rsCancion.next())
            {
                resultado.add(new Cancion(rsCancion.getString("nombre"),null,rsCancion.getInt("idalbum"),rsCancion.getInt("idcancion")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmCancion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public ArrayList<Contenido> buscar(String busqueda){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmCancion=null;
        ResultSet rsCancion;

        con=this.getConexion();

        try {
            stmCancion=con.prepareStatement("select ca.nombre as nombre, ca.idalbum as caidalbum, ca.idcancion as idcancion, ar.nombreartistico as arnombre, ca.duracion as duracion, ca.explicito as explicito "+
                    "from cancion ca, componer c, artista ar "+
                    "where ca.nombre like ? and ca.idalbum=c.idalbum and c.idartista=ar.nombre");
            stmCancion.setString(1, "%"+busqueda+"%");
            rsCancion=stmCancion.executeQuery();
            while (rsCancion.next())
            {
                resultado.add(new Cancion(rsCancion.getString("nombre"), rsCancion.getString("arnombre"), rsCancion.getInt("caidalbum"),
                        rsCancion.getInt("idcancion"), rsCancion.getTime("duracion"), rsCancion.getBoolean("explicito")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmCancion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public void eliminar(int eliminar){
        Connection con;
        PreparedStatement stmCancion=null;
        ResultSet rsCancion;

        con=this.getConexion();
        try {
            System.out.println("idcancion: "+ eliminar);
            stmCancion=con.prepareStatement("delete from cancion where idcancion = ? ");
            stmCancion.setString(1, "%"+eliminar+"%");
            stmCancion.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmCancion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

    }
}
