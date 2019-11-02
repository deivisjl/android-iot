package com.umg.iot.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.umg.iot.R;
import com.umg.iot.api.FirebaseApi;
import com.umg.iot.gas.GasFragment;
import com.umg.iot.intruder.IntruderFragment;
import com.umg.iot.lib.MySharedPreference;
import com.umg.iot.login.LoginActivity;
import com.umg.iot.main.adapter.MainSectionsPagerAdapter;
import com.umg.iot.panico.PanicoFragment;
import com.umg.iot.temperature.TemperatureFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.container)
    ViewPager viewPager;

    private MySharedPreference sharedPreferences;
    private FirebaseApi helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupAdapter();
        sharedPreferences = MySharedPreference.getInstance(getApplicationContext());
        helper = FirebaseApi.getInstance();
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[]{new TemperatureFragment(), new IntruderFragment(), new PanicoFragment(), new GasFragment()};
        String[] titles = new String[]{getString(R.string.main_header_temperature), getString(R.string.main_header_intruder),getString(R.string.main_header_panico), getString(R.string.main_header_gas)};
        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getSupportFragmentManager(),1, fragments, titles);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        sharedPreferences.deleteData();
        helper.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
