package com.thelivelock.elasticsearch_autocomplete.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.thelivelock.elasticsearch_autocomplete.model.Formulation;
import com.thelivelock.elasticsearch_autocomplete.repository.FormulationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormulationService {
    @Autowired
    private FormulationRepo formulationRepository;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public List<Formulation> listAll() {
        return this.formulationRepository.findAll();
    }

    public Formulation save(Formulation user) {
        return this.formulationRepository.save(user);
    }

    public long count() {
        return this.formulationRepository.count();
    }

    public List<Formulation> search(String keywords) {
        Float nonExistingBoost = null; // even though it exists in SpringBoot, ElasticSearch has no boost for this type of query
        // when you analyze what matchQuery returns, it also has nothing related to boost
        Query query = QueryBuilders.matchQuery("description", keywords, Operator.Or, nonExistingBoost)._toQuery();
        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build();
        SearchHits<Formulation> result = this.elasticsearchOperations.search(nativeQuery, Formulation.class);
        return result.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
