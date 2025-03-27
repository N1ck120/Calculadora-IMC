package br.gov.sp.cps.fatecdiadema.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class ResultadoMedia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado_media);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        Button fechar = findViewById(R.id.btnClose);
        TextView res = findViewById(R.id.textView3);
        TextInputEditText altura = findViewById(R.id.altura);
        TextInputEditText peso = findViewById(R.id.peso);

        altura.setText(intent.getStringExtra("Altura"));
        peso.setText(intent.getStringExtra("Peso"));


        res.setText(String.format("IMC: %.2f", intent.getDoubleExtra("IMC", 0)));

        fechar.setOnClickListener(view -> {
            finish();
        });
    }
}