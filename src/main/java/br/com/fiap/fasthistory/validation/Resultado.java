package br.com.fiap.fasthistory.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ResultadoValidator.class)

public @interface Resultado {

	String message() default "{partida.resultado.invalido}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
