/**
 * File name: CalculatorModel.java
 * Author: Brandon Keohane 040719123
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: Assignment 1 p2
 * Date: Nov  2nd, 2016
 * Professor: Svillen Ranev
 * Purpose: Model holds the data of the calculator
 */

/**
 * Class holds all of the data of the calculator and provides methods to 
 * modify data.
 * @version v1.1
 * @author Brandon Keohane
 * @see javax.swing.JPanel
 * @since 1.8.0_73
 */
public class CalculatorModel {

    /**********************************************************/
    /*********************** Constants ************************/
    /**********************************************************/
    
    /** Precision constant for 1 decimal format. Value of {@value #PRECISION_0} */
    public static final int PRECISION_0 = 0;

    /** Precision constant for 2 decimal format. Value of {@value #PRECISION_00} */
    public static final int PRECISION_00 = 1;

    /** Precision constant for scientific format. Value of {@value #PRECISION_SCI} */
    public static final int PRECISION_SCI = 2;

    /** Mode to put calculator in integer state. Value of {@value #MODE_INTEGER}  */
    public static final int MODE_INTEGER = 3;

    /** Mode to put calculator in float state. Value of {@value #MODE_FLOAT} */   
    public static final int MODE_FLOAT = 4;

    /** State of adding to operand 1. Value of {@value #STATE_OPERAND_1} */
    public static final int STATE_OPERAND_1 = 5;

    /** State of adding to operand 2. Value of {@value #STATE_OPERAND_2} */
    public static final int STATE_OPERAND_2 = 6;

    /** State of adding after having a result. Value of {@value #STATE_RESULT} */
    public static final int STATE_RESULT = 8;

    /** Constant of operation multiplication. Value of {@value #OP_MULTIPLICATION} */
    public static final String OP_MULTIPLICATION = "*";

    /** Constant of operation division. Value of {@value #OP_DIVISION} */
    public static final String OP_DIVISION = "/";

    /** Constant of operation addition. Value of {@value #OP_ADDITION} */
    public static final String OP_ADDITION = "+";

    /** Constant of operation subtraction. Value of {@value #OP_SUBTRACTION} */
    public static final String OP_SUBTRACTION = "-";

    /**********************************************************/
    /*********************** Variables ************************/
    /**********************************************************/
    
    // Holds if calculator is in an error state
    private boolean errorState;
    // Holds the operation mode constants
    private int op_mode;
    // Holds the precision mode that the calculator is in
    private int precision_mode;
    // Holds the state constant 
    private int current_state;
    // String of the operation to use when performing calculation
    private String operation;
    // String of the first number to use for calculation
    private String operand1;
    // String of the second number to use for calculation
    private String operand2;

    /**********************************************************/
    /********************** Constructor ***********************/
    /**********************************************************/
    
    public CalculatorModel() {
        errorState = false;
        op_mode = MODE_FLOAT;
        precision_mode = PRECISION_00;
        current_state = STATE_OPERAND_1;
        operation = null;
        operand1 = null;
        operand2 = null;
    }
    
    /**********************************************************/
    /************************ Methods *************************/
    /**********************************************************/
    
    /**
     * Sets the operand 1 to the parameter string value.
     * @param operand String operand to copy.
     */
    public void setOperand1(String operand) {
        current_state = STATE_OPERAND_1;
        operand1 = operand;
    }

    /**
     * Sets the operand 2 to the parameter string value.
     * @param operand String operand to copy.
     */
    public void setOperand2(String operand) {
        current_state = STATE_RESULT;
        operand2 = operand;
    }

    /**
     * Sets the operation to the parameter string value.
     * @param operation String operation to copy.
     */
    public void setOperation(String operation) {
        current_state = STATE_OPERAND_2;
        // Always overrides the operation if still in operation state
        this.operation = operation;
    }

    /**
     * Sets operation mode based on constant mode value passed in parameter.
     * @param opMode operation mode constant to place the calculator in.
     */
    public void setOperationMode(final int opMode) {
        this.op_mode = opMode;
    }

    /**
     * Set method to set the floating point precision
     * @param precision operation mode constant to place the calculator in.
     */
    public void setFloatingPointPrecision(final int precision) {
        this.precision_mode = precision;
    }

    /**
     * Returns the results from the calculation in formatted form.
     * @return The results from the calculation in formatted form.
     */ 
    public String getResults() {
        // Get result from calculation
        String result = performCalculation();
        // If result was successful make operand1 equal result
        if (result != null) operand1 = result;
        // Reset the model data back to default values 
        else reset();
        
        return result;
    }

    /**
     * Changes if calculator is in error state based on parameter passed.
     * @param eState flips error state value on or off.
     */
    public void setErrorState(boolean eState) {
        this.errorState = eState;
    }

    /**
     * Returns if calculator is in error state
     * @return true if in error state, false if not.
     */
    public boolean isErrorState() {
        return errorState;
    }

    /**
     * Performs the calculation based on the mode of the calculator 
     * and returns formatted value.
     * @return Formatted string object containing calculated result
     */
    public String performCalculation() {
        // Integer mode 
        if (op_mode == MODE_INTEGER) return performIntegerCalculation();
        // Float mode
        else return performFloatCalculation();
    }
    
    /**
     * Performs the calculations by converting the operands to integers 
     * and using the operation inputed by user.
     * After performing the calculation it is formated properly in a String 
     * format and returned to the get result method.
     * @return String containing formated integer number, null if error occurs.
     */
    private String performIntegerCalculation() {
        // Operands used for calculations
        int op1 = 0, op2 = 0;
        
        // Handles number being inputted that are too big to be help in integer
        try{
            // Case if the user switched to integer half way through calculation 
            // and operand had a floating point number in it.
            if (operand1.contains(".")) {
                op1 = Integer.valueOf(operand1.substring(0, operand1.indexOf(".")));
            } else {
                op1 = Integer.valueOf(operand1);
            }
            // Case if the user switched to integer half way through calculation 
            // and operand had a floating point number in it.
            if (operand2.contains(".")) {
                op2 = Integer.valueOf(operand2.substring(0, operand2.indexOf(".")));
            } else {
                op2 = Integer.valueOf(operand2);
            }
        } catch(NumberFormatException e){
            errorState = true;
            return null;
        }
        
        if (op2 == 0) {
            reset();
            errorState = true;
            return null;
        }
        
        // Make state result to notify controller of the success
        current_state = STATE_RESULT;
        
        switch (operation) {
            case OP_MULTIPLICATION: return String.format("%d", op1 * op2);
            case OP_DIVISION:       return String.format("%d", op1 / op2);
            case OP_ADDITION:       return String.format("%d", op1 + op2);
            case OP_SUBTRACTION:    return String.format("%d", op1 - op2);
        }
        return null;
    }

    /**
     * Performs the calculations by converting the operands to doubles 
     * and using the operation inputed by user.
     * After performing the calculation it is formated properly in a String 
     * format and returned to the get result method.
     * @return String containing formated float number, null if error occurs.
     */
    private String performFloatCalculation() {
        double op1 = 0, op2 = 0;
        
        // Handle number being to big to hold in double
        try {
            op1 = Double.valueOf(operand1);
            op2 = Double.valueOf(operand2);
        } catch(NumberFormatException e){
            errorState = true;
            return null;
        }
        
        if (op2 == 0) {
            reset();
            errorState = true;
            return null;
        }
        
        // Make state result to notify controller of the success
        current_state = STATE_RESULT;
        
        // Single floating point precision
        if (precision_mode == PRECISION_0){
            switch (operation) {
                case OP_MULTIPLICATION: return String.format("%.1f", op1 * op2);
                case OP_DIVISION:       return String.format("%.1f", op1 / op2);
                case OP_ADDITION:       return String.format("%.1f", op1 + op2);
                case OP_SUBTRACTION:    return String.format("%.1f", op1 - op2);
            }
        }
        // Double floating point precision
        if (precision_mode == PRECISION_00){
            switch (operation) {
                case OP_MULTIPLICATION: return String.format("%.2f", op1 * op2);
                case OP_DIVISION:       return String.format("%.2f", op1 / op2);
                case OP_ADDITION:       return String.format("%.2f", op1 + op2);
                case OP_SUBTRACTION:    return String.format("%.2f", op1 - op2);
            }
        }
        // Scientific floating point precision
        if (precision_mode == PRECISION_SCI){
            switch (operation) {
                case OP_MULTIPLICATION: return String.format("%.6E", op1 * op2);
                case OP_DIVISION:       return String.format("%.6E", op1 / op2);
                case OP_ADDITION:       return String.format("%.6E", op1 + op2);
                case OP_SUBTRACTION:    return String.format("%.6E", op1 - op2);
            }
        }
        return null;
    }

    /**
     * Resets all the values that put the calculator to the default state
     */
    public void reset() {
        current_state = STATE_OPERAND_1;
        errorState = false;
        operation = null;
        operand1 = null;
        operand2 = null;
    }

    /**
     * Returns the current state of the calculator
     * @return the current state the calculator is in
     */
    public int getState() {
        return current_state;
    }
    
    /**
     * Returns if the calculator is in integer mode
     * @return true if in integer mode, false if not
     */
    public boolean isIntegerMode(){
        return op_mode == MODE_INTEGER;
    }
    
    /**
     * Returns constant value of the precision mode the calculator 
     * is currently in. Return values can be:
     * PRECISION_0, PRECISION_00, PRECISION_SCI
     * @return constant value of the precision mode
     */
    public int getPrecision(){
        return precision_mode;
    }
}
