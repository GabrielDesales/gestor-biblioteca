
package logica_del_proyecto;

/**
 *
 * @author danie
 */
import java.time.LocalDate;

public class Prestamo {
    
    private Libro libro;
    private String nombre_Usuario;
    private LocalDate fecha_Prestamo;

    public Prestamo(Libro libro, String nombreUsuario) {
        this.libro = libro;
        this.nombre_Usuario = nombreUsuario;
        this.fecha_Prestamo = LocalDate.now();
    }

    public Libro getLibro() {
        return libro;
    }

    public String getNombreUsuario() {
        return nombre_Usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fecha_Prestamo;
    }
}