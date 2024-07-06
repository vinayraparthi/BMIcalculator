package com.vinay.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultTest;
    private RadioButton maleBtn;
    private RadioButton femaleBtn;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateBtnEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }
    private void findViews(){
        resultTest = findViewById(R.id.text_view_result);
        maleBtn = findViewById(R.id.radio_btn_male);
        femaleBtn = findViewById(R.id.radio_btn_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateBtnEditText = findViewById(R.id.btn_calculate);
    }

    private void setupButtonClickListener() {
        calculateBtnEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ageStr = ageEditText.getText().toString();
                String feetStr = feetEditText.getText().toString();
                String inchesStr = inchesEditText.getText().toString();
                String weightStr = weightEditText.getText().toString();
                if(ageStr.isEmpty() || feetStr.isEmpty() || inchesStr.isEmpty() || weightStr.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fill the required fields!!",Toast.LENGTH_LONG).show();
                }else{

                    double bmi = calculateBmi();
                    String ageText = ageEditText.getText().toString();
                    int age = Integer.parseInt(ageText);

                    if (age >= 18) {
                        DisplayResult(bmi);
                    } else
                        displayGuidance(bmi);

                }
            }
        });
    }

    private double calculateBmi() {

        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightTest = weightEditText.getText().toString();


        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightTest);

        int totalInches = (feet*12)+inches;

        //Height in meters is the inches multiplied by 0.024
        double heightInMeters = totalInches *0.0254;

        //BMI formula = weight in kg divided by height in meters squared
        return weight/(heightInMeters* heightInMeters);
    }


    private void DisplayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTestResult = myDecimalFormatter.format(bmi);
        String fullResultString;
        if(bmi < 18.5){
            //Display underWeight
            fullResultString = bmiTestResult +" - You are underweight";
        }else if(bmi > 25){
            //Display overWeight
            fullResultString = bmiTestResult +" - You are overweight";
        }else{
            //Display healthy
            fullResultString = bmiTestResult +" - You are healthy weight";
        }
        resultTest.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTestResult = myDecimalFormatter.format(bmi);
        String fullResultString;

        if(maleBtn.isChecked()){
            //Display boy
            fullResultString = bmiTestResult +" - As you are under 18, please consult with your doctor for the healthy range for boys";
        }else if(femaleBtn.isChecked()){

            //Display girl
            fullResultString = bmiTestResult +" - As you are under 18, please consult with your doctor for the healthy range for girls";
        }else{
            //General
            fullResultString = bmiTestResult +" - As you are under 18, please consult with your doctor for the healthy range";
        }

        resultTest.setText(fullResultString);
    }
}