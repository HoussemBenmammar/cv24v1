package fr.univrouen.cv24v1.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.google.common.base.Optional;

import fr.univrouen.cv24v1.models.Resume;

public interface ResumeRepository extends MongoRepository<Resume, String> {
    // You can define custom database queries here
	boolean existsByIdentiteGenreAndIdentiteNomAndIdentitePrenomAndIdentiteTel(
	        String identiteGenre, String identiteNom, String identitePrenom, String identiteTel);
    //boolean existsByName(String name);
	@Query("{'identite.nom': ?0}")
    List<Resume> findByName(String name);
	
	@Query("{'idResume': ?0}")
    Optional<Resume> findByIdResume(int idResume);
	
	@Query(value = "{'idResume': ?0}", delete = true)
    void deleteByResumeId(int id);
	
}

