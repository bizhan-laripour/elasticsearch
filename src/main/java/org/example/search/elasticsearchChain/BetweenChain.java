package org.example.search.elasticsearchChain;

import org.example.annotation.SearchField;
import org.example.enums.SearchType;
import org.springframework.data.elasticsearch.core.query.Criteria;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

public class BetweenChain implements CriteriaChain{



    @Override
    public Criteria createCriteria(Field field, Criteria criteria, Object o) throws IllegalAccessException {

        Annotation[] annotations = field.getAnnotations();
        Object value = field.get(o);
        if(value != null && !value.equals("")) {
            Criteria cr;
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == SearchField.class) {
                    SearchField annotationField = (SearchField) annotation;
                    if (annotationField.type() == SearchType.BETWEEN) {
                        if (field.getType() == List.class) {
                            List<Object> list = (List<Object>) value;
                            cr = new Criteria(!annotationField.target_field().isEmpty() ? annotationField.target_field() : field.getName()).between(list.get(0), list.get(1));
                            criteria.and(cr);
                        }
                    }
                }
            }
        }
        return criteria;
    }
}
