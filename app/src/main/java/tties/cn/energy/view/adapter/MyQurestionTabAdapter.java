package tties.cn.energy.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.application.MyApplication;


/**
 * Created by li on 2018/3/22
 * description：
 * author：guojlli
 */

public class MyQurestionTabAdapter extends FragmentPagerAdapter{
    String[] array;
    List<Fragment> list;
    public MyQurestionTabAdapter(FragmentManager fm,String[] array,List<Fragment> list) {
        super(fm);
        this.array=array;
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return array[position];
    }

}
