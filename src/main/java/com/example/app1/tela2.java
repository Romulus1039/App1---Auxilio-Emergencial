package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tela2 extends AppCompatActivity {

    private TextView dataPagamento;
    private TextView saldoReceber;
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tela2 );

        dataPagamento = findViewById(R.id.dataPagamento.txt);
        saldoReceber = findViewById(R.id.saldoReceber.txt);

        Bundle bundle = getIntent().getExtras();

        dataPagamento.setText(bundle.getString("dataPagamento"));
        saldoReceber.setText("R$" + bundle.getString("saldoReceber"));

    }

    public void onClickVoltar(View view){
        finish();
    }
}
