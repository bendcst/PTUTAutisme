package AutisMono.entity;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA

      public class Patient extends Utilisateur {

    public Patient( String username, String password, String nom, String prenom, String adresse, String ville, String email, String numtel, String numsecu, String datenaissance,String nommedecin) {
        super(username, password, nom, prenom, adresse, ville, email, numtel);
        this.numsecu = numsecu;
        this.datenaissance = datenaissance;
        this.nommedecin = nommedecin;
    }
    
    
   @NonNull
    private String numsecu;
    
    @NonNull
    private String datenaissance;
    
    @NonNull
    private String nommedecin;
      
}
