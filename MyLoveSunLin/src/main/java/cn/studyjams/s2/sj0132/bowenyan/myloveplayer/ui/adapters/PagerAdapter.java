/**
 *
 */

package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.ui.adapters;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.helpers.RefreshableFragment;

/**
 * Created by yanbowen on 4/20/2017.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> mFragments = new ArrayList<Fragment>();

    public PagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * This method update the fragments that extends the {@link RefreshableFragment} class
     */
    public void refresh() {
        for (int i = 0; i < mFragments.size(); i++) {
            if (mFragments.get(i) instanceof RefreshableFragment) {
                ((RefreshableFragment) mFragments.get(i)).refresh();
            }
        }
    }

}
