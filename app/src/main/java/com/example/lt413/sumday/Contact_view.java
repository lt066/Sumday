package com.example.lt413.sumday;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lt413 on 2015/12/6.
 */
public class Contact_view extends Fragment{

    private ViewPager viewPager;
    private SectionsPagerAdapter1 sectionsPagerAdapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact,container,false);
        sectionsPagerAdapter1 = new SectionsPagerAdapter1(getFragmentManager());
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPaper);
        viewPager.setAdapter(sectionsPagerAdapter1);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    public class SectionsPagerAdapter1 extends FragmentPagerAdapter
    {
        public SectionsPagerAdapter1(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new Wait_tro();
                case 1:
                    return new Wait_report();
                case 2:
                    return new Wait_modify();
                case 3:
                    return new Wait_confirm();
                case 4:
                    return new Wait_back();
                case 5:
                    return new Success();
                case 6:
                    return new Fail();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "待参与";
                case 1:
                    return "待提交";
                case 2:
                    return "待修改";
                case 3:
                    return "待确认";
                case 4:
                    return "待返款";
                case 5:
                    return "已完成";
                case 6:
                    return "已终止";
            }
            return null;
        }
    }
}
