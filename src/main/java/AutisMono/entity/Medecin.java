package AutisMono.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity // Une entité JPA
public class Medecin extends Utilisateur {
    //@Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    //private Integer id;

    @NonNull
    private String numrpps;
    @NonNull
    private String specialite;
    @NonNull
    private Integer diplome;

    public Medecin(String username, String password, String nom, String prenom, String adresse, String ville, String email, String numtel, String numrpps, String specialite, Integer diplome) {
        super(username, password, nom, prenom, adresse, ville, email, numtel);
        this.numrpps = numrpps;
        this.specialite = specialite;
        this.diplome = diplome;
    }

    public int Notif(int fc) {

        if ((fc < 200) && (fc > 60)) {

            System.out.println("La fréquence cardiaque est: " + fc);
        }
        if (fc >= 200) {

            System.out.println("Envoyer une alerte au medecin car fr>200: " + fc);
        }
        if (fc <= 60) {

            System.out.println("Envoyer une alerte au medecin car fr<60: " + fc);
        }

        return fc;

    }
}
