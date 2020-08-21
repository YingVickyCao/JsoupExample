package com.hades.example.jsoup;

import com.hades.example.java.lib.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestParseHtml {
    public static void main(String[] args) {
        try {
            String htmlText = new FileUtils().readFileAsString("response.html");
            Document document = Jsoup.parse(htmlText);
            Elements allElements = document.getAllElements();
            Element dest = null;
            String destValue = null;
            for (Element element : allElements) {
//                System.out.println("tag:" + element.tag() + ",nodeName:" + element.nodeName() + ",value: " + element.val());
                if ("input".equalsIgnoreCase(element.nodeName())) {
                    Attributes attributes = element.attributes();
                    // <input type="hidden" name="SRequest" value="+aN+bg+f+cN+="/>
                    if (!attributes.isEmpty() && attributes.size() == 3) {
                        if ("hidden".equalsIgnoreCase(attributes.get("type")) && "SRequest".equalsIgnoreCase(attributes.get("name"))) {
                            destValue = attributes.get("value");
                            dest = element;
                        }
                    }
                }
            }
            System.out.println(dest.tag() + "nodeName:" + dest.nodeName() + ",value: " + dest.val());
            System.out.println(destValue);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}