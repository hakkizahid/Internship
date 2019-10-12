package obss.movietracker.model.payload;

import lombok.Data;

@Data
public class MovieListModel {
    private Long userId;
    private Long movieId;
    private String type;
}
