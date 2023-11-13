package com.stock.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XsdToJsonConverter {
    public static void main(String[] args) {
        try {
            // 加载 XSD 文件
            File xsdFile = new File("D:\\project\\myproject\\makuo123\\src\\main\\resources\\xml\\1.xsd");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xsdFile);

            // 解析 XSD 并转换为 JSON
            Map<String, Object> json = parseXsd(document);

            // 将 JSON 格式化输出
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> parseXsd(Document document) {
        Element rootElement = document.getDocumentElement();
        return parseElement(rootElement);
    }

    private static Map<String, Object> parseElement(Element element) {
        Map<String, Object> json = new HashMap<>();
        json.put("name", element.getNodeName());

        // 解析属性
        NamedNodeMap attributes = element.getAttributes();
        if (attributes.getLength() > 0) {
            Map<String, String> attrJson = new HashMap<>();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                attrJson.put(attribute.getNodeName(), attribute.getNodeValue());
            }
            json.put("attributes", attrJson);
        }

        // 解析子元素
        NodeList childNodes = element.getChildNodes();
        List<Map<String, Object>> childrenJson = new ArrayList<>();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node child = childNodes.item(i);
            if (child instanceof Element) {
                Map<String, Object> childJson = parseElement((Element) child);
                childrenJson.add(childJson);
            }
        }
        if (!childrenJson.isEmpty()) {
            json.put("children", childrenJson);
        }

        return json;
    }
}

