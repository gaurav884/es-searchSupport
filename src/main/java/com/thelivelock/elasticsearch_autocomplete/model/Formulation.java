package com.thelivelock.elasticsearch_autocomplete.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "masterdata.disease")
@Setting(settingPath = "es-config/elastic-analyzer.json")
@Getter
@Setter
public class Formulation {
    @Id
    private String id;
    private String name;


    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String description;
}
