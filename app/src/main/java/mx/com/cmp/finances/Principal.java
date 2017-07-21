package mx.com.cmp.finances;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import mx.com.cmp.finances.fragments.AccountFragment;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_ACCOUNT = "account";
    private static final String TAG_CREDIT_CARD = "credit_card";
    private static final String TAG_CATEGORY = "category";
    public static String CURRENT_TAG = TAG_ACCOUNT;


    private boolean mLoadHomeFragment = true;
    private String mTitles[];
    private int menuIndex = 0;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitles = getResources().getStringArray(R.array.menu_titles);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new floatingButtonListener());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.menu_view);
        navigationView.setNavigationItemSelectedListener(this);
        setToolbarTitle();
    }

    public class floatingButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), AccountActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        if (mLoadHomeFragment) {
            if (menuIndex != 0) {
                menuIndex = 0;
                CURRENT_TAG = TAG_ACCOUNT;
                loadSection();
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.superior_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_account:
                menuIndex = 0;
                CURRENT_TAG = TAG_ACCOUNT;
                break;
            case R.id.nav_credit_Card:
                menuIndex = 1;
                CURRENT_TAG = TAG_CREDIT_CARD;
                break;
            case R.id.nav_categories:
                menuIndex = 2;
                CURRENT_TAG = TAG_CATEGORY;
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        setToolbarTitle();
        loadSection();

        return true;
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(mTitles[menuIndex]);
    }

    private void loadSection() {
        if(menuIndex == 2) {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
        } else {
            Fragment fragment = getFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.principal_frame, fragment, CURRENT_TAG);
            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    private Fragment getFragment() {
        Fragment fragment = null;
        if (menuIndex == 0) {
            fragment = new AccountFragment();
        }
        return fragment;
    }


}
