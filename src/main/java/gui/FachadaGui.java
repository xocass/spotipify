package gui;

import aplicacion.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class FachadaGui extends Application {
    private Stage entrarStage;
    private Stage principalStage;
    private FachadaAplicacion fa;
    private Oyente actual;


    public Oyente getActual() {return actual;}
    @Override
    public void start(Stage stage) throws IOException {
        entrarStage=stage;
        entrarStage.setResizable(false);
        principalStage = new Stage();
        principalStage.setResizable(false);
        fa= new FachadaAplicacion(this);
        iniciarSesion();
    }
    public void muestraExcepcion(String txtExcepcion){}

    public void registrar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vRegistrarse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800,450);
        cRegistrarse controller = fxmlLoader.getController();
        controller.setFgui(this, this.fa);
        entrarStage.setTitle("Registrarse");
        entrarStage.setScene(scene);
        entrarStage.show();
    }
    public void iniciarSesion() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vIniciaSesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        cIniciaSesion controller = fxmlLoader.getController();
        controller.setFachadas(this,this.fa);
        controller.setHostServices(getHostServices());
        entrarStage.setTitle("Iniciar Sesion");
        entrarStage.setScene(scene);
        entrarStage.show();
    }
    public void cargando(Oyente aux) throws IOException {
        actual=aux;
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vCargando.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        entrarStage.setTitle("Cargando...");
        entrarStage.setScene(scene);
        entrarStage.show();
        principal();
        principalStage.setTitle("Spotipify");
        entrarStage.close();
    }

    public void principal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cPrincipal controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        controller.iniciar();
        principalStage.setScene(scene);
        principalStage.show();
    }

    public void showUsuario() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vUsuario.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cUsuario controller = fxmlLoader.getController();
        controller.setFachadas(this,fa,actual);
        principalStage.setScene(scene);
        principalStage.show();
    }
    public void showArtista(Artista artista) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vArtista.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cArtista controller = fxmlLoader.getController();
        controller.setLabelGeneros(fa.getGeneros(artista.getNombre()));
        controller.setLabelArtista(artista.getNombreArtistico());
        controller.setLabelPais(artista.getPaisNacimiento());
        controller.setLabelSeguidores(fa.getSeguidores(artista.getNombre()));
        controller.setFachadas(this,fa);
        principalStage.setScene(scene);
        principalStage.show();
    }
    public void showBuscar() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vBuscar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cBuscar controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        principalStage.setScene(scene);
        principalStage.show();
    }
    public void showAjustes() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vAjustes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cAjustes controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        principalStage.setScene(scene);
        principalStage.show();
    }
    public void showBiblioteca() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vBiblioteca.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cBiblioteca controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        principalStage.setScene(scene);
        controller.iniciar();
        principalStage.show();
    }
    public void showAdmin() throws IOException{
        //actual=aux;
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 518, 246);
        cAdmin controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        principalStage.setTitle("Ventana de administrador");
        principalStage.setScene(scene);
        principalStage.show();
        entrarStage.close();
    }
    public void showOyenteMod() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vOyenteMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 566);
        cOyenteMod controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        principalStage.setScene(scene);
        principalStage.show();
    }
    public void showArtistasMod() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vArtistaMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 566);
        cArtistaMod controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        principalStage.setScene(scene);
        principalStage.show();
    }
    public void showContenidoMod() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vContenidoMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 566);
        cContenidoMod controller = fxmlLoader.getController();

       /* if(contenido!=null){
            controller.clickEntrar(contenido);
        }*/
        controller.setFachadas(this, fa);
        principalStage.setScene(scene);
        principalStage.show();

    }
    public void showPlaylist() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vPlaylist.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 566);
        cPlaylist controller = fxmlLoader.getController();

        controller.setFachadas(this, fa);
        principalStage.setScene(scene);
        principalStage.show();

    }
    public void irAtrasMod() throws IOException{showAdmin();}
    public static void main(String[] args) {
        launch();
    }
}

