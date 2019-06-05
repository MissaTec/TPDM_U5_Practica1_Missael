package com.example.tpdm_u5_practica1_missael;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG="SmsBroadcastReceiver";
    public String msg="";
    Mensajes[] vector;
    Mensajes base;

    @Override
    public void onReceive(Context context, Intent intent) {



        Log.i(TAG,"Intent received: "+intent.getAction());
        base=new Mensajes(context);
        vector=base.select();
        if(intent.getAction()==SMS_RECEIVED){
            Bundle dataBundle=intent.getExtras();
            if(dataBundle!=null){
                Object[] mypdu=(Object[])dataBundle.get("pdus");
                final SmsMessage[] message=new SmsMessage[mypdu.length];
                for(int i=0;i<mypdu.length;i++){
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        String format=dataBundle.getString("format");
                        message[i]=SmsMessage.createFromPdu((byte[])mypdu[i],format);

                    }else{
                        message[i]=SmsMessage.createFromPdu((byte[])mypdu[i]);
                    }
                    msg=message[i].getMessageBody();
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();

                    for(int j=0;j<vector.length;j++){
                        String msg2=vector[j].msj;
                        if(msg.equals(msg2)){


                            SmsManager smgr = SmsManager.getDefault();
                            smgr.sendTextMessage(message[i].getOriginatingAddress(), null, vector[j].respuesta, null, null);

                        }
                    }

                }
            }
        }
    }
}
