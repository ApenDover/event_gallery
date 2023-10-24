package GUI.Gallery.imageViewProcess;

import GUI.Gallery.setUp.SettingsLoader;
import GUI.Gallery.utils.FileStringConverter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PictureBuilder {

    public ImageView buildImageView(String file) {
        Image image = new Image(FileStringConverter.getFileInputString(SettingsLoader.getSourceFolder(), file));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        final var difSize = image.getWidth() / image.getHeight();
        if (difSize > 1) {
            imageView.setFitWidth(1200);
            imageView.setFitHeight(1200 / difSize);
        } else {
            imageView.setFitWidth(800 * difSize);
            imageView.setFitHeight(800);
        }
        imageView.setId(file);
        return imageView;
    }

}
