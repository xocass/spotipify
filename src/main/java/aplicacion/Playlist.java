package aplicacion;

public class Playlist {
    private String nombre;
    private String creador;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Playlist(String nombre, String creador, int id){
        this.nombre=nombre;
        this.creador=creador;
        this.id=id;
    }
    public String getNombre(){return nombre;}
}
