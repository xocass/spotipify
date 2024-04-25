package baseDatos;

import aplicacion.Contenido;
import aplicacion.Oyente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoUsuarios extends AbstractDAO{
    public daoUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Oyente validarUsuario(String usuario, String contrasena){
        Oyente resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select nombre, contraseña, email, fechanacimiento "+
                    "from Oyente "+
                    "where nombre = ? and contraseña = ?");
            stmUsuario.setString(1, usuario);
            stmUsuario.setString(2, contrasena);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Oyente(rsUsuario.getString("nombre"), rsUsuario.getString("contraseña"), rsUsuario.getString("email"),rsUsuario.getString("fechanacimiento"));

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public Oyente validarAdmin(String usuario, String contrasena){
        Oyente resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select nombre, contraseña, email, fechanacimiento "+
                    "from administrador "+
                    "where nombre = ? and contraseña = ?");
            stmUsuario.setString(1, usuario);
            stmUsuario.setString(2, contrasena);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Oyente(rsUsuario.getString("nombre"), rsUsuario.getString("contraseña"), rsUsuario.getString("email"),rsUsuario.getString("fechanacimiento"));

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public Oyente comprobarAdmin(String usuario){
        Oyente resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select nombre "+
                    "from administrador "+
                    "where nombre = ?");
            stmUsuario.setString(1, usuario);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Oyente(rsUsuario.getString("nombre"), null);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public Oyente comprobarArtista(String usuario){
        Oyente resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select nombre "+
                    "from artista "+
                    "where nombre = ?");
            stmUsuario.setString(1, usuario);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Oyente(rsUsuario.getString("nombre"), null);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void registrarUsuario(String usuario, String contrasena, String email, String nacimiento){
        Connection con;
        PreparedStatement stmUsuario=null;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("insert into Oyente values "+
                    "(?, ?, ?, CAST(? as date), 'Basico', null, null, null)");
            stmUsuario.setString(1, usuario);
            stmUsuario.setString(2, contrasena);
            stmUsuario.setString(3, email);
            stmUsuario.setString(4, nacimiento);
            stmUsuario.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public ArrayList<Oyente> buscar(String busqueda){
        ArrayList<Oyente> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsOyente;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select u.nombre as nombre, u.email as email "+
                    "from oyente u "+
                    "where u.nombre like ? ");
            stmUsuario.setString(1, "%"+busqueda+"%");
            rsOyente=stmUsuario.executeQuery();
            while (rsOyente.next())
            {
                resultado.add(new Oyente(rsOyente.getString("nombre"), rsOyente.getString("email")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

}
