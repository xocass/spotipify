package baseDatos;

import aplicacion.Artista;
import aplicacion.Contenido;
import aplicacion.Oyente;

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

    public ArrayList<Artista> buscar(String busqueda){
        ArrayList<Artista> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmArtista=null;
        ResultSet rsArtista;

        con=this.getConexion();

        try {
            stmArtista=con.prepareStatement("select * "+
                    "from artista "+
                    "where nombreartistico like ?");
            stmArtista.setString(1, "%"+busqueda+"%");
            rsArtista=stmArtista.executeQuery();
            while (rsArtista.next())
            {
                resultado.add(new Artista(rsArtista.getString("nombre"), rsArtista.getString("contraseña"),
                        rsArtista.getString("email"), rsArtista.getDate("fechanacimiento").toString(),
                        rsArtista.getString("nombreartistico"), rsArtista.getString("paisnacimiento"),
                        rsArtista.getBoolean("verificado")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmArtista.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void eliminar(String eliminar){
        Connection con;
        PreparedStatement stmOyente=null;
        ResultSet rsOyente;

        con=this.getConexion();
        try {
            stmOyente=con.prepareStatement("delete from artista where nombre = ? ");
            stmOyente.setString(1, eliminar);
            stmOyente.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmOyente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

    }
    public ArrayList<Oyente> verificados(){
        ArrayList<Oyente> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmArtista=null;
        ResultSet rsArtista;

        con=this.getConexion();

        try {
            stmArtista=con.prepareStatement("select nombre, nombreartistico "+
                    "from artista "+
                    "where verificado is TRUE");
            rsArtista=stmArtista.executeQuery();
            while (rsArtista.next())
            {
                resultado.add(new Oyente(rsArtista.getString("nombre"), rsArtista.getString("nombreartistico")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmArtista.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public ArrayList<String> siguiendoArtista(String nombre){
        ArrayList<String> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmSiguiendo=null;
        ResultSet rsSiguiendo;

        con=this.getConexion();

        try {
            stmSiguiendo=con.prepareStatement("select idartista "+
                    "from seguirArtista "+
                    "where idoyente = ?");
            stmSiguiendo.setString(1,nombre);
            rsSiguiendo=stmSiguiendo.executeQuery();
            while (rsSiguiendo.next())
            {
                resultado.add(rsSiguiendo.getString("idartista"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmSiguiendo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
