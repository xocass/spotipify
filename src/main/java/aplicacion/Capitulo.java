package aplicacion;

import java.sql.Time;
public class Capitulo extends Podcast{
    final private int idCapitulo;
    final private Time duracion;
    private boolean explicito;

    public Capitulo(String nombre, int idPodcast, String artista, int idCapitulo, Time duracion,
                    boolean explicito){
        super(nombre, artista, idPodcast);
        this.idCapitulo= idCapitulo;
        this.duracion= duracion;
        this.explicito= explicito;
    }

    public int getIdCapitulo() {return idCapitulo;}
    public Time getDuracion(){return duracion;}
    public boolean getExplicito(){return explicito;}
    public void setExplicito(boolean explicito) {this.explicito= explicito;}
}
