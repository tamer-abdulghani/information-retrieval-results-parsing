/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.helpers;

import java.io.File;
import javax.lang.model.element.Element;
import javax.swing.text.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;
import java.util.ArrayList;
import termproject_ir.models.Subtopic;
import termproject_ir.models.SubtopicType;
import termproject_ir.models.Topic;
import termproject_ir.models.TopicType;
import termproject_ir.models.UserFormulation;
import termproject_ir.models.UserTopic;

/**
 *
 * @author Tamer
 */
public class XmlService {

    public ArrayList<Topic> ReadTopicsXmlFile(String FileName) {
        ArrayList<Topic> list = new ArrayList<>();
        try {
            File fXmlFile = new File(FileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("topic");

            //System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Topic topic = new Topic();
                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                    topic.setNumber(Integer.parseInt(eElement.getAttribute("number")));
                    topic.setType(TopicType.valueOf(eElement.getAttribute("type")));
                    topic.setQuery(eElement.getElementsByTagName("query").item(0).getTextContent());
                    topic.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());

                    NodeList subtopicList = eElement.getElementsByTagName("subtopic");
                    ArrayList<Subtopic> subtopicsList = new ArrayList<>();
                    for (int j = 0; j < subtopicList.getLength(); j++) {
                        Subtopic s = new Subtopic();
                        Node nNode2 = subtopicList.item(j);

                        //System.out.println("\nCurrent Element :" + nNode2.getNodeName());
                        if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                            org.w3c.dom.Element eElement1 = (org.w3c.dom.Element) nNode2;
                            s.setNumber(Integer.parseInt(eElement1.getAttribute("number")));
                            s.setType(SubtopicType.valueOf(eElement1.getAttribute("type")));
                            s.setContent(eElement1.getTextContent());
                        }
                        subtopicsList.add(s);
                    }
                    list.add(topic);
                }
            }
        } catch (Exception e) {

        }

        return list;
    }

    public ArrayList<UserFormulation> ReadFormulationXmlDirectory(String dirPath) {
        ArrayList<UserFormulation> list = new ArrayList<>();

        File[] files = new File(dirPath).listFiles();
        for (File file : files) {

            if (!file.isFile()) {
                continue;
            }
            try {
                File fXmlFile = new File(file.getPath());
                System.out.println("" + fXmlFile.getPath());
                String name = fXmlFile.getName();
                UserFormulation forma = new UserFormulation();
                forma.setUserId(Integer.parseInt(name.split("_")[1]));

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

                doc.getDocumentElement().normalize();

                //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                NodeList nList = doc.getElementsByTagName("top");

                //System.out.println("----------------------------");
                ArrayList<UserTopic> topics = new ArrayList<>();

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    UserTopic ut = new UserTopic();
                    Node nNode = nList.item(temp);

                    //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
                        ut.setUserId(Integer.parseInt(name.split("_")[1]));
                        ut.setTopId(eElement.getAttribute("idactivite"));
                        ut.setNum(Integer.parseInt(eElement.getElementsByTagName("num").item(0).getTextContent()));
                        ut.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    }
                    topics.add(ut);
                }
                forma.setTopics(topics);
                list.add(forma);
                } catch (Exception e) {
                }

        }
        return list;
    }
}
