package com.pablo.sideb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pablo.sideb.model.Cep;
import com.pablo.sideb.service.RetornoJSON;

import java.util.concurrent.ExecutionException;

public class PedidoEndereco extends AppCompatActivity {

    EditText edCep, edLongadouro, edComplemento, edBairro, edCidade, edUf;
    TextView txEndereco;
    Button btnChecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);
        edCep = findViewById(R.id.edCep);
        btnChecar = findViewById(R.id.btnBuscar);
        edLongadouro = findViewById(R.id.edLongadouro);
        edComplemento = findViewById(R.id.edComplemento);
        edBairro = findViewById(R.id.edBairro);
        edCidade = findViewById(R.id.edCidade);
        edUf = findViewById(R.id.edUf);

        btnChecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cep retorno;
                    RetornoJSON rj = new RetornoJSON(edCep.getText().toString());
                    retorno = rj.execute().get();
                    if(retorno.getCep() != null){
                        edLongadouro.setText(formatSpace(retorno.getLongadouro()));
                        edBairro.setText(formatSpace(retorno.getBairro()));
                        edCidade.setText(formatSpace(retorno.getLocalidade()));
                        edUf.setText(retorno.getUf());

                        edLongadouro.setFocusable(false);
                        edLongadouro.setClickable(false);
                        edLongadouro.setCursorVisible(false);

                        edBairro.setFocusable(false);
                        edBairro.setClickable(false);
                        edBairro.setCursorVisible(false);

                        edCidade.setFocusable(false);
                        edCidade.setClickable(false);
                        edCidade.setCursorVisible(false);

                        edUf.setFocusable(false);
                        edUf.setClickable(false);
                        edUf.setCursorVisible(false);

                        edComplemento.requestFocus();

                    } else {
                        Toast.makeText(PedidoEndereco.this, "CEP não encontrado! Verifique e tente novamente.", Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String formatSpace(String s) {
        //Método para adicionar os espaços entre as palavras retornadas pela consulta
        int i = 0;
        StringBuilder result = new StringBuilder();
        for (i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append(" " + c);
            } else {
                result.append(c);
            }
        }
        result.insert(0, s.charAt(0));
        return result.toString();
    }
}