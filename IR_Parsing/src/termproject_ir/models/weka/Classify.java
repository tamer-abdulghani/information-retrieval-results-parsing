/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.models.weka;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.core.FastVector;
import weka.core.Instances;

/**
 *
 * @author Vu Dinh Dieu
 */
public class Classify {
//    public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds){
//        Instances[][] splitData = new Instances[2][numberOfFolds];
//        for(int i = 0; i< numberOfFolds; i++){
//            splitData[0][i] = data.trainCV(numberOfFolds, i);
//            splitData[1][i] = data.testCV(numberOfFolds, i);
//        }
//        return splitData;
//    }
    
    public static Instances crossValidationSplit(Instances data, int numberOfFolds){
        Instances[] splitData = new Instances[numberOfFolds];
        for(int i = 0; i< numberOfFolds; i++){
            splitData[0] = data.trainCV(numberOfFolds, i);
        }
        return splitData[0];
    }
    
    public static double calculateAccuracy(FastVector prediction){
        double corr = 0;
        for(int i = 0; i< prediction.size(); i++){
            NominalPrediction np = (NominalPrediction)prediction.elementAt(i);
            if(np.predicted() == np.actual()){
                corr++;
            }
        }
        return corr*100/prediction.size();
    }
    
    public static Evaluation classify(Classifier model, Instances trainingSet) throws Exception {
        Evaluation evaluation = new Evaluation(trainingSet);
        model.buildClassifier(trainingSet);
        //evaluation.evaluateModel(model, testingSet);
        evaluation.evaluateModel(model, trainingSet);
        
        return evaluation;
    }
}
