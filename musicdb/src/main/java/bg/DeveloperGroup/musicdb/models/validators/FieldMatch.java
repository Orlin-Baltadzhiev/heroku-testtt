package bg.DeveloperGroup.musicdb.models.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ({ElementType.TYPE})
@Retention (RetentionPolicy.CLASS)
@Constraint (validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

     String message() default "Fields should match";
     String first();
     String second();

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}