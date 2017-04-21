package cn.studyjams.s2.sj0132.bowenyan.myloveplayer.activities;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yanbowen on 4/20/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    //界面列表
    private List<View> views;

    public ViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    //arg1位置的界
    @Override
    public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
        arg0.removeView(views.get(arg1));
    }

    @Override
    public void finishUpdate(ViewGroup arg0) {
        // TODO Auto-generated method stub

    }

    //获得当前界面
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    //初始化arg1位置的界
    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {

        arg0.addView(views.get(arg1), 0);

        return views.get(arg1);
    }

    //判断是否由对象生成界
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startUpdate(ViewGroup arg0) {
        // TODO Auto-generated method stub

    }
}