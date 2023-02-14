package com.stock.util;

import com.itextpdf.text.BadElementException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    //定义GetImage方法，根据PDF页数分割印章图片
    static BufferedImage[] splitImage(String imagePath, int num)  {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int rows = 1;
        int cols = num;
        int chunks = rows * cols;
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;
        int count = 0;
        BufferedImage[] imgs = new BufferedImage[ chunks ];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                imgs[ count ] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
                Graphics2D gr = imgs[ count++ ].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight,
                        chunkWidth * y, chunkHeight * x,
                        chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, Color.WHITE,null);
                gr.dispose();
            }
        }
        return imgs;
    }

    /**
     * 切割图片
     * @param imgPath  原始图片路径
     * @param n 切割份数
     * @return  itextPdf的Image[]
     * @throws IOException
     * @throws BadElementException
     */
    public static com.itextpdf.text.Image[] subImages(String imgPath,int n) throws IOException, BadElementException {
        com.itextpdf.text.Image[] nImage = new com.itextpdf.text.Image[n];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedImage img = ImageIO.read(new File(imgPath));
        int h = img.getHeight();
        int w = img.getWidth();

        int sw = w/n;
        for(int i=0;i<n;i++){
            BufferedImage subImg;
            if(i==n-1){//最后剩余部分
                subImg = img.getSubimage(i * sw, 0, w-i*sw, h);
            }else {//前n-1块均匀切
                subImg = img.getSubimage(i * sw, 0, sw, h);
            }

            ImageIO.write(subImg,imgPath.substring(imgPath.lastIndexOf('.')+1),out);
            nImage[i] = com.itextpdf.text.Image.getInstance(out.toByteArray());
            out.flush();
            out.reset();
        }
        return nImage;
    }

    public static void main(String[] args) throws IOException {
        String image = "C:\\Users\\makuo\\Desktop\\seal.png";
        BufferedImage[] bufferedImages = splitImage(image, 5);
        System.out.println(bufferedImages);
    }
}
