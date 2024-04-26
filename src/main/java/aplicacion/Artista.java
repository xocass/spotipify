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
    public Artista(String nombre, String nombreArtistico, boolean verificado, String paisNacimiento){
        super(nombre);
        this.nombreArtistico=nombreArtistico;
        this.verificado=verificado;
        this.paisNacimiento=paisNacimiento;
    }

    public String getNombreArtistico(){return nombreArtistico;}
    public void setNombreArtistico(String nombreArtistico){this.nombreArtistico=nombreArtistico;}
    public String getPaisNacimiento(){return paisNacimiento;}
    public void setPaisNacimiento(String paisNacimiento) {this.paisNacimiento = paisNacimiento;}
    public boolean getVerificado(){return verificado;}
    public void setVerificado(boolean verificado) {this.verificado = verificado;}
    public void setGeneros(ArrayList<String> generos){this.generos=generos;}

}
