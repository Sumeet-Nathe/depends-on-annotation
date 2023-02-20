package co.in.an.eye.tech.custom.field.depends.on.annotation.config.annotation;

import co.in.an.eye.tech.custom.field.depends.on.annotation.exception.CustomException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class DependsOnFieldValidator implements ConstraintValidator <DependsOnField,Object>{

    String fieldName;
    String expectedFieldValue;
    String dependentFieldName;
    @Override
    public void initialize(DependsOnField dependsOnField) {
        fieldName = dependsOnField.fieldName();
        expectedFieldValue = dependsOnField.fieldValue();
        dependentFieldName = dependsOnField.dependentFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return true;
        }
        try{
            String fieldValue = BeanUtils.getProperty(value,fieldName);
            String dependentFieldValue = BeanUtils.getProperty(value,dependentFieldName);

            if(expectedFieldValue.equals(fieldValue) && dependentFieldValue.isBlank()){
                createCostraintValidatorContext(constraintValidatorContext,dependentFieldName);
                return false;
            }

            if(expectedFieldValue.equals(fieldValue) && dependentFieldValue != null && !dependentFieldValue.isBlank()){
                Integer [] numOfDivs;
                 try{
                     numOfDivs = Arrays.stream(dependentFieldValue.split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
                 }catch (NumberFormatException e){
                     createCostraintValidatorContext(constraintValidatorContext,dependentFieldName);
                     return false;
                 }
                 boolean result = true;
                for (Integer i : numOfDivs ) {
                    if(i<=1){
                        createCostraintValidatorContext(constraintValidatorContext,dependentFieldName);
                        return false;
                    }
                }
                return result;
            }
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception){
            throw new CustomException("Error in DependsFieldValue annotation");
        }
        return true;
    }

    private void createCostraintValidatorContext(ConstraintValidatorContext constraintValidatorContext, String dependentFieldName) {
    constraintValidatorContext.disableDefaultConstraintViolation();
    constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
            .addPropertyNode(dependentFieldName)
            .addConstraintViolation();
    }
}
