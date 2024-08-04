package com.example.uc;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private EditText value;
    private Spinner fromspinner;
    private Spinner tospinner;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        value = findViewById(R.id.editTextText2);
        fromspinner = findViewById(R.id.spinner);
        tospinner = findViewById(R.id.spinner2);
        Button button2 = findViewById(R.id.button2);
        resultText = findViewById(R.id.textView);

        String[] units = {"Centi Meter", "Meter", "Kilo Gram", "Gram"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromspinner.setAdapter(adapter);
        tospinner.setAdapter(adapter);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performConversion();
            }
        });
    }

    private void performConversion() {
        String input = value.getText().toString();
        String fromUnit = fromspinner.getSelectedItem().toString();
        String toUnit = tospinner.getSelectedItem().toString();

        if (input.isEmpty()) {
            Toast.makeText(MainActivity2.this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double value = Double.parseDouble(input);
        double result = 0;

        switch (fromUnit) {
            case "Centi Meter":
                if (toUnit.equals("Meter")) {
                    result = value / 100;
                } else if (toUnit.equals("Centi Meter")) {
                    result = value;
                }
                break;
            case "Meter":
                if (toUnit.equals("Centi Meter")) {
                    result = value * 100;
                } else if (toUnit.equals("Meter")) {
                    result = value;
                }
                break;
            case "Gram":
                if (toUnit.equals("Kilo Gram")) {
                    result = value / 1000;
                } else if (toUnit.equals("Gram")) {
                    result = value;
                }
                break;
            case "Kilo Gram":
                if (toUnit.equals("Gram")) {
                    result = value * 1000;
                } else if (toUnit.equals("Kilo Gram")) {
                    result = value;
                }
                break;
            default:
                resultText.setText("Invalid Conversion");
                return;
        }

        resultText.setText(String.format("%.2f %s = %.2f %s", value, fromUnit, result, toUnit));
    }
}
