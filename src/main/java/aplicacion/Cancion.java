package aplicacion;

public class Cancion {
    private int id;
    private String nombre;
    private int visualizaciones;
    public Cancion(int id, String nombre){
        this.id=id;
        this.nombre=nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
