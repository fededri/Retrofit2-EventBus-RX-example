package com.buses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.buses.adapters.Item;

/**
 * Created by federico on 21/05/2016.
 */
public class PathologyItem implements Item {
    Object data;

    public void setData(Object data) {
        this.data = data;
    }

    private boolean isStringText = false;

    public Pathology getData() {
        return (Pathology)data;
    }

    public View getView(View view, final Context context) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.pathology_item, null);
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_pathology);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.textView.setText(getData().getName());

        return view;
    }

    class ViewHolder {
        TextView textView;
    }

}
