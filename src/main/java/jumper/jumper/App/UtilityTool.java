package jumper.jumper.App;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class UtilityTool {

    public Image scaleImage(Image original, int width, int height) {
        if (original == null) throw new IllegalArgumentException("Original image cannot be null");

        WritableImage scaledImage = new WritableImage(width, height);
        PixelReader pixelReader = original.getPixelReader();

        if (pixelReader != null) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Calculate corresponding pixel from original image
                    int originalX = (int) (x*(original.getWidth()/(double) width));
                    int originalY = (int) (y*(original.getHeight()/(double) height));
                    // Get pixel colour and write it to new image
                    scaledImage.getPixelWriter().setColor(x, y, pixelReader.getColor(originalX, originalY));
                }
            }
        }
        return scaledImage;
    }
}
