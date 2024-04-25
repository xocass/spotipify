package aplicacion;

import java.util.ArrayList;
//hola
public class Contenido {
    private String nombre;
    private String pais_tipoalbum;
    private ArrayList<String> creador;
    private int tipo;   //ARTISTA= 0;
                        //ALBUM = 1;
                        //PODCAST = 2;

    public Contenido(String nombre, String pais_tipoalbum, String creador, int tipo){
        this.nombre=nombre;
        this.pais_tipoalbum=pais_tipoalbum;
        this.creador=new ArrayList<>();
        if(tipo>0)this.creador.add(creador);
        this.tipo=tipo;
    }

    public String getNombre() {
        return nombre;
    }
    public String getPais_tipoalbum(){return pais_tipoalbum;}
    public ArrayList<String> getCreador(){return creador;}

    public int getTipo() {return tipo;}
}
