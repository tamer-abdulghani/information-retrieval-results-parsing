/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.models.weka;

/**
 *
 * @author Vu Dinh Dieu
 */
public class Result {
    private int position;
    private double accuracy;
    private String algorithm;

    public int getPosition() {
        return position;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
    
    
}
