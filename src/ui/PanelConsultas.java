package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica_del_proyecto.Libro;
import logica_del_proyecto.LibroEnlazado;

/**
 * Panel para consultar todos los libros disponibles
 */
public class PanelConsultas extends JPanel
{

    private LibroEnlazado listaLibros;

    // Componentes UI
    private JTable tblLibros;
    private DefaultTableModel modeloLibros;
    private JLabel lblTotalLibros;
    private JLabel lblLibrosDisponibles;

    // Botones
    private JButton btnActualizar;
    private JButton btnRegresar;

    public PanelConsultas(LibroEnlazado listaLibros)
    {
        this.listaLibros = listaLibros;
        initComponents();
        cargarLibros();
        actualizarContadores();
    }

    private void initComponents()
    {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        add(panelTitulo(), BorderLayout.NORTH);
        add(panelTabla(), BorderLayout.CENTER);
        add(panelBotones(), BorderLayout.SOUTH);
    }

    private JPanel panelTitulo()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(153, 180, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JLabel lblTitulo = new JLabel("CONSULTA DE LIBROS DISPONIBLES");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo);

        return panel;
    }

    private JPanel panelTabla()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(204, 204, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Lista de libros"));

        // Crear tabla
        String[] columnas =
        {
            "Título", "Autor", "ISBN", "Categoría", "Año", "Estado", "Portada"
        };
        modeloLibros = new DefaultTableModel(columnas, 0);
        tblLibros = new JTable(modeloLibros);
        tblLibros.setRowHeight(25);
        tblLibros.setGridColor(new Color(230, 230, 230));
        tblLibros.setSelectionBackground(new Color(153, 180, 255));

        // Scroll para tabla
        JScrollPane scroll = new JScrollPane(tblLibros);
        scroll.setPreferredSize(new java.awt.Dimension(800, 400));

        // Panel resumen
        JPanel resumen = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resumen.setBackground(new Color(204, 204, 255));
        resumen.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        lblTotalLibros = new JLabel("Total libros: 0");
        lblTotalLibros.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblTotalLibros.setForeground(new Color(50, 100, 200));
        resumen.add(lblTotalLibros);

        lblLibrosDisponibles = new JLabel(" | Disponibles: 0");
        lblLibrosDisponibles.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblLibrosDisponibles.setForeground(new Color(50, 150, 50));
        resumen.add(lblLibrosDisponibles);

        panel.add(resumen, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel panelBotones()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panel.setBackground(new Color(204, 204, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBackground(new Color(100, 200, 100));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnActualizar.addActionListener(evt ->
        {
            cargarLibros();
            actualizarContadores();
        });

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(200, 100, 100));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnRegresar.addActionListener(evt ->
        {
            // Lógica para regresar (emitir evento o cerrar)
            if (getParent() instanceof javax.swing.JComponent)
            {
                ((javax.swing.JComponent) getParent()).remove(this);
                getParent().revalidate();
                getParent().repaint();
            }
        });

        panel.add(btnActualizar);
        panel.add(btnRegresar);

        return panel;
    }

    /**
     * Carga todos los libros en la tabla
     */
    private void cargarLibros()
    {
        modeloLibros.setRowCount(0); // Limpiar tabla

        if (listaLibros.listaVacia())
        {
            return;
        }

        Libro actual = listaLibros.getPrimero();
        while (actual != null)
        {
            modeloLibros.addRow(new Object[]
            {
                actual.getTitulo(),
                actual.getAutor(),
                actual.getIsbn(),
                actual.getCategoria(),
                actual.getAnio(),
                actual.estaDisponible() ? "Disponible" : "Prestado",
                actual.getPortada() != null ? "📷" : "📄"
            });
            actual = actual.getNext();
        }
    }

    /**
     * Actualiza contadores de libros
     */
    private void actualizarContadores()
    {
        int total = 0;
        int disponibles = 0;

        Libro actual = listaLibros.getPrimero();
        while (actual != null)
        {
            total++;
            if (actual.estaDisponible())
            {
                disponibles++;
            }
            actual = actual.getNext();
        }

        lblTotalLibros.setText("Total libros: " + total);
        lblLibrosDisponibles.setText(" | Disponibles: " + disponibles);
    }

    // Getters para acceso externo
    public JTable getTblLibros()
    {
        return tblLibros;
    }

    public DefaultTableModel getModeloLibros()
    {
        return modeloLibros;
    }
}
