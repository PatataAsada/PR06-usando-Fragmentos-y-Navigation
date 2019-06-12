package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;

import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.DrawerLayoutBinding;

public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayoutBinding drawerLayoutBinding;
    private NavController navController;

    private static final String STUDENT_LIST = "STUDENT_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerLayoutBinding = DataBindingUtil.setContentView(this,R.layout.drawer_layout);

        navController = Navigation.findNavController(this,R.id.frglist);
        setToolbar();
        setNavigation();
    }

    private void setNavigation() {
        drawerLayoutBinding.navigationView.setNavigationItemSelectedListener(this);
    }

    private void setToolbar() {
        setSupportActionBar(drawerLayoutBinding.toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayoutBinding.drawerLayout,
                drawerLayoutBinding.toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayoutBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }



    @Override
    public void onBackPressed() {
        if(drawerLayoutBinding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayoutBinding.drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuHomeOption:
                navController.navigate(R.id.mainFragment);
                break;
            case R.id.mnuSettingsOption:
                navController.navigate(R.id.preferenceFragment2);
                break;
            case R.id.mnuThreadOption:
                navController.navigate(R.id.retrofitFragment2);
                break;
        }
        return true;
    }
}
