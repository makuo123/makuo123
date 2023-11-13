package com.stock.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlToExcelConverter {
    public static void main(String[] args) {
        try {
            // 加载 XML 文件
            File xmlFile = new File("path/to/your/xml/file.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(xmlFile);

            // 将 XML 解析为 Person 列表
            List<Person> persons = parseXml(document);

            // 创建 Excel 工作簿并添加表头
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            Row headerRow = sheet.createRow(0);
            Cell nameHeader = headerRow.createCell(0);
            nameHeader.setCellValue("Name");
            Cell ageHeader = headerRow.createCell(1);
            ageHeader.setCellValue("Age");
            Cell addressHeader = headerRow.createCell(2);
            addressHeader.setCellValue("Address");
            Cell phoneHeader = headerRow.createCell(3);
            phoneHeader.setCellValue("Phone");

            // 将 Person 写入 Excel 中
            int rowNum = 1;
            for (Person person : persons) {
                Row row = sheet.createRow(rowNum++);
                Cell nameCell = row.createCell(0);
                nameCell.setCellValue(person.getName());
                Cell ageCell = row.createCell(1);
                ageCell.setCellValue(person.getAge());
                Cell addressCell = row.createCell(2);
                addressCell.setCellValue(person.getAddress());
                Cell phoneCell = row.createCell(3);
                phoneCell.setCellValue(person.getPhone());
                // 将地址和电话号码单元格合并为一个单元格
                sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 2, 3));
            }

            // 将 Excel 写入文件
            FileOutputStream outputStream = new FileOutputStream("path/to/your/excel/file.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            System.out.println("XML 已成功转换为 Excel。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 XML 文件解析成 Person 对象的列表
     */
    private static List<Person> parseXml(org.w3c.dom.Document document) {
        List<Person> persons = new ArrayList<>();
        NodeList personNodes = document.getElementsByTagName("person");
        for (int i = 0; i < personNodes.getLength(); i++) {
            Node personNode = personNodes.item(i);
            if (personNode.getNodeType() == Node.ELEMENT_NODE) {
                Element personElement = (Element) personNode;
                String name = personElement.getAttribute("name");
                int age = Integer.parseInt(personElement.getAttribute("age"));
                String address = personElement.getElementsByTagName("address").item(0).getTextContent();
                String phone = personElement.getElementsByTagName("phone").item(0).getTextContent();
                Person person = new Person(name, age, address, phone);
                persons.add(person);
            }
        }
        return persons;
    }

    /**
     * Person 类
     */
    private static class Person {
        private String name;
        private int age;
        private String address;
        private String phone;

        public Person(String name, int age, String address, String phone) {
            this.name = name;
            this.age = age;
            this.address = address;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }
    }
}

