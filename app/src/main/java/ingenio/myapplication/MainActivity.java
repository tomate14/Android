package ingenio.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //checkIntent();
        login();
    }


    /*private void checkIntent() {
        if(getIntent()!=null){
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                String user_name = bundle.getString("notificacion");
                Log.d ("myApplication", user_name + " is a key in the bundle");
            }
        }
    }*/

    private void login() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Start the next activity
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}