package aplicacion;

import java.util.ArrayList;

public class Artista extends Usuario{
    private String nombreArtistico;
    private String paisNacimiento;
    private boolean verificado;
    private ArrayList<String> generos;

    public Artista(String nombre, String contrasena, String email, String fechanac, String nombreArtistico, String paisNacimiento, boolean verificado){
        super(nombre, contrasena, email, fechanac);
        this.nombreArtistico= nombreArtistico;
        this.paisNacimiento= paisNacimiento;
        this.verificado=verificado;
    }

    public String getNombreArtistico(){return nombreArtistico;}
    public void setNombreArtistico(String nombreArtistico){this.nombreArtistico=nombreArtistico;}
    public String getPaisNacimiento(){return paisNacimiento;}
    public void setPaisNacimiento(String paisNacimiento) {this.paisNacimiento = paisNacimiento;}
    public Boolean getVerificado(){return verificado;}
    public void setVerificado(String paisNacimiento) {this.verificado = verificado;}
    public void setGeneros(ArrayList<String> generos){this.generos=generos;}

}
