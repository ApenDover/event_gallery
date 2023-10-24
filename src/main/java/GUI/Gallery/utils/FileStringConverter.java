package GUI.Gallery.utils;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@UtilityClass
public class FileStringConverter {

    private final String SLASH = "/";

    private final String POINT = ".";

    public String getName(File file) {
        return file.getName().substring(0, file.getName().lastIndexOf('.'));
    }

    public String getExtension(File file) {
        return file.getName().substring(file.getName().lastIndexOf('.') + 1);
    }

    public String getExtension(String file) {
        return file.substring(file.lastIndexOf('.') + 1);
    }

    public File getFile(String source, String name, String fileType) {
        return new File(getFilePath(source, name, fileType));
    }

    public File getFile(String source, String name) {
        return new File(getFilePath(source, name));
    }

    public FileInputStream getFileInputString(String source, String name) {
        try {
            return new FileInputStream(getFilePath(source, name));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFilePath(String source, String name, String fileType) {
        return source + SLASH + name + POINT + fileType;
    }

    public String getFilePath(String source, String name) {
        return source + SLASH + name;
    }

    public String getNameWithEx(String fileName, String ex) {
        return fileName + POINT + ex;
    }

}
