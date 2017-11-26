/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package termproject_ir.models;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import termproject_ir.controllers.mainController;
import termproject_ir.gui.mainFrame;

/**
 *
 * @author Tamer
 */
public class TermProject_IR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        // TODO code application logic here
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        mainController controller = new mainController();
        controller.startGUI();
    }
}
