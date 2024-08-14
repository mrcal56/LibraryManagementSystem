/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

/**
 *
 * @author Marlon
 */
public class Libro {
    private String titulo;
    private String autor;
    private String ISBN;
    private int añoPublicacion;
    private boolean estaDisponible;
    
    //Constructor de clase
    public Libro(String titulo, String autor, String ISBN, int añoPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.añoPublicacion = añoPublicacion;
        this.estaDisponible = true;   // Por defecto, el libro está disponible al crearse

    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }
    
    public String getISBN(){
        return ISBN;
    }
    
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }
    
    public int getAñoPublicacion(){
       return añoPublicacion;
    }
    
    public void setAñoPublicacion(int añoPublicacion){
        this.añoPublicacion = añoPublicacion;
    }
    
    public boolean isDisponible(){
       return estaDisponible;
   }
    
    public void setDisponible(boolean estaDisponible){
        this.estaDisponible = estaDisponible;
    }
   
    
    
    
    
}
