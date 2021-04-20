/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutisMono.entity;
import java.util.ArrayList;

/**
 *
 * @author johan
 */
public class HistoriqueAccelerometre {
        public ArrayList<Accelerometre> HistoriqueAccelerometre;

    public HistoriqueAccelerometre(ArrayList<Accelerometre> accelerometre) {
        this.HistoriqueAccelerometre = accelerometre;
    }

    @Override
    public String toString() {
        return "HistoriqueAccelerometre{" + "HistoriqueAccelerometre=" + HistoriqueAccelerometre + '}';
    }

    /**
     * Adds an acc to this historique
     *
     * @param acc the event to add
     */
    public void addAcc(Accelerometre acc) {
        // TODO : implémenter cette méthode
        this.HistoriqueAccelerometre.add(acc);

    }
    
}
