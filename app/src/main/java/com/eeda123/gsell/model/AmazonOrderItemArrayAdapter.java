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

package com.eeda123.gsell.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.truiton.bottomnavigation.R;

/**
 * Created by a13570610691 on 2017/3/22.
 */

public class AmazonOrderItemArrayAdapter extends ArrayAdapter<AmazonOrderItemModel> {
    private final Activity context;
    private final AmazonOrderItemModel[] models;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    public AmazonOrderItemArrayAdapter(Activity context, AmazonOrderItemModel[] models) {
        super(context, R.layout.amazon_order_list_item, models);
        this.context = context;
        this.models = models;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.amazon_order_list_item, null);
            // configure view holder
            ViewHolder vhPlatform = new ViewHolder();
            vhPlatform.text = (TextView) rowView.findViewById(R.id.tvPlatform);
            ViewHolder vhShopName = new ViewHolder();
            vhShopName.text = (TextView) rowView.findViewById(R.id.tvShopName);

            //rowView.setTag(viewHolder);
        }

        // fill data
        AmazonOrderItemModel s = models[position];

        TextView tvPlatform = (TextView) rowView.findViewById(R.id.tvPlatform);
        tvPlatform.setText(s.getStrPlatform());

        TextView tvShopName = (TextView) rowView.findViewById(R.id.tvShopName);
        tvShopName.setText(s.getStrShopName());

        TextView tvOrderNo = (TextView) rowView.findViewById(R.id.tvOrderNo);
        tvOrderNo.setText(String.valueOf(s.getStrOrderNo()));

        TextView tvSalesRecordNo = (TextView) rowView.findViewById(R.id.tvSalesRecordNo);
        tvSalesRecordNo.setText(String.valueOf(s.getSalesRecordNo()));

        TextView tvBuyerName = (TextView) rowView.findViewById(R.id.tvBuyerName);
        tvBuyerName.setText(String.valueOf(s.getStrBuyerName()));

        TextView tvSku = (TextView) rowView.findViewById(R.id.tvSku);
        tvSku.setText(String.valueOf(s.getStrSku()));

        TextView tvCurCode = (TextView) rowView.findViewById(R.id.tvCurCode);
        tvCurCode.setText(String.valueOf(s.getStrCurrencyCode()));
        TextView tvAmount = (TextView) rowView.findViewById(R.id.tvAmount);
        tvAmount.setText(String.valueOf(s.getdTotal()));
        TextView tvStatus = (TextView) rowView.findViewById(R.id.tvStatus);
        tvStatus.setText(String.valueOf(s.getStrStatus()));
        TextView tvCreateTime = (TextView) rowView.findViewById(R.id.tvCreateTime);
        tvCreateTime.setText(String.valueOf(s.getStrCreateDate()));
        return rowView;
    }
}
