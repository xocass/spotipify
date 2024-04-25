package baseDatos;

import aplicacion.Contenido;
import aplicacion.Oyente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoAlbumes extends AbstractDAO{
    public daoAlbumes(Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Contenido> buscar(String busqueda){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmAlbum=null;
        ResultSet rsAlbum;

        con=this.getConexion();

        try {
            stmAlbum=con.prepareStatement("select al.nombre as nombre, al.tipo as tipo, ar.nombreartistico as creador "+
                    "from album al, componer c, artista ar "+
                    "where al.nombre like ? and al.idalbum=c.idalbum and c.idartista=ar.nombre");
            stmAlbum.setString(1, "%"+busqueda+"%");
            rsAlbum=stmAlbum.executeQuery();
            while (rsAlbum.next())
            {
                resultado.add(new Contenido(rsAlbum.getString("nombre"), rsAlbum.getString("tipo"),rsAlbum.getString("creador"),1));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmAlbum.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

}






