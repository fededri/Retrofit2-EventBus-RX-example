package com.buses.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.List;

public class AdapterGeneric extends BaseAdapter {

	protected Context context;
	protected List<Item> itemsList;

	public AdapterGeneric(Context context, List<Item> itemsList) {
		this.context = context;
		this.itemsList = itemsList;
	}

	public void remove(Item item) {
		itemsList.remove(item);
	}

	public void insert(Item item, int to) {
		itemsList.add(to, item);
	}

	public int getCount() {
		return itemsList != null ? itemsList.size() : 0;
	}

	public Object getItem(int position) {
		return itemsList != null ? itemsList.get(position) : null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		return itemsList != null ? itemsList.get(position).getView(convertView,
				context) : null;
	}

	public void setItemsList(List<Item> itemsList) {
		this.itemsList = itemsList;
	}

}
