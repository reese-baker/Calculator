package edu.jsu.mcis.cs408.calculator;

import android.util.Log;

import java.math.BigDecimal;
import java.util.HashMap;

public class CalculatorController extends AbstractController {

    public static final String KEY_PROPERTY = "Key";
    public static final String SCREEN_PROPERTY = "Screen";
    private static final String TAG = "CalculatorController";
    private CalculatorModel model;
    public void changeKey(String newText) {
        setModelProperty(KEY_PROPERTY, newText);
    }

    public void changeScreen(String newText) {
        setModelProperty(SCREEN_PROPERTY, newText);
    }
    private void changeFunction(CalculatorFunction calculatorFunction) {}


    public CalculatorController() {
        super();
    }

    private HashMap createKeyMap() {
        HashMap<String, Character> map = new HashMap<>();
        map.put("btn0", '0');
        map.put("btn1", '1');
        map.put("btn2", '2');
        map.put("btn3", '3');
        map.put("btn4", '4');
        map.put("btn5", '5');
        map.put("btn6", '6');
        map.put("btn7", '7');
        map.put("btn8", '8');
        map.put("btn9", '9');
        map.put("btnDecimal", '.');
        return map;
    }

    private HashMap createFunctionMap() {
        HashMap<String, CalculatorFunction> map = new HashMap<>();
        map.put("btnMinus", CalculatorFunction.MINUS);
        map.put("btnEquals", CalculatorFunction.EQUALS);
        map.put("btnPlus", CalculatorFunction.PLUS);
        map.put("btnSign", CalculatorFunction.SIGN);
        map.put("btnMultiply", CalculatorFunction.MULTIPLY);
        map.put("btnDivide", CalculatorFunction.DIVIDE);
        map.put("btnPercent", CalculatorFunction.PERCENT);
        map.put("btnClear", CalculatorFunction.CLEAR);
        map.put("btnSqrt", CalculatorFunction.SQRT);
        return map;
    }

    public void processInput(String tag) {
        HashMap<String, Character> keyMap = createKeyMap();
        HashMap<String, CalculatorFunction> functionMap = createFunctionMap();

        if (keyMap.containsKey(tag)) {

            changeKey(keyMap.get(tag).toString());
        } else if (functionMap.containsKey(tag)) {

            CalculatorFunction function = functionMap.get(tag);
            changeFunction(function);
        } else {

            Log.i(TAG, "Error: " + tag);
        }
    }
}
