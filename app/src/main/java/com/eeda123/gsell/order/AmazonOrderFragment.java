

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

package com.eeda123.gsell.order;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.eeda123.gsell.MainActivity;
import com.eeda123.gsell.model.AmazonOrderItemArrayAdapter;
import com.eeda123.gsell.model.AmazonOrderItemModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.truiton.bottomnavigation.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class AmazonOrderFragment extends ListFragment   implements SwipeRefreshLayout.OnRefreshListener{
    public static final String TAG = "retrofit2 Call";
    private boolean isRefresh = false;//是否刷新中
    private SwipeRefreshLayout mSwipeLayout;

    private AmazonOrderItemModel[] models = new AmazonOrderItemModel[0];
    private ArrayAdapter<AmazonOrderItemModel> adapter = null;

    public static AmazonOrderFragment newInstance() {
        AmazonOrderFragment fragment = new AmazonOrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        return inflater.inflate(R.layout.fragment_item_one, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRefresh();
        if(models.length==0)
            getData();
    }

    private void getData() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
//    http://192.168.0.108:8080/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EedaService service = retrofit.create(EedaService.class);

        Call<HashMap<String, Object>> call = service.list();

        call.enqueue(eedaCallback());
    }

    @NonNull
    private Callback<HashMap<String,Object>> eedaCallback() {
        return new Callback<HashMap<String,Object>>() {
            @Override
            public void onResponse(Call<HashMap<String,Object>> call, Response<HashMap<String,Object>> response) {
                // The network call was a success and we got a response
                Log.d(TAG, "server contacted at: " + call.request().url());
                HashMap<String,Object> json = response.body();
                ArrayList<Map> recList =  (ArrayList<Map>)json.get("data");
                models = new AmazonOrderItemModel[recList.size()];
                int index = 0;
                for(Map<String, Object> rec: recList){
                    AmazonOrderItemModel model = new AmazonOrderItemModel("amazon",
                            "",//rec.get("SELLER_USER_ID")==null?"":rec.get("SELLER_USER_ID").toString(),
                            rec.get("AMAZON_ORDER_ID").toString(),
                            rec.get("BUYER_NAME")==null?"":rec.get("BUYER_NAME").toString(),
                            rec.get("SKU")==null?"":rec.get("SKU").toString(),
                            rec.get("ORDER_CURRENCY_CODE")==null?"":rec.get("ORDER_CURRENCY_CODE").toString(),
                            rec.get("TOTAL_AMOUNT")==null?0:Double.valueOf(rec.get("TOTAL_AMOUNT").toString()),
                            rec.get("FULFILLMENT_CHANNEL")==null?"":rec.get("FULFILLMENT_CHANNEL").toString(),
                            "",
                            rec.get("ORDER_STATUS")==null?"":rec.get("ORDER_STATUS").toString(),
                            rec.get("PURCHASE_DATE").toString());
                    models[index] = model;
                    index++;
                }

                adapter = new AmazonOrderItemArrayAdapter(getActivity(), models);
                setListAdapter(adapter);

//                if(adapter!=null){
//                    adapter.clear();
//                    adapter.addAll(models);
//                }else {
//                    adapter = new OrderItemArrayAdapter(getActivity(), models);
//                    setListAdapter(adapter);
//                }
            }
            @Override
            public void onFailure(Call<HashMap<String,Object>> call, Throwable t) {
                // the network call was a failure
                Log.d(TAG, "call failed against the url: " + call.request().url());
            }
        };
    }

    public interface EedaService {
        @GET("app/order/amazonOrderList")
        Call<HashMap<String,Object>> list();
    }

    private void initRefresh() {
        //设置SwipeRefreshLayout
        mSwipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipeLayout);

        mSwipeLayout.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);

        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置圆圈的大小
        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE);

        //设置下拉刷新的监听
        mSwipeLayout.setOnRefreshListener(this);
    }

    /*
     * 监听器SwipeRefreshLayout.OnRefreshListener中的方法，当下拉刷新后触发
     */
    @Override
    public void onRefresh() {
        //检查是否处于刷新状态
        if (!isRefresh) {
            isRefresh = true;
            //加载网络数据
            getData();
            //模拟加载网络数据，这里设置4秒，正好能看到4色进度条
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    //显示或隐藏刷新进度条
                    mSwipeLayout.setRefreshing(false);
                    //修改adapter的数据
//                    data.add("这是新添加的数据");
//                    mAdapter.notifyDataSetChanged();
                    isRefresh = false;
                }
            }, 4000);
        }
    }
}
