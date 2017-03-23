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

import com.eeda123.gsell.model.HomeItemModel;
import com.truiton.bottomnavigation.R;

/**
 * Created by a13570610691 on 2017/3/22.
 */

public class HomeEbayItemArrayAdapter extends ArrayAdapter<HomeItemModel> {
    private final Activity context;
    private final HomeItemModel[] models;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }

    public HomeEbayItemArrayAdapter(Activity context, HomeItemModel[] models) {
        super(context, R.layout.home_list_ebay_item, models);
        this.context = context;
        this.models = models;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.home_list_ebay_item, null);
            // configure view holder
            ViewHolder vhPlatform = new ViewHolder();
            vhPlatform.text = (TextView) rowView.findViewById(R.id.tvPlatform);
            ViewHolder vhShopName = new ViewHolder();
            vhShopName.text = (TextView) rowView.findViewById(R.id.tvShopName);

            //rowView.setTag(viewHolder);
        }

        // fill data
        HomeItemModel s = models[position];

        TextView tvPlatform = (TextView) rowView.findViewById(R.id.tvPlatform);
        tvPlatform.setText(s.getStrPlatform());

        TextView tvShopName = (TextView) rowView.findViewById(R.id.tvShopName);
        tvShopName.setText(s.getStrShopName());

        TextView tvNoPay = (TextView) rowView.findViewById(R.id.tvNoPay);
        tvNoPay.setText(String.valueOf(s.getIntNoPay()));

        TextView tvOrderCount = (TextView) rowView.findViewById(R.id.tvOrderCount);
        tvOrderCount.setText(String.valueOf(s.getIntOrderCount()));

        TextView tvNoship = (TextView) rowView.findViewById(R.id.tvNoship);
        tvNoship.setText(String.valueOf(s.getIntNoShip()));

        return rowView;
    }
}
