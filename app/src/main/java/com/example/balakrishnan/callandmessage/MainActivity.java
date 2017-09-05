package com.example.balakrishnan.callandmessage;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button callButton,msgButton;
    EditText ePhoneNo,eMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callButton = (Button) findViewById(R.id.buttonCall);
        ePhoneNo = (EditText)findViewById(R.id.phoneNo);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ePhoneNo.getText()));
                try{startActivity(callIntent);}
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_SHORT).show();
                }
            }
        });

        eMsg = (EditText)findViewById(R.id.editText2);
        msgButton =(Button)findViewById(R.id.buttonMessage);
        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = ePhoneNo.getText().toString();
                String sms = eMsg.getText().toString();

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
