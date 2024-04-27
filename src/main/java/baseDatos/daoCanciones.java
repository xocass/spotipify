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
                resultado.add(new Cancion(rsCancion.getString("nombre"),null,null,rsCancion.getInt("idalbum"),rsCancion.getInt("idcancion")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
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
            stmCancion=con.prepareStatement("select ca.nombre as nombre, ca.idalbum as caidalbum, ca.idcancion as idcancion, ar.nombreartistico as arnombre, ca.duracion as duracion, ca.explicito as explicito, ca.nombregenero as nombregenero, ar.nombre as idnombre "+
                    "from cancion ca, componer c, artista ar "+
                    "where ca.nombre like ? and ca.idalbum=c.idalbum and c.idartista=ar.nombre");
            stmCancion.setString(1, "%"+busqueda+"%");
            rsCancion=stmCancion.executeQuery();
            while (rsCancion.next())
            {
                resultado.add(new Cancion(rsCancion.getString("nombre"), rsCancion.getString("arnombre"), rsCancion.getString("idnombre"),rsCancion.getInt("caidalbum"),
                        rsCancion.getInt("idcancion"), rsCancion.getTime("duracion"), rsCancion.getBoolean("explicito"), rsCancion.getString("nombregenero")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCancion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public void eliminar(int eliminar){
        Connection con;
        PreparedStatement stmCancion=null;

        con=this.getConexion();
        try {
            stmCancion=con.prepareStatement("delete from cancion where idcancion = ? ");
            stmCancion.setInt(1, eliminar);
            stmCancion.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCancion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public boolean cambiarExplicito(int idcancion){
        boolean explicito= false;
        Connection con;
        PreparedStatement stmCancion=null;
        ResultSet rsCancion;

        con=this.getConexion();
        try {
            stmCancion=con.prepareStatement("select explicito "+
                    "from cancion "+
                    "where idcancion = ?");
            stmCancion.setInt(1, idcancion);
            rsCancion=stmCancion.executeQuery();
            rsCancion.next();
            explicito= !rsCancion.getBoolean("explicito");

            stmCancion=con.prepareStatement("update cancion "+
                    "set explicito = ? "+
                    "where cancion.idcancion = ?");
            stmCancion.setBoolean(1, explicito);
            stmCancion.setInt(2, idcancion);
            stmCancion.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCancion.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return explicito;
    }
}
