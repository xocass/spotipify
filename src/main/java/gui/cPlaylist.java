package gui;

import aplicacion.Cancion;
import aplicacion.FachadaAplicacion;
import aplicacion.Oyente;
import aplicacion.Playlist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class cPlaylist {
    private char opcion;//a playlist       b disco       c podcast
    private int id;
    private Oyente user;
    private FachadaGui fgui;
    private FachadaAplicacion fa;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelCreador;
    @FXML
    private Label labelDuracion;
    @FXML
    private Label labelNelementos;
    @FXML
    private Label labelGeneros;
    @FXML
    private ImageView imagen;
    public void setOpcionIdUser(char opcion, int id, Oyente user){
        this.opcion=opcion;
        this.id=id;
        this.user=user;
    }

    public void setImagen() {
        Image userImage=null;
        switch(opcion) {
            case'a':
                userImage= new Image(getClass().getResource("/spoti/icons8-lista-de-reproducción-de-vídeos-100.png").toExternalForm());
                break;
            case 'b':
                userImage= new Image(getClass().getResource("/spoti/icons8-cd-98.png").toExternalForm());
                break;
            case 'c':
                userImage=new Image(getClass().getResource("/spoti/icons8-micrófono-100.png").toExternalForm());
        }
        imagen.setImage(userImage);
    }
    public void setLabelNombre(String nombre){
        labelNombre.setText(nombre);
    }
    public void setLabelCreador(ArrayList<String> creador){
        for(int i=0;i<creador.size();i++){
            if(i!=0)labelCreador.setText(labelCreador.getText()+", ");
            labelCreador.setText(labelCreador.getText()+creador.get(i));
        }
    }
    public void setLabelDuracion(Time suma){
        labelDuracion.setText(suma.toString());
    }
    public void setLabelNelementos(int n){
        labelNelementos.setText(n+labelNelementos.getText());
    }
    public void setLabelGeneros(ArrayList<String> generos) {
        for (int i = 0; i < generos.size(); i++) {
            if (i != 0) labelGeneros.setText(labelGeneros.getText() + ", ");
            labelGeneros.setText(labelGeneros.getText() + generos.get(i));
        }
    }

    public void setFachadas(FachadaGui fgui, FachadaAplicacion fa){
        this.fgui=fgui;
        this.fa=fa;
    }
    public cargarCanciones(){
        switch(opcion) {
            case 'a','b':
                ArrayList<Cancion> resultado=fa.getCancionesAP(id,opcion);
                for (Cancion aux : resultado) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("templateAnhadirArtista.fxml"));
                    boxAlbum.getChildren().add(loader.load());
                    cTemplateAnhadirArtista controller = loader.getController();
                    controller.setLabelTexto(aux.getNombre());
                }
                break;
            case'c':
        }
    }
    @FXML
    public void clickInicio() throws IOException {
        fgui.principal();
    }
    @FXML
    public void clickUsuario() throws IOException {
        fgui.showUsuario();
    }
    @FXML
    public void clickBiblioteca() throws IOException{
        fgui.showBiblioteca();
    }
    @FXML
    public void clickAjustes() throws IOException{
        fgui.showAjustes();
    }
    @FXML
    public void clickBuscar() throws IOException {
        fgui.showBuscar();
    }
}
