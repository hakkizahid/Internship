package obss.movietracker.model.payload;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;

@Data
@Scope("prototype")
public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(boolean b, String s) {
    }
}