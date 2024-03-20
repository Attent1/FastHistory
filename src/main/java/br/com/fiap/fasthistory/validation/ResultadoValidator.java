package br.com.fiap.fasthistory.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ResultadoValidator implements ConstraintValidator<Resultado, String> {

    @Override
    public boolean isValid(String resultado, ConstraintValidatorContext arg1) {
        return resultado.equals("VITÓRIA") || resultado.equals("DERROTA");
    }

}
