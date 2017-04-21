
package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import java.lang.ref.WeakReference;

import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.R;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.cache.ImageInfo;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.cache.ImageProvider;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.helpers.utils.MusicUtils;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.ui.fragments.grid.ArtistsFragment;
import cn.studyjams.s2.sj0132.bowenyan.myloveplayer.views.ViewHolderGrid;

import static cn.studyjams.s2.sj0132.bowenyan.myloveplayer.Constants.*;

/**
 * @author Andrew Neal
 */
public class ArtistAdapter extends SimpleCursorAdapter {

    private AnimationDrawable mPeakOneAnimation, mPeakTwoAnimation;

    private WeakReference<ViewHolderGrid> holderReference;
    
    private Context mContext;
    
    private ImageProvider mImageProvider;

    public ArtistAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    	mContext = context;
    	mImageProvider = ImageProvider.getInstance( (Activity) mContext );
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = super.getView(position, convertView, parent);

        Cursor mCursor = (Cursor) getItem(position);
        // ViewHolderGrid
        final ViewHolderGrid viewholder;

        if (view != null) {

            viewholder = new ViewHolderGrid(view);
            holderReference = new WeakReference<ViewHolderGrid>(viewholder);
            view.setTag(holderReference.get());

        } else {
            viewholder = (ViewHolderGrid)convertView.getTag();
        }

        // Artist Name
        String artistName = mCursor.getString(ArtistsFragment.mArtistNameIndex);
        holderReference.get().mViewHolderLineOne.setText(artistName);

        // Number of albums
        int albums_plural = mCursor.getInt(ArtistsFragment.mArtistNumAlbumsIndex);
        boolean unknown = artistName == null || artistName.equals(MediaStore.UNKNOWN_STRING);
        String numAlbums = MusicUtils.makeAlbumsLabel(mContext, albums_plural, 0, unknown);
        holderReference.get().mViewHolderLineTwo.setText(numAlbums);

        ImageInfo mInfo = new ImageInfo();
        mInfo.type = TYPE_ARTIST;
        mInfo.size = SIZE_THUMB;
        mInfo.source = SRC_FIRST_AVAILABLE;
        mInfo.data = new String[]{ artistName };
        
        mImageProvider.loadImage( viewholder.mViewHolderImage, mInfo );

        // Now playing indicator
        long currentartistid = MusicUtils.getCurrentArtistId();
        long artistid = mCursor.getLong(ArtistsFragment.mArtistIdIndex);
        if (currentartistid == artistid) {
            holderReference.get().mPeakOne.setImageResource(R.drawable.peak_meter_1);
            holderReference.get().mPeakTwo.setImageResource(R.drawable.peak_meter_2);
            mPeakOneAnimation = (AnimationDrawable)holderReference.get().mPeakOne.getDrawable();
            mPeakTwoAnimation = (AnimationDrawable)holderReference.get().mPeakTwo.getDrawable();
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
