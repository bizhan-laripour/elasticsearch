package org.example.search.elasticsearchChain;

import org.springframework.data.elasticsearch.core.query.Criteria;

import java.lang.reflect.Field;

public interface CriteriaChain {

    Criteria createCriteria(Field field , Criteria criteria , Object o) throws IllegalAccessException;

}
