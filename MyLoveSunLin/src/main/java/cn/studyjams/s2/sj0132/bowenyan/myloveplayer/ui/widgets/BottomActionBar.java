/**
 * 
 */

package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.ui.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.R;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.activities.AudioPlayerHolder;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.activities.QuickQueue;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.cache.ImageInfo;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.cache.ImageProvider;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.helpers.utils.MusicUtils;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.helpers.utils.ThemeUtils;

import static cn.studyjams.s2.sj0132.bowenyan.myloveplayer.Constants.*;


/**
 * @author Andrew Neal
 */
public class BottomActionBar extends LinearLayout implements OnClickListener, OnLongClickListener {
	 
    public BottomActionBar(Context context) {
        super(context);
    }

    public BottomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    public BottomActionBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Updates the bottom ActionBar's info
     * 
     * @param activity
     * @throws RemoteException
     */
    public void updateBottomActionBar(Activity activity) {
        View bottomActionBar = activity.findViewById(R.id.bottom_action_bar);
        if (bottomActionBar == null) {
            return;
        }

        if (MusicUtils.mService != null && MusicUtils.getCurrentAudioId() != -1) {

            // Track name
            TextView mTrackName = (TextView)bottomActionBar
                    .findViewById(R.id.bottom_action_bar_track_name);
            mTrackName.setText(MusicUtils.getTrackName());

            // Artist name
            TextView mArtistName = (TextView)bottomActionBar
                    .findViewById(R.id.bottom_action_bar_artist_name);
            mArtistName.setText(MusicUtils.getArtistName());

            // Album art
            ImageView mAlbumArt = (ImageView)bottomActionBar
                    .findViewById(R.id.bottom_action_bar_album_art);
            

            ImageInfo mInfo = new ImageInfo();
            mInfo.type = TYPE_ALBUM;
            mInfo.size = SIZE_THUMB;
            mInfo.source = SRC_FIRST_AVAILABLE;
            mInfo.data = new String[]{ String.valueOf(MusicUtils.getCurrentAlbumId()) , MusicUtils.getArtistName(), MusicUtils.getAlbumName() };
            
            ImageProvider.getInstance( activity ).loadImage( mAlbumArt , mInfo );
            
            // Divider
            ImageView mDivider = (ImageView)activity
                    .findViewById(R.id.bottom_action_bar_info_divider);
            
            // Theme chooser
            ThemeUtils.setTextColor(activity, mTrackName, "bottom_action_bar_text_color");
            ThemeUtils.setTextColor(activity, mArtistName, "bottom_action_bar_text_color");
            ThemeUtils.setBackgroundColor(activity, mDivider, "bottom_action_bar_info_divider");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_action_bar:
                Intent intent = new Intent();
                intent.setClass(v.getContext(), AudioPlayerHolder.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        Context context = v.getContext();
        context.startActivity(new Intent(context, QuickQueue.class));
        return true;
    }
    
}
