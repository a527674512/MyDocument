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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lhf.mydocument.R;
import com.example.lhf.mydocument.bean.Fruit;
import com.example.lhf.mydocument.bean.Msg;

import java.util.List;

/**
 * Created by lenovo on 2017/11/16.
 */

public class ChatMsgAdapter extends ArrayAdapter<Msg> {

    private int resourceId;

    public ChatMsgAdapter(@NonNull Context context, @LayoutRes int textViewResourceId, @NonNull List<Msg> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.llLeft =  view.findViewById(R.id.left_layout);
            viewHolder.llRight = view.findViewById(R.id.right_layout);
            viewHolder.tvLeft = view.findViewById(R.id.left_msg);
            viewHolder.tvRight = view.findViewById(R.id.Right_msg);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        if(msg.getType() == Msg.TYPE_RECEIVED){
            viewHolder.llLeft.setVisibility(View.VISIBLE);
            viewHolder.llRight.setVisibility(View.GONE);
            viewHolder.tvLeft.setText(msg.getContent());
        }else if(msg.getType() == Msg.TYPE_SEND){
            viewHolder.llLeft.setVisibility(View.GONE);
            viewHolder.llRight.setVisibility(View.VISIBLE);
            viewHolder.tvRight.setText(msg.getContent());

        }

        return view;


    }

    class ViewHolder{
        LinearLayout llLeft;
        LinearLayout llRight;

        TextView tvLeft;
        TextView tvRight;
    }



}
