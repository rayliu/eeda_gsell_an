
package com.eeda123.gsell;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.truiton.bottomnavigation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{
    public static String HOST_URL = "http://192.168.0.100:8080/";
//    public static String HOST_URL = "http://192.168.0.4:8080/";
//    public static String HOST_URL = "https://wms.eeda123.com/";
    int selectedId = 0;
    private boolean isExit;

    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindView(R.id.rbOrder)
    RadioButton rbOrder;
    @BindView(R.id.rbMsg)
    RadioButton rbMsg;
    @BindView(R.id.rbIssue)
    RadioButton rbIssue;
    @BindView(R.id.rbMe)
    RadioButton rbMe;

    HomeFragment homeFragment;
    OrderFragment orderFragment;
    MsgFragment msgFragment;
    MyConfigFragment myConfigFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().hide();//隐藏actionBar
//        登录页面
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        showHomeFragment();
    }

    @Override
    public void onBackPressed() {

        if(!isExit){
            Toast.makeText(getBaseContext(), "再按一次退出程序", Toast.LENGTH_LONG).show();
            isExit = true;
            //EventBus.getDefault().post(isExit);
        }else{
            super.onBackPressed();
        }

    }

    @OnClick({R.id.rbHome, R.id.rbOrder, R.id.rbMsg, R.id.rbIssue, R.id.rbMe})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbHome:
                showHomeFragment();
                break;
            case R.id.rbOrder:
                showOrderFragment();
                break;
            case R.id.rbMsg:
                showMsgFragment();
                break;
            case R.id.rbIssue:
                showIssueFragment();
                break;
            case R.id.rbMe:
                showMineFragment();
                break;
        }
    }

    public void showHomeFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if(homeFragment == null){
            homeFragment = HomeFragment.newInstance();
            fragmentTransaction.add(R.id.frame_layout, homeFragment);
        }
        commitShowFragment(fragmentTransaction, homeFragment);
    }

    public void showOrderFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(orderFragment == null){
            orderFragment = OrderFragment.newInstance();
            fragmentTransaction.add(R.id.frame_layout, orderFragment);
        }

        commitShowFragment(fragmentTransaction,orderFragment);
    }

    public void showMsgFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(msgFragment == null){
            msgFragment = MsgFragment.newInstance();
            fragmentTransaction.add(R.id.frame_layout, msgFragment);
        }

        commitShowFragment(fragmentTransaction, msgFragment);
    }

    public void showIssueFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(msgFragment == null){
            msgFragment = MsgFragment.newInstance();
            fragmentTransaction.add(R.id.frame_layout, msgFragment);
        }

        commitShowFragment(fragmentTransaction, msgFragment);
    }

    public void showMineFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fragmentTransaction);
        if(myConfigFragment == null){
            myConfigFragment = MyConfigFragment.newInstance();
            fragmentTransaction.add(R.id.frame_layout, myConfigFragment);
        }

        commitShowFragment(fragmentTransaction, myConfigFragment);
    }

    public void commitShowFragment(FragmentTransaction fragmentTransaction,Fragment fragment){
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction){
        hideFragment(fragmentTransaction,homeFragment);
        hideFragment(fragmentTransaction,orderFragment);
        hideFragment(fragmentTransaction,msgFragment);
        hideFragment(fragmentTransaction,myConfigFragment);
    }

    private void hideFragment(FragmentTransaction fragmentTransaction,Fragment fragment){
        if(fragment!=null){
            fragmentTransaction.hide(fragment);
        }
    }
}
