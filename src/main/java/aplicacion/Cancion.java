package aplicacion;

import java.sql.Time;
public class Cancion extends Album{
    private int idCancion;
    private Time duracion;
    private String idioma;
    private String nombreGenero;
    private boolean letra;
    private boolean explicito;
    private int visualizaciones;


    public Cancion(String nombre, String artista, String idartista, int idAlbum, Time duracion, String idioma, String nombreGenero,
                   boolean letra, boolean explicito, int visualizaciones){
        super(nombre, artista, idartista, idAlbum, null);
        this.duracion= duracion;
        this.idioma=idioma;
        this.nombreGenero= nombreGenero;
        this.letra= letra;
        this.explicito= explicito;
        this.visualizaciones= visualizaciones;
    }

    public Cancion(String nombre, String artista, String idartista,int idAlbum, int idCancion, Time duracion, boolean explicito){
        super(nombre, artista, idartista, idAlbum, null);
        this.idCancion= idCancion;
        this.duracion= duracion;
        this.explicito= explicito;

    }
    public Cancion(String nombre, String artista, String idartista,int idAlbum, int idCancion, Time duracion, boolean explicito, String genero){
        super(nombre, artista, idartista, idAlbum, null);
        this.idCancion= idCancion;
        this.duracion= duracion;
        this.explicito= explicito;
        this.nombreGenero= genero;
    }
    public Cancion(String nombre, String artista, String idartista, int idAlbum, int idCancion){
        super(nombre, artista, idartista, idAlbum, null);
        this.idCancion= idCancion;
    }
    public Cancion(String nombre, String artista, String idartista, int idAlbum, int idCancion, boolean explicito, Time duracion){
        super(nombre, artista, idartista, idAlbum, null);
        this.idCancion= idCancion;
        this.explicito=explicito;
        this.duracion=duracion;
    }


    public int getIdCancion(){return idCancion;}
    public Time getDuracion(){return duracion;}
    public boolean getExplicito(){return explicito;}
    public void setExplicito(boolean explicito){this.explicito= explicito;}
    public void setVisualizaciones(int visualizaciones){this.visualizaciones=visualizaciones;}
    public int getVisualizaciones(){return visualizaciones;}
    public void setIdioma(String idioma){this.idioma=idioma;}
    public String getIdioma(){return idioma;}
    public void setLetra(boolean letra){this.letra= letra;}
    public boolean getLetra(){return letra;}
    public  String getNombreGenero(){return nombreGenero;}
}
