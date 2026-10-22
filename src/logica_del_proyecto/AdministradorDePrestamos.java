
package logica_del_proyecto;


import java.util.LinkedList;
import java.util.Queue;

public class AdministradorDePrestamos {

    //Cola (fifo)
    //almacena los prestamos activos
    private Queue<Prestamo> colaPrestamos = new LinkedList<>();

    //metodo para prestar un libro
    public String prestar_libro(Libro libro, String nombre_usuario) {

        //validamos si el libro ya esta en la cola o sea si ya fue prestado
        for (Prestamo p : colaPrestamos) {

            //hacemos la comparacion de isbn del libro actual con los q ya estan prestados
            if (p.getLibro().getIsbn().equals(libro.getIsbn())) {

                //no se permite agregarlo otra vez si ya fue prestado
                return "Este libro ya fue prestado";
            }
        }

        //verificamos si el libro esta disponible
        if (libro.estaDisponible()) {

            //se cambia el estado del libro a prestado ps cuando se presta JAJAJA
            libro.prestar();

            //para crear un nuevo objeto prestamo con el libro y el usuario
            Prestamo p = new Prestamo(libro, nombre_usuario);

            //se agrega a la cola de prestamos q creamos
            colaPrestamos.add(p);

            return "Prestamo realizado correctamente";

        } else {
           
            return "El libro ya está prestado";
        }
    }

    // este es el metodo para devolver un libro
    public void devolver_libro() {

        // se verifica si la cola está vacia
        if (colaPrestamos.isEmpty()) {

            // si no hay prestamos se muetsra este mensaje
            System.out.println("No hay préstamos registrados");
            return;
        }

        // aqui se obtiene yse elimina el primer préstamo de la cola 
        Prestamo p = colaPrestamos.poll();

        // aqui cambia el estado del libro
        p.getLibro().devolver();

        System.out.println("Libro devuelto correctamente: " +
                           p.getLibro().getIsbn());
    }

    // metodo getter para obtener la cola
    public Queue<Prestamo> getColaPrestamos() {

        return colaPrestamos;
    }
}

