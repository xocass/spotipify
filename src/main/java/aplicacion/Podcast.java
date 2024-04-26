package aplicacion;

import java.util.ArrayList;

public class Podcast extends Contenido{
    private int idPodcast;

    public Podcast(String nombre, String artista, int idPodcast){
        super(nombre, artista);
        this.idPodcast= idPodcast;
    }

    public int getIdPodcast() {return idPodcast;}

}
