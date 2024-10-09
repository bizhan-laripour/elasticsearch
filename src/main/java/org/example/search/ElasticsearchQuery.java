package org.example.search;

import org.example.annotation.SearchField;
import org.example.search.elasticsearchChain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author a.mehdizadeh on 5/1/2024
 */
@Component
public class ElasticsearchQuery<T> {

    private final ElasticsearchOperations elasticsearchTemplate;

    public ElasticsearchQuery(ElasticsearchOperations elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }


    public ResponseGenerator<T> search(Object object, Class<T> clazz, Pageable pageable, Boolean isPageable) throws IllegalAccessException {
        Query query = queryBuilder(object);
        ResponseGenerator<T> generator = new ResponseGenerator<>();
        generator.setTotal(elasticsearchTemplate.search(query, clazz).get().count());
        if (isPageable) {
            query.setPageable(pageable);
        }
        List<T> result = elasticsearchTemplate.search(query, clazz).get().map(SearchHit::getContent).toList();
        generator.setObject(result);

        return generator;

    }


    public Query queryBuilder(Object o) throws IllegalAccessException {
        Criteria criteria = new Criteria();
        Class<?> clazz = o.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            criteria = new EqualsToChain(new ContainsChain(new LessThanChain(new GreaterThanChain(new BetweenChain())))).createCriteria(field , criteria , o);
        }
        return new CriteriaQuery(criteria);
    }



}
