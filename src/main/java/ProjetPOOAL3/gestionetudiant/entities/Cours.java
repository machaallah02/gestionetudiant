package ProjetPOOAL3.gestionetudiant.entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;



@Setter
@Data
@AllArgsConstructor
@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomC;
    private String description;

    //Chaque cours accueille plusieurs étudiants

    @ManyToMany(mappedBy = "coursList")
    private List<Etudiant> etudiantList;

    public Cours(){

    }

    @ManyToOne
    private Inscription inscription;

    public Cours (Long id, String nomC){
        this.id=id;
        this.nomC=nomC;
    }

    // Constructeur, getters et setters...

    
    public void setNomC(String nomC) {
        this.nomC = nomC;
    }
    
    public String getNomC() {
        return nomC;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
