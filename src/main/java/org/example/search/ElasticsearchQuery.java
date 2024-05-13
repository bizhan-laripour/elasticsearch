package org.example.search;

import org.example.annotation.SearchField;
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
            criteria = makeCriteria(field, criteria, o);
        }
        return new CriteriaQuery(criteria);
    }


    private Criteria makeCriteria(Field field, Criteria criteria, Object o) throws IllegalAccessException {
        field.setAccessible(true);
        Annotation[] annotations = field.getAnnotations();
        Object value = field.get(o);
        if (value != null && !value.equals("")) {
            Criteria c = new Criteria();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == SearchField.class) {
                    SearchField annotationField = (SearchField) annotation;
                    switch (annotationField.type()) {
                        case EQUAL_TO -> {
                            c = new Criteria(!annotationField.target_field().isEmpty() ? annotationField.target_field() : field.getName()).is(value);
                        }
                        case CONTAINS -> {
                            c = new Criteria(!annotationField.target_field().isEmpty() ? annotationField.target_field() : field.getName()).contains(value.toString());
                        }
                        case GREATER_THAN -> {
                            c = new Criteria(!annotationField.target_field().isEmpty() ? annotationField.target_field() : field.getName()).greaterThanEqual(value);

                        }
                        case LESS_THAN -> {
                            if (value instanceof Date) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime((Date) value);
                                calendar.add(Calendar.DATE, 1);
                                c = new Criteria(!annotationField.target_field().isEmpty() ? annotationField.target_field() : field.getName()).lessThanEqual(calendar.getTime());
                            } else {
                                c = new Criteria(!annotationField.target_field().isEmpty() ? annotationField.target_field() : field.getName()).lessThanEqual(value);
                            }
                        }
                        case BETWEEN -> {
                            if (field.getType() == List.class) {
                                List<Object> list = (List<Object>) value;
                                c = new Criteria(!annotationField.target_field().isEmpty() ? annotationField.target_field() : field.getName()).between(list.get(0), list.get(1));
                            }
                        }

                    }
                    criteria.and(c);

                }
            }
        }
        return criteria;
    }


}
