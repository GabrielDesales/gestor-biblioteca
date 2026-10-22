/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica_del_proyecto;

/**
 *
 * @author ptorr
 */
public class Validar
{
    public static void validaAlfabeticos(java.awt.event.KeyEvent evt, int max, String texto)
    {
        char c = evt.getKeyChar();

        if (!Character.isLetter(c) && c != ' ')
        {
            evt.consume();
            return;
        }

        if (texto.length() >= max)
        {
            evt.consume();
        }
    }
    
    public static void validaAlfanumericos(java.awt.event.KeyEvent evt, int max, String texto)
    {
        char c = evt.getKeyChar();

        if (!Character.isLetterOrDigit(c) && c != ' ')
        {
            evt.consume();
            return;
        }

        if (texto.length() >= max)
        {
            evt.consume();
        }
    }
    
    public static void validaIsbn(java.awt.event.KeyEvent evt, int max, String texto)
    {
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) && c != ' ' && c!= '-')
        {
            evt.consume();
            return;
        }

        if (texto.length() >= max)
        {
            evt.consume();
        }
    }

    public static void validaEntero(java.awt.event.KeyEvent evt, int max, String texto)
    {
        char c = evt.getKeyChar();

        if (!Character.isDigit(c))
        {
            evt.consume();
            return;
        }

        if (texto.length() >= max)
        {
            evt.consume();
        }
    }

    public static void enterCadenaNoVacia(java.awt.Component ventana,
            java.awt.event.KeyEvent evt,
            javax.swing.JTextField actual,
            java.awt.Component siguiente)
    {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
        {
            if (actual.getText().trim().isEmpty())
            {
                javax.swing.JOptionPane.showMessageDialog(ventana, "Campo obligatorio");
                actual.requestFocus();
            } else
            {
                siguiente.requestFocus();
            }
        }
    }

    public static void enterEntero(java.awt.Component ventana,
            java.awt.event.KeyEvent evt,
            javax.swing.JTextField actual,
            java.awt.Component siguiente)
    {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
        {
            try
            {
                Integer.parseInt(actual.getText());
                siguiente.requestFocus();
            } catch (NumberFormatException e)
            {
                javax.swing.JOptionPane.showMessageDialog(ventana, "Número inválido");
                actual.requestFocus();
            }
        }
    }
    

    public static boolean campoVacio(javax.swing.JTextField campo)
    {
        return campo.getText().trim().isEmpty();
    }

    public static boolean camposVacios(javax.swing.JTextField... campos)
    {
        for (javax.swing.JTextField campo : campos)
        {
            if (campo.getText().trim().isEmpty())
            {
                return true;
            }
        }
        return false;
    }
}
