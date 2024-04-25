package baseDatos;

import aplicacion.Contenido;
import aplicacion.FachadaAplicacion;

import java.sql.Connection;
import java.util.ArrayList;

public class daoPlaylist extends AbstractDAO{
    private Connection connexion;
    private FachadaAplicacion fa;
    public daoPlaylist(Connection conexion, FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public ArrayList<Contenido> playlistDefecto(){
        ArrayList<Contenido> resultado=new ArrayList<>();

        return resultado;
    }


}
