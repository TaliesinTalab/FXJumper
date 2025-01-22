package jumper.jumper.app;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/*  This is like a tool box class, whenever we come up with a convenient function,
we can put it inside this class.
using pixel mapping, pixels are proportionally retrieved from the original image.
* Author by @Lu Wang
 */

public class UtilityTool {

    //this method takes one original image and scale it and return the scaleImage

    public Image scaleImage(Image original, int width, int height) {

        //to be sure that the original imagine is not a null
        if (original == null) throw new IllegalArgumentException("Original image cannot be null");
        //create the scaled Image
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
