import java.util.ArrayList;
import java.util.Date;

public class Movie extends VideoMedia {

    public Movie(String id, String name, String description, Date releaseDate, String[] categories, double rating, Credits[] credits, String imageFileName) {
        super(id, name, description, releaseDate, categories, rating, credits, imageFileName);

    }
}
