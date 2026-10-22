

package logica_del_proyecto;

import java.util.ArrayList;
import java.util.Queue;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorReportes {

    
    // GENERAR ARRAYLIST DE LIBROS DISPONIBLES
    // Recorre la LibroEnlazado y filtra los disponibles
    
    public ArrayList<Libro> obtenerLibrosDisponibles(LibroEnlazado listaLibros) {
        ArrayList<Libro> librosDisponibles = new ArrayList<>();

        // Recorremos el mapa de la lista enlazada
        for (Libro libro : listaLibros.mp.values()) {
            if (libro.estaDisponible()) {
                librosDisponibles.add(libro);
            }
        }

        return librosDisponibles;
    }

    
    // GENERAR ARRAYLIST DE LIBROS PRESTADOS
    // Usa la cola de prestamos activos del controlador
   
    public ArrayList<Prestamo> obtenerLibrosPrestados(AdministradorDePrestamos control) {
        ArrayList<Prestamo> librosPrestados = new ArrayList<>();

        Queue<Prestamo> cola = control.getColaPrestamos();

        // Recorremos la cola y agregamos cada prestamo al ArrayList
        for (Prestamo p : cola) {
            librosPrestados.add(p);
        }

        return librosPrestados;
    }

   
    // GUARDAR REPORTE EN ARCHIVO DE TEXTO
    // Recibe la lista de libros y el controlador para obtener
    // ambos ArrayLists y escribir el reporte en .txt
    
    public void guardarReporteEnArchivo(LibroEnlazado listaLibros,
                                        AdministradorDePrestamos control,
                                        String nombreArchivo) {

        // Obtener los dos ArrayLists filtrados
        ArrayList<Libro>   disponibles = obtenerLibrosDisponibles(listaLibros);
        ArrayList<Prestamo> prestados  = obtenerLibrosPrestados(control);

        // Fecha y hora del reporte
        LocalDateTime ahora = LocalDateTime.now();
        String fechaConsulta = ahora.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

            // ---- Encabezado ----
            writer.write("=".repeat(65));
            writer.newLine();
            writer.write("        BIBLIOTECA DIGITAL - REPORTE DE ESTADO");
            writer.newLine();
            writer.write("=".repeat(65));
            writer.newLine();
            writer.write("Fecha de consulta  : " + fechaConsulta);
            writer.newLine();
            writer.write("Libros disponibles : " + disponibles.size());
            writer.newLine();
            writer.write("Libros prestados   : " + prestados.size());
            writer.newLine();
            writer.write("=".repeat(65));
            writer.newLine();
            writer.newLine();

            // ---- Seccion: Libros Disponibles ----
            writer.write(">>> LIBROS DISPONIBLES (" + disponibles.size() + ")");
            writer.newLine();
            writer.write("-".repeat(65));
            writer.newLine();

            if (disponibles.isEmpty()) {
                writer.write("  (No hay libros disponibles en este momento)");
                writer.newLine();
            } else {
                // Recorremos el ArrayList de disponibles
                for (int i = 0; i < disponibles.size(); i++) {
                    Libro libro = disponibles.get(i);
                    writer.write(String.format("  %d. %s", i + 1, libro.getTitulo()));
                    writer.newLine();
                    writer.write(String.format("     Autor     : %s", libro.getAutor()));
                    writer.newLine();
                    writer.write(String.format("     ISBN      : %s", libro.getIsbn()));
                    writer.newLine();
                    writer.write(String.format("     Categoría : %s", libro.getCategoria()));
                    writer.newLine();
                    writer.write(String.format("     Año       : %d", libro.getAnio()));
                    writer.newLine();
                    writer.write("     Estado    : [DISPONIBLE]");
                    writer.newLine();
                    if (i < disponibles.size() - 1) writer.newLine();
                }
            }

            writer.newLine();

            // ---- Seccion: Libros Prestados ----
            writer.write(">>> LIBROS PRESTADOS (" + prestados.size() + ")");
            writer.newLine();
            writer.write("-".repeat(65));
            writer.newLine();

            if (prestados.isEmpty()) {
                writer.write("  (No hay libros prestados en este momento)");
                writer.newLine();
            } else {
                // Recorremos el ArrayList de prestados
                for (int i = 0; i < prestados.size(); i++) {
                    Prestamo p    = prestados.get(i);
                    Libro    libro = p.getLibro();
                    writer.write(String.format("  %d. %s", i + 1, libro.getTitulo()));
                    writer.newLine();
                    writer.write(String.format("     Autor          : %s", libro.getAutor()));
                    writer.newLine();
                    writer.write(String.format("     ISBN           : %s", libro.getIsbn()));
                    writer.newLine();
                    writer.write(String.format("     Categoría      : %s", libro.getCategoria()));
                    writer.newLine();
                    writer.write(String.format("     Prestado a     : %s", p.getNombreUsuario()));
                    writer.newLine();
                    writer.write(String.format("     Fecha préstamo : %s", p.getFechaPrestamo()));
                    writer.newLine();
                    writer.write("     Estado         : [PRESTADO]");
                    writer.newLine();
                    if (i < prestados.size() - 1) writer.newLine();
                }
            }

            // ---- Pie ----
            writer.newLine();
            writer.write("=".repeat(65));
            writer.newLine();
            writer.write("           FIN DEL REPORTE - " + fechaConsulta);
            writer.newLine();
            writer.write("=".repeat(65));
            writer.newLine();

            System.out.println("[OK] Reporte guardado en: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("[ERROR] No se pudo guardar el reporte: " + e.getMessage());
        }
    }
}