package logica_del_proyecto;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import poo.Lecturas;

public class LibroEnlazado
{

    //Es el apuntador al primer nodo de la lista
    private Libro primero;

    //y este apuntador al ultimo nodo 
    private Libro ultimo;

    //mapa con el q se accede rapido a un libro usando su isbn
    Map<String, Libro> mp = new HashMap<>();
    Map<String, Libro> mpTitulo = new HashMap<>();
    Map<String, Libro> mpAutor = new HashMap<>();

    //Constructor
    //inicializa la lista vacia
    public LibroEnlazado()
    {
        primero = null;
        ultimo = null;
    }

    //Metodo q verifica si la lista está vacia
    public boolean listaVacia()
    {
        return (primero == null);
    }

    //Metodo q inserta un libro al inicio de la lista
public void insertarPrimero(String tit, String aut, String isb, String cat, int ani, String port)
{
    // 1. VALIDACIÓN REAL: Si el método retorna 1, el ISBN ya existe.
    if (validarIsbn(isb) == 1) 
    {
        // Lanzamos una excepción para que el Panel (Controlador) la capture 
        // y muestre un mensaje de error al usuario.
        throw new RuntimeException("Error: El ISBN " + isb + " ya está registrado.");
    }

    // 2. Crear nodo (Solo si la validación pasó)
    Libro newLibro = new Libro(tit, aut, isb, cat, ani, port);

    // 3. Enlazar lista
    if (listaVacia())
    {
        primero = ultimo = newLibro;
    } else
    {
        newLibro.next = primero;
        primero = newLibro;
    }

    // 4. Actualizar Mapas para búsquedas rápidas
    mp.put(isb, newLibro);
    mpTitulo.put(tit.toLowerCase(), newLibro);
    mpAutor.put(aut.toLowerCase(), newLibro);
}

    // Metodo q inserta un libro al final de la lista
    public void insertarUltimo(String tit, String aut, String isb, String cat, int ani, String port)
    {
        validarIsbn(isb);
        // Crear nodo
        Libro newLibro = new Libro(tit, aut, isb, cat, ani, port);

        // Enlazar lista
        if (listaVacia())
        {
            primero = ultimo = newLibro;
        } else
        {
            ultimo.next = newLibro;
            ultimo = newLibro;
        }

        // Mapas
        mp.put(isb, newLibro);
        mpTitulo.put(tit.toLowerCase(), newLibro);
        mpAutor.put(aut.toLowerCase(), newLibro);
    }
    
    public int validarIsbn(String isb)
    {
        if (mp.containsKey(isb))
        {
            return 1;
        }else
        {
            return 0;
        }
    }

    //metodo para eliminar un libro 
    /**
     * Elimina primer libro que coincida con criterio (título/autor/ISBN)
     */
    public boolean eliminarPorCriterio(String criterio)
    {
        String busqueda = criterio.trim().toLowerCase();

        // Buscar coincidencia
        Libro actual = primero;
        Libro anterior = null;

        while (actual != null)
        {
            if (actual.getTitulo().toLowerCase().contains(busqueda)
                    || actual.getAutor().toLowerCase().contains(busqueda)
                    || actual.getIsbn().contains(busqueda))
            {

                // Encontrado: eliminar
                if (anterior == null)
                { // Es primero
                    primero = actual.next;
                    if (primero == null)
                    {
                        ultimo = null;
                    }
                } else
                {
                    anterior.next = actual.next;
                    if (actual == ultimo)
                    {
                        ultimo = anterior;
                    }
                }

                // Remover del mapa
                mp.remove(actual.getIsbn());
                mpTitulo.remove(actual.getTitulo().toLowerCase());
                mpAutor.remove(actual.getAutor().toLowerCase());
                return true;
            }
            anterior = actual;
            actual = actual.next;
        }
        return false; // No encontrado
    }

    public Libro buscarFlexible(String texto)
    {
        String busqueda = texto.trim().toLowerCase();

        // ISBN exacto (rápido)
        if (mp.containsKey(busqueda))
        {
            return mp.get(busqueda);
        }

        // Título o autor (recorre lista)
        Libro actual = primero;
        while (actual != null)
        {
            if (actual.getTitulo().toLowerCase().contains(busqueda)
                    || actual.getAutor().toLowerCase().contains(busqueda))
            {
                return actual;
            }
            actual = actual.next;
        }

        return null;
    }

// Renombra buscarLibro:
    public Libro buscarLibro(String criterio)
    {
        return buscarFlexible(criterio);
    }

    public Libro getPrimero()
    {
        return primero;
    }

    public Libro getUltimo()
    {
        return ultimo;
    }
}
