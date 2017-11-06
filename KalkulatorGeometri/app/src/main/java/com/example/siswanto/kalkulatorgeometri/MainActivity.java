package com.example.siswanto.kalkulatorgeometri;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    private EditText edt_input1, edt_input2;
    private Button btn_hitung;
    private TextView tv_output1, tv_output2;
    private Spinner sp_pilih;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp_pilih = (Spinner) findViewById(R.id.sp_pilihan);
        edt_input1 = (EditText) findViewById(R.id.et_input1);
        edt_input2 = (EditText) findViewById(R.id.et_input2);
        btn_hitung = (Button) findViewById(R.id.bt_hitung);
        tv_output1 = (TextView) findViewById(R.id.tv_output1);
        tv_output2 = (TextView) findViewById(R.id.tv_output2);

        btn_hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = edt_input1.getText().toString().trim();
                String input2 = edt_input2.getText().toString().trim();

                double P = Double.parseDouble(input1);
                double L = Double.parseDouble(input2);
                double Luas = 0;
                double Keliling = 0;

                if (sp_pilih.getSelectedItem().toString().equals("Persegi"))
                {
                    Luas = L * P ;
                    Keliling = ( 2 * P ) + ( 2 * L );
                }
                else if (sp_pilih.getSelectedItem().toString().equals("Lingkaran"))
                {
                    Luas = Math.PI * ( P * P );
                    Keliling = Math.PI * ( 2 * P );

                }else {

                    Luas = ( P * L ) / 2;
                    Keliling = (P + L + ( Math.sqrt((P * P) + (L * L))));
                }

                tv_output1.setText("Luas dari bangun datar = "+Luas);
                tv_output2.setText("Keliling Lingkaran = "+Keliling);
            }
        });
    }

    public void notif(View view) {
        NotificationManagerCompat myManager = NotificationManagerCompat.from(this);

        NotificationCompat.Builder myNotif = new NotificationCompat.Builder(this);
        myNotif.setContentTitle("Kalkulator Goemetri");
        myNotif.setContentText("Luas = "+tv_output1+"Keliling = "+tv_output2);
        myNotif.setSmallIcon(android.R.drawable.ic_btn_speak_now);

        Intent i1 = new Intent(this, MainActivity.class);
        PendingIntent pd = PendingIntent.getActivity(this, 1, i1, 0);
        myNotif.setContentIntent(pd);
        myNotif.setAutoCancel(true);

        myManager.notify(1, myNotif.build());
    }
}
