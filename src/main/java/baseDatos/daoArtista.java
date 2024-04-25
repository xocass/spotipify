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

    public ArrayList<Contenido> buscar(String busqueda){
        ArrayList<Contenido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmArtista=null;
        ResultSet rsArtista;

        con=this.getConexion();

        try {
            stmArtista=con.prepareStatement("select nombreartistico, paisnacimiento "+
                    "from artista "+
                    "where nombreartistico like ?");
            stmArtista.setString(1, "%"+busqueda+"%");
            rsArtista=stmArtista.executeQuery();
            while (rsArtista.next())
            {
                resultado.add(new Contenido(rsArtista.getString("nombreartistico"), rsArtista.getString("paisnacimiento"),null,0));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmArtista.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }

    public ArrayList<Artista> buscarMod(String busqueda){
        ArrayList<Artista> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmArtista=null;
        ResultSet rsArtista;

        con=this.getConexion();

        try {
            stmArtista=con.prepareStatement("select nombreartistico, paisnacimiento, verificado "+
                    "from artista "+
                    "where nombreartistico like ? or nombre like ?");
            stmArtista.setString(1, "%"+busqueda+"%");
            rsArtista=stmArtista.executeQuery();
            while (rsArtista.next())
            {
                resultado.add(new Artista(rsArtista.getString("nombreartistico"), rsArtista.getString("paisnacimiento"),rsArtista.getBoolean("verificado")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }finally{
            try {stmArtista.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
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
}
