package com.stock.util.zebra;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImageUtil {

    /**
     * 调整bufferedimage大小
     * @param source BufferedImage 原始image
     * @param targetW int  目标宽
     * @param targetH int  目标高
     * @param flag boolean 是否同比例调整
     * @return BufferedImage  返回新image
     */
    public static BufferedImage resizeBufferedImage(BufferedImage source, int targetW, int targetH, boolean flag) {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        if (flag && sx > sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else if(flag && sx <= sy){
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }
        Graphics2D g = target.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }
}
