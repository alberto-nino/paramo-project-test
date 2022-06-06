package sat.recruitment.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@EqualsAndHashCode(callSuper=false)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityException extends RuntimeException {

	private static final long serialVersionUID = 2364715451767492464L;

	private String resourceName;
    private String fieldName;
    private Object fieldValue;
    private String message;

   public EntityException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Problem with entity %s with %s(attribute) : '%s' error values", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
