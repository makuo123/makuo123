package com.stock.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.automaticfields.PdfCompositeField;
import com.spire.pdf.automaticfields.PdfPageCountField;
import com.spire.pdf.automaticfields.PdfPageNumberField;
import com.spire.pdf.graphics.*;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfUtil {

    public static void main(String[] args) throws Exception {
//        addSeal();
        String infilePath = "C:\\Users\\makuo\\Desktop\\LIMS操作手册.pdf";
        String outfilePaht = "D:\\result\\Result.pdf";
        String picPath = "C:\\Users\\makuo\\Desktop\\seal.png";
        stamperCheckMarkPDF(infilePath, outfilePaht, picPath);
    }

    public static void addSeal(){
        String imagePath = "C:\\Users\\makuo\\Desktop\\seal.png";
        //要生成的文件模板
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("C:\\Users\\makuo\\Desktop\\LIMS操作手册.pdf");
        //添加一个空白页，目的为了删除jar包添加的水印，后面再移除这一页
        pdf.getPages().add();
        //创建字体
        PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("宋体", Font.PLAIN, 10),true);
        //遍历文档中的页
        for (int i = 0; i < pdf.getPages().getCount(); i++) {
            Dimension2D pageSize = pdf.getPages().get(i).getSize();
            float y = (float) pageSize.getHeight() - 40;
            //初始化页码域
            PdfPageNumberField number = new PdfPageNumberField();
            //初始化总页数域
            PdfPageCountField count = new PdfPageCountField();
            //创建复合域
            PdfCompositeField compositeField = new PdfCompositeField(font, PdfBrushes.getBlack(), "第{0}页 共{1}页", number, count);
            //设置复合域内文字对齐方式
            compositeField.setStringFormat(new PdfStringFormat(PdfTextAlignment.Right, PdfVerticalAlignment.Top));
            //测量文字大小
            Dimension2D textSize = font.measureString(compositeField.getText());
            //设置复合域的在PDF页面上的位置及大小
            compositeField.setBounds(new Rectangle2D.Float(((float) pageSize.getWidth() - (float) textSize.getWidth())/2, y, (float) textSize.getWidth(), (float) textSize.getHeight()));
            //将复合域添加到PDF页面
            compositeField.draw(pdf.getPages().get(i).getCanvas());
        }
        //移除第一个页
        pdf.getPages().remove(pdf.getPages().get(pdf.getPages().getCount()-1));
        //获取分割后的印章图片
        BufferedImage[] images = ImageUtil.splitImage(imagePath, pdf.getPages().getCount());
        float x = 0;
        float y = 0;
        //实例化PdfUnitConvertor类
        PdfUnitConvertor convert = new PdfUnitConvertor();
        PdfPageBase pageBase;
        //将图片绘制到PDF页面上的指定位置
        for (int i = 0; i < pdf.getPages().getCount(); i++)
        {
            BufferedImage image= images[ i ];
            pageBase = pdf.getPages().get(i);
            x = (float)pageBase.getSize().getWidth() - convert.convertUnits(image.getWidth(), PdfGraphicsUnit.Point, PdfGraphicsUnit.Pixel) + 40;
            y = (float) pageBase.getSize().getHeight()/ 2;
            pageBase.getCanvas().drawImage(PdfImage.fromImage(image), new Point2D.Float(x, y));
        }
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        //最终生成缝骑章   的结果
        pdf.saveToFile("D:\\result\\Result.pdf");
    }

    /**
     *  盖骑缝章
     *
     * @param infilePath    原PDF路径
     * @param outFilePath    输出PDF路径
     * @param picPath    章图片路径
     * @throws IOException
     * @throws DocumentException
     */
    public static void stamperCheckMarkPDF(String infilePath,String outFilePath,String picPath) throws Exception {
        PdfReader reader = new PdfReader(infilePath);//选择需要印章的pdf
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outFilePath));//加完印章后的pdf

        com.itextpdf.text.Rectangle pageSize = reader.getPageSize(1);//获得第一页
        float height = pageSize.getHeight();
        float width  = pageSize.getWidth();

        int nums = reader.getNumberOfPages();
        com.itextpdf.text.Image[] nImage =  ImageUtil.subImages(picPath,nums);//生成骑缝章切割图片


        for(int n=1;n<=nums;n++){
            PdfContentByte over = stamp.getOverContent(n);//设置在第几页打印印章
            com.itextpdf.text.Image img = nImage[n-1];//选择图片
//            img.setAlignment(1);
//            img.scaleAbsolute(200,200);//控制图片大小
            img.setAbsolutePosition(width-img.getWidth(),height/2-img.getHeight()/2);//控制图片位置
            over.addImage(img);
        }
        stamp.close();
    }

}
