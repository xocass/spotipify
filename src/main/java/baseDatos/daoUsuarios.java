package baseDatos;

import aplicacion.*;

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
    public Usuario comprobarAdmin(String usuario){
        Usuario resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select * "+
                    "from administrador "+
                    "where nombre = ?");
            stmUsuario.setString(1, usuario);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Administrador(rsUsuario.getString("nombre"), rsUsuario.getString("contraseña"),
                        rsUsuario.getString("email"), rsUsuario.getDate("fechanacimiento").toString());
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            //this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public Usuario comprobarArtista(String usuario){
        Usuario resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select * "+
                    "from artista "+
                    "where nombre = ?");
            stmUsuario.setString(1, usuario);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado = new Artista(rsUsuario.getString("nombre"), rsUsuario.getString("contraseña"),
                        rsUsuario.getString("email"), rsUsuario.getDate("fechanacimiento").toString(),
                        rsUsuario.getString("nombreartistico"), rsUsuario.getString("paisnacimiento"),
                        rsUsuario.getBoolean("verificado"));
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
    public void actualizarUsuario(String usuario, String contrasena, String email, String nacimiento){
        Connection con;
        PreparedStatement stmUsuario=null;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("update Oyente "+
                    "set contraseña= ?, email = ?, fechanacimiento = ? "+
                    "where nombre like ?");
            stmUsuario.setString(1, contrasena);
            stmUsuario.setString(2, email);
            stmUsuario.setString(3, nacimiento);
            stmUsuario.setString(4, usuario);
            stmUsuario.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }

    public void eliminar(String eliminar){
        Connection con;
        PreparedStatement stmOyente=null;
        ResultSet rsOyente;

        con=this.getConexion();
        try {
            stmOyente=con.prepareStatement("delete from oyente where nombre = ? ");
            stmOyente.setString(1, eliminar);
            stmOyente.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmOyente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }

    }
    public ArrayList<Oyente> buscar(String busqueda){
        ArrayList<Oyente> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmOyente=null;
        ResultSet rsOyente;

        con=this.getConexion();

        try {
            stmOyente=con.prepareStatement("select *  "+
                    "from oyente "+
                    "where nombre like ?");
            stmOyente.setString(1, "%"+busqueda+"%");
            rsOyente=stmOyente.executeQuery();
            while (rsOyente.next())
            {
                resultado.add(new Oyente(rsOyente.getString("nombre"),rsOyente.getString("contraseña"), rsOyente.getString("email"), rsOyente.getString("fechanacimiento")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmOyente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public ArrayList<String> siguiendo(String nombre){
        ArrayList<String> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmSiguiendo=null;
        ResultSet rsSiguiendo;

        con=this.getConexion();

        try {
            stmSiguiendo=con.prepareStatement("select idoyente2 "+
                    "from seguir "+
                    "where idoyente1 = ?");
            stmSiguiendo.setString(1,nombre);
            rsSiguiendo=stmSiguiendo.executeQuery();
            while (rsSiguiendo.next())
            {
                resultado.add(rsSiguiendo.getString("idoyente2"));
                System.out.println(rsSiguiendo.getString("idoyente2"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmSiguiendo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void actualizarPlanUsuario(String usuario, String plan, int tipo){
        Connection con;
        PreparedStatement stmUsuario=null;

        String valor;

        switch (tipo){
            case 1:case 2:
                valor = "30";
                break;
            case 3:
                valor = "365";
                break;
            default:
                valor = "0";
                break;
        }

        con=this.getConexion();

        try {

            if(tipo!=0) {
                stmUsuario = con.prepareStatement("update Oyente " +
                        "set tipoplan = ?,  fechapago = now(), fechavencimiento = now() + INTERVAL '"+valor+" DAYS' " +
                        "where nombre like ?");
                stmUsuario.setString(1, plan);
                stmUsuario.setString(2, usuario);
                stmUsuario.executeQuery();
            }
            else{
                stmUsuario = con.prepareStatement("update Oyente " +
                        "set tipoplan= ?,  fechapago = null, fechavencimiento = null " +
                        "where nombre like ?");
                stmUsuario.setString(1, plan);
                stmUsuario.setString(2, usuario);
                stmUsuario.executeQuery();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public int getSeguidores(String nombre){
        int resultado=0;
        Connection con;
        PreparedStatement stmGeneros=null;
        ResultSet rsGeneros;

        con=this.getConexion();
        try {
            stmGeneros=con.prepareStatement("select count(*) as seguidores "+
                    "from seguir " +
                    "where idoyente2 = ?");
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
}
