package co.in.an.eye.tech.custom.field.depends.on.annotation;


import co.in.an.eye.tech.custom.field.depends.on.annotation.model.MerchantModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/add-merchant")
    public ResponseEntity addMerchant(@Valid MerchantModel merchantModel){
        System.out.println("Adding merchant to DB : "+merchantModel);
        return ResponseEntity.ok().build();
    }
}
