package obss.movietracker.model.payload;

import lombok.Data;

@Data
public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private String role;
}
