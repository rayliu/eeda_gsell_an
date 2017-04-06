
package com.eeda123.gsell;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.truiton.bottomnavigation.R;

public class MainActivity extends AppCompatActivity{
    public static String HOST_URL = "http://192.168.0.105:8080/";
//    public static String HOST_URL = "https://wms.eeda123.com/";
    int selectedId = 0;
    HomeFragment homeFragment;
    OrderFragment orderFragment;
    EbayMsgFragment msgFragment;
    MyConfigFragment myConfigFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();//隐藏actionBar
//        登录页面
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        homeFragment = HomeFragment.newInstance();
        msgFragment = EbayMsgFragment.newInstance();
        orderFragment = OrderFragment.newInstance();
        myConfigFragment = MyConfigFragment.newInstance();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        //防止连续点菜单，连续加载相同的fragment
                        if(selectedId ==item.getItemId())
                            return true;

                        selectedId = item.getItemId();
                        switch (item.getItemId()) {
                            case R.id.action_item1:

                                selectedFragment = homeFragment;
                                selectedId = item.getItemId();
                                break;
                            case R.id.action_msg:
                                selectedFragment = msgFragment;
                                break;
                            case R.id.action_issue:
                                selectedFragment = orderFragment;
                                break;
                            case R.id.action_item3:
                                selectedFragment = myConfigFragment;
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();


        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }


}
