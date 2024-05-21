package fr.univrouen.cv24v1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.univrouen.cv24v1.models.Resume;

public interface ResumeRepository extends MongoRepository<Resume, String> {
    // You can define custom database queries here
}

