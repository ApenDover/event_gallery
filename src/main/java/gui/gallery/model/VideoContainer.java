package gui.gallery.model;

import gui.gallery.model.Comparator.ImageViewComparator;
import gui.gallery.utils.FileStringConverter;
import gui.gallery.utils.videoResizer.VideoResizerJpg;
import gui.gallery.singleton.SettingsConst;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Objects;

@Getter
@Setter
public class VideoContainer extends AbstractContainer implements Resizeable {

    private static final ImageViewComparator COMPARATOR = new ImageViewComparator();

    private ResizedImageContainer resized;

    @Getter
    private MediaView mediaView;

    public VideoContainer(File file) {
        super(file, file.getAbsolutePath(), FileStringConverter.getName(file), FileStringConverter.getExtension(file));
        final var media = new Media(file.toURI().toString());
        final var mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaView = new MediaView();
        mediaView.setFitWidth(SettingsConst.LONG_SIDE.getValue());
        mediaView.setFitHeight(SettingsConst.SHOT_SIDE.getValue());
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setId(FileStringConverter.getFullNameFromPath(file.getAbsolutePath()));
        setWidth(media.getWidth());
        setHeight(media.getHeight());
        createResizePreview();
    }

    @Override
    public ResizedImageContainer getResizedImageContainer() {
        return resized;
    }

    public boolean isResizedAlive() {
        if (Objects.isNull(resized)) {
            return false;
        }
        return resized.getFile().exists();
    }

    @Override
    public void createResizePreview() {
        if (!isResizedAlive()) {
            resized = new ResizedImageContainer(VideoResizerJpg.getImageFromVideo(this.getFile(),
                    SettingsConst.SCALE_RESIZE_LONG_SIDE.getValue(), true), this);
        }
    }

    @Override
    public boolean isOriginalAlive() {
        return getFile().exists();
    }

    public ResizedImageContainer getResized() {
        createResizePreview();
        return resized;
    }


    @Override
    public Node getNode() {
        return mediaView;
    }

}
