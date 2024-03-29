package ProjetPOOAL3.gestionetudiant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetPOOAL3.gestionetudiant.entities.Cours;

public interface CoursRepository extends JpaRepository <Cours, Long> {

}
