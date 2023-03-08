package edu.jsu.mcis.cs408.calculator;

import android.util.Log;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorModel extends AbstractModel {

    public static final String TAG = "CalculatorModel";
    private final String START_VALUE = "0";
    private final String sign = null;
    private final String DECIMAL_POINT = ".";
    private boolean hasDecimalPoint = false;
    private StringBuilder screen;
    private CalculatorState state;
    private CalculatorState operator;
    public CalculatorFunction currentOperator;
    private BigDecimal lhs, rhs;


    public CalculatorModel() {
        screen = new StringBuilder();
    }

    public void init() {
        state = CalculatorState.CLEAR;
        lhs = rhs = null;
        operator = null;
        hasDecimalPoint = false;
        setScreen("0");
        state = CalculatorState.LHS;
    }

    public void changeFunction(CalculatorFunction function){
        Log.i(TAG, "Function change: " + function.toString());
        try {
            switch (function) {
                case PLUS: case MINUS: case DIVIDE: case MULTIPLY: case EQUALS: case SQRT: case PERCENT:
                    changeState(CalculatorState.OP_SELECTED);
                    currentOperator = function;
                    break;

                case CLEAR:
                    changeState(CalculatorState.CLEAR);
                    init();
                    break;

                case SIGN:
                    currentOperator = function;
                    changeState(CalculatorState.OP_SELECTED);
                    break;
            }
        }
        catch (Exception e) {
            e.toString();
        }
    }

    public void setKey(String key) {
        if (key.equals(DECIMAL_POINT)) {
            if (!hasDecimalPoint) {
                appendingDigit(key);
                hasDecimalPoint = true;
            }
        } else {
            if (!hasDecimalPoint && screen.toString().equals(START_VALUE)) {
                screen.setLength(0);
                hasDecimalPoint = false;
            }
            appendingDigit(key);
        }
    }

    public void setScreen(String newText) {
        String oldText = screen.toString();
        screen.setLength(0);
        screen.append(newText);
        Log.i(TAG, "Screen Change: From " + oldText + " to " + newText);

        firePropertyChange(CalculatorController.SCREEN_PROPERTY, newText);
    }

    public void appendingDigit(String digit) {
        String oldText = screen.toString();
        screen.append(digit);
        String newText = screen.toString();
        Log.i(TAG, "Screen Change: From " + digit);
        firePropertyChange(CalculatorController.SCREEN_PROPERTY, newText);
    }

    private enum CalculatorState {
        CLEAR, LHS, OP_SELECTED, RHS, RESULT, ERROR
    }

    public void changeState(CalculatorState newState) {
        switch (state) {
            case LHS:
                lhs = new BigDecimal(screen.toString());
                rhs = null;
                break;

            case RHS:
                rhs = new BigDecimal(screen.toString());
                lhs = rhs;
                evaluate();
                break;

            case OP_SELECTED:
                operator = newState;
                changeState(CalculatorState.RHS);
                break;

            case RESULT:
                lhs = new BigDecimal(screen.toString());
                rhs = null;
                operator = null;
                break;

            case CLEAR:
                operator = null;
                if (newState == CalculatorState.CLEAR) {
                    init();
                }

            case ERROR:
                break;
        }
        state = newState;
    }

    public void evaluate() {}

}



