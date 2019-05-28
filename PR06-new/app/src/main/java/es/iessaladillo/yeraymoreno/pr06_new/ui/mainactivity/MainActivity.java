package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.DrawerLayoutBinding;
import es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.mainFragment.MainFragment;
import es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.preferencesFragment.PreferenceFragment;

public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayoutBinding drawerLayoutBinding;

    private static final String STUDENT_LIST = "STUDENT_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerLayoutBinding = DataBindingUtil.setContentView(this,R.layout.drawer_layout);

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
                getSupportFragmentManager().beginTransaction().replace(R.id.frglist,
                        new MainFragment()).commit();
                break;
            case R.id.mnuSettingsOption:
                getSupportFragmentManager().beginTransaction().replace(R.id.frglist,
                        new PreferenceFragment()).commit();
                break;
            case R.id.mnuThreadOption:
                getSupportFragmentManager().beginTransaction().replace(R.id.frglist,
                        new MainFragment()).commit();
                break;
        }
        return true;
    }
}
