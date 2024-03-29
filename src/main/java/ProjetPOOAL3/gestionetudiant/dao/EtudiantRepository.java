package ProjetPOOAL3.gestionetudiant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetPOOAL3.gestionetudiant.entities.Etudiant;

public interface EtudiantRepository  extends JpaRepository <Etudiant,Long>{

}
