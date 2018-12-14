package netflix.models.media;

import netflix.models.Credits;

import java.util.Date;
import java.util.List;

/**
 * Generic media class, for all types of media on the site
 */
public abstract class Media {
    protected String id;
    protected String name;
    protected String description;
    protected Date releaseDate;
    protected List<String> categories;
    protected double rating;
    protected List<Credits> credits;
    protected String imageFileName;

    /**
     * @param id Id associated with the media.
     * @param name Name of the media.
     * @param description Description of the media.
     * @param releaseDate Release date of the media.
     * @param categories Categories held by the media.
     * @param rating Rating of the media.
     * @param credits Credits for the media.
     * @param imageFileName Name of media image file.
     */
    public Media(
            String id,
            String name,
            String description,
            Date releaseDate,
            List<String> categories,
            double rating,
            List<Credits> credits,
            String imageFileName
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.categories = categories;
        this.rating = rating;
        this.credits = credits;
        this.imageFileName = imageFileName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    public List<Credits> getCredits() {
        return credits;
    }

    public String getImageFileName() {
        return imageFileName;
    }
}