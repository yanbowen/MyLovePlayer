package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.helpers;

import android.app.Fragment;

/**
 * Created by yanbowen on 4/21/2017.
 *
 * An abstract class that defines a {@link Fragment} like refreshable
 *
 */
public abstract class RefreshableFragment extends Fragment {
    /**
     * Method invoked when the fragment need to be refreshed
     */
    public abstract void refresh();
}
