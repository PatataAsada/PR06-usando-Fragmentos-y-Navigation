package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.yeraymoreno.pr06_new.R;

public class MainActivity extends AppCompatActivity {


    private static final String STUDENT_LIST = "STUDENT_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
