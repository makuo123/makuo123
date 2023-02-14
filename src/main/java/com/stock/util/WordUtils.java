package com.stock.util;

import com.itextpdf.text.pdf.PdfReader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @Author mk
 * @Date 2021/6/8 17:17
 * @Version 1.0
 */
public class WordUtils {

    private static Configuration configuration = null;
    //classLoader.getResource()只能获取相对路径的资源
//     private static final String templateFolder = WordUtils.class.getClassLoader().getResource("template").getPath();
    //class.getResource()可以获取绝对路径和相对路径
    private static final String templateFolder = WordUtils.class.getResource("/templates").getPath();


    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        try {
            configuration.setDirectoryForTemplateLoading(new File(templateFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WordUtils() {
        throw new AssertionError();
    }

    public static void exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, Map map, String title, String ftlFile) throws IOException {
        Template freemarkerTemplate = configuration.getTemplate(ftlFile);
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            // 调用工具类的createDoc方法生成Word文档
            file = createDoc(map, freemarkerTemplate);
            fin = new FileInputStream(file);

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件名
            String fileName = title + DateUtil.getNowDate() + ".doc";
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

            out = response.getOutputStream();
            byte[] buffer = new byte[512];  // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if (fin != null) fin.close();
            if (out != null) out.close();
            if (file != null) file.delete(); // 删除临时文件
        }
    }

    private static File createDoc(Map<?, ?> dataMap, Template template) {
        String name = "sellPlan.doc";
        File f = new File(name);
        Template t = template;
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }


    public static class FilePagesUtils {
        /**
         *
         * @param fileInputStream  文件流
         * @param fileType  文件后缀
         * @return
         * @throws IOException
         */
        public static int filesPage(InputStream fileInputStream, String fileType) throws IOException {
            int count = 0;
            if (".doc".equals(fileType)) {
                count = countWord2003Page(fileInputStream);
            }
            if (".docx".equals(fileType)) {
                count = countWord2007Page(fileInputStream);
            }
            if (".pdf".equals(fileType)) {
                count = countPdfPage(fileInputStream);
            }
            if (".pptx".equals(fileType)) {
                count = countPPTXPage(fileInputStream);
            }
            /*if (".ppt".equals(fileType)) {
                count = countPPTPage(fileInputStream);
            }*/
            return count;
        }

        /**
         * 计算PDF格式文档的页数
         */
        public static int countPdfPage(InputStream fileInputStream) {
            int pageCount = 0;
            PdfReader reader = null;
            try {
                reader = new PdfReader(fileInputStream);
                pageCount = reader.getNumberOfPages();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                reader.close();
            }
            return pageCount;
        }

        /**
         * 计算PPTX格式文档的页数
         * @param fileInputStream
         * @return
         * @throws IOException
         */
        /*public static int countPPTPage(InputStream fileInputStream) throws IOException {
            int pageCount = 0;
            ZipSecureFile.setMinInflateRatio(-1.0d);

            HSLFSlideShow hslfSlideShow = new HSLFSlideShow(fileInputStream);
            try {
                pageCount = hslfSlideShow.getSlides().size();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fileInputStream.close();
            }
            return pageCount;

        }*/

        /**
         * 计算PPTX格式文档的页数
         */
        public static int countPPTXPage(InputStream fileInputStream) throws IOException {
            int pageCount = 0;
            ZipSecureFile.setMinInflateRatio(-1.0d);
            try {
                XMLSlideShow pptxFile = new XMLSlideShow(fileInputStream);
                pageCount = pptxFile.getSlides().size();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fileInputStream.close();
            }
            return pageCount;
        }

        /**
         * 计算WORD2007(*.docx)格式文档的页数
         */
        public static int countWord2007Page(InputStream fileInputStream) throws IOException {
            int pageCount = 0;
            ZipSecureFile.setMinInflateRatio(-1.0d);
            XWPFDocument docx = null;
            try {
                docx = new XWPFDocument(fileInputStream);
                pageCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();//总页数
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                docx.close();
            }
            return pageCount;
        }

        /**
         * 计算WORD2003(*.doc)格式文档的页数
         */
        public static int countWord2003Page(InputStream fileInputStream) throws IOException {
            /*int pageCount = 0;
            WordExtractor doc = null;
            ZipSecureFile.setMinInflateRatio(-1.0d);
            try {
                doc = new WordExtractor(fileInputStream);//.doc格式Word文件提取器
                pageCount = doc.getSummaryInformation().getPageCount();//总页数
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                doc.close();
            }
            return pageCount;*/
            return 0;
        }
    }
}
