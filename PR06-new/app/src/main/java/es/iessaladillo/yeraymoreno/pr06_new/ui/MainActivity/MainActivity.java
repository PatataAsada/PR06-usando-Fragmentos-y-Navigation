package es.iessaladillo.yeraymoreno.pr06_new.ui.MainActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import es.iessaladillo.yeraymoreno.pr06_new.R;

public class MainActivity extends AppCompatActivity implements NavHost {


    private static final String STUDENT_LIST = "STUDENT_LIST";
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this,R.id.navHostFragment);
        setupViews();
    }

    private void setupViews() {
    }

    @NonNull
    @Override
    public NavController getNavController() {
        return navController;
    }
}
