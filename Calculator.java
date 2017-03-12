/**
 * File name: Calculator.java
 * Author: Brandon Keohane 040719123
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: Assignment 1 p2
 * Date: Nov  2nd, 2016
 * Professor: Svillen Ranev
 * Purpose: Runs the calculator
 */
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Class implements splash screen and calculator view
 * @author Brandon Keohane
 * @version v1.1
 * @see javax.swing.JFrame
 * @since 1.8.0_73
 */
public class Calculator {
    
    public static void main(String[] args){
        
        CalculatorSplashScreen splash = new CalculatorSplashScreen(5000);
        splash.showSplashScreen();
        
        EventQueue.invokeLater(() ->{
            // Create Frame
            JFrame frame = new JFrame("Calculator");
            // Set close operation
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Set minimum size of frame
            frame.setMinimumSize(new Dimension(330, 400));
            // Set content pane of frame
            frame.setContentPane(new CalculatorView());
            // Set location to start the frame
            frame.setLocationByPlatform(true);
            // Set visible
            frame.setVisible(true);
        });
        
    }
    
}
