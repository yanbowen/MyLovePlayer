
package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import java.lang.ref.WeakReference;

import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.R;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.helpers.utils.MusicUtils;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.ui.fragments.list.TracksFragment;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.views.ViewHolderList;

/**
 * Created by yanbowen on 4/20/2017.
 */
public class TrackAdapter extends SimpleCursorAdapter {

    private AnimationDrawable mPeakOneAnimation, mPeakTwoAnimation;

    private WeakReference<ViewHolderList> holderReference;

    public TrackAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    /**
     * Used to quickly our the ContextMenu
     */
    private final View.OnClickListener showContextMenu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.showContextMenu();
        }
    };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = super.getView(position, convertView, parent);

        Cursor mCursor = (Cursor) getItem(position);
        // ViewHolderList
        ViewHolderList viewholder;

        if (view != null) {

            viewholder = new ViewHolderList(view);
            holderReference = new WeakReference<ViewHolderList>(viewholder);
            view.setTag(holderReference.get());

        } else {
            viewholder = (ViewHolderList) convertView.getTag();
        }

        // Track name
        String trackName = mCursor.getString(TracksFragment.mTitleIndex);
        viewholder.mViewHolderLineOne.setText(trackName);

        // Artist name
        String artistName = mCursor.getString(TracksFragment.mArtistIndex);
        holderReference.get().mViewHolderLineTwo.setText(artistName);

        // Hide the album art
        holderReference.get().mViewHolderImage.setVisibility(View.GONE);

        holderReference.get().mQuickContext.setOnClickListener(showContextMenu);

        // Now playing indicator
        long currentaudioid = MusicUtils.getCurrentAudioId();
        long audioid = mCursor.getLong(TracksFragment.mMediaIdIndex);
        if (currentaudioid == audioid) {
            holderReference.get().mPeakOne.setImageResource(R.drawable.peak_meter_1);
            holderReference.get().mPeakTwo.setImageResource(R.drawable.peak_meter_2);
            mPeakOneAnimation = (AnimationDrawable) holderReference.get().mPeakOne.getDrawable();
            mPeakTwoAnimation = (AnimationDrawable) holderReference.get().mPeakTwo.getDrawable();
            try {
                if (MusicUtils.mService.isPlaying()) {
                    mPeakOneAnimation.start();
                    mPeakTwoAnimation.start();
                } else {
                    mPeakOneAnimation.stop();
                    mPeakTwoAnimation.stop();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            holderReference.get().mPeakOne.setImageResource(0);
            holderReference.get().mPeakTwo.setImageResource(0);
        }
        return view;
    }
}
