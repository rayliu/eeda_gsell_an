/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.eeda123.gsell;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eeda123.gsell.order.AliexpressOrderFragment;
import com.eeda123.gsell.order.AmazonOrderFragment;
import com.eeda123.gsell.order.EbayOrderFragment;
import com.truiton.bottomnavigation.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;

public class OrderFragment extends Fragment {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    ViewPager mViewPager;

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        return inflater.inflate(R.layout.fragment_order, container, false);
    }
    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        //
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.

        //FragmentPagerAdapter getItem is not being triggered
//    http://stackoverflow.com/questions/15588120/fragmentpageradapter-getitem-is-not-being-triggered

        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getActivity().getSupportFragmentManager());

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) getActivity().findViewById(R.id.issue_pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("onPageScrolled", "position: " + position);
//                Fragment fragment = mDemoCollectionPagerAdapter.getItem(position);
//                Log.d("onPageScrolled", "fragment: " + fragment.getArguments());
//                fragment.onCreate(savedInstanceState);
//                fragment.onStart();
                //mDemoCollectionPagerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageSelected(int position) {
                Log.d("onPageSelected", "position: " + position);
//                Fragment fragment = mDemoCollectionPagerAdapter.getItem(position);
//                fragment.onCreate(savedInstanceState);
////                fragment.onStart();
//                Log.d("onPageSelected", "fragment: " + fragment.getArguments());
//                if(position==1)
                    mDemoCollectionPagerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("onStateChanged", "state: " + state);
                Log.d("onStateChanged", "getCurrentItem: " + mViewPager.getCurrentItem());
            }
        });
    }
    /*
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_issue);

        // Create an adapter that when requested, will return a fragment representing an object in
        // the collection.
        //
        // ViewPager and its adapters use support library fragments, so we must use
        // getSupportFragmentManager.
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());

        // Set up action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.issue_pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, MainActivity.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     *
     * FragmentStatePagerAdapter  会丢fragment（）,   而FragmentPagerAdapter却不会。
     */
    public static class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter

    {
        List<Fragment> fragmentList;

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentList = new ArrayList<Fragment>(3);
            fragmentList.add(new EbayOrderFragment());
            fragmentList.add(new AmazonOrderFragment());
            fragmentList.add(new AliexpressOrderFragment());
        }

        @Override
        public Fragment getItem(int i) {
            Log.d("getItem", "index: " + i);
            Fragment fragment = fragmentList.get(i);

            Bundle args = new Bundle();
            args.putInt("num", i); // Our object is just an integer :-P
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 100-object collection.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            switch (position){
                case 0:
                    title = "eBay订单";
                    break;
                case 1:
                    title = "Amazon订单";
                    break;
                case 2:
                    title = "速卖通订单";
                    break;
            }
            return title;
        }

        @Override
        public int getItemPosition(Object object){
            return PagerAdapter.POSITION_NONE;
        }
    }


}
