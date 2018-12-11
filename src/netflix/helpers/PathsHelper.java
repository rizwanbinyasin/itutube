package netflix.helpers;

import netflix.models.User;
import netflix.models.media.Media;

public class PathsHelper {
    public static String getDataFilePath(String dataType) {
        return "src/resources/" + dataType + ".txt";
    }

    public static String getDefaultMediaImagePath() {
        return "src/resources/posters/default.jpg";
    }

    public static String getMediaImagePath(Media media) {
        if (media == null) {
            return null;
        }
        String mediaType = media.getClass().getSimpleName();
        String mediaName = media.getName();
        return "src/resources/posters/" + mediaType + "Posters/" + mediaName + ".jpg";
    }

    public static String getUserImagePath(User user) {
        if (user == null) {
            return null;
        }
        String userName = user.getName();
        return "src/resources/usersImages/" + userName + ".png";
    }

    public static String getDefaultUserImagePath() {
        return "src/resources/usersImages/default.jpg";
    }

    public static String getCategoryImagePath(String category) {
        return "src/resources/categoryImages/" + category + ".jpg";
    }

    public static String getDefaultCategoryImagePath() {
        return "src/resources/categoryImages/default.jpg";
    }
}
