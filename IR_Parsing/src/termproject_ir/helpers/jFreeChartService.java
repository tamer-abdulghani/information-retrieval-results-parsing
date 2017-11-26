/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.helpers;

import java.awt.Color;
import java.awt.Dimension;
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
import org.jfree.ui.TextAnchor;
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
        public float getRecall() {
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
        public float getPrecision() {
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
            return Float.compare(this.getRecall(), o.getRecall());
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
            LabeledXYDataset labelSource = (LabeledXYDataset) dataset;
            return labelSource.getLabel(series, item);
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

        return chart2;
    }
}
