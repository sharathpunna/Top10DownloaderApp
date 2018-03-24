package com.example.punna.top10downloaderapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by punna on 2/14/2018.
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutresources;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> applications;

    public FeedAdapter(@NonNull Context context, int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.layoutresources=resource;
        this.layoutInflater=LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null) {
            convertView = layoutInflater.inflate(layoutresources, parent, false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
//        View view =layoutInflater.inflate(layoutresources,parent,false);
//        TextView tvName=(TextView)convertView.findViewById(R.id.tvname);
//        TextView tvartist=(TextView)convertView.findViewById(R.id.tvArtist);
//        TextView tvSummary=(TextView)convertView.findViewById(R.id.tvSummary);

        FeedEntry currentapp=applications.get(position);
        viewHolder.tvName.setText(currentapp.getName());
        viewHolder.tvartist.setText(currentapp.getArtist());
        viewHolder.tvsummary.setText(currentapp.getSummary());
        return convertView;

    }
    private class ViewHolder{
        final TextView tvName;
        final TextView tvartist;
        final TextView tvsummary;

        ViewHolder(View v){
            tvName=(TextView)v.findViewById(R.id.tvname);
            tvartist=(TextView)v.findViewById(R.id.tvArtist);
            tvsummary=(TextView)v.findViewById(R.id.tvSummary);
        }
    }
}
