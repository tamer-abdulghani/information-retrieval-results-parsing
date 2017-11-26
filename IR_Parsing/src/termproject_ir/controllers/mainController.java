package termproject_ir.controllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import termproject_ir.gui.mainFrame;
import termproject_ir.helpers.CsvService;
import termproject_ir.helpers.XmlService;
import termproject_ir.models.MeasureValuePair;
import termproject_ir.models.Topic;
import termproject_ir.models.UserFormulation;
import termproject_ir.models.UserQueryPair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tamer
 */
public class mainController {

    XmlService xmlService;
    CsvService csvService;

    public mainController() {
        xmlService = new XmlService();
        csvService = new CsvService();
    }

    public void startGUI() {
        mainFrame frame = new mainFrame(this);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        frame.setVisible(true);
    }

    public ArrayList<Topic> ReadParseXml(String fileName) {
        return xmlService.ReadTopicsXmlFile(fileName);
    }

    public ArrayList<UserFormulation> ReadParseXmlDirectory(String dirPath) {
        return xmlService.ReadFormulationXmlDirectory(dirPath);
    }

    public HashMap<UserQueryPair, ArrayList<MeasureValuePair>> ReadCsvUserQueryEval(String fullpath) {
        HashMap<UserQueryPair, ArrayList<MeasureValuePair>> a = csvService.ReadCSVFile(fullpath);
        for (Map.Entry<UserQueryPair, ArrayList<MeasureValuePair>> b : a.entrySet()) {
            for (MeasureValuePair v : b.getValue()) {
                System.out.println(b.getKey().getUserID() + "," + b.getKey().getTopicID() + "," + v.getName() + "," + v.getValue());
            }
        }
        return a;
    }

    public HashMap<String, ArrayList<Float>> ReadSystemsConfiguraionCSVDirectory(String fulldirpath) {
        return csvService.ReadCSVFileForSystemConfigurations(fulldirpath);
    }
}
