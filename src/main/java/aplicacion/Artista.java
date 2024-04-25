package aplicacion;

public class Artista {
    String nombre;
    String contrasena;
    String email;
    String fechanac;
    String nombreArtistico;
    String paisNacimiento;
    boolean verificado;

    public Artista(String nombre, String contrasena, String email, String fechanac, String nombreArtistico, String paisNacimiento, boolean verificado){
        this.nombre=nombre;
        this.contrasena=contrasena;
        this.email=email;
        this.fechanac=fechanac;
        this.nombreArtistico= nombreArtistico;
        this.paisNacimiento= paisNacimiento;
        this.verificado=verificado;
    }
    public Artista(String nombreArtistico, String paisNacimiento, boolean verificado){
        this.nombreArtistico= nombreArtistico;
        this.paisNacimiento= paisNacimiento;
        this.verificado=verificado;
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

}
