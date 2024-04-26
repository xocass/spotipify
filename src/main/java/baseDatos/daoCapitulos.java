package baseDatos;

import aplicacion.Cancion;
import aplicacion.Contenido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoCapitulos extends AbstractDAO{
    public daoCapitulos(Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    public ArrayList<Contenido> buscar(String busqueda){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmCapitulo=null;
        ResultSet rsCapitulo;

        con=this.getConexion();

        try {
            stmCapitulo=con.prepareStatement("select ca.nombre as nombre, ca.idpodcast as caidpodcast, p.idpodcast as idpodcast, p.idartista as pidartista, ar.nombre as arnombre, ca.duracion as duracion, ca.explicito as explicito "+
                    "from capitulo ca, participarpodcast p, artista ar "+
                    "where ca.nombre like ? and ca.idpodcast=p.idpodcast and p.idartista=ar.nombre");
            stmCapitulo.setString(1, "%"+busqueda+"%");
            rsCapitulo=stmCapitulo.executeQuery();
            while (rsCapitulo.next())
            {
                resultado.add(new Contenido(rsCapitulo.getString("nombre"), rsCapitulo.getString("arnombre"),rsCapitulo.getTime("duracion"),rsCapitulo.getBoolean("explicito"),4));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmCapitulo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
