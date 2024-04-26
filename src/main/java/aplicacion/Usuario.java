package aplicacion;

public abstract class Usuario {
    private String nombre;
    private String contrasena;
    private String email;
    private String fechanac;

    public Usuario(String nombre, String contrasena, String email, String fechanac){
        this.nombre= nombre;
        this.contrasena= contrasena;
        this.email= email;
        this.fechanac= fechanac;
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
