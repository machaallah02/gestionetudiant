package ProjetPOOAL3.gestionetudiant.entities;


import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomC;
    private String description;

    //Chaque cours accueille plusieurs étudiants

    @ManyToMany
    private List<Etudiant> etudiantList;
    
}
