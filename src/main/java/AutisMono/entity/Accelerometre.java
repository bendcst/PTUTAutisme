/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutisMono.entity;


/**
 *
 * @author johan
 */
public class Accelerometre {
     private int X;
     
     private int Y;
     
     private int Z;

    public Accelerometre(int X, int Y, int Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }   
    @Override  
    public String toString() {
        return "Accelerometre{" + "X=" + X + ", Y=" + Y + ", Z=" + Z +'}';
    }
    
     
}
