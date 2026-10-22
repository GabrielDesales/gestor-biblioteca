package ui;

import javax.swing.JOptionPane;
import logica_del_proyecto.Libro;
import logica_del_proyecto.LibroEnlazado;
import logica_del_proyecto.Validar;

/**
 *
 * @author ptorr
 */
public class PanelEliminar extends javax.swing.JPanel
{

    private javax.swing.JPanel contentArea;
    private LibroEnlazado obj;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PanelEliminar.class.getName());

    /**
     * Creates new form PanelEliminarLibro
     */
    public PanelEliminar(LibroEnlazado libro, javax.swing.JPanel contentArea)
    {
        initComponents();
        this.obj = libro;
        this.contentArea = contentArea;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents()
    {

        fondo = new javax.swing.JPanel();
        tituloFormulario = new javax.swing.JPanel();
        añadirLibro = new javax.swing.JLabel();
        isbn = new javax.swing.JLabel();
        isbnT = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        libroAEliminar = new javax.swing.JLabel();
        resultados = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbResultados = new javax.swing.JTable();

        fondo.setBackground(new java.awt.Color(204, 204, 255));
        fondo.setLayout(new java.awt.BorderLayout());

        tituloFormulario.setBackground(new java.awt.Color(153, 153, 255));
        tituloFormulario.setPreferredSize(new java.awt.Dimension(669, 50));

        añadirLibro.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        añadirLibro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        añadirLibro.setText("ELIMINAR LIBRO");

        javax.swing.GroupLayout tituloFormularioLayout = new javax.swing.GroupLayout(tituloFormulario);
        tituloFormulario.setLayout(tituloFormularioLayout);
        tituloFormularioLayout.setHorizontalGroup(
                tituloFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tituloFormularioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(añadirLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                                .addContainerGap())
        );
        tituloFormularioLayout.setVerticalGroup(
                tituloFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tituloFormularioLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(añadirLibro)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        isbn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        isbn.setText("ISBN: ");

        isbnT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        isbnT.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                isbnTKeyPressed(evt);
            }

            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                isbnTKeyTyped(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(153, 153, 255));
        btnAceptar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAceptar.setText("Eliminar");
        btnAceptar.setBorderPainted(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAceptarActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(153, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBuscarActionPerformed(evt);
            }
        });

        libroAEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        libroAEliminar.setAlignmentX(25);
        libroAEliminar.setText("Ingrese el ISBN, titulo o autor del libro a eliminar:");

        resultados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        resultados.setText("Vista previa del libro:");

        tbResultados.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]
                {
                    {
                        null, null, null, null
                    },
                    {
                        null, null, null, null
                    },
                    {
                        null, null, null, null
                    },
                    {
                        null, null, null, null
                    }
                },
                new String[]
                {
                    "Nombre", "Autor", "Año", "Categoría"
                }
        )
        {
            Class[] types = new Class[]
            {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        });
        tbResultados.setEnabled(false);
        tbResultados.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbResultados);
        if (tbResultados.getColumnModel().getColumnCount() > 0)
        {
            tbResultados.getColumnModel().getColumn(0).setPreferredWidth(150);
            tbResultados.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbResultados.getColumnModel().getColumn(2).setPreferredWidth(60);
            tbResultados.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
                fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tituloFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                        .addGroup(fondoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2)
                                        .addGroup(fondoLayout.createSequentialGroup()
                                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(libroAEliminar)
                                                        .addGroup(fondoLayout.createSequentialGroup()
                                                                .addComponent(isbn)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(isbnT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(resultados)
                                                        .addGroup(fondoLayout.createSequentialGroup()
                                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 12, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        fondoLayout.setVerticalGroup(
                fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(fondoLayout.createSequentialGroup()
                                .addComponent(tituloFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(libroAEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(isbn)
                                        .addComponent(isbnT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar))
                                .addGap(18, 18, 18)
                                .addComponent(resultados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar)
                                        .addComponent(btnAceptar))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    private void isbnTKeyPressed(java.awt.event.KeyEvent evt)
    {
        // Enter ejecuta búsqueda
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
        {
            btnBuscarActionPerformed(null);
        }
        Validar.enterCadenaNoVacia(this, evt, isbnT, btnBuscar);
    }

    private void isbnTKeyTyped(java.awt.event.KeyEvent evt)
    {
       
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt)
    {
      
{
    String inputRaw = isbnT.getText().trim();

    if (this.obj.listaVacia())
    {
        JOptionPane.showMessageDialog(this, "No hay libros en la biblioteca", 
            "Biblioteca vacía", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    if (inputRaw.isEmpty())
    {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un ISBN, título o autor", 
            "Campo vacío", JOptionPane.WARNING_MESSAGE);
        isbnT.requestFocus();
        return;
    }

    // buscarFlexible ya maneja ISBN, título y autor
    Libro libroEncontrado = this.obj.buscarLibro(inputRaw);

    if (libroEncontrado != null)
    {
        tabla(libroEncontrado);
        JOptionPane.showMessageDialog(this, "Libro encontrado. Revise la vista previa antes de eliminar.", 
            "Libro encontrado", JOptionPane.INFORMATION_MESSAGE);
        btnAceptar.setEnabled(true);
    }
    else
    {
        tabla(null);
        JOptionPane.showMessageDialog(this, "Libro no encontrado en la biblioteca", 
            "No encontrado", JOptionPane.INFORMATION_MESSAGE);
        isbnT.requestFocus();
        isbnT.selectAll();
        btnAceptar.setEnabled(false);
    }
}

    
    } 

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt)
{
    String isbnRaw = isbnT.getText().trim();
    String isbnLimpio = isbnRaw.replace("-", "").replace(" ", "");

    if (isbnLimpio.isEmpty())
    {
        JOptionPane.showMessageDialog(this, "Primero busque un libro válido", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // ✅ Intenta con ambos formatos
    Libro encontrado = obj.buscarLibro(isbnLimpio);
    if (encontrado == null) encontrado = obj.buscarLibro(isbnRaw);

    if (encontrado != null)
    {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el libro?\n\n"
                + "Título: " + encontrado.getTitulo() + "\n"
                + "Autor: " + encontrado.getAutor() + "\n"
                + "ISBN: " + isbnRaw + "\n\n"
                + "Esta acción es permanente e irreversible.",
                "Confirmar eliminación permanente",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION)
        {
            //   eliminar con ambos formatos
            boolean eliminado = obj.eliminarPorCriterio(isbnLimpio);
            if (!eliminado) eliminado = obj.eliminarPorCriterio(isbnRaw);

            if (eliminado)
            {
                JOptionPane.showMessageDialog(this, "Libro eliminado correctamente", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                limpiarTodo(); // Solo limpiar, sin navegar
            } else
            {
                JOptionPane.showMessageDialog(this, "Error al eliminar el libro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else
    {
        JOptionPane.showMessageDialog(this, "No se encontró el libro. Busque primero.", "Error", JOptionPane.WARNING_MESSAGE);
    }
}
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt)
    {
       
{
    int resultado = JOptionPane.showConfirmDialog(this, "¿Está seguro de cancelar esta acción?",
            "Cancelar", JOptionPane.YES_NO_OPTION);

    if (resultado == JOptionPane.YES_OPTION)
    {
        limpiarTodo();
    }
}
    }

    /**
     * Carga los datos del libro en la tabla para vista previa
     */
    private void tabla(Libro libro)
    {
        // Se obtiene el modelo de la tabla actual
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tbResultados.getModel();

        // Se limpia la tabla por si había una búsqueda anterior
        modelo.setRowCount(0);

        // Si el libro existe, se agrega como una fila
        if (libro != null)
        {
            Object[] fila =
            {
                libro.getTitulo(),
                libro.getAutor(),
                libro.getAnio(),
                libro.getCategoria()
            };
            modelo.addRow(fila);
        }
    }

    /**
     * Limpia todos los campos y desactiva botón eliminar
     */
    private void limpiarTodo()
    {
        isbnT.setText("");
        tabla(null);
        btnAceptar.setEnabled(false);
        isbnT.requestFocus();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel añadirLibro;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel isbn;
    private javax.swing.JTextField isbnT;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel libroAEliminar;
    private javax.swing.JLabel resultados;
    private javax.swing.JTable tbResultados;
    private javax.swing.JPanel tituloFormulario;
    // End of variables declaration                   
}
