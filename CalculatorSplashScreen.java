/**
 * File name: CalculatorSplashScreen.java
 * Author: Brandon Keohane 040719123
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: Assignment 1 p2
 * Date: Nov 2nd, 2016
 * Professor: Svillen Ranev
 * Purpose: Creates the calculator splash screen
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.ProgressMonitor;

/**
 * Class creates and displays splash screen for a specific duration
 * @version v1.1
 * @author Brandon Keohane
 * @see javax.swing.JWindow
 * @since 1.8.0_73
 */
public class CalculatorSplashScreen extends JWindow{
    
    /**
     * Duration in milliseconds that the splash screen stays on the screen
     */
    private final int duration;
    
    /**
     * Constructor to instantiate splash screen.
     * @param duration shows splash screen for this duration
     */
    public CalculatorSplashScreen(int duration){
        this.duration = duration;
    }
    
    /**
     * Instantiates splash screen components and displays the splash
     * screen for the duration specified in constructor.
     */
    public void showSplashScreen(){
        // Panels to hold ccmponents for splash screen
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        
        // Progress bar to show loading progress
        JProgressBar progress = new JProgressBar();
        progress.setMinimum(0);
        progress.setMaximum(duration);
        progress.setBackground(Color.LIGHT_GRAY);
        progress.setForeground(Color.RED);
        progress.setPreferredSize(new Dimension(10, 20));
        
        // Label to show that splash screen is loading progress
        JLabel progressLabel = new JLabel("Loading Calculator. Please wait...");
        progressLabel.setForeground(Color.WHITE);
        progressLabel.setHorizontalAlignment(JLabel.CENTER);
        progressLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        progressLabel.setOpaque(false);
        
        // Add components to the top panel
        topPanel.add(progress, BorderLayout.NORTH);
        topPanel.add(progressLabel, BorderLayout.SOUTH);
        
        // Label to hold picture for splash screen
        JLabel picture = new JLabel(new ImageIcon(getClass().getResource("giphy.gif")));
        
        // Holds title info to display
        JLabel titleInfo = new JLabel("Brandon Keohane - 040719123", JLabel.CENTER);
        titleInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,  16));
        titleInfo.setForeground(Color.WHITE);
        
        // Add components to the panel
        panel.add(topPanel, BorderLayout.NORTH );
        panel.add(picture, BorderLayout.CENTER );
        panel.add(titleInfo, BorderLayout.SOUTH  ); 
        
        // Add new panel to content pane
        getContentPane().add(panel);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        int w = 500; // width
        int h = 250; // height
        
        // Set how big the splash screen should be and position of component
        setBounds((screenSize.width-w)/2,(screenSize.height-h)/2,w, h);
        
        // All done show the splash screen
        setVisible(true);
        
        try {
            for(int i = 0; i < duration; ++i){
                // Update progress of progress bar
                progress.setValue(i);
                // Sleep for a millisecond
                Thread.sleep(1);
            }
        } catch (InterruptedException e){e.printStackTrace();};
        
        // Once done with component dispose of the frame
        dispose();
    }
    
}


