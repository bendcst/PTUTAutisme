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
    
    
    
    
    
    /*@OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<Transaction> achats = new LinkedList<>();
    
    public float budgetArt(int annee) {
        float result=0.0f;
        for (Transaction achat : achats)
            if (achat.getVenduLe().getYear() == annee)
                result += achat.getPrixVente();
        return result;
        // Peut s'écrire en utilisant l'API Stream 
        // cf. https://www.baeldung.com/java-stream-filter-lambda
        /*
        return achats.stream()
                .filter( achat -> achat.getVenduLe().getYear() == annee) // On filtre sur l'annee
                .map(achat -> achat.getPrixVente()) // On garde le prix de vente
                .reduce(0f, Float::sum); // On additionne
       
    }*/   
}
