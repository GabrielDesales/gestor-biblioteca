/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import logica_del_proyecto.LibroEnlazado;
import logica_del_proyecto.AdministradorDePrestamos;
import logica_del_proyecto.Prestamo;
import logica_del_proyecto.Libro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Queue;

public class PanelPrestamosYDev extends JPanel {

    private AdministradorDePrestamos control;
    private LibroEnlazado listaLibros;

    // Colores basados en tu imagen
    Color lilaFondo = new Color(204, 204, 255);
    Color moradoTitulo = new Color(153, 153, 255);
    Color grisBoton = new Color(225, 225, 225);

    private JTextField txtIsbnPrestar;
    private JTextField txtUsuario;
    private JButton btnPrestar;
    private JButton btnDevolver;
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;

    public PanelPrestamosYDev(AdministradorDePrestamos control, LibroEnlazado listaLibros) {
        this.control = control;
        this.listaLibros = listaLibros;

        setLayout(new BorderLayout(0, 0));
        setBackground(lilaFondo);

        JLabel lblTitulo = new JLabel("PRÉSTAMOS Y DEVOLUCIONES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(moradoTitulo);
        lblTitulo.setPreferredSize(new Dimension(0, 40));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel contenedorCentral = new JPanel(new BorderLayout(10, 10));
        contenedorCentral.setBackground(lilaFondo);
        contenedorCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        contenedorCentral.add(panelPrestar(), BorderLayout.NORTH);
        contenedorCentral.add(panelTabla(), BorderLayout.CENTER);

        add(contenedorCentral, BorderLayout.CENTER);
    }

    private JPanel panelPrestar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panel.setBackground(lilaFondo);
        
        JLabel lblIsbn = new JLabel("ISBN:");
        lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 12));
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));

        txtIsbnPrestar = new JTextField(12);
        txtUsuario = new JTextField(12);
        
        btnPrestar = new JButton("Prestar");
        estilizarBoton(btnPrestar);

        panel.add(lblIsbn);
        panel.add(txtIsbnPrestar);
        panel.add(lblUser);
        panel.add(txtUsuario);
        panel.add(btnPrestar);

        btnPrestar.addActionListener(e -> prestarLibro());

        return panel;
    }

    private JPanel panelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(lilaFondo);

        JLabel lblSubtitulo = new JLabel("Préstamos Activos:");
        lblSubtitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSubtitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(lblSubtitulo, BorderLayout.NORTH);

        String[] columnas = {"ISBN", "Título", "Usuario", "Fecha", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        
        tablaPrestamos = new JTable(modeloTabla);
        tablaPrestamos.setFillsViewportHeight(true);
        
        JScrollPane scroll = new JScrollPane(tablaPrestamos);
        panel.add(scroll, BorderLayout.CENTER);

        btnDevolver = new JButton("Devolver libro");
        estilizarBoton(btnDevolver);
        btnDevolver.setPreferredSize(new Dimension(180, 35));
        btnDevolver.addActionListener(e -> devolverLibro());
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(lilaFondo);
        panelBoton.add(btnDevolver);
        panelBoton.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(moradoTitulo);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
    }

    // --- MÉTODOS DE LÓGICA (Al final para que reconozcan las variables de arriba) ---

    private void prestarLibro() {
        String isbn = txtIsbnPrestar.getText().trim();
        String usuario = txtUsuario.getText().trim();

        if (isbn.isEmpty() || usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el ISBN y el usuario");
            return;
        }

        Libro libro = listaLibros.buscarLibro(isbn);
        if (libro == null) {
            JOptionPane.showMessageDialog(this, "No existe un libro con ese ISBN");
            return;
        }

        String mensaje = control.prestar_libro(libro, usuario);
        if (!mensaje.equals("Prestamo realizado correctamente")) {
            JOptionPane.showMessageDialog(this, mensaje);
            return;
        }

        actualizarTabla();
        txtIsbnPrestar.setText("");
        txtUsuario.setText("");
    }

    private void devolverLibro() {
        if (control.getColaPrestamos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay préstamos activos");
            return;
        }
        control.devolver_libro();
        actualizarTabla();
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        Queue<Prestamo> cola = control.getColaPrestamos();
        for (Prestamo p : cola) {
            modeloTabla.addRow(new Object[]{
                p.getLibro().getIsbn(),
                p.getLibro().getTitulo(),
                p.getNombreUsuario(),
                p.getFechaPrestamo(),
                p.getLibro().getEstado()
            });
        }
    }
}