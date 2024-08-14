package com.mycompany.librarymanagementsystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibraryManagementSystemGUI extends JFrame {
    private Biblioteca biblioteca; // Instancia de la clase Biblioteca

    public LibraryManagementSystemGUI() {
        biblioteca = new Biblioteca(); // Inicializar la biblioteca
        mostrarMenuOpciones(); // Mostrar menú de opciones al iniciar
    }

    private void mostrarMenuOpciones() {
        String[] opciones = {"Ver Libros", "Agregar Libro"};
        int seleccion = JOptionPane.showOptionDialog(
                this,
                "¿Qué deseas hacer?",
                "Opciones",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0] // Opción predeterminada
        );

        if (seleccion == 0) {
            mostrarLibros(); // Método que mostrará los libros
        } else if (seleccion == 1) {
            mostrarFormularioAgregarLibro(); // Método que abrirá el formulario para agregar un libro
        }
    }

    private void mostrarFormularioAgregarLibro() {
        JFrame frameAgregar = new JFrame("Agregar Libro");
        frameAgregar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar el programa al cerrar esta ventana
        JPanel panel = new JPanel();

        JTextField txtTitulo = new JTextField(15);
        JTextField txtAutor = new JTextField(15);
        JTextField txtISBN = new JTextField(15);
        JTextField txtAñoPublicacion = new JTextField(4);
        JCheckBox chkDisponible = new JCheckBox("Disponible");
        JButton btnAgregarLibro = new JButton("Agregar Libro");
        JButton btnRegresar = new JButton("Regresar al Menú");

        // Configurar el botón de agregar libro
        btnAgregarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                String isbn = txtISBN.getText();
                int añoPublicacion = Integer.parseInt(txtAñoPublicacion.getText());
                boolean disponible = chkDisponible.isSelected();

                // Crear un nuevo libro y agregarlo a la base de datos
                Libro nuevoLibro = new Libro(titulo, autor, isbn, añoPublicacion);
                nuevoLibro.setDisponible(disponible);
                biblioteca.agregarLibro(nuevoLibro);

                // Limpiar los campos de entrada
                txtTitulo.setText("");
                txtAutor.setText("");
                txtISBN.setText("");
                txtAñoPublicacion.setText("");
                chkDisponible.setSelected(false);
                JOptionPane.showMessageDialog(frameAgregar, "Libro agregado exitosamente.");
            }
        });

        // Configurar el botón para regresar al menú
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameAgregar.dispose(); // Cerrar la ventana actual
                mostrarMenuOpciones(); // Volver al menú principal
            }
        });

        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("Autor:"));
        panel.add(txtAutor);
        panel.add(new JLabel("ISBN:"));
        panel.add(txtISBN);
        panel.add(new JLabel("Año de Publicación:"));
        panel.add(txtAñoPublicacion);
        panel.add(chkDisponible);
        panel.add(btnAgregarLibro);
        panel.add(btnRegresar); // Añadir botón de regresar

        frameAgregar.add(panel);
        frameAgregar.setSize(200, 450);
        frameAgregar.setLocationRelativeTo(null); // Centrar ventana
        frameAgregar.setVisible(true);
    }

    private void mostrarLibros() {
        JFrame frameLibros = new JFrame("Libros en la Biblioteca");
        frameLibros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar el programa al cerrar esta ventana
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        List<Libro> libros = biblioteca.getLibros(); // Obtener la lista de libros
        for (Libro libro : libros) {
            textArea.append(libro.getTitulo() + " - " + (libro.isDisponible() ? "Disponible" : "No Disponible") + "\n"); // Mostrar cada libro
        }

        JButton btnRegresar = new JButton("Regresar al Menú");
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameLibros.dispose(); // Cerrar la ventana actual
                mostrarMenuOpciones(); // Volver al menú principal
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JScrollPane(textArea));
        panel.add(btnRegresar); // Añadir botón de regresar al panel

        frameLibros.add(panel);
        frameLibros.setSize(400, 300);
        frameLibros.setLocationRelativeTo(null); // Centrar ventana
        frameLibros.setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryManagementSystemGUI(); // Iniciar la GUI
    }
}
