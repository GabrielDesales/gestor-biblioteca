/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ui;

import logica_del_proyecto.GeneradorReportes;
import logica_del_proyecto.LibroEnlazado;
import logica_del_proyecto.AdministradorDePrestamos;
import logica_del_proyecto.Libro;
import logica_del_proyecto.Prestamo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PanelReportes extends JPanel {

    private GeneradorReportes generador;
    private LibroEnlazado listaLibros;
    private AdministradorDePrestamos control;

    // Tablas
    private JTable tablaDisponibles;
    private JTable tablaPrestados;
    private DefaultTableModel modeloDisponibles;
    private DefaultTableModel modeloPrestados;

    // Labels de resumen
    private JLabel lblTotalDisponibles;
    private JLabel lblTotalPrestados;
    private JLabel lblUltimaActualizacion;

    public PanelReportes(LibroEnlazado listaLibros, AdministradorDePrestamos control) {
        this.listaLibros = listaLibros;
        this.control     = control;
        this.generador   = new GeneradorReportes();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        add(panelResumen(),  BorderLayout.NORTH);
        add(panelTablas(),   BorderLayout.CENTER);
        add(panelBotones(),  BorderLayout.SOUTH);

        // Cargar datos al abrir el panel
        actualizarTodo();
    }

    // ---- Panel superior: contadores de resumen ----
    private JPanel panelResumen() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        panel.setBackground(new Color(240, 240, 255));
        panel.setBorder(BorderFactory.createTitledBorder("Resumen"));

        lblTotalDisponibles    = new JLabel("Disponibles: 0");
        lblTotalPrestados      = new JLabel("Prestados: 0");
        lblUltimaActualizacion = new JLabel("Última actualización: —");

        lblTotalDisponibles.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalDisponibles.setForeground(new Color(0, 128, 0));

        lblTotalPrestados.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalPrestados.setForeground(new Color(180, 50, 0));

        lblUltimaActualizacion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUltimaActualizacion.setForeground(Color.GRAY);

        panel.add(lblTotalDisponibles);
        panel.add(new JLabel(" | "));
        panel.add(lblTotalPrestados);
        panel.add(new JLabel("          "));
        panel.add(lblUltimaActualizacion);

        return panel;
    }

    // ---- Panel central: dos tablas una al lado de la otra ----
    private JPanel panelTablas() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        panel.setBackground(Color.WHITE);

        // --- Tabla disponibles ---
        String[] colsDisp = {"ISBN", "Título", "Autor", "Categoría", "Año"};
        modeloDisponibles = new DefaultTableModel(colsDisp, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaDisponibles = new JTable(modeloDisponibles);
        tablaDisponibles.setRowHeight(22);

        JPanel panelDisp = new JPanel(new BorderLayout());
        panelDisp.setBorder(BorderFactory.createTitledBorder("📗  Libros Disponibles"));
        panelDisp.add(new JScrollPane(tablaDisponibles), BorderLayout.CENTER);

        // --- Tabla prestados ---
        String[] colsPres = {"ISBN", "Título", "Usuario", "Fecha Préstamo"};
        modeloPrestados = new DefaultTableModel(colsPres, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaPrestados = new JTable(modeloPrestados);
        tablaPrestados.setRowHeight(22);

        JPanel panelPres = new JPanel(new BorderLayout());
        panelPres.setBorder(BorderFactory.createTitledBorder("📕  Libros Prestados"));
        panelPres.add(new JScrollPane(tablaPrestados), BorderLayout.CENTER);

        panel.add(panelDisp);
        panel.add(panelPres);

        return panel;
    }

    // ---- Panel inferior: botones de accion ----
    private JPanel panelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panel.setBackground(Color.WHITE);

        JButton btnActualizar = new JButton("🔄 Actualizar");
        JButton btnGuardar    = new JButton("💾 Guardar Reporte (.txt)");

        btnActualizar.setPreferredSize(new Dimension(150, 35));
        btnGuardar.setPreferredSize(new Dimension(200, 35));

        btnActualizar.addActionListener(e -> actualizarTodo());

        btnGuardar.addActionListener(e -> {
            // Nombre del archivo con fecha y hora para no sobreescribir
            String fecha = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String nombreArchivo = "reporte_libros_" + fecha + ".txt";

            generador.guardarReporteEnArchivo(listaLibros, control, nombreArchivo);

            JOptionPane.showMessageDialog(this,
                    "Reporte guardado correctamente:\n" + nombreArchivo,
                    "Reporte generado",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(btnActualizar);
        panel.add(btnGuardar);

        return panel;
    }

    // ---- Actualiza ambas tablas y los contadores ----
    public void actualizarTodo() {
        // Obtener ArrayLists desde el generador
        ArrayList<Libro>    disponibles = generador.obtenerLibrosDisponibles(listaLibros);
        ArrayList<Prestamo> prestados   = generador.obtenerLibrosPrestados(control);

        // Limpiar y rellenar tabla de disponibles
        modeloDisponibles.setRowCount(0);
        for (Libro l : disponibles) {
            modeloDisponibles.addRow(new Object[]{
                l.getIsbn(),
                l.getTitulo(),
                l.getAutor(),
                l.getCategoria(),
                l.getAnio()
            });
        }

        // Limpiar y rellenar tabla de prestados
        modeloPrestados.setRowCount(0);
        for (Prestamo p : prestados) {
            modeloPrestados.addRow(new Object[]{
                p.getLibro().getIsbn(),
                p.getLibro().getTitulo(),
                p.getNombreUsuario(),
                p.getFechaPrestamo()
            });
        }

        // Actualizar labels de resumen
        lblTotalDisponibles.setText("Disponibles: " + disponibles.size());
        lblTotalPrestados.setText("Prestados: " + prestados.size());
        lblUltimaActualizacion.setText("Última actualización: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}
