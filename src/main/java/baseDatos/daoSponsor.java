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
        ArrayList<String> resultado=new ArrayList<>();
        Connection con;
        PreparedStatement stmSponsor=null;
        ResultSet rsSponsor;

        con=this.getConexion();

        try {
            stmSponsor=con.prepareStatement("select s.nombre as sponsor "+
                    "from sponsor s, patrocinar p "+
                    "where p.idartista=? and s.idsponsor=p.idsponsor");
            stmSponsor.setString(1, id);
            rsSponsor=stmSponsor.executeQuery();
            if (rsSponsor.next())
            {
                resultado.add(rsSponsor.getString("sponsor"));

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmSponsor.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return resultado;
    }
}
