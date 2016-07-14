package ly.aswin.mymusic.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ly.aswin.mymusic.R;
import ly.aswin.mymusic.data.models.User;
import ly.aswin.mymusic.managers.DataManager;
import ly.aswin.mymusic.ui.adapters.TrackListsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set users for fragments in view pager
        List<User> users = new ArrayList<>();
        users.add(new User(getString(R.string.kipzes), DataManager.KIPZES_USER_ID));
        users.add(new User(getString(R.string.specdrums), DataManager.SPECDRUMS_USER_ID));
        TrackListsPagerAdapter trackListsPagerAdapter = new TrackListsPagerAdapter(getSupportFragmentManager(), users);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(trackListsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }
}
