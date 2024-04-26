package aplicacion;

import java.util.ArrayList;

public abstract class Contenido {
    private String nombre;
    private ArrayList<String> creador;

    public Contenido(String nombre, String artista){
        this.nombre= nombre;
        this.creador= new ArrayList<>();
        this.creador.add(artista);
    }

    public String getNombre() {
        return nombre;
    }
    public ArrayList<String> getCreador(){return creador;}
    public void setCreador(ArrayList<String> creadores){this.creador= creadores;}
    public void addCreador(String artista){this.creador.add(artista);}

}
