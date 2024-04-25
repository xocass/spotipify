package baseDatos;

import aplicacion.Oyente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            stmUsuario=con.prepareStatement("select nombre, contraseña "+
                    "from Oyente "+
                    "where nombre = ? and contraseña = ?");
            stmUsuario.setString(1, usuario);
            stmUsuario.setString(2, contrasena);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Oyente(rsUsuario.getString("nombre"), rsUsuario.getString("contraseña"));

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
            stmUsuario=con.prepareStatement("select nombre, contraseña "+
                    "from administrador "+
                    "where nombre = ? and contraseña = ?");
            stmUsuario.setString(1, usuario);
            stmUsuario.setString(2, contrasena);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Oyente(rsUsuario.getString("nombre"), rsUsuario.getString("contraseña"));

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
}
