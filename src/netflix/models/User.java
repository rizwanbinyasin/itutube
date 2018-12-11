package netflix.models;

import netflix.models.media.Media;

import java.util.ArrayList;

/**
 * A user for the logged in account
 */
public class User implements Saveable {
    private String name;
    private UserType type;
    private ArrayList<Media> favoritesList;

    public User(String name, UserType type, ArrayList<Media> favoritesList) {
        this.name = name;
        this.type = type;
        this.favoritesList = favoritesList;
    }

    public User(String name, UserType type) {
        this(name, type, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }

    public ArrayList<Media> getFavoritesList() {
        return favoritesList;
    }

    @Override
    public String getSaveString() {
        String[] idArray = new String[favoritesList.size()];
        for(int i = 0; i < favoritesList.size(); i++) {
            idArray[i] = favoritesList.get(i).getId();
        }
        String idString = String.join(",", idArray);
        return name + ";" + type + ";" + idString + ";";
    }
}
