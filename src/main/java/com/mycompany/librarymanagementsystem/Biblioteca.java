/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Marlon
 */
public class Biblioteca {
    
    private List<Libro> libros; // Lista de libros en la biblioteca
    private List<Usuario> usuarios;  // Lista de usuarios registrados
     
    public Biblioteca(){
        this.libros = new ArrayList<>(); // Inicializar la lista de libros
        this.usuarios = new ArrayList<>(); // Inicializar la lista de usuarios
    }
    
     // Método para agregar un libro a la base de datos
    public void agregarLibro(Libro libro){
        String sql = "INSERT INTO libros (titulo, autor, isbn, año_publicacion, disponible ) VALUES (?, ?, ?, ?, ?)";
       try(Connection conn = DatabaseConnector.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setString(1, libro.getTitulo());
           stmt.setString(2, libro.getAutor());
           stmt.setString(3, libro.getISBN());
           stmt.setInt(4, libro.getAñoPublicacion());
           stmt.setBoolean(5, libro.isDisponible());
           stmt.executeUpdate();
           System.out.println("Libro'" + libro.getTitulo() + "' agregado a la base de datos");
                                 
       }catch(SQLException e){
           System.out.println("Error al agregar el libro: " + e.getMessage());
       }
    }
    
    
    
    public void eliminarLibro(String isbn){
       String sql = "DELETE FROM libros WHERE isbn =  ?";
       try(Connection conn = DatabaseConnector.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)) {
           stmt.setString(1, isbn);
           int rowsAffected = stmt.executeUpdate();
           
           if(rowsAffected > 0) {
               System.out.println("Libro con ISBN '" + isbn + "' eliminado de la base de datos");
           } else {
               System.out.println("No se encontró un libro con ISBN '" + isbn);
           }                    
       } catch (SQLException e){
           System.out.println("Error al eliminar libro: " + e.getMessage());
           
       }
    }
    
    public void registrarUsuario(Usuario usuario){
        String sql = "INSERT INTO usuarios (nombre, id_usuario, email) VALUES (?, ?, ?)";
        try(Connection conn = DatabaseConnector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2,usuario.getIdUsuario());
            stmt.setString(3,usuario.getEmail());
            stmt.executeUpdate();
            System.out.println("Usuario: '" + usuario.getNombre() + "' registrado en la base de datos");
            
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());           
        }
    }
    
    public Libro buscarLibro (String titulo){
      String sql = "SELECT * FROM libros WHERE titulo = ?";
      try(Connection conn = DatabaseConnector.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql)){
          stmt.setString(1, titulo);
          ResultSet rs = stmt.executeQuery();
          if(rs.next()){
              Libro libro = new Libro(rs.getString("titulo"), rs.getString("autor"), rs.getString("isbn"), rs.getInt("año_publicacion"));
                libro.setDisponible(rs.getBoolean("disponible"));
                return libro; // Devolver el libro si se encuentra            
          }
      } catch (SQLException e) {
          System.out.println("Error al buscar libro : " + e.getMessage());         
      }
      return null;
    } 
    
    public List<Libro> getLibros() {
    List<Libro> listaLibros = new ArrayList<>();
    String sql = "SELECT * FROM libros";
    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Libro libro = new Libro(
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("isbn"),
                rs.getInt("año_publicacion")
            );
            libro.setDisponible(rs.getBoolean("disponible"));
            listaLibros.add(libro);
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener libros: " + e.getMessage());
    }
    return listaLibros;
}
 
    public Usuario buscarUsuario(String idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ? ";
        try(Connection conn = DatabaseConnector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, idUsuario);
                    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getString("nombre"), rs.getString("id_usuario"), rs.getString("email")); // Devolver el usuario si se encuentra
            }                           
        } catch(SQLException e){
            System.out.println("Error al buscar usuario: " + e.getMessage());           
        }
        return null;
    }
    
    public void eliminarUsuario(String idUsuario){
        String sql = "DELETE FROM usuarios WHERE id_usuario = ? ";
       try(Connection conn = DatabaseConnector.getConnection();
               PreparedStatement stmt = conn.prepareStatement(sql)){
           stmt.setString(1, idUsuario);
           int rowsAffected = stmt.executeUpdate();
           if (rowsAffected > 0) {
                   System.out.println("Usuario con ID '" + idUsuario + "' eliminado de la base de datos");
               } else {
               System.out.println("No se encontro un usuario con ID '" + idUsuario);
           }
       } catch (SQLException e) {
           System.out.println("Error al eliminar usuario: " + e.getMessage());          
       }
    }
    
    
}
