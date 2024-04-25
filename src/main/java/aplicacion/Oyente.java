package aplicacion;

public class Oyente {
    //PENE
    String nombre;
    String contrasena;
    String email;
    String fechanac;
    String plan;
    int cancion;
    String fechapago;
    String fechavenc;
    public Oyente(String nombre, String contrasena, String email, String fechanac, String plan, int cancion, String fechapago, String fechavenc){
        this.nombre=nombre;
        this.contrasena=contrasena;
        this.email=email;
        this.fechanac=fechanac;
        this.plan=plan;
        this.cancion=cancion;
        this.fechapago=fechapago;
        this.fechavenc=fechavenc;
    }
    public Oyente(String nombre, String contrasena){
        this.nombre=nombre;
        this.contrasena=contrasena;
    }
    public Oyente(String nombre, String contrasena, String email, String fechanac) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.fechanac = fechanac;
    }
    public String getNombre(){return nombre;}
    public void setNombre(){this.nombre=nombre;}
    public String getContrasena(){return contrasena;}

    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public String getFechanac(){return fechanac;}

    public void setFechanac(String fechanac) {this.fechanac = fechanac;}

    public String getEmail(){return email;}

    public void setEmail(String email) {this.email = email;}
}
