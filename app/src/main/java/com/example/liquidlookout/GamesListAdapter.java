package com.example.liquidlookout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class GamesListAdapter extends ArrayAdapter<Games> {
    private String TAG = "gamesAdapter";
    private Context context;
    private int res;

    public GamesListAdapter(Context context, int resource, ArrayList<Games> list){
        super(context,resource,list);
        this.context=context;
        res=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //geting input name and pictures
        String nameIn = getItem(position).getName();
        int  imageUrl = getItem(position).getImgUrl();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(res,parent,false);

        TextView vieName = convertView.findViewById(R.id.nameOfGame);
        ImageView imagePic = convertView.findViewById(R.id.gamePic);
        ImageView BlackArrow = convertView.findViewById(R.id.blackArrow);

        vieName.setText(nameIn);
        imagePic.setImageResource(imageUrl);
        BlackArrow.setImageResource(R.drawable.arrow_right);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upcomingMatchIntent = new Intent(context,UpcomingMatchesActivity.class);
                context.startActivity(upcomingMatchIntent);
            }
        });

        return convertView;
    }




}
