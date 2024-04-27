package aplicacion;

import java.util.ArrayList;

public class Podcast extends Contenido{
    private int idPodcast;

    public Podcast(String nombre, String artista, String idartista,int idPodcast){
        super(nombre, artista, idartista);
        this.idPodcast= idPodcast;
    }

    public int getIdPodcast() {return idPodcast;}

}
