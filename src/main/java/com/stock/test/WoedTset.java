package com.stock.test;

import cn.hutool.core.collection.CollectionUtil;
import com.deepoove.poi.xwpf.NiceXWPFDocument;
import com.spire.doc.Document;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileInputStream;
import java.util.*;

public class WoedTset {

    public static void main(String[] args) throws Exception {
        String rtfPath = "C:\\Users\\makuo\\Desktop\\慧巢\\lims管理文档\\02报告系统\\报告文件解析\\1.原始记录参考\\2022-07(1)\\笔记本-传导发射\\笔记本L1.Rtf";
        //Create a Document instance
        Document document = new Document();

        //Load a RTF document conducted or radiated disturbances 场景布置 测试
//        document.loadFromFile("Input.rtf", FileFormat.Rtf);
//        document.loadFromStream(new FileInputStream(rtfPath), FileFormat.Rtf);

        //Save the document to Doc
//        document.saveToFile("toDoc.doc", FileFormat.Doc);

        //Save the document to Docx
//        document.saveToFile("toDocx.docx", FileFormat.Docx_2013);

        NiceXWPFDocument doc = new NiceXWPFDocument(new FileInputStream("toDocx.docx"));

        List<XWPFParagraph> paragraphs = doc.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {

            if ("Final_Result".equalsIgnoreCase(paragraph.getText().trim())) {
                int posOfParagraph = doc.getPosOfParagraph(paragraph);

                Set<String> allLabels = new HashSet<>();

                List<XWPFTable> allTables = doc.getAllTables();
                for (XWPFTable table : allTables) {

                    // 重复标签
                    Set<String> duplicateLabels = new HashSet<>();

                    // 当前表格字段标签数据
                    Map<String, List<Object>> labelDatas = new HashMap<>();

                    // 当前表格字段标签集合
                    List<String> tableTitleLabelList = new ArrayList<>();

                    int posOfTable = doc.getPosOfTable(table);
                    if (posOfTable > posOfParagraph) {

                        // 抽取表格字段标签
                        XWPFTableRow row = table.getRow(0);
                        for (XWPFTableCell cell : row.getTableCells()) {

                            for (XWPFParagraph cellParagraph : cell.getParagraphs()) {
                                String label = (cellParagraph.getText().contains("(") ? cellParagraph.getText().substring(0, cellParagraph.getText().indexOf("(")) : cellParagraph.getText()).trim();
                                System.out.println(label.trim());
                                // 添加到当前表格字段标签
                                tableTitleLabelList.add(label);
                                // 判断字段标签是否在其他表格已解析
                                if (allLabels.contains(label)){
                                    // 已解析过该字段标签，放入重复标签集合中
                                    duplicateLabels.add(label);
                                }else {
                                    // 未解析过放入全部标签集合
                                    allLabels.add(label);
                                }
                            }

                        }

                        // 封装表格字段标签数据
                        List<XWPFTableRow> rows = table.getRows();
                        for (int i = 1; i < rows.size(); i++) {
                            XWPFTableRow tableRow = rows.get(i);
                            List<XWPFTableCell> tableCells = tableRow.getTableCells();
                            for (int j = 0; j < tableCells.size(); j++) {

                                if (duplicateLabels.contains(tableTitleLabelList.get(j))){
                                    continue;
                                }

                                String text = tableCells.get(j).getText();
                                List<Object> labelData = labelDatas.get(tableTitleLabelList.get(j));
                                if (CollectionUtil.isEmpty(labelData)) {
                                    labelData = new ArrayList<>();
                                    labelDatas.put(tableTitleLabelList.get(j), labelData);
                                }
                                // 数据添加到对应的标签集合中
                                labelData.add(text);
                                System.out.println(text);
                            }

                            System.out.println();
                        }

                        /*for (XWPFTableRow tableRow : rows) {

                            List<XWPFTableCell> tableCells = tableRow.getTableCells();
                            for (XWPFTableCell tableCell : tableCells) {
                                final String text = tableCell.getText();
                                System.out.println(text);
                            }

                        }*/

                    }
                }
            }
        }

        // 读取图表
        /*List<XWPFPictureData> allPictures = doc.getAllPictures();
        XWPFPictureData xwpfPictureData = allPictures.get(0);
        String fileName = xwpfPictureData.getFileName();
        byte[] data = xwpfPictureData.getData();
        // 将图片字节数组写入输出流
        try (FileOutputStream out = new FileOutputStream(new File(fileName))) {
            out.write(data,0,data.length);
        }catch (Exception e){

        }*/
    }
}
