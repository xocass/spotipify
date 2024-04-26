package aplicacion;

public class Oyente extends Usuario{
    private String plan;
    private int cancion;
    private String fechapago;
    private String fechavenc;
    public Oyente(String nombre, String contrasena, String email, String fechanac, String plan, int cancion, String fechapago, String fechavenc){
        super(nombre, contrasena, email, fechanac);
        this.plan=plan;
        this.cancion=cancion;
        this.fechapago=fechapago;
        this.fechavenc=fechavenc;
    }
    public Oyente(String nombre, String contrasena, String email, String fechanac) {
        super(nombre, contrasena, email, fechanac);
    }

}
