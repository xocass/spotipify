package baseDatos;

import aplicacion.Oyente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoSponsor extends AbstractDAO{
    public daoSponsor(Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<String> getSponsor(String id){
        ArrayList<String> resultado=null;
        Connection con;
        PreparedStatement stmUsuario=null;
        ResultSet rsUsuario;

        con=this.getConexion();

        try {
            stmUsuario=con.prepareStatement("select s.nombre as sponsor "+
                    "from sponsor s, patrocinar p "+
                    "where p.idartista=? and s.idsponsor=p.idsponsor");
            stmUsuario.setString(1, id);
            rsUsuario=stmUsuario.executeQuery();
            if (rsUsuario.next())
            {
                resultado.add(rsUsuario.getString("sponsor"));

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
