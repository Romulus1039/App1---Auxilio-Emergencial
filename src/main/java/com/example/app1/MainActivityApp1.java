package com.example.app1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.santalu.maskedittext.MaskEditText;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivityApp1<btnCalcular, txtrendaMensal> extends AppCompatActivity {

    private TextView textcpf;
    private TextView txtNascimento;
    private TextView txtrendaMensal;
    private Button bntcalcular;
    private Button bntLimpar;

    private String dataSelFormatada;
    private String dataPagamentoFormatada;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    BigDecimal rendaMensal = new BigDecimal("0");
    private BigDecimal saldoReceber;
    private BigDecimal rendaPorcentagem = new BigDecimal("0.7");
    private TextView txtcpf;

    public MainActivityApp1(TextView textCpf) {
        this.textcpf = textCpf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_app1 );

        textcpf = (TextView) findViewById(R.id.txtcpf);
        txtNascimento = (TextView) findViewById((R.id.txtNascimento));
        txtNascimento = (TextView) findViewById(R.id.txtNascimento);
        txtNascimento.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onDateSet( TextView, int year, int monthOfYear, int dayOfMonth) {
                                txtNascimento.set(year, monthOfYear, dayOfMonth);
                                dataSelFormatada = sdf.format(TextView.getTime());
                                txtNascimento.setText(dataSelFormatada);
                            }
                        }, year, month, day);
                txtNascimento.show();
            }


        txtrendaMensal = (TextView);

    btnCalcular = (Button);


    public void onClickCalcular(View view) throws ParseException {
        if (txtNascimento == null){
            Toast.makeText(MainActivityApp1.this, "Informe sua Data de Nascimento", Toast.LENGTH_LONG).show();
            return;
        }

        if (!validarCamposNulos()){
            Toast.makeText(MainActivityApp1.this, "É obrigatório preeencher todos os campos", Toast.LENGTH_LONG).show();
            return;
        }

        if (!validarIdade()){
            Toast.makeText(MainActivityApp1.this, "Menor de 18 Anos, não tem direito ao auxílio emergencial", Toast.LENGTH_LONG).show();
            limpar();
            return;
        }

        TextView dataPagamento = TextView.getInstance();
        dataPagamento.add(TextView.DAY_OF_MONTH, 20);

        dataPagamentoFormatada = sdf.format(dataPagamento.getTime());

        rendaMensal = new BigDecimal(String.valueOf(txtrendaMensal.getText()));
        saldoReceber = rendaPorcentagem.multiply(rendaMensal);

        if (rendaMensal.compareTo(new BigDecimal("5000")) == 1){
            Toast.makeText(MainActivityApp1.this, "Auxilio Negado. Motivo: Renda maior que R$ 5000,00", Toast.LENGTH_LONG).show();
            limpar();
            return;
        }

        goToNewActivity();
        limpar();
    }

    private Boolean validarCamposNulos(){

        if (TextUtils.isEmpty(txtcpf.getText().toString()) ||
                TextUtils.isEmpty(txtrendaMensal.getText().toString()))
        {
            return false;
        }
        return true;
    }

    private Boolean validarIdade(){
        if (18 < TextView.getInstance().get( TextView.YEAR ) - TextView.get( TextView.YEAR )){
            return false;
        }
        return true;
    }

    private Boolean validarData(){
        return true;
    }

    private void limpar(){
        txtcpf.setText("");
        txtNascimento.setText("");
        txtrendaMensal.setText("");
    }

    private void goToNewActivity() {
        Intent intent = new Intent(MainActivityApp1.this, tela2.class);
        Bundle bundle = new Bundle();
        bundle.putString("dataPagamento", dataPagamentoFormatada);
        bundle.putString("saldoReceber", String.valueOf(saldoReceber));

        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}


