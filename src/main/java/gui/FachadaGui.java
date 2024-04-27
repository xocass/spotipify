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
    private Stage transaccionStage;
    private Stage escuchandoStage;
    private FachadaAplicacion fa;
    private Oyente actual;


    public Oyente getActual() {return actual;}

    public void setActual(Oyente aux){this.actual = aux;};

    @Override
    public void start(Stage stage) throws IOException {
        entrarStage=stage;
        entrarStage.setResizable(false);
        principalStage = new Stage();
        principalStage.setResizable(false);
        fa= new FachadaAplicacion(this);
        iniciarSesion();
    }

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
    public void showPerfil(String id) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vArtista.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        ArrayList<String> plan = new ArrayList<>();
        plan.add(fa.getPlan(id));
        cArtista controller = fxmlLoader.getController();
        controller.setFachadas(this,fa,false);
        controller.setId(id, false);
        controller.setLabelArtista(id);
        controller.setLabelPais_Seguidos(((Integer)fa.getnSeguidosU(id)).toString());
        controller.setLabelGeneros_Plan(plan);
        controller.iniciarP();

        controller.setLabelSeguidores(fa.getnSeguidoresU(id));
        principalStage.setScene(scene);
        principalStage.show();
    }
    public void showArtista(Artista artista) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vArtista.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cArtista controller = fxmlLoader.getController();
        controller.setFachadas(this,fa,true);
        controller.setId(artista.getNombre(), artista.getVerificado());
        controller.iniciar();
        controller.setLabelGeneros_Plan(fa.getGeneros(artista.getNombre()));
        controller.setLabelArtista(artista.getNombreArtistico());
        controller.setLabelPais_Seguidos(artista.getPaisNacimiento());
        controller.setLabelSeguidores(fa.getnSeguidores(artista.getNombre()));
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


    public void showActualizarPlan() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vActualizarPlan.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cActualizarPlan controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        controller.setHostServices(getHostServices());
        principalStage.setScene(scene);
        principalStage.show();
    }

    public void showTransaccionEnCurso(int tipo) throws IOException{

        transaccionStage = new Stage();
        transaccionStage.setResizable(false);
        transaccionStage.setTitle("Transaccion en curso");
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vTransaccionCurso.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 486,262);
        cTransaccionCurso controller = fxmlLoader.getController();
        controller.setFachadas(this,fa);
        controller.setTipo(tipo);
        transaccionStage.setScene(scene);
        transaccionStage.show();
    }

    public void showEscuchando(Contenido contenido) throws IOException{

        escuchandoStage = new Stage();
        escuchandoStage.setResizable(false);
        escuchandoStage.setTitle("Disfruta de tu música");
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vEscuchar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450,369);
        cEscuchar controller = fxmlLoader.getController();
        controller.setFachadas(this,fa,contenido);
        escuchandoStage.setScene(scene);
        escuchandoStage.show();
    }

    public void cerrarTransaccionEnCurso(){
        transaccionStage.close();
    }

    public void irAtrasMod() throws IOException{showAdmin();}
    public static void main(String[] args) {
        launch();
    }

    public void showLista(int id, char opcion) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(FachadaGui.class.getResource("vPlaylist.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        cPlaylist controller = fxmlLoader.getController();
        controller.setOpcionIdUser(opcion,id,actual);
        controller.setFachadas(this,fa);
        controller.setImagen();
        switch(opcion){
            case'a':
                Playlist contenido=fa.getPlaylistId(id);
                ArrayList<String> aux=new ArrayList<String>();
                aux.add(contenido.getCreador());
                controller.setLabelCreador(aux);
                controller.setLabelNombre(contenido.getNombre());
                controller.setLabelDuracion(fa.getDuracionPlaylist(id));
                break;
            case 'b':
                Album contenido1=fa.getAlbumId(id);
                controller.setLabelCreador(contenido1.getCreador());
                controller.setLabelNombre(contenido1.getNombre());
                controller.setLabelDuracion(fa.getDuracionAlbum(id));
                break;
            case'c':
                Podcast contenido2=fa.getPodcastId(id);
                controller.setLabelCreador(contenido2.getCreador());
                controller.setLabelNombre(contenido2.getNombre());
                controller.setLabelDuracion(fa.getDuracionPodcast(id));
                break;
        }

        principalStage.setScene(scene);
        principalStage.show();
    }
}

