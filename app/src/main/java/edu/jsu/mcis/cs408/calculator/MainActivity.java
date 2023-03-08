package edu.jsu.mcis.cs408.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import edu.jsu.mcis.cs408.calculator.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.beans.PropertyChangeEvent;



public class MainActivity extends AppCompatActivity implements AbstractView {

    public static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    private CalculatorController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* Create Controller and Model */

        controller = new CalculatorController();
        CalculatorModel model = new CalculatorModel();

        /* Register Activity View and Model with Controller */

        controller.addView(this);
        controller.addModel(model);

        /* Initialize Model to Default Values */

        model.init();

        /* Associate Click Handler with Input Buttons */

        DefaultClickHandler click = new DefaultClickHandler();
        ConstraintLayout layout = binding.layout;

        for (int i = 0; i < layout.getChildCount(); ++i) {
            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                child.setOnClickListener(click);
            }
        }
    }

    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {

        String propertyName = evt.getPropertyName();
        String propertyValue = evt.getNewValue().toString();

        Log.i(TAG, "New " + propertyName + " Value from Model: " + propertyValue);

        if (propertyName.equals(CalculatorController.SCREEN_PROPERTY)) {
            String oldPropertyValue = binding.textView.getText().toString();

            if (!oldPropertyValue.equals(propertyValue)) {
                binding.textView.setText(propertyValue);
            }
        }
    }

    class DefaultClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String tag = ((Button) v).getTag().toString();
            controller.processInput(tag);
        }
    }
}




