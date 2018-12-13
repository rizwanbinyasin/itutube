package netflix.helpers;

import javafx.scene.image.Image;
import netflix.models.ImageButtonInfo;
import netflix.models.MediaList;
import netflix.models.media.Media;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class ImageButtonInfoHelper {
    public static List<ImageButtonInfo> mediasToImageButtonInfos(List<Media> medias, Consumer<Media> handleAction) {
        List<ImageButtonInfo> infoList = new ArrayList<>();
        for (Media media : medias) {
            String text = media.getName();
            Image image = Images.getMediaImage(media);
            infoList.add(new ImageButtonInfo(text, image, e -> handleAction.accept(media)));
        }
        return infoList;
    }

    public static List<ImageButtonInfo> categoriesToImageButtonInfos(List<MediaList> categories, Consumer<MediaList> handleAction) {
        List<ImageButtonInfo> imageButtonInfos = new ArrayList<>();
        for (MediaList category : categories) {
            Image image = Images.getCategoryImage(category.getName());
            String title = category.getName();
            ImageButtonInfo imageButtonInfo = new ImageButtonInfo(title, image, e -> handleAction.accept(category));
            imageButtonInfos.add(imageButtonInfo);
        }
        return imageButtonInfos;
    }

    public static List<MediaList> mediasToCategories(List<Media> medias) {
        HashMap<String, List<Media>> categoryMap = new HashMap<>();
        for (Media media : medias) {
            for (String category : media.getCategories()) {
                if (!categoryMap.containsKey(category)) {
                    categoryMap.put(category, new ArrayList<>());
                }
                categoryMap.get(category).add(media);
            }
        }
        List<MediaList> categories = new ArrayList<>();
        for (HashMap.Entry<String, List<Media>> entry : categoryMap.entrySet()) {
            MediaList category = new MediaList(entry.getKey(), entry.getValue());
            categories.add(category);
        }
        return categories;
    }
}
