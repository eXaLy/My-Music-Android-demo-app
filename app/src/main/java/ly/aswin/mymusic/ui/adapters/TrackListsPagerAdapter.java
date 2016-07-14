package ly.aswin.mymusic.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import ly.aswin.mymusic.data.models.User;
import ly.aswin.mymusic.ui.fragments.TrackListFragment;

/**
 * Created by Aswin on 14-7-16
 */
public class TrackListsPagerAdapter extends FragmentPagerAdapter {

    private List<User> users;

    public TrackListsPagerAdapter(FragmentManager fm, List<User> users) {
        super(fm);
        this.users = users;
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && position < users.size()) {
            return TrackListFragment.newInstance(users.get(position).getUserId());
        }
        return null;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position >= 0 && position < users.size()) {
            return users.get(position).getUsername();
        }
        return null;
    }
}
