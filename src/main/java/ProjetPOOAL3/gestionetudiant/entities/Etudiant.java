package ProjetPOOAL3.gestionetudiant.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;

    // Chaque étudiant a plusieurs cours
    @ManyToMany(mappedBy = "etudiantList")
    private List<Cours> coursList;

    public Etudiant(Long id, String nom, String prenom){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
    }

    public Etudiant( String nom, String prenom){
        
        this.nom=nom;
        this.prenom=prenom;
    }

    @Override
    public String toString(){
        return "Etudiant{"+
                "id=" + id + 
                ",Nom='" + nom + '\''+
                ",Prenom='" + prenom + '\''+
                '}';
    }

}
