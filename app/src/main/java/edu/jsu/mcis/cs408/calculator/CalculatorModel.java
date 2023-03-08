package edu.jsu.mcis.cs408.calculator;

import android.util.Log;
import java.math.BigDecimal;

public class CalculatorModel extends AbstractModel {

    public static final String TAG = "CalculatorModel";
    private final String START_VALUE = "0";
    private final String DECIMAL_POINT = ".";
    private boolean hasDecimalPoint = false;
    private StringBuilder screen;
    private CalculatorState state;
    private CalculatorState operator;
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

    public void clearFunction(CalculatorState function) {
        operator = null;
        setScreen(function.toString());
        if (function.equals(CalculatorState.CLEAR)) {
            init();
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
        Log.i(TAG, "Screen Change: " + newText);
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

            case RHS:

            case OP_SELECTED:

            case RESULT:

            case ERROR:

        }
        state = newState;
    }
}



