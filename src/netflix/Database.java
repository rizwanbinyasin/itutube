package netflix;

import netflix.helpers.FakeDataHelper;
import netflix.models.Credits;
import netflix.models.User;
import netflix.models.media.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public final class Database {
    private static Database databaseInstance = null;

    private static HashMap<String, Media> media;
    private static ArrayList<User> users;

    private Database() {
        media = new HashMap<String, Media>();
        users = new ArrayList<User>();
    }

    /**
     * Get singleton instance of netflix.Database class
     * @return Instance of netflix.Database class
     */
    public static Database getInstance() {
        if (databaseInstance == null) {
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    /**
     * Get a netflix.media.Media by its ID.
     * @param id Id of the media
     * @return netflix.media.Media with the ID, otherwise null
     */
    public static Media getMediaById(String id) {
        throw new NotImplementedException();
    }

    /**
     * Get all media in the database as a list
     * @return List of all media
     */
    public static ArrayList<Media> getMediaList() {
        return new ArrayList<Media>(media.values());
    }

    /**
     * Get all users in the database;
     * @return List of all users
     */
    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Add a new user to the database
     * @param user The user to add
     */
    public static void addUser(User user) {
        throw new NotImplementedException();
    }

    /**
     * Save the database to the disk.
     */
    public static void save() {
        throw new NotImplementedException();
    }

    /**
     * Load the database from the disk.
     */
    public static void load() {
        HashMap<String, Media> db = new HashMap<>();

        Movie[] movies = fetchMovies();
        Series[] series = fetchSeries();

        ArrayList<Media> allMedia = new ArrayList<>(Arrays.asList(movies));
        allMedia.addAll(Arrays.asList(series));

        for(Media m : allMedia) {
               db.put(m.getId(), m);
        }

        media = db;
    }

    private static Movie lineToMovie(String line) {
        String[] properties = line.split(";");
        for(int i = 0; i < properties.length; i++) {
            properties[i] = properties[i].trim();
        }
        String id = properties[0];
        String name = properties[1];
        Date releaseDate = new Date(Integer.parseInt(properties[2]), 1, 1);
        String[] categories = properties[3].split(", ");
        double rating = Double.parseDouble(properties[4]);

        String description = FakeDataHelper.getLoremIpsum(100);
        Credits[] credits = FakeDataHelper.generateFakeCredits();
        String imageFileName = name + ".png";
        int runtime = FakeDataHelper.generateFakeRuntime();

        return new Movie(id, name, description, releaseDate, categories, rating, credits, imageFileName, runtime);
    }

    private static Movie[] fetchMovies() {
        Scanner s = new Scanner("movies.txt");
        ArrayList<Movie> movies = new ArrayList<>();
        while(s.hasNext()) {
            String line = s.nextLine();
            movies.add(lineToMovie(line));
        }
        Movie[] movieArray = new Movie[movies.size()];
        return movies.toArray(movieArray);
    }

    private static Episode[] fetchEpisodes(Series series, Season season, int episodeAmount) {
        ArrayList<Episode> episodes = new ArrayList<>();
        for(int i = 1; i < episodeAmount; i++) {
            String id = FakeDataHelper.generateFakeId();
            String name = season.getName() + "E" + i;
            String description = FakeDataHelper.getLoremIpsum(200);
            Date releaseDate = season.getReleaseDate();
            String[] categories = season.getCategories();
            double rating = FakeDataHelper.generateFakeRating();
            Credits[] credits = season.getCredits();
            String imageFileName = season.getImageFileName();
            int runtime = FakeDataHelper.generateFakeRuntime();

            episodes.add(new Episode(id, name, description, releaseDate, categories, rating, credits, imageFileName, runtime, season, series));
        }
        Episode[] episodeArray = new Episode[episodes.size()];
        return episodes.toArray(episodeArray);
    }

    private static Season[] fetchSeasons(Series series, String line) {
        ArrayList<Season> seasons = new ArrayList<>();
        String[] seasonsString = line.split(" ");
        for(String s : seasonsString) {

            int seasonNumber = Integer.parseInt(s.split("-")[0]);
            int episodeAmount = Integer.parseInt(s.split("-")[1]);
            String id = FakeDataHelper.generateFakeId();
            String name = series.getName() + " S" + seasonNumber;
            String description = FakeDataHelper.getLoremIpsum(150);

            Date firstSeasonDate = series.getReleaseDate();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, seasonNumber-1);
            Date currentSeasonDate = c.getTime();

            String[] categories = series.getCategories();
            double rating = FakeDataHelper.generateFakeRating();
            Credits[] credits = series.getCredits();
            String imageFileName = series.getImageFileName();

            Season season = new Season(id, name, description, currentSeasonDate, categories, rating, credits, imageFileName, series);


            Episode[] episodes = fetchEpisodes(series, season, episodeAmount);
            season.setEpisodes(episodes);
            seasons.add(season);
        }
        Season[] seasonArray = new Season[seasons.size()];
        return seasons.toArray(seasonArray);
    }

    private static Date[] getSeriesDates(String line) {
        Date releaseDate = new Date(Integer.parseInt(line.substring(0,3)), 1, 1);
        Date endDate = null;
        if(line.length() == 9) {
            endDate = new Date(Integer.parseInt(line.substring(5, 8)), 1, 1);
        }
        else if(line.length() == 4) {
            endDate = releaseDate;
        }
        return new Date[]{releaseDate, endDate};
    }

    private static Series lineToSeries(String line) {
        String[] properties = line.split(";");
        String id = properties[0];
        String name = properties[1];

        Date[] dates = getSeriesDates(properties[2]);
        Date releaseDate = dates[0];
        Date endDate = dates[1];

        String[] categories = properties[3].split(", ");
        double rating = Double.parseDouble(properties[4]);

        String description = FakeDataHelper.getLoremIpsum(175);
        Credits[] credits = FakeDataHelper.generateFakeCredits();
        String imageFileName = name + ".png";

        Series series = new Series(id, name, description, releaseDate, endDate, categories, rating, credits, imageFileName);
        Season[] seasons = fetchSeasons(series, properties[5]);
        series.setSeasons(seasons);

        return series;
    }

    private static Series[] fetchSeries() {
        Scanner s = new Scanner("series.txt");
        ArrayList<Series> seriesList = new ArrayList<>();
        while(s.hasNext()) {
            String line = s.nextLine();
            Series series = lineToSeries(line);
            seriesList.add(series);
        }
        Series[] seriesArray = new Series[seriesList.size()];
        return seriesList.toArray(seriesArray);
    }


}