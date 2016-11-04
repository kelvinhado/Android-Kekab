package com.kelvinhado.kebab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelvinhado.kebab.model.Shop;

import java.util.List;

/**
 * Created by kelvin on 03/11/2016.
 */

public class ShopListAdapter extends ArrayAdapter {

    List<Shop> listShop;
    LayoutInflater mInflater;
    Context context;

    public ShopListAdapter(Context context, List<Shop> list) {
        super(context, 0, list);
        this.listShop = list;
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.fragment_list_item,parent,false);
            holder = new ViewHolder();

            // we do the mapping with our xml views
            holder.shopName = (TextView) convertView.findViewById(R.id.tvShopName);
            holder.shopAddress =(TextView) convertView.findViewById(R.id.tvShopAddress);
            holder.shopPrice =(TextView) convertView.findViewById(R.id.tvPrice);
            holder.shopPicture =(ImageView) convertView.findViewById(R.id.imgShopThumb);
//            holder.shopRanking =(TextView) convertView.findViewById(R.id.tvShopAddress);

            // we set a tag to our view to re-use it
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        // we finally set our values here
        Shop shop = listShop.get(position);
        holder.shopName.setText(shop.getName());
        holder.shopAddress.setText(shop.getAddress());
        holder.shopPrice.setText(shop.getPrice().toString());
//        holder.shopPicture.setImageDrawable(shop.getLastName());
        return convertView;

    }

    static class ViewHolder
    {
        TextView shopName;
        TextView shopAddress;
        TextView shopPrice;
//        TextView shopRanking;
        ImageView shopPicture;
    }
}