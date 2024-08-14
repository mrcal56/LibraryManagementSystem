/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystem;

/**
 *
 * @author Marlon
 */
public class LibraryManagementSystem {

    public static void U(String[] args) {
        
             // Crear una nueva biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Crear algunos libros
        Libro libro1 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", "978-3-16-148410-0", 1967);
        Libro libro2 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "978-0-12-345678-9", 1605);
        Libro libro3 = new Libro("Don Segundo Sombra", "Ricardo Güiraldes", "978-987-565-262-7", 1926);

        // Agregar libros a la base de datos
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);

        // Crear un usuario
        Usuario usuario1 = new Usuario("Juan Pérez", "U123", "juan.perez@example.com");

        // Registrar el usuario en la base de datos
        biblioteca.registrarUsuario(usuario1);

      

        // Buscar un libro por su título
        Libro libroBuscado = biblioteca.buscarLibro("Cien Años de Soledad");
        if (libroBuscado != null) {
            System.out.println("Libro encontrado: " + libroBuscado.getTitulo());
        } else {
            System.out.println("Libro no encontrado.");
        }
        
       
   
        
        
        
    }
}
