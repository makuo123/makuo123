package com.stock.test;

import com.spire.doc.Document;
import com.spire.doc.*;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.xwpf.usermodel.*;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        String rtfPath = "D:\\BaiduNetdiskDownload\\1661419257522.docx";

        String image = "D:\\project\\myproject\\makuo123\\image1.wmf";
        /*NiceXWPFDocument doc = new NiceXWPFDocument(new FileInputStream(rtfPath));
        final List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            CTPPr pPr = paragraph.getCTP().getPPr();
        }*/
        Document document = new Document();
        document.loadFromFile(rtfPath);
        SectionCollection sections = document.getSections();
        for (int i = 0; i < sections.getCount(); i++) {
            Section section = sections.get(i);

            float y = (float) (section.getPageSetup().getPageSize().getHeight()/3);
            HeaderFooter header1 = section.getHeadersFooters().getHeader();//获取页眉
            /*DocPicture docPicture = new DocPicture(document);
            docPicture.loadImage("D:\\project\\myproject\\makuo123\\image1.wmf");*/
            header1.getParagraphs().clear();//删除原有页眉格式的段落
            Paragraph para1= header1.addParagraph();//重新添加段落
            DocPicture pic1 = para1.appendPicture(image);//加载图片
            pic1.setTextWrappingStyle(TextWrappingStyle.Behind);//图片置于文字下方
            pic1.setVerticalPosition(y);
            pic1.setWidth(20);
            pic1.setHeight(50);
            pic1.setHorizontalAlignment(ShapeHorizontalAlignment.Right);//设置图片对齐方式

            /*docPicture.setWidth(20);
            docPicture.setHeight(50);
            docPicture.setHorizontalPosition(370.0F);
            docPicture.setVerticalPosition(100.0F);
            docPicture.setTextWrappingStyle(TextWrappingStyle.Square);
            section.getParagraphs().get(0).getChildObjects().insert(0,docPicture);*/
        }

        document.saveToFile("D:\\project\\myproject\\makuo123\\image12.docx", FileFormat.Docx);
    }




    /**
     * @author: xunxi
     * @description:
     */
    public static class TestReadWord {


        public void test(){
            File file = new File("文件路径");
            try(FileInputStream is = new FileInputStream(file)){
                XWPFDocument doc = new XWPFDocument(is);

                List<XWPFFooter> footerList = doc.getFooterList();
                //获取以rid为key的页脚
                Map<String,XWPFFooter> footerMap = footerList.stream().collect(Collectors.toMap(footer -> footer.getParent().getRelationId(footer), footer -> footer));


                /*********************** 遍历word文档，找到各节的内容 ***********************************/

                Iterator<IBodyElement> elementsIterator = doc.getBodyElementsIterator();

                List<IBodyElement> tempBodyElementList = Lists.newArrayList();

                List<Map<String,Object>> sectList = Lists.newArrayList();

                Map<String,Object> tempMap = new HashMap<>(2);

                while(elementsIterator.hasNext()){
                    IBodyElement iBodyElement = elementsIterator.next();
                    tempBodyElementList.add(iBodyElement);

                    if(iBodyElement.getElementType() == BodyElementType.PARAGRAPH){
                        XWPFParagraph paragraph = (XWPFParagraph)iBodyElement;

                        //节的属性存储在sectPr元素中。对于除最后一部分以外的所有部分，sectPr元素存储为该部分最后一个paragraph元素的子元素。对于最后一部分，sectPr被存储为body元素的子元素
                        List<Node> sectNodeList = getChildNodes(paragraph.getCTP().getDomNode(), "w:sectPr");
                        if(!sectNodeList.isEmpty()){
                            tempMap.put("sectNode",sectNodeList.get(0));
                            tempMap.put("bodyElementList",tempBodyElementList);
                            sectList.add(tempMap);
                            tempBodyElementList = Lists.newArrayList();
                            tempMap = new HashMap<>(2);
                        }
                    }
                }
                //最后一节不在paragraph内,而在body的直接子元素下
                Node bodyNode = doc.getDocument().getBody().getDomNode();
                Node sectNode = getNextNodes(bodyNode, "w:sectPr").get(0);

                tempMap.put("sectNode",sectNode);
                tempMap.put("bodyElementList",tempBodyElementList);
                sectList.add(tempMap);


                /***********遍历各节的内容和页脚***************/
                Iterator<Map<String, Object>> sectIterator = sectList.iterator();
                while(sectIterator.hasNext()){
                    Map<String, Object> sectMap = sectIterator.next();

                    Node sNode = (Node)sectMap.get("sectNode");

                    List<IBodyElement> bodyElementList = (List<IBodyElement>) sectMap.get("bodyElementList");
                    /**
                     * sectPr元素内的部分结构;其中"r:id"的值是引用id，"w:type"表示该页脚的应用规则。
                     * 详细规则看"http://officeopenxml.com/WPSectionFooterReference.php"
                     *  <w:sectPr w:rsidR="00F269C4">
                     *      <w:headerReference w:type="default" r:id="rId8"/>
                     *      <w:footerReference w:type="default" r:id="rId9"/>
                     *  </w:sectPr>
                     *
                     */
                    bodyElementList.forEach(e->{
                        if(e.getElementType() == BodyElementType.PARAGRAPH){
                            XWPFParagraph paragraph = (XWPFParagraph)e;
                            System.out.println(paragraph.getParagraphText());
                        }else if(e.getElementType() == BodyElementType.TABLE){
                            XWPFTable table =  (XWPFTable)e;
                            System.out.println(table.getText());
                        }
                    });

                    List<Node> footNodeList = getNextNodes(sNode, "w:footerReference");

                    System.out.println("以上内容的页脚是：");
                    footNodeList.forEach(node->{
                        NamedNodeMap attributes = node.getAttributes();
                        String rid = attributes.getNamedItem("r:id").getNodeValue();

                        XWPFFooter footer = footerMap.get(rid);
                        footer.getListParagraph().stream().map(XWPFParagraph::getParagraphText).forEach(System.out::println);
                    });
                    sectIterator.remove();
                }
                /* */

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         *获取下一级节点名为<code>nodeName</code>的节点
         * @param node
         * @param nodeName 节点名称
         * @return
         */
        private List<Node> getNextNodes(Node node, String nodeName){
            List<Node> resultList = Lists.newArrayList();
            if(node == null || !node.hasChildNodes()){
                return resultList;
            }
            NodeList nodeList = node.getChildNodes();
            for(int i=0;i<nodeList.getLength();i++){
                Node node1 = nodeList.item(i);
                if(node1.getNodeName().equals(nodeName)){
                    resultList.add(node1);
                }
            }
            return resultList;
        }

        /**
         * 获取节点名为<code>nodeName</code>的子节点
         * @param node
         * @param nodeName 子节点的名称
         * @return
         */
        private List<Node> getChildNodes(Node node, String nodeName){
            if(node == null || !node.hasChildNodes()){
                return Lists.newArrayList();
            }
            List<Node> resultList = Lists.newArrayList();
            NodeList nodeList = node.getChildNodes();
            for(int i=0;i<nodeList.getLength();i++){
                Node node1 = nodeList.item(i);
                if(node1.getNodeName().equals(nodeName)){
                    resultList.add(node1);
                }
                resultList.addAll(getChildNodes(node1,nodeName));

            }
            return resultList;
        }
    }

}
