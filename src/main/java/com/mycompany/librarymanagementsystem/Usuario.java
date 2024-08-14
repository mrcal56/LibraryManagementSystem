/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Marlon
 */
public class Usuario {
    
    private String nombre;
    private String idUsuario;
    private String email;
    private List<Libro> librosPrestados;
    
    public Usuario(String nombre, String idUsuario,  String email){
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.email = email;
        this.librosPrestados = new ArrayList<>();   // Inicializar la lista de libros prestados
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getIdUsuario(){
        return idUsuario;
    }
    
    public void setIdUsuario(String idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public List<Libro> getLibrosPrestados(){
        return librosPrestados;
    }
    
    
    public void agregarPrestamo(Libro libro){
        if (libro.isDisponible()) {
            librosPrestados.add(libro);
            libro.setDisponible(false);
            System.out.println("El libro '" + libro.getTitulo() + "' ha sido prestado a " + this.nombre );
        }else {
            System.out.println("El libro '" + libro.getTitulo() + "' no esta disponible");
        }
    }
    
    public void devolverLibro(Libro libro){
        if (librosPrestados.contains(libro)){
            librosPrestados.remove(libro);
            libro.setDisponible(true);
            System.out.println("El libro '" + libro.getTitulo()+ "' ha sido devuelto por " + this.nombre);           
        }else{
            System.out.println("Ese libro  no fue prestado a '" +  this.nombre);
        }        
    }
    
    public void consultarPrestamos(){
        System.out.println("Libros prestados a " + this.nombre + ":");
        if (librosPrestados.isEmpty()) {
            System.out.println("No hay libros prestados");
        }else{
            for (Libro libro : librosPrestados){
                System.out.println("-  " + libro.getTitulo());
            }
        }
    
}
    
}
    

    
