package logica_del_proyecto;

public class Libro
{

    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private int anio;
    private String estado;
    public Libro next;
    private String portada;

    // constructor vacio
    public Libro()
    {
    }

    // constructor
    public Libro(String tit, String aut, String isb,
            String cat, int ani, String portada)
    {
        this.titulo = tit;
        this.autor = aut;
        this.isbn = isb;
        this.categoria = cat;
        this.anio = ani;
        this.estado = "Disponible";
        this.portada = portada;
    }

    //metodo para prestar
    public boolean prestar()
    {
        if (estado.equalsIgnoreCase("Disponible"))
        {
            estado = "Prestado";
            return true;
        }
        return false;
    }

    //metodo para devolver
    public void devolver()
    {
        estado = "Disponible";
    }

    //metodo para verificar disponibilidad
    public boolean estaDisponible()
    {
        return estado.equalsIgnoreCase("Disponible");
    }

    //Getters y Setters
    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getAutor()
    {
        return autor;
    }

    public void setAutor(String autor)
    {
        this.autor = autor;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public int getAnio()
    {
        return anio;
    }

    public void setAnio(int anio)
    {
        this.anio = anio;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public Libro getNext()
    {
        return next;
    }

    public void setNext(Libro next)
    {
        this.next = next;
    }
    
    public String getPortada()
    {
        return portada;
    }

    public void setPortada(String portada)
    {
        this.portada = portada;
    }
}
