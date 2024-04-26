package aplicacion;

import java.util.ArrayList;

public class Album extends Contenido{
    private int idAlbum;
    private String tipo;
    private int anhoLanzamiento;
    private int idDiscografica;

    public Album(String nombre, String artista, int idAlbum, String tipo, int anhoLanzamiento, int idDiscografica){
        super(nombre, artista);
        this.idAlbum= idAlbum;
        this.tipo=tipo;
        this.anhoLanzamiento= anhoLanzamiento;
        this.idDiscografica= idDiscografica;
    }
    public Album(String nombre, String artista, int idAlbum, String tipo){
        super(nombre, artista);
        this.idAlbum= idAlbum;
        this.tipo= tipo;
    }


    public int getIdAlbum(){return idAlbum;}
    public void setTipoA(String tipo) {this.tipo = tipo;}

    public String getTipoA(){return tipo;}
    public void setAnhoLanzamiento(int anho) {this.anhoLanzamiento = anho;}
    public int getAnhoLanzamiento(){return anhoLanzamiento;}
    public void setIdDiscografica(int idDiscografica) {this.idDiscografica = idDiscografica;}
    public int getIdDiscografica(){return idDiscografica;}
}
