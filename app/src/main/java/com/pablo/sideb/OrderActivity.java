package com.pablo.sideb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.pablo.sideb.dao.DAO;
import com.pablo.sideb.model.Cep;
import com.pablo.sideb.model.Cliente;
import com.pablo.sideb.service.ResponseJSON;

import java.util.concurrent.ExecutionException;

public class OrderActivity extends AppCompatActivity {

    Button btnBuscar, btnFazerPedido;
    TextInputEditText edNomeCli, edCpfCli, edTelefoneCli,edCep, edLongadouro, edComplemento, edNumero, edBairro, edCidade, edUf,
            edProduto, edQtde, edValor;
    Intent reiceveData;
    DAO database = new DAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        edCep = findViewById(R.id.edCep);
        btnBuscar = findViewById(R.id.btnBuscar);
        edLongadouro = findViewById(R.id.edLongadouro);
        edComplemento = findViewById(R.id.edComplemento);
        edBairro = findViewById(R.id.edBairro);
        edCidade = findViewById(R.id.edCidade);
        edUf = findViewById(R.id.edUf);
        edNumero = findViewById(R.id.edNumero);
        edProduto = findViewById(R.id.edProduto);
        edQtde = findViewById(R.id.edQtde);
        edValor = findViewById(R.id.edValor);
        btnFazerPedido = findViewById(R.id.btnFazerPedido);
        edNomeCli = findViewById(R.id.edNomeCli);
        edCpfCli = findViewById(R.id.edCpfCli);
        edTelefoneCli = findViewById(R.id.edTelefoneCli);
        reiceveData = getIntent();


        //Preenche os campos de identificação
      /*  Cliente cli = new Cliente();
        cli = database.consultarCliente();
        edNomeCli.setText(cli.getNome());
        edCpfCli.setText(cli.getCpf());
        edTelefoneCli.setText(cli.getTelefone());*/



        //Preenche os dados do produto
        String produto = "CD " + reiceveData.getStringExtra("produto"),
                valor = reiceveData.getStringExtra("valor");
        edProduto.setText(produto);
        edQtde.setText("01");
        edValor.setText(valor);

        //Valida o CEP na API
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cep retorno;
                    ResponseJSON rj = new ResponseJSON(edCep.getText().toString());
                    retorno = rj.execute().get();
                    if (retorno.getCep() != null) {
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

                        edNumero.requestFocus();

                    } else {
                        Toast.makeText(OrderActivity.this, "CEP não encontrado! Verifique e tente novamente.", Toast.LENGTH_SHORT).show();
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