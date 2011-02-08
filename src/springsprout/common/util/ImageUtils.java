package springsprout.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
	private static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
       
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
                g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
       
        return scaledBI;
    }
   
    /**
     * 이미지 리사이즈
     * @param srcFile 원본 이미지
     * @param destFile 리사이즈 이미지
     * @param width 리사이즈될 가로 길이
     * @param height 리사이즈될 세로 길이
     * @param imageType 리사이즈될 이미지 타입(PNG/JPG 등)
     * @param preserveAlpha 투명화 여부
     * @throws IOException
     */
    public static void resizeImage(File srcFile, File destFile, int width, int height, String imageType, boolean preserveAlpha) throws IOException   {
       
        BufferedImage originalImage = ImageIO.read(srcFile);
        BufferedImage resizedImage = createResizedCopy(originalImage, width, height, preserveAlpha);
        ImageIO.write(resizedImage, imageType, destFile);
       
    }
}
