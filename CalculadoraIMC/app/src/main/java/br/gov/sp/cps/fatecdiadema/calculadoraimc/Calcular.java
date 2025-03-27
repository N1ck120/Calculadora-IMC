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
import com.google.android.material.textfield.TextInputLayout;

public class Calcular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcular);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextInputEditText altura = findViewById(R.id.fieldAltura);
        TextInputEditText peso = findViewById(R.id.fieldPeso);
        Button calcular = findViewById(R.id.btnCalc);
        Button fechar = findViewById(R.id.btnClose);

        calcular.setOnClickListener(view -> {
            if (altura.getText().toString().trim().isEmpty()){
                altura.setError("Este campo é obrigatório!");
            } else if (peso.getText().toString().trim().isEmpty()) {
                peso.setError("Este campo é obrigatório!");
            } else{
                double res = calcImc(Double.valueOf(String.valueOf(altura.getText())), Double.valueOf(String.valueOf(peso.getText())));
                if (res < 18.5){
                    Intent intent = new Intent(this, ResultadoAbaixo.class);
                    intent.putExtra("Altura",String.valueOf(altura.getText()));
                    intent.putExtra("Peso",String.valueOf(peso.getText()));
                    intent.putExtra("IMC",res);
                    startActivity(intent);
                    finish();
                } else if (res > 18.5 && res < 25) {
                    Intent intent = new Intent(this, ResultadoMedia.class);
                    intent.putExtra("Altura",String.valueOf(altura.getText()));
                    intent.putExtra("Peso",String.valueOf(peso.getText()));
                    intent.putExtra("IMC",res);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(this, ResultadoAcima.class);
                    intent.putExtra("Altura",String.valueOf(altura.getText()));
                    intent.putExtra("Peso",String.valueOf(peso.getText()));
                    intent.putExtra("IMC",res);
                    startActivity(intent);
                    finish();
                }
            }

        });

        fechar.setOnClickListener(view -> {
            finish();
        });
    }
    private Double calcImc(Double a, Double p){
        return p/(a*a);
    }
}