/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import termproject_ir.models.MeasureValuePair;
import termproject_ir.models.UserQueryPair;

/**
 *
 * @author Tamer
 */
public class CsvService {

    public HashMap<UserQueryPair, ArrayList<MeasureValuePair>> ReadCSVFile(String csvfileName) {
        HashMap<UserQueryPair, ArrayList<MeasureValuePair>> map = new HashMap<>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int count = 0;
        String[] measuresNames = {""};
        try {

            br = new BufferedReader(new FileReader(csvfileName));
            while ((line = br.readLine()) != null) {

                if (count == 0) {
                    // collecting measures
                    measuresNames = line.split(cvsSplitBy);
                } else {
                    // collecting values
                    String[] values = line.split(cvsSplitBy);
                    UserQueryPair pair = new UserQueryPair(Integer.parseInt(values[0].split("_")[1]), Integer.parseInt(values[1]));
                    ArrayList<MeasureValuePair> list = new ArrayList<>();
                    for (int i = 2; i < values.length; i++) {
                        MeasureValuePair fpair = new MeasureValuePair(measuresNames[i], Float.parseFloat(values[i]));
                        list.add(fpair);
                    }
                    map.put(pair, list);
                }
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;

    }

    public HashMap<String, ArrayList<Float>> ReadCSVFileForSystemConfigurations(String csvFileName) {
        // HashMap<String, HashMap<String, ArrayList<Float>>> map = new HashMap<>();
        HashMap<String, ArrayList<Float>> measuresMap = new HashMap<>();

        //File[] files = new File(fulldirpath).listFiles();
        //for (File file : files) {
        //if (!file.isFile()) {
        //  continue;
        //}
        //File csvFile = new File(file.getPath());
        //String csvFileName = csvFile.getName();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int count = 0;
        String[] measuresNames = {""};
        try {

            br = new BufferedReader(new FileReader(csvFileName));
            while ((line = br.readLine()) != null) {

                if (count == 0) {
                    // collecting measures
                    measuresNames = line.split(cvsSplitBy);
                    for (String mName : measuresNames) {
                        measuresMap.put(mName, new ArrayList<Float>());
                    }
                } else {
                    // collecting values
                    String[] values = line.split(cvsSplitBy);

                    for (int i = 2; i < values.length; i++) {
                        String measureName = measuresNames[i];

                        measuresMap.get(measureName).add(Float.parseFloat(values[i]));
                    }
                }
                count++;
            }
            //map.put(csvFileName, measuresMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return measuresMap;
    }
}
