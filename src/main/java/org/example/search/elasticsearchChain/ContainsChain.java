package org.example.search.elasticsearchChain;

import org.example.annotation.SearchField;
import org.example.enums.SearchType;
import org.springframework.data.elasticsearch.core.query.Criteria;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ContainsChain implements CriteriaChain{

    private CriteriaChain criteriaChain;

    public ContainsChain(CriteriaChain criteriaChain) {
        this.criteriaChain = criteriaChain;
    }

    @Override
    public Criteria createCriteria(Field field, Criteria criteria, Object o) throws IllegalAccessException {
        Annotation[] annotations = field.getAnnotations();
        Object value = field.get(o);
        if(value != null && !value.equals("")) {
            Criteria cr;
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == SearchField.class) {
                    SearchField annotationField = (SearchField) annotation;
                    if (annotationField.type() == SearchType.CONTAINS) {
                        cr = new Criteria(!annotationField.target_field().isEmpty() ? annotationField.target_field() : field.getName()).is(value);
                        criteria.and(cr);
                    }
                }
            }
        }
       return criteriaChain.createCriteria(field, criteria, o);
    }
}
