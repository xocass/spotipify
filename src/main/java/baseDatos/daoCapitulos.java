package baseDatos;

import aplicacion.Capitulo;
import aplicacion.Capitulo;
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
            stmCapitulo=con.prepareStatement("select ca.nombre as nombre, ca.idpodcast as idpodcast, ca.idcapitulo as idcapitulo, p.idartista as pidartista, ar.nombre as arnombre, ca.duracion as duracion, ca.explicito as explicito, ar.nombre as idartista "+
                    "from capitulo ca, participarpodcast p, artista ar "+
                    "where ca.nombre like ? and ca.idpodcast=p.idpodcast and p.idartista=ar.nombre");
            stmCapitulo.setString(1, "%"+busqueda+"%");
            rsCapitulo=stmCapitulo.executeQuery();
            while (rsCapitulo.next())
            {
                resultado.add(new Capitulo(rsCapitulo.getString("nombre"), rsCapitulo.getInt("idpodcast"),
                        rsCapitulo.getString("idartista"), rsCapitulo.getString("arnombre"), rsCapitulo.getInt("idcapitulo"),
                        rsCapitulo.getTime("duracion"),rsCapitulo.getBoolean("explicito")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCapitulo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public void eliminar(int eliminar){
        Connection con;
        PreparedStatement stmCapitulo=null;

        con=this.getConexion();
        try {
            stmCapitulo=con.prepareStatement("delete from capitulo where idcapitulo = ? ");
            stmCapitulo.setInt(1, eliminar);
            stmCapitulo.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCapitulo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
    }
    public boolean cambiarExplicito(int idcapitulo){
        boolean explicito= false;
        Connection con;
        PreparedStatement stmCapitulo=null;
        ResultSet rsCapitulo;

        con=this.getConexion();
        try {
            stmCapitulo=con.prepareStatement("select explicito "+
                    "from capitulo "+
                    "where idcapitulo = ?");
            stmCapitulo.setInt(1, idcapitulo);
            rsCapitulo=stmCapitulo.executeQuery();
            rsCapitulo.next();
            explicito= !rsCapitulo.getBoolean("explicito");

            stmCapitulo=con.prepareStatement("update capitulo "+
                    "set explicito = ? "+
                    "where capitulo.idcapitulo = ?");
            stmCapitulo.setBoolean(1, explicito);
            stmCapitulo.setInt(2, idcapitulo);
            stmCapitulo.execute();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCapitulo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return explicito;
    }
    public ArrayList<Capitulo> getCapitulosPodcast(int id){
        ArrayList<Capitulo> resultado=new ArrayList<Capitulo>();
        Connection con;
        PreparedStatement stmCapitulo=null;
        ResultSet rsCapitulo;

        con=this.getConexion();
        try {
            stmCapitulo=con.prepareStatement("select nombre, idCapitulo, explicito, duracion "+
                    "from Capitulo  "+
                    "where idpodcast = ?");

            stmCapitulo.setInt(1, id);
            rsCapitulo=stmCapitulo.executeQuery();
            while(rsCapitulo.next()){
                resultado.add(new Capitulo(rsCapitulo.getString("nombre"),id, null,null, rsCapitulo.getInt("idCapitulo"), rsCapitulo.getTime("duracion"),rsCapitulo.getBoolean("explicito")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCapitulo.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
