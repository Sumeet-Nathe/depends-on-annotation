package co.in.an.eye.tech.custom.field.depends.on.annotation.model;


import co.in.an.eye.tech.custom.field.depends.on.annotation.config.annotation.DependsOnField;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DependsOnField(
        fieldName = "split",
        fieldValue = "true",
        dependentFieldName = "noOfDivs",
        message = "{enter.correct.divs}"
)
public class MerchantModel {
    private String shop;
    private String merchantId;
    private String merchantCcid;
    private String email;
    private boolean split;
    private String noOfDivs;

}
