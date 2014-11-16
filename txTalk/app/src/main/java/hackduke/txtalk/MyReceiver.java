package hackduke.txtalk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {

        final SmsManager sms = SmsManager.getDefault();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try{
            if(bundle != null){
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for(int i=0; i<pdusObj.length; i++){

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNumber= phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Toast.makeText(context, "Sender#:" + senderNumber + ", Msg: " + message, Toast.LENGTH_LONG).show();

                }
            }

        }catch(Exception e){
            Log.e("SmsReceiver Error", "Exception SmsReceiver" + e);
        }
    }
}
