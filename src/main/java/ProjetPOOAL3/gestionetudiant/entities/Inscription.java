package ProjetPOOAL3.gestionetudiant.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity

public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Cours cours;

    @OneToMany(mappedBy = "inscription", fetch = FetchType.LAZY)
    private List<Cours> coursList;


    public List<Cours> getCours() {
        return this.coursList; // ou tout autre attribut contenant la liste de cours
    }

    public List<Cours> getCoursList() {
    return coursList;
}



    public Inscription( long id) {
        this.id=id;
    }

    public Long getId(){
        return id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setCours(Cours cours){
        this.cours=cours;
    }
}

