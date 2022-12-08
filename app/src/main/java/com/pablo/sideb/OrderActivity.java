package com.pablo.sideb;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.pablo.sideb.dao.DAO;
import com.pablo.sideb.model.Cep;
import com.pablo.sideb.model.Cliente;
import com.pablo.sideb.model.Pedido;
import com.pablo.sideb.service.ResponseJSON;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@RequiresApi(api = Build.VERSION_CODES.O)
public class OrderActivity extends AppCompatActivity {

    Button btnBuscar, btnFazerPedido;
    TextInputEditText edNomeCli, edCpfCli, edTelefoneCli, edCep, edLongadouro, edComplemento, edNumero, edBairro, edCidade, edUf,
            edProduto, edQtde, edValor;
    Intent reiceveData;
    DAO database = new DAO(this);
    TextView cancelar;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now;

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
        cancelar = findViewById(R.id.cancelar);
        btnFazerPedido = findViewById(R.id.btnFazerPedido);
        edNomeCli = findViewById(R.id.edNomeCli);
        edCpfCli = findViewById(R.id.edCpfCli);
        edTelefoneCli = findViewById(R.id.edTelefoneCli);
        reiceveData = getIntent();


        //Preenche os campos de identificação
        Cliente cli = new Cliente();
        cli = database.consultarCliente();
        edNomeCli.setText(cli.getNome());
        edCpfCli.setText(cli.getCpf());
        edTelefoneCli.setText(cli.getTelefone());


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

        btnFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edCep.getText().toString().equals("")) {
                    Toast.makeText(OrderActivity.this, "CEP não pode ficar em branco!", Toast.LENGTH_SHORT).show();
                    edCep.requestFocus();
                } else if (edLongadouro.getText().toString().equals("")) {
                    Toast.makeText(OrderActivity.this, "Toque no botão buscar para realizar busca de CEP.", Toast.LENGTH_SHORT).show();
                    btnBuscar.requestFocus();
                } else if (edNumero.getText().toString().equals("")) {
                    Toast.makeText(OrderActivity.this, "N° não pode ficar em branco!", Toast.LENGTH_SHORT).show();
                    edNumero.requestFocus();
                } else if (edComplemento.getText().toString().equals("")) {
                    Toast.makeText(OrderActivity.this, "Complemento não pode ficar em branco!", Toast.LENGTH_SHORT).show();
                    edComplemento.requestFocus();
                } else {
                    String endereco = edLongadouro.getText().toString() + ", " +
                            edNumero.getText().toString() + ", " +
                            edComplemento.getText().toString() + " - " +
                            edBairro.getText().toString() + ",  " +
                            edCidade.getText().toString() + " - " +
                            edUf.getText().toString() + ", " +
                            edCep.getText().toString();
                    Random r = new Random();
                    now = LocalDateTime.now();
                    Pedido ped = new Pedido();
                    ped.setDtCompra(dtf.format(now));
                    ped.setEnderecoEntrega(endereco);
                    ped.setItem(edProduto.getText().toString());
                    ped.setQtde(Integer.parseInt(edQtde.getText().toString()));
                    ped.setValorPedido(edValor.getText().toString());
                    try {
                        database.addPedido(ped);
                        finish();
                        Toast.makeText(OrderActivity.this, "Pedido realizado!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e){
                        Toast.makeText(OrderActivity.this, "Não foi possível realizar o pedido!\nTente novamente mais tarde.", Toast.LENGTH_SHORT).show();
                    } 
                }
            }
        });


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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