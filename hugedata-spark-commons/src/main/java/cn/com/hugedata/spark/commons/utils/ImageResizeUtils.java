package cn.com.hugedata.spark.commons.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizeUtils {

    /**
     * 保持比例缩放图片.
     * 缩放后图片大小为(targetWidth,targetHeight)，原图比例不一致时存在透明背景.
     * @param sourceImage  源图片
     * @param targetWidth  目标图片宽度
     * @param targetHeight 目标图片高度
     * @return             缩放后的图片
     */
    public static BufferedImage resizeImage(BufferedImage sourceImage, int targetWidth, int targetHeight) {
        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        // 图片缩放后的大小
        int scaledWidth;
        int scaledHeight;
        if (sourceWidth * targetHeight > targetWidth * sourceHeight) {
            scaledWidth = targetWidth;
            scaledHeight = targetWidth * sourceHeight / sourceWidth;
        } else {
            scaledHeight = targetHeight;
            scaledWidth = targetHeight * sourceWidth / sourceHeight;
        }
        
        // 创建图片
        BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = targetImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        graphics.drawImage(
                sourceImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH), 
                (targetWidth - scaledWidth) / 2, 
                (targetHeight - scaledHeight) / 2, 
                null);
        
        return targetImage;
    }

    /**
     * 保持比例缩放图片文件.
     * 缩放后图片大小为(targetWidth,targetHeight)，原图比例不一致时存在透明背景.
     * @param input        源文件
     * @param targetWidth  缩放后的宽度
     * @param targetHeight 缩放后的高度
     * @param output       缩放后保存的文件
     * @param format       缩放后保存的类型
     * @throws IOException 文件读写发生错误
     */
    public static void resizeImage(File input, int targetWidth, int targetHeight, File output, String format)
            throws IOException {
        BufferedImage sourceImage = ImageIO.read(input);
        BufferedImage targetImage = resizeImage(sourceImage, targetWidth, targetHeight);
        ImageIO.write(targetImage, format, output);
    }

    /**
     * 保持比例缩放图片，缩放后大小不超过(maxWidth,maxHeight).
     * @param sourceImage     源图片
     * @param maxWidth        目标图片最大宽度
     * @param maxHeight       目标图片最大高度
     * @param backgroundColor 背景色
     * @return                缩放后的图片
     */
    public static BufferedImage resizeImageWithMaxSize(BufferedImage sourceImage, int maxWidth, int maxHeight,
            int backgroundColor) {
        int sourceWidth = sourceImage.getWidth();
        int sourceHeight = sourceImage.getHeight();
        
        // 图片缩放后的大小
        int scaledWidth;
        int scaledHeight;
        if (sourceWidth * maxWidth > maxHeight * sourceHeight) {
            scaledWidth = maxWidth;
            scaledHeight = maxWidth * sourceHeight / sourceWidth;
        } else {
            scaledHeight = maxHeight;
            scaledWidth = maxHeight * sourceWidth / sourceHeight;
        }
        
        // 创建图片
        BufferedImage targetImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = targetImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        Rectangle2D rect = new Rectangle2D.Float(0, 0, scaledWidth, scaledHeight);
        graphics.setPaint(new Color(backgroundColor, true));
        graphics.fill(rect);
        
        graphics.drawImage(
                sourceImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH), 
                0, 
                0, 
                null);
        
        return targetImage;
    }


    /**
     * 保持比例缩放图片，缩放后大小不超过(maxWidth,maxHeight).
     * @param sourceImage     源图片
     * @param maxWidth        目标图片最大宽度
     * @param maxHeight       目标图片最大高度
     * @return                缩放后的图片
     */
    public static BufferedImage resizeImageWithMaxSize(BufferedImage sourceImage, int maxWidth, int maxHeight) {
        return resizeImageWithMaxSize(sourceImage, maxWidth, maxHeight, 0);
    }


    /**
     * 保持比例缩放图片文件，缩放后大小不超过(maxWidth,maxHeight).
     * @param input           源文件
     * @param maxWidth        目标图片最大宽度
     * @param maxHeight       目标图片最大高度
     * @param output          缩放后保存的文件
     * @param format          缩放后保存的类型
     * @param backgroundColor 背景色
     * @return                缩放后的图片
     * @throws IOException    文件读写发生错误
     */
    public static void resizeImageWithMaxSize(File input, int maxWidth, int maxHeight, File output, String format,
            int backgroundColor) throws IOException {
        BufferedImage sourceImage = ImageIO.read(input);
        BufferedImage targetImage = resizeImageWithMaxSize(sourceImage, maxWidth, maxHeight, backgroundColor);
        ImageIO.write(targetImage, format, output);
    }

    /**
     * 保持比例缩放图片文件，缩放后大小不超过(maxWidth,maxHeight).
     * @param input        源文件
     * @param maxWidth     目标图片最大宽度
     * @param maxHeight    目标图片最大高度
     * @param output       缩放后保存的文件
     * @param format       缩放后保存的类型
     * @return             缩放后的图片
     * @throws IOException 文件读写发生错误
     */
    public static void resizeImageWithMaxSize(File input, int maxWidth, int maxHeight, File output, String format)
            throws IOException {
        resizeImageWithMaxSize(input, maxWidth, maxHeight, output, format, 0);
    }

    /**
     * 不允许实例化.
     */
    private ImageResizeUtils() {
    }
}
