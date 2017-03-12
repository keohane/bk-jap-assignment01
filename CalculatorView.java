/**
 * File name: CalculatorView.java
 * Author: Brandon Keohane 040719123
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: Assignment 1 p2
 * Date: Nov  2nd, 2016
 * Professor: Svillen Ranev
 * Purpose: Model holds the data of the calculator
 */

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Class instantiates calculator panel with all components
 * @version v1.1
 * @author Brandon Keohane
 * @see javax.swing.JPanel
 * @since 1.8.0_73
 */
public class CalculatorView extends JPanel {

    /** 
     * Specifies the border size as the value of {@value #B_SIZE} 
     * to surround the panel.
     */
    private static final int B_SIZE = 5;
    
    /**
     * Default label for Integer mode button is value {@value #LABEL_INT}
     */
    private static final String LABEL_INT = "Int";
    
    /**
     * Default label for 0 mode button is value {@value #LABEL_0}
     */
    private static final String LABEL_0 = ".0";
    
    /**
     * Default label for 00 mode button is value {@value #LABEL_00}
     */
    private static final String LABEL_00 = ".00";
    
    /**
     * Default label for 0 mode button is value {@value #LABEL_SCI}
     */
    private static final String LABEL_SCI = "Sci";
    
    /**
     * Default label for equals button is value {@value #LABEL_EQUALS}
     */
    private static final String LABEL_EQUALS = "=";
    
    /**
     * Default label for clear button is value {@value #LABEL_C}
     */
    private static final String LABEL_C = "C";
    
    /** Text field to display calculations */
    private JTextField display;
    
    /** Label to show which error state calculator is in */
    private JLabel error;
    
    /** Decimal button used to do calculations using floating point numbers */
    private JButton dotButton;
    
    /** Radio button for double decimal precision */
    private JRadioButton rbPoint00;

    /**
     * Constructor instantiates calculator view
     */
    public CalculatorView() {
        // Set 10 pixel black border
        setBorder(BorderFactory.createMatteBorder(B_SIZE, B_SIZE, B_SIZE, B_SIZE, Color.BLACK));
        setLayout(new BorderLayout());

        // Handles button pushes
        CalculatorModel model = new CalculatorModel();
        Controller handler = new Controller(model);
        
        // Button names for all grid buttons
        String buttonNames[] = new String[]{
            "7", "8", "9", CalculatorModel.OP_DIVISION, 
            "4", "5", "6", CalculatorModel.OP_MULTIPLICATION, 
            "1", "2", "3", CalculatorModel.OP_SUBTRACTION, 
            "\u00B1", "0", ".", CalculatorModel.OP_ADDITION
        };
        // Text color array
        Color colors[] = new Color[]{
            Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW,
            Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW,
            Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW,
            Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW
        };
        int kEvents[] = new int[]{
            KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_SLASH,
            KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_PERIOD,
            KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_COMMA,
            KeyEvent.VK_N, KeyEvent.VK_0, KeyEvent.VK_D, KeyEvent.VK_M
        };

        /*
            3 border layouts
            2 flow layouts
            1 grid layout
            1 horizontal box
         */
        // JPanels
        // 1 Grid Panel
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 4, 4)); // #1
        // 2 Border Panels plus this panel
        JPanel topBorderWrapper = new JPanel(new BorderLayout(0, 0)); // #1
        JPanel radioBorderWrapper = new JPanel(new BorderLayout(0, 0)); // #2 this panel #3
        // 2 Flow Layouts
        JPanel displayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // #1
        JPanel blackBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // #2
        // 1 Horizontal Box
        JPanel hboxPanel = new JPanel(); // #1
        hboxPanel.setLayout(new BoxLayout(hboxPanel, BoxLayout.X_AXIS));
        hboxPanel.setOpaque(false);

        // JButtons
        JButton clear = new JButton(LABEL_C);
        JButton equals = new JButton(LABEL_EQUALS);
        JButton backspace = new JButton(String.valueOf("\u2190"));

        // JCheckButton
        JCheckBox greenBox = new JCheckBox(LABEL_INT);
        greenBox.setActionCommand(LABEL_INT);
        greenBox.addActionListener(handler);
        this.getActionMap().put(LABEL_INT, new CustomAction(greenBox));
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), LABEL_INT);

        // JRadioButtons
        ButtonGroup bGroup = new ButtonGroup();
        JRadioButton rbPoint0 = new JRadioButton(LABEL_0);
        rbPoint0.setOpaque(false);
        rbPoint0.setActionCommand(LABEL_0);
        rbPoint0.addActionListener(handler);
        this.getActionMap().put(LABEL_0, new CustomAction(rbPoint0));
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0), LABEL_0);
        
        rbPoint00 = new JRadioButton(LABEL_00);
        rbPoint00.setOpaque(false);
        rbPoint00.setActionCommand(LABEL_00);
        rbPoint00.addActionListener(handler);
        this.getActionMap().put(LABEL_00, new CustomAction(rbPoint00));
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), LABEL_00);
        
        JRadioButton rbScience = new JRadioButton(LABEL_SCI);
        rbScience.setOpaque(false);
        rbScience.setActionCommand(LABEL_SCI);
        rbScience.addActionListener(handler);
        this.getActionMap().put(LABEL_SCI, new CustomAction(rbScience));
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), LABEL_SCI);

        /* 
            Error Label
         */
        // Create error textfield
        error = new JLabel("F", JLabel.CENTER);
        // Must be 25 by 25
        error.setPreferredSize(new Dimension(25, 25));
        // Set opaque before applying background color
        error.setOpaque(true);
        // Set background yellow default mode
        error.setBackground(Color.yellow);

        /*
            Display TextField
         */
        // Create display
        display = new JTextField("0.0");
        // Set height to 30
        display.setPreferredSize(new Dimension(500, 30));
        // Set background white
        display.setBackground(Color.WHITE);
        // Set 16 columns wide
        display.setColumns(16);
        // Set not editable
        display.setEditable(false);
        // Set alignment right
        display.setHorizontalAlignment(JTextField.RIGHT);

        /*
            Backspace Button
         */
        // Initialize backspace button
        // Set action command viewed by handler
        backspace.setActionCommand("backspace");
        // Set dimensions to 25 by 25
        backspace.setPreferredSize(new Dimension(25, 25));
        // Set transparent
        backspace.setContentAreaFilled(false);
        // Mnemonic Alt B
        backspace.setMnemonic('B');
        // Set tooltip
        backspace.setToolTipText("Backspace");
        // Set icon color
        backspace.setForeground(Color.red);
        // Set background color
        backspace.setBackground(Color.white);
        // Set border 1 px red
        backspace.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        // Set listener
        backspace.addActionListener(handler);
        this.getActionMap().put("Backspace", new CustomAction(backspace));
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACKSPACE, 0), "Backspace");
        

        // Add all compontents to display panel
        displayPanel.add(error.getName(), error);
        displayPanel.add(display.getName(), display);
        displayPanel.add(backspace.getName(), backspace);

        /*
            Clear Button
         */
        // Initialize Clear button
        clear.setActionCommand(LABEL_C);
        clear.addActionListener(handler);
        clear.setBackground(Color.RED);
        clear.setPreferredSize(new Dimension(45, clear.getHeight()));
        this.getActionMap().put(LABEL_C, new CustomAction(clear));
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), LABEL_C);

        /*
            Equals Button
         */
        // Initialize Equals button
        equals.setActionCommand(LABEL_EQUALS);
        equals.addActionListener(handler);
        equals.setBackground(Color.YELLOW);
        equals.setPreferredSize(new Dimension(45, clear.getHeight()));
        this.getActionMap().put(LABEL_EQUALS, new CustomAction(equals));
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), LABEL_EQUALS);

        // Initialize Panels for top display and check box panel
        displayPanel.setBackground(Color.WHITE); // Set background white
        blackBoxPanel.setBackground(Color.BLACK); // Set background black

        // Create check box
        greenBox.setBackground(Color.GREEN);

        /*
            Group of radio buttons
         */
        // Create group of radio buttons
        bGroup.add(greenBox);
        bGroup.add(rbPoint0);
        bGroup.add(rbPoint00);
        bGroup.add(rbScience);
        rbPoint00.setSelected(true); //  Default selection

        radioBorderWrapper.setBackground(Color.YELLOW);
        radioBorderWrapper.add(rbPoint0, BorderLayout.WEST);
        radioBorderWrapper.add(rbPoint00, BorderLayout.CENTER);
        radioBorderWrapper.add(rbScience, BorderLayout.EAST);

        // HBox for laying out Clear Grid and equals
        hboxPanel.add(greenBox);
        hboxPanel.add(Box.createHorizontalStrut(18));
        hboxPanel.add(radioBorderWrapper);

        // Add green check box and radio button group to black box
        blackBoxPanel.add(hboxPanel);

        gridPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        for (int index = 0; index < buttonNames.length; ++index) {
            if (index == 14) {
                dotButton = createButton(buttonNames[index],buttonNames[index], colors[index], Color.BLUE, handler);
                gridPanel.add(dotButton);
            } else {
                gridPanel.add(createButton(buttonNames[index],buttonNames[index], colors[index], Color.BLUE, handler));
            }
            this.getActionMap().put(buttonNames[index], new CustomAction((JButton)gridPanel.getComponent(index)));
            this.getInputMap().put(KeyStroke.getKeyStroke(kEvents[index], 0), buttonNames[index]);
        }

        topBorderWrapper.add(displayPanel, BorderLayout.NORTH);
        topBorderWrapper.add(blackBoxPanel, BorderLayout.SOUTH);

        add(topBorderWrapper, BorderLayout.NORTH);
        add(clear, BorderLayout.WEST);
        add(gridPanel, BorderLayout.CENTER);
        add(equals, BorderLayout.EAST);
    }

    /**
     * Creates a button and returns the button created.
     *
     * @param text Text displayed on button
     * @param ac Action command string
     * @param fg Foreground color ie text color
     * @param bg Button color
     * @param handler Handles button events created with this method
     * @return returns button with specified parameters
     */
    private JButton createButton(String text, String ac,
            Color fg, Color bg, ActionListener handler) {
        // Create button with name
        JButton button = new JButton(text);
        // Set action command to listen for
        if (ac != null) {
            button.setActionCommand(ac);
        }
        // Set font size to 20
        button.setFont(new Font(
                button.getFont().getFamily(),
                button.getFont().getStyle(),
                20
        ));
        
        // Set foreground color of button
        button.setForeground(fg);
        // Set background color of button
        button.setBackground(bg);
        // Set controller that will listen for events
        button.addActionListener(handler);
        // Return created button
        return button;
    }

    /**
     * Shows or hides the decimal based on the mode the calculator is in.
     * @param enabled true if decimal should be enabled, false if not.
     */
    public void showDecimal(boolean enabled) {
        if (enabled) {
            // Re enable button
            dotButton.setEnabled(enabled);
            // Set background back from black to blue
            dotButton.setBackground(Color.blue);
            // Set error text
            error.setText("F");
            // Set error background
            error.setBackground(Color.yellow);
        } else {
            // Re enable button
            dotButton.setEnabled(enabled);
            // Set background back from black to blue
            dotButton.setBackground(Color.black);
            // Set error text
            error.setText("I");
            // Set error background
            error.setBackground(Color.green);
        }
    }

    /**
     * Class is the controller for the calculator view and model. It 
     * provides algorithms to handle the state at which the calculator is in.
     * @version v1.0
     * @author Brandon Keohane
     * @since 1.8.0_73
     */
    private class Controller implements ActionListener {

        // Reference to model class instance of mvc pattern
        CalculatorModel model;
        // Flag for the controller that the decimal has already been pushed
        boolean decimalFlag = false;
        // Flag for the calculator if it should override the display
        boolean overrideFlag = true;
        // Flag for if calculator is in default state
        boolean defaultFlag = true;
        // Int flag for bonus 2
        boolean intFlag = false;

        /**
         * Constructor for controller that gets an instance of a model.
         * @param model Model holding data for the controller and to 
         *              perform calculations
         */
        public Controller(CalculatorModel model) {
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean setErrorDisplay = false;
            switch (e.getActionCommand()) {
                case "1": case "2": case "3":
                case "4": case "5": case "6":
                case "7": case "8": case "9":
                case "0":
                    if (!model.isErrorState()) {
                        handleNumberAction(e.getActionCommand());
                        // If was put in error state after handling action
                        if (model.isErrorState()) {
                            setErrorDisplay = true;
                        }
                    }
                    break;
                case LABEL_EQUALS:
                    if (!model.isErrorState()) {
                        handleEqualsAction();
                        // If was put in error state after handling action
                        if (model.isErrorState()) {
                            setErrorDisplay = true;
                        }
                    }
                    break;
                case "/": case "*": case "+": case "-":
                    if (!model.isErrorState()) {
                        handleOperationAction(e.getActionCommand());
                        decimalFlag = false;
                        // If was put in error state after handling action
                        if (model.isErrorState()) {
                            setErrorDisplay = true;
                        }
                    }
                    break;
                case ".":
                    if (!model.isErrorState()) {
                        if (!decimalFlag){
                            if (overrideFlag){
                                display.setText(".");
                                overrideFlag = false;
                                defaultFlag = false;
                            }
                            else {
                                display.setText(display.getText().concat("."));
                            }
                            decimalFlag = true;
                        }
                    }
                    break;
                case "backspace":
                    if (!model.isErrorState()) {
                        // If not in default state for the display
                        if (!overrideFlag){
                            // If length is greater than 1 remove letter from end
                            if (display.getText().length() > 1){
                                // If character being deleted is a decimal reset flag
                                if(display.getText().endsWith(".")) decimalFlag = false;
                                // Set display to substring of display text
                                display.setText(display.getText().substring(0, display.getText().length()-1));
                                // If there is only a negative sign left
                                if (display.getText().length() == 1 && display.getText().contains("-")){
                                    // Reset display to default 
                                    setDefaultDisplay();
                                    // Reset the flag to being in default display
                                    overrideFlag = true;
                                    decimalFlag = false;
                                    defaultFlag = true;
                                }
                            }
                            // Only one letter left so just delete and reset
                            else {
                                // Reset display to default 
                                setDefaultDisplay();
                                // Reset the flag to being in default display
                                overrideFlag = true;
                                decimalFlag = false;
                                defaultFlag = true;
                            }
                        }
                        // If was put in error state after handling action
                        if (model.isErrorState()) {
                            setErrorDisplay = true;
                        }
                    }
                    break;
                // Plus minus symbol
                case "\u00B1":
                    if (!model.isErrorState()) {
                        if (display.getText().startsWith("-")){
                            display.setText(display.getText().substring(1));
                        } else {
                            display.setText("-".concat(display.getText()));
                        }
                        // If was put in error state after handling action
                        if (model.isErrorState()) {
                            setErrorDisplay = true;
                        }
                    }
                    break;
                case LABEL_C:
                    // If in error state
                    if (model.isErrorState()) {
                        showDecimal(!model.isIntegerMode());
                    }
                    // Reset display to current modes default value
                    setDefaultDisplay();
                    // Reset model data
                    model.reset();
                    // Reset to default
                    overrideFlag = true;
                    decimalFlag = false;
                    defaultFlag = true;
                    break;
                case LABEL_0:
                    intFlag = false;
                    model.setFloatingPointPrecision(CalculatorModel.PRECISION_0);
                    model.setOperationMode(CalculatorModel.MODE_FLOAT);
                    if (!model.isErrorState()) {
                        showDecimal(true);
                        // If there is a number to convert
                        if (!defaultFlag){
                            setDisplay();
                        } 
                        // Else in default state either display 0 or 0.0
                        else {
                            setDefaultDisplay();
                        }
                    }
                    break;
                case LABEL_00:
                    intFlag = false;
                    model.setFloatingPointPrecision(CalculatorModel.PRECISION_00);
                    model.setOperationMode(CalculatorModel.MODE_FLOAT);
                    if (!model.isErrorState()) {
                        showDecimal(true);
                        // If there is a number to convert
                        if (!defaultFlag){
                            setDisplay();
                        } 
                        // Else in default state either display 0 or 0.0
                        else {
                            setDefaultDisplay();
                        }
                    }
                    break;
                case LABEL_SCI:
                    intFlag = false;
                    model.setFloatingPointPrecision(CalculatorModel.PRECISION_SCI);
                    model.setOperationMode(CalculatorModel.MODE_FLOAT);
                    if (!model.isErrorState()) {
                        showDecimal(true);
                        // If there is a number to convert
                        if (!defaultFlag){
                            setDisplay();
                        } 
                        // Else in default state either display 0 or 0.0
                        else {
                            setDefaultDisplay();
                        }
                    }
                    break;
                case LABEL_INT:
                    // Unchecks the check box if flag is thrown
                    if (intFlag){
                        rbPoint00.doClick();
                        break;
                    }
                    // Set flag to being checked
                    intFlag = true;
                    // Set calculator to integer mode
                    model.setOperationMode(CalculatorModel.MODE_INTEGER);
                    if (!model.isErrorState()) {
                        showDecimal(false);
                        // If there is a number to convert
                        if (!defaultFlag){
                            setDisplay();
                        } 
                        // Else in default state either display 0 or 0.0
                        else {
                            setDefaultDisplay();
                        }
                    }
                    break;

                default:
                    break;
            }
            
            if (setErrorDisplay) {
                error.setText("E");
                error.setBackground(Color.red);
                display.setText("--");
            }
        }
        
        /**
         * Handles the string containing the number pressed by user. If the
         * calculator can handle the action pressed then it will but if not it
         * will place the calculator in error state.
         * @param s number being inputed from button click.
         */
        public void handleNumberAction(String s){
            switch (model.getState()){
                /* State Operand 1 */
                case CalculatorModel.STATE_OPERAND_1:
                    // If the next number should override the display
                    if (overrideFlag) {
                        display.setText(s);
                        // Reset flag to be append next time
                        overrideFlag = false;
                        defaultFlag = false;
                        break;
                    }
                    // Else append to the display
                    display.setText(display.getText().concat(s));
                    break;
                /* State Operand 2 */
                case CalculatorModel.STATE_OPERAND_2:
                    // If should override display
                    if (overrideFlag) {
                        // Override and set as new number
                        display.setText(s);
                        // Turn flag off
                        overrideFlag = false;
                        break;
                    }
                    // Concat to display
                    display.setText(display.getText().concat(s));
                    break;
                /* State Result */
                case CalculatorModel.STATE_RESULT:
                    // Reset values and start over in state operand 1
                    model.reset();
                    // Set the display to new number pushed
                    display.setText(s);
                    break;
                // Should not happen
                default:                  
                    model.setErrorState(true);
                    break;
            }
        }
        
        /**
         * Handles the string containing the operation pressed by user. If the
         * calculator can handle the action pressed then it will but if not it
         * will place the calculator in error state.
         * @param s operation being inputed from button click.
         */
        public void handleOperationAction(String s){
            switch (model.getState()){
                /* State Operand 1 */
                case CalculatorModel.STATE_OPERAND_1:
                    // Reset flag to override display for next button push
                    overrideFlag = true;
                    // Set the operand to the current display text
                    model.setOperand1(display.getText());
                    // Set the operation to the one that was pushed
                    // This call moves the state to operation state
                    model.setOperation(s);
                    break;
                /* State Operand 2 */
                case CalculatorModel.STATE_OPERAND_2:
                    if (overrideFlag) {
                        model.setOperation(s);
                        break;
                    }
                    break;
                /* State Result */
                case CalculatorModel.STATE_RESULT:
                    // Set operation
                    model.setOperation(s);
                    // Set flag to override display next number button push
                    overrideFlag = true;
                    break;
                // Should not happen
                default:                  
                    model.setErrorState(true);
                    break;
            }
        }

        /**
         * Handles the string containing the equals button pressed by user. 
         * If the calculator can handle the action pressed then it will but 
         * if not it will place the calculator in error state.
         */
        public void handleEqualsAction() {
            switch (model.getState()) {
                // State Operand 2
                case CalculatorModel.STATE_OPERAND_2:
                    // Set operand 2 as display string
                    model.setOperand2(display.getText());
                    // Get value from calculation
                    String result = model.getResults();
                    // If result was successful 
                    if (result != null) {
                        // Set the display as the result
                        display.setText(result);
                        break;
                    }
                    // Result was bad set error state
                    model.setErrorState(true);
                    break;
                
                // All other state should put in error state
                default:                  
                    model.setErrorState(true);
                    break;
            }
        }
        
        /**
         * Resets the display to the default display based on the mode 
         * the calculator is currently in.
         */
        public void setDefaultDisplay(){
            if (model.isIntegerMode()){
                display.setText("0");
            }
            else {
                display.setText("0.0");
            }
            overrideFlag = true;
        }
        
        /**
         * Resets the display by updating string with new reformatted number.
         * This methods is called when a new precision is selected.
         */
        public void setDisplay(){
            if (model.isIntegerMode()){
                display.setText(String.format("%d", (int)Double.parseDouble(display.getText())));
            }
            else {
                switch (model.getPrecision()){
                    case CalculatorModel.PRECISION_0: 
                        display.setText(String.format("%.1f", Double.parseDouble(display.getText()))); break;
                    case CalculatorModel.PRECISION_00: 
                        display.setText(String.format("%.2f", Double.parseDouble(display.getText()))); break;
                    case CalculatorModel.PRECISION_SCI: 
                        display.setText(String.format("%.6E", Double.parseDouble(display.getText()))); break;
                }
            }
        }

    }
    
    /**
     * Class is custom action for the keyboard inputs, it automatically 
     * clicks the button when the correct key is pressed.
     * @version v1.0
     * @author Brandon Keohane
     * @see javax.swing.AbstractAction
     * @since 1.8.0_73
     */
    private class CustomAction extends AbstractAction {
        
        // Holds the reference to the object to click
        AbstractButton button;
        
        /**
         * Constructor takes a button as parameter and saves reference
         * @param button Button to click once keyboard key is clicked
         */
        public CustomAction(AbstractButton button){
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            button.doClick();
        }
        
    }

}
