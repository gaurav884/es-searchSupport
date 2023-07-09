package com.thelivelock.elasticsearch_autocomplete.repository;

import com.thelivelock.elasticsearch_autocomplete.model.Formulation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulationRepo extends ElasticsearchRepository<Formulation, Long> {
    List<Formulation> findAll();
}
