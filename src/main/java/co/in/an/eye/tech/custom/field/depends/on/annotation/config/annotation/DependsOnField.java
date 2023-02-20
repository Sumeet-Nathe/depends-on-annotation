package co.in.an.eye.tech.custom.field.depends.on.annotation.config.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(DependsOnField.List.class)
@Constraint(validatedBy = DependsOnFieldValidator.class)
@Documented
public @interface DependsOnField {
    String fieldName();

    String fieldValue();

    String dependentFieldName();

    String message();

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};


    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented

    @interface List {
        DependsOnField [] value();
    }

}
