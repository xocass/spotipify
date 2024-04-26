package aplicacion;

import java.sql.Time;
import java.util.ArrayList;

public class Contenido {
    private String nombre;
    private String pais_tipoalbum;
    private ArrayList<String> creador;
    private Time duracion;
    private boolean explicito;
    private int tipo;   //ARTISTA= 0;
                        //ALBUM = 1;
                        //PODCAST = 2;
                        //CANCION = 3;
                        //CAPITULO = 4;

    public Contenido(String nombre, String pais_tipoalbum, String creador, int tipo){
        this.nombre=nombre;
        this.pais_tipoalbum=pais_tipoalbum;
        this.creador=new ArrayList<>();
        if(tipo>0)this.creador.add(creador);
        this.tipo=tipo;
    }
    public Contenido(String nombre, String creador, Time duracion, Boolean explicito, int tipo){
        this.nombre=nombre;
        this.duracion=duracion;
        this.explicito=explicito;
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

    public Time getDuracion(){return duracion;}
    public boolean getExplicito(){return explicito;}
    public void setExplicito(boolean explicito){this.explicito= explicito;}
}
