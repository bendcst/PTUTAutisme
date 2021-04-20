/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package AutisMono.entity;

import java.util.ArrayList;

 
public class HistoriqueFc {
    

    public ArrayList<fc> frequencecardiaque;
    public  int frcard=(int) (170*Math.random()+50);

    public HistoriqueFc(ArrayList<fc> frequencecardiaque) {
        this.frequencecardiaque = frequencecardiaque;
    }

    @Override
    public String toString() {
        return "HistoriqueFc{" + "frequencecardiaque=" + frequencecardiaque + '}';
    }

    /**
     * Adds an fc to this historique
     *
     * @param freq
     */
     
    public void addfc(fc freq) {
        // TODO : implémenter cette méthode
        this.frequencecardiaque.add(freq);

    }
}

