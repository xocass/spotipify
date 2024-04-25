package aplicacion;

public class Playlist {
    private String nombre;
    private String creador;

    public Playlist(String nombre, String creador){
        this.nombre=nombre;
        this.creador=creador;
    }
    public String getNombre(){return nombre;}
}
