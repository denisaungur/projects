import blur.GlowFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.rint;

/**
 * Created by Denisa on 22.03.2017.
 */
public class ImageTransformer {

    float[] matrixBlur = {
            1f / 9f, 1f / 9f, 1f / 9f,
            1f / 9f, 1f / 9f, 1f / 9f,
            1f / 9f, 1f / 9f, 1f / 9f,
    };


    float[] matrixGaussian = {
            1 / 16f, 1 / 8f, 1 / 16f,
            1 / 8f, 1 / 4f, 1 / 8f,
            1 / 16f, 1 / 8f, 1 / 16f,
    };

    float[] matrixEdgeDetection = {
            -1, -1, -1,
            -1, 8, -1,
            -1, -1, -1};


    public BufferedImage blurImage(BufferedImage image) {
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, matrixBlur));
        return op.filter(image, null);

    }


    public BufferedImage edgeDetection(BufferedImage image) {
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, matrixEdgeDetection));
        return op.filter(image, null);
    }

    public BufferedImage glowFilter(BufferedImage image) {
        GlowFilter op = new GlowFilter();
        return op.filter(image, null);
    }

    public BufferedImage imageBinarization(BufferedImage image, int max) {
        int alpha, red, green, blue;
        int newPixel;

        BufferedImage lum = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                red = new Color(image.getRGB(i, j)).getRed();
                green = new Color(image.getRGB(i, j)).getGreen();
                blue = new Color(image.getRGB(i, j)).getBlue();
                alpha = new Color(image.getRGB(i, j)).getAlpha();

                if (red > max || blue > max || green > max) {
                    red = 255;
                    green = 255;
                    blue = 255;
                } else {
                    red = 0;
                    blue = 0;
                    green = 0;
                }
                newPixel = colorToRGB(alpha, red, green, blue);

                lum.setRGB(i, j, newPixel);

            }
        }

        return lum;
    }


    public double[] histogram(BufferedImage image) {

        BufferedImage bi = greyScale(image);

        double[] histogram = new double[bi.getWidth()* bi.getHeight() + 1];
        Arrays.fill(histogram, 0);

        int red;
        int count = 0;
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                red = new Color(bi.getRGB(x, y)).getRed();
//                histogram[red] ++;
                histogram[count] = red;
                count++;

            }

        }

//        int max = (int) Arrays.stream(histogram).max().getAsDouble();
//        double[] result = new double[max + 1];
//
//        for( int i =0; i< histogram.length; i++){
//            result[(int)histogram[i]] = i;
//        }
        return histogram;
    }


    public BufferedImage briteningFactor(BufferedImage image, float factor) {

        RescaleOp op = new RescaleOp(factor / 100, 0, null);
        return op.filter(image, null);
    }

    public BufferedImage greyScale(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getHeight(); i++) {

            for (int j = 0; j < image.getWidth(); j++) {

                Color c = new Color(image.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                Color newColor = new Color(red + green + blue,

                        red + green + blue, red + green + blue);

                result.setRGB(j, i, newColor.getRGB());
            }
        }

        return result;
    }

    public BufferedImage negative(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {

                Color c = new Color(image.getRGB(j, i));
                int red = (int) (255 - c.getRed());
                int green = (int) (255 - c.getGreen());
                int blue = (int) (255 - c.getBlue());
                Color newColor = new Color(red,

                        green, blue);

                result.setRGB(j, i, newColor.getRGB());
            }
        }

        return result;
    }


    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }


}
