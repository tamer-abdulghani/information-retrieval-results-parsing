/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.helpers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ShapeUtilities;
import termproject_ir.models.MeasureValuePair;
import termproject_ir.models.Topic;
import termproject_ir.models.UserQueryPair;
import termproject_ir.models.UserTopic;

/**
 *
 * @author Tamer
 */
public class jFreeChartService {

    public XYDataset createDatasetPrecision(Topic topic, UserTopic userTopic, HashMap<UserQueryPair, ArrayList<MeasureValuePair>> map) {

        LabeledXYDataset ds = new LabeledXYDataset("Recall on Number of Documents");
        int[] nbDocs = new int[]{1, 2, 5, 10, 15, 20, 30, 50, 100, 200, 500, 1000};
        for (Map.Entry<UserQueryPair, ArrayList<MeasureValuePair>> element : map.entrySet()) {
            if (element.getKey().getTopicID() == topic.getNumber() && element.getKey().getUserID() == userTopic.getUserId()) {
                for (int i : nbDocs) {
                    for (MeasureValuePair f : element.getValue()) {
                        if (f.getName().startsWith("P_") && i == Integer.parseInt(f.getName().split("_")[1])) {
                            int nbDoc = Integer.parseInt(f.getName().split("_")[1]);
                            float precisionValue = f.getValue();
                            ds.add(nbDoc, precisionValue, f.getName());
                        }
                    }
                }

            }
        }
        return ds;
    }

    public XYDataset createDatasetRecall(Topic topic, UserTopic userTopic, HashMap<UserQueryPair, ArrayList<MeasureValuePair>> map) {

        LabeledXYDataset ds = new LabeledXYDataset("Recall on Number of Documents");
        int[] nbDocs = new int[]{1, 2, 5, 10, 15, 20, 30, 50, 100, 200, 500, 1000};
        for (Map.Entry<UserQueryPair, ArrayList<MeasureValuePair>> element : map.entrySet()) {
            if (element.getKey().getTopicID() == topic.getNumber() && element.getKey().getUserID() == userTopic.getUserId()) {
                for (int i : nbDocs) {
                    for (MeasureValuePair f : element.getValue()) {
                        if (f.getName().startsWith("recall_") && i == Integer.parseInt(f.getName().split("_")[1])) {
                            int nbDoc = Integer.parseInt(f.getName().split("_")[1]);
                            float precisionValue = f.getValue();
                            ds.add(nbDoc, precisionValue, "R_" + f.getName().split("_")[1]);
                        }
                    }
                }

            }
        }
        return ds;
    }

    public class XYRecallPrecision implements Comparable<XYRecallPrecision> {

        public XYRecallPrecision(float x, float y, String queryId) {
            this.recall = x;
            this.precision = y;
            this.queryId = queryId;

        }
        private float recall;
        private float precision;
        private String queryId;

        /**
         * @return the recall
         */
        public Float getRecall() {
            return recall;
        }

        /**
         * @param recall the recall to set
         */
        public void setRecall(float recall) {
            this.recall = recall;
        }

        /**
         * @return the precision
         */
        public Float getPrecision() {
            return precision;
        }

        /**
         * @param precision the precision to set
         */
        public void setPrecision(float precision) {
            this.precision = precision;
        }

        @Override
        public int compareTo(XYRecallPrecision o) {
            //return Float.compare(this.getRecall(), o.getRecall());
            return this.getPrecision().compareTo(o.getPrecision());

            /*if (Float.compare(this.getRecall(), o.getRecall()) > 0 && Float.compare(this.getPrecision(), o.getPrecision()) > 0) {
                System.out.println("12");
                return 1;
            } else if (Float.compare(this.getRecall(), o.getRecall()) > 0 && Float.compare(this.getPrecision(), o.getPrecision()) < 0) {
                System.out.println("123");
                return 1;
            } else if (Float.compare(this.getRecall(), o.getRecall()) < 0 && Float.compare(this.getPrecision(), o.getPrecision()) > 0) {
                System.out.println("1234");
                return -1;
            } else if (Float.compare(this.getRecall(), o.getRecall()) < 0 && Float.compare(this.getPrecision(), o.getPrecision()) < 0) {
                System.out.println("12345");
                return -1;
            } else if (Float.compare(this.getRecall(), o.getRecall()) == 0 && Float.compare(this.getPrecision(), o.getPrecision()) == 0) {
                return 0;
            }*/
 /*int result = Float.compare(this.getRecall(), o.getRecall());
            if (result == 0) {
                // both X are equal -> compare Y too
                result = Float.compare(this.getPrecision(), o.getPrecision());
            }
            
            return result;
             */
            //System.out.println("heloooooooooooo");
            // return 0;
        }

        /**
         * @return the queryId
         */
        public String getQueryId() {
            return queryId;
        }

        /**
         * @param queryId the queryId to set
         */
        public void setQueryId(String queryId) {
            this.queryId = queryId;
        }

    }

    public XYDataset createDatasetPrecisionRecall(int nbDoc, HashMap<String, ArrayList<Float>> map, boolean orderXaxis) {
        LabeledXYDataset ds = new LabeledXYDataset("Queries Plotting");

        ArrayList<Float> precisionValues = map.get("P_" + nbDoc);
        ArrayList<Float> recallValues = map.get("recall_" + nbDoc);

        ArrayList<XYRecallPrecision> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new XYRecallPrecision(recallValues.get(i), precisionValues.get(i), "Q_" + (i + 1)));
        }

        if (orderXaxis) {
            Collections.sort(list);
        }

        for (int i = 0; i < 50; i++) {
            ds.add(list.get(i).getRecall(), list.get(i).getPrecision(), list.get(i).getQueryId());
        }

        return ds;
    }

    public XYDataset createAllSystemsPrecisionRecall(int nbDoc, HashMap<String, HashMap<String, ArrayList<Float>>> map, boolean orderXaxis) {
        final XYSeriesCollection ds = new XYSeriesCollection();
        boolean orderNow = true;
        for (Map.Entry<String, HashMap<String, ArrayList<Float>>> ele : map.entrySet()) {

            ArrayList<Float> precisionValues = ele.getValue().get("P_" + nbDoc);
            ArrayList<Float> recallValues = ele.getValue().get("recall_" + nbDoc);

            ArrayList<XYRecallPrecision> list = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                list.add(new XYRecallPrecision(recallValues.get(i), precisionValues.get(i), ""));
            }

            if (orderXaxis && orderNow) {
                System.out.println("000000:" + list.get(10).getRecall());
                Collections.sort(list);
                System.out.println("1111111:" + list.get(10).getRecall());
                list.stream().forEach(x -> System.out.println("" + x.getRecall() + "," + x.getPrecision()));
                orderNow = false;
            }
            System.out.println("asdasdas::::" + ele.getKey());
            final XYSeries series = new XYSeries(ele.getKey().replace(".csv", ""));
            for (int i = 0; i < 50; i++) {

                series.add(list.get(i).getRecall(), list.get(i).getPrecision());

            }
            ds.addSeries(series);
        }

        return ds;
    }

    public XYDataset createAVGSystemsPrecisionRecall(ArrayList<Integer> docsNb, HashMap<String, HashMap<String, ArrayList<Float>>> map, boolean b) {
        final XYSeriesCollection ds = new XYSeriesCollection();
        for (Map.Entry<String, HashMap<String, ArrayList<Float>>> ele : map.entrySet()) {
            ArrayList<Float> avgPrecisionValues = new ArrayList<>();
            ArrayList<Float> avgRecallValues = new ArrayList<>();
            for (Integer docNb : docsNb) {
                ArrayList<Float> precisionValues = ele.getValue().get("P_" + docNb);
                avgPrecisionValues.add((float) precisionValues.stream().mapToDouble(a -> a).average().getAsDouble());

                ArrayList<Float> recallValues = ele.getValue().get("recall_" + docNb);
                avgRecallValues.add((float) recallValues.stream().mapToDouble(a -> a).average().getAsDouble());

            }
            ArrayList<XYRecallPrecision> list = new ArrayList<>();
            for (int i = 0; i < docsNb.size(); i++) {
                list.add(new XYRecallPrecision(avgRecallValues.get(i), avgPrecisionValues.get(i), "At " + docsNb.get(i) + " docs"));
            }

            final XYSeries series = new XYSeries(ele.getKey().replace(".csv", ""));
            for (int i = 0; i < docsNb.size(); i++) {

                series.add(list.get(i).getRecall(), list.get(i).getPrecision());

            }
            ds.addSeries(series);
        }

        return ds;
    }

    private static class LabeledXYDataset extends AbstractXYDataset {

        private List<Number> x = new ArrayList<Number>();
        private List<Number> y = new ArrayList<Number>();
        private List<String> label = new ArrayList<String>();
        private String setLabel;

        public LabeledXYDataset(String labelName) {
            this.setLabel = labelName;
        }

        public void add(double x, double y, String label) {
            this.x.add(x);
            this.y.add(y);
            this.label.add(label);
        }

        public String getLabel(int series, int item) {
            return label.get(item);
        }

        @Override
        public int getSeriesCount() {
            return 1;
        }

        @Override
        public Comparable getSeriesKey(int series) {
            return this.setLabel;
        }

        @Override
        public int getItemCount(int series) {
            return label.size();
        }

        @Override
        public Number getX(int series, int item) {
            return x.get(item);
        }

        @Override
        public Number getY(int series, int item) {
            return y.get(item);
        }
    }

    private static class LabelGenerator implements XYItemLabelGenerator {

        @Override
        public String generateLabel(XYDataset dataset, int series, int item) {
            try {
                LabeledXYDataset labelSource = (LabeledXYDataset) dataset;
                return labelSource.getLabel(series, item);
            } catch (Exception e) {
                return "";
            }
        }
    }

    public static JFreeChart createChart(final XYDataset dataset, String xAxis, String yAxis) {
        NumberAxis domain = new NumberAxis(xAxis);
        NumberAxis range = new NumberAxis(yAxis);
        domain.setAutoRangeIncludesZero(false);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setBaseItemLabelGenerator(new LabelGenerator());
        renderer.setBaseItemLabelPaint(Color.green.darker());
        renderer.setBasePositiveItemLabelPosition(
                new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        renderer.setBaseItemLabelFont(
                renderer.getBaseItemLabelFont().deriveFont(14f));
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        XYPlot plot = new XYPlot(dataset, domain, range, renderer);
        JFreeChart chart = new JFreeChart(
                xAxis + " " + yAxis, JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        return chart;
    }

    public static JFreeChart createScatterPlot(final XYDataset dataset, String xAxis, String yAxis) {
        /*NumberAxis domain = new NumberAxis(xAxis);
        NumberAxis range = new NumberAxis(yAxis);
        domain.setAutoRangeIncludesZero(false);
        XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setBaseItemLabelGenerator(new LabelGenerator());
        renderer.setBaseItemLabelPaint(Color.green.darker());
        renderer.setBasePositiveItemLabelPosition(
                new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        renderer.setBaseItemLabelFont(
                renderer.getBaseItemLabelFont().deriveFont(14f));
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        renderer.setBaseShape(ShapeUtilities.createDiagonalCross(3, 1));
        XYPlot plot = new XYPlot(dataset, domain, range, renderer);
        JFreeChart chart = new JFreeChart(
                xAxis + " " + yAxis, JFreeChart.DEFAULT_TITLE_FONT, plot, false);
         */
        JFreeChart chart2 = ChartFactory.createScatterPlot(
                "Queries Plot", // chart title
                xAxis, // x axis label
                yAxis, // y axis label
                dataset, // data  ***-----PROBLEM------***
                PlotOrientation.VERTICAL,
                true, // include legend
                false, // tooltips
                false // urls
        );
        /*
        Shape cross = ShapeUtilities.createDiagonalCross(3, 1);
        XYPlot xyPlot = (XYPlot) chart2.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesShape(0, cross);
        renderer.setSeriesPaint(0, Color.red);
        renderer.setBaseShape(ShapeUtilities.createDiagonalCross(3, 1));
         */
        return chart2;
    }
}
