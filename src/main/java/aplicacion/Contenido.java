package aplicacion;

import java.util.ArrayList;

public abstract class Contenido {
    private String nombre;
    private ArrayList<String> creador;

    private ArrayList<String> idartista;

    public Contenido(String nombre, String artista, String idartista){
        this.nombre= nombre;
        this.creador= new ArrayList<>();
        this.creador.add(artista);
        this.idartista= new ArrayList<>();
        this.idartista.add(idartista);
    }
    public ArrayList<String> getIdartista(){return idartista;}
    public void setIdartista(ArrayList<String> idartista) {this.idartista = idartista;}


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
