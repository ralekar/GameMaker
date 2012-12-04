package imagewizard;

import java.io.File;

public class MyFilter extends javax.swing.filechooser.FileFilter {
    public boolean accept(File file) {
        String filename = file.getName();
        return (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png"));
    }
    public String getDescription() {
        return "*.jpeg or *.jpg or *.png";
    }
}