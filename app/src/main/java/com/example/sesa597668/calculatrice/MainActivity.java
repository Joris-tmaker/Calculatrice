package com.example.sesa597668.calculatrice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private int op1=0;
    private int op2=0;
    private Ops operator=null;
    private boolean isOp1=true;

    public enum Ops {
        PLUS,
        MOINS,
        FOIS,
        DIV,
    }
    public void compute(){
        if(!isOp1){
            switch (operator){
                case PLUS : op1 = op1 + op2; break;
                case MOINS : op1 = op1 - op2; break;
                case FOIS : op1 = op1 * op2; break;
                case DIV: op1 = op1 / op2; break;
                default : return;

            }
            op2=0;
            isOp1=true;
            updateDisplay();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);

        Button btnEgal = (Button)findViewById(R.id.btnEgal);
        btnEgal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                compute();
            }
        });
    }



    public void setOperator(View v){
        switch (v.getId()){
            case R.id.btnPlus : operator=Ops.PLUS; break;
            case R.id.btnMoins : operator=Ops.MOINS; break;
            case R.id.btnFois: operator=Ops.FOIS; break;
            case R.id.btnDiv : operator=Ops.DIV; break;
            default:
                Toast.makeText(this,"operateur non reconnu",Toast.LENGTH_LONG);
                return;
        }
        isOp1=false;
        updateDisplay();
    }

    public void addNumber(View v){
        try{
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOp1){
                op1=op1*10+val;
                updateDisplay();
            }else{
                op2=op2*10+val;
                updateDisplay();
            }
        }catch(NumberFormatException | ClassCastException e){
            Toast.makeText(this, "Valeur erronée", Toast.LENGTH_LONG).show();
        }
    }
    public void updateDisplay(){
        int v=op1;
        if (!isOp1){
            v=op2;
        }
        screen.setText(String.format("%9d",v));

    }


}
