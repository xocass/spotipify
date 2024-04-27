package baseDatos;

import aplicacion.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

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
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public Oyente actualizarUsuario(String usuario, String contrasena, String email, String nacimiento){
        Oyente resultado = new Oyente(usuario,contrasena,email,nacimiento);
        Connection con;
        PreparedStatement stmUsuario=null;

        con=this.getConexion();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(nacimiento);
            java.sql.Date fechaNacimiento = new java.sql.Date(parsed.getTime());

            stmUsuario=con.prepareStatement("update Oyente "+
                    "set contraseña= ?, email = ?, fechanacimiento = ? "+
                    "where nombre like ?");
            stmUsuario.setString(1, contrasena);
            stmUsuario.setString(2, email);
            stmUsuario.setDate(3, fechaNacimiento);
            stmUsuario.setString(4, usuario);
            stmUsuario.executeQuery();

        } catch (SQLException | ParseException e){
            System.out.println(e.getMessage());
        } finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
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
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmSiguiendo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public ArrayList<String> seguidores(String nombre){
        ArrayList<String> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmSeguidores=null;
        ResultSet rsSeguidores;

        con=this.getConexion();

        try {
            stmSeguidores=con.prepareStatement("select idoyente1 "+
                    "from seguir "+
                    "where idoyente2 = ?");
            stmSeguidores.setString(1,nombre);
            rsSeguidores=stmSeguidores.executeQuery();
            while (rsSeguidores.next())
            {
                resultado.add(rsSeguidores.getString("idoyente1"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmSeguidores.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
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
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public int getnSeguidoresU(String nombre){
        int resultado=0;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();
        try {
            stmUsuario=con.prepareStatement("select count(*) as seguidores "+
                    "from seguir " +
                    "where idoyente2 = ?");
            stmUsuario.setString(1,nombre);
            rsUsuario=stmUsuario.executeQuery();
            while (rsUsuario.next())
            {
                resultado=rsUsuario.getInt("seguidores");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
    public int getnSeguidosU(String nombre){
        int resultado=0;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();
        try {
            stmUsuario=con.prepareStatement("select count(*) as seguidos "+
                    "from seguir " +
                    "where idoyente1 = ?");
            stmUsuario.setString(1,nombre);
            rsUsuario=stmUsuario.executeQuery();
            while (rsUsuario.next())
            {
                resultado=rsUsuario.getInt("seguidos");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void seguirPerfil(String seguido,String seguidor){
        Connection con;
        PreparedStatement stmUsuario=null;

        con=this.getConexion();
        try {
            stmUsuario=con.prepareStatement("insert into seguir "+
                    "values (?,?)");
            stmUsuario.setString(1,seguidor);
            stmUsuario.setString(2,seguido);
            stmUsuario.executeQuery();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public String getPlan(String id){
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;
        con=this.getConexion();
        String resultado=null;
        try {
            stmUsuario=con.prepareStatement("select tipoplan" +
                    " from Oyente where nombre=?");
            stmUsuario.setString(1,id);
            rsUsuario=stmUsuario.executeQuery();
            if(rsUsuario.next()){
                resultado= rsUsuario.getString("tipoplan");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void actualizarEscuchando(String usuario, Contenido contenido){
        Connection con;
        PreparedStatement stmUsuario=null;
        String valor;

        con=this.getConexion();
        try {
                stmUsuario = con.prepareStatement("update Oyente " +
                        "set cancion = ? " +
                        "where nombre like ?");
                if(contenido instanceof Cancion){
                    stmUsuario.setInt(1, ((Cancion) contenido).getIdCancion());
                }else if(contenido instanceof Capitulo){
                    stmUsuario.setInt(1, ((Capitulo) contenido).getIdCapitulo());
                }
                stmUsuario.setString(2, usuario);
                stmUsuario.executeQuery();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public void noEscuchando(String usuario){
        Connection con;
        PreparedStatement stmUsuario=null;
        String valor;

        con=this.getConexion();
        try {
            stmUsuario = con.prepareStatement("update Oyente " +
                    "set cancion = null " +
                    "where nombre like ?");
            stmUsuario.setString(1, usuario);
            stmUsuario.executeQuery();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
}
