package com.example.lhf.mydocument.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lhf.mydocument.R;
import com.example.lhf.mydocument.bean.Fruit;

import java.util.List;

/**
 * Created by lenovo on 2017/11/16.
 */

public class ListviewAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public ListviewAdapter(@NonNull Context context, @LayoutRes int textViewResourceId, @NonNull List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.ivListItem =  view.findViewById(R.id.iv_listview_item);
            viewHolder.tvListItem = view.findViewById(R.id.tv_listview_item);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        };
        viewHolder.ivListItem.setImageResource(fruit.getImageId());
        viewHolder.tvListItem.setText(fruit.getName());

        return view;


    }

    class ViewHolder{
        ImageView ivListItem;
        TextView tvListItem;
    }


    @Nullable
    @Override
    public Fruit getItem(int position) {
        return super.getItem(position);
    }
}
