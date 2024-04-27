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

    public ArrayList<Artista> buscar(String busqueda) {
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
    public boolean cambiarVerificado(String nombre){
        boolean verificado= false;
        Connection con;
        PreparedStatement stmArtista=null;
        ResultSet rsArtista;

        con=this.getConexion();
        try {
            stmArtista=con.prepareStatement("select verificado "+
                    "from artista "+
                    "where nombre like ?");
            stmArtista.setString(1, nombre);
            rsArtista=stmArtista.executeQuery();
            rsArtista.next();
            verificado= !rsArtista.getBoolean("verificado");

            stmArtista=con.prepareStatement("update artista "+
                    "set verificado = ? "+
                    "where artista.nombre like ?");
            stmArtista.setBoolean(1, verificado);
            stmArtista.setString(2, nombre);
            stmArtista.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmArtista.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return verificado;
    }
    public ArrayList<Artista> verificados(){
        ArrayList<Artista> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmArtista=null;
        ResultSet rsArtista;

        con=this.getConexion();

        try {
            stmArtista=con.prepareStatement("select nombre, nombreartistico, verificado, paisnacimiento "+
                    "from artista "+
                    "where verificado is TRUE");
            rsArtista=stmArtista.executeQuery();
            while (rsArtista.next())
            {
                resultado.add(new Artista(rsArtista.getString("nombre"), rsArtista.getString("nombreartistico"),rsArtista.getBoolean("verificado"), rsArtista.getString("paisnacimiento")));
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
    public ArrayList<String> getGeneros(String nombre){
        ArrayList<String> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmGeneros=null;
        ResultSet rsGeneros;

        con=this.getConexion();

        try {
            stmGeneros=con.prepareStatement("select nombregenero "+
                    "from participargenero "+
                    "where idartista = ?");
            stmGeneros.setString(1,nombre);
            rsGeneros=stmGeneros.executeQuery();
            while (rsGeneros.next())
            {
                resultado.add(rsGeneros.getString("nombregenero"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmGeneros.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public int getSeguidores(String nombre){
        int resultado=0;
        Connection con;
        PreparedStatement stmGeneros=null;
        ResultSet rsGeneros;

        con=this.getConexion();
        try {
            stmGeneros=con.prepareStatement("select count(*) as seguidores "+
                    "from seguirartista " +
                    "where idartista = ?");
            stmGeneros.setString(1,nombre);
            rsGeneros=stmGeneros.executeQuery();
            while (rsGeneros.next())
            {
                resultado=rsGeneros.getInt("seguidores");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmGeneros.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public void seguirArtista(String artista,String oyente){
        Connection con;
        PreparedStatement stmGeneros=null;
        ResultSet rsGeneros;

        con=this.getConexion();
        try {
            stmGeneros=con.prepareStatement("insert into seguirartista "+
                    "values (?,?)");
            stmGeneros.setString(1,oyente);
            stmGeneros.setString(2,artista);
            rsGeneros=stmGeneros.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmGeneros.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
}
