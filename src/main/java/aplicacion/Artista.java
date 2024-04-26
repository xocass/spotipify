package aplicacion;

import java.util.ArrayList;

public class Artista {
    private String nombre;
    private String contrasena;
    private String email;
    private String fechanac;
    private String nombreArtistico;
    private String paisNacimiento;
    private boolean verificado;
    private ArrayList<String> generos;

    public Artista(String nombre, String contrasena, String email, String fechanac, String nombreArtistico, String paisNacimiento, boolean verificado, ArrayList<String> generos){
        this.nombre=nombre;
        this.contrasena=contrasena;
        this.email=email;
        this.fechanac=fechanac;
        this.nombreArtistico= nombreArtistico;
        this.paisNacimiento= paisNacimiento;
        this.verificado=verificado;
        this.generos=generos;
    }
    public Artista(String nombreArtistico, String paisNacimiento, boolean verificado){
        this.nombreArtistico= nombreArtistico;
        this.paisNacimiento= paisNacimiento;
        this.verificado=verificado;
    }
    public Artista(String nombre, String nombreArtistico, ArrayList<String> generos){
        this.nombre=nombre;
        this.nombreArtistico=nombreArtistico;
        this.generos=generos;
    }

    public String getNombre(){return nombre;}
    public void setNombre(){this.nombre=nombre;}
    public String getContrasena(){return contrasena;}

    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public String getFechanac(){return fechanac;}

    public void setFechanac(String fechanac) {this.fechanac = fechanac;}

    public String getEmail(){return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNombreArtistico(){return nombreArtistico;}
    public void setNombreArtistico(String nombreArtistico){this.nombreArtistico=nombreArtistico;}
    public String getPaisNacimiento(){return paisNacimiento;}
    public void setPaisNacimiento(String paisNacimiento) {this.paisNacimiento = paisNacimiento;}
    public Boolean getVerificado(){return verificado;}
    public void setVerificado(String paisNacimiento) {this.verificado = verificado;}
    public void setGeneros(ArrayList<String> generos){this.generos=generos;}

}
