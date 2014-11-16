package hackduke.txtalk;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class msgActivity extends Activity {

    Button sendBtn;
    EditText phoneNo;
    EditText txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        //Initializes Firebase Lib
        Firebase.setAndroidContext(this);

        Firebase fbRef = new Firebase("https://txtalk.firebaseio.com");

        fbRef.child("message").setValue("Sayin' Something.");

        phoneNo = (EditText) findViewById(R.id.phoneNoET);
        txtMsg = (EditText) findViewById(R.id.msgET);
        sendBtn = (Button) findViewById(R.id.sendButton);

        sendBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                sendSMSMessage();
            }
        });
    }


    protected void sendSMSMessage(){
        String txtPhoneNo = phoneNo.getText().toString();
        String message = txtMsg.getText().toString();

        try{
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(txtPhoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "Sms Send.", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "SMS Failed.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.msg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
