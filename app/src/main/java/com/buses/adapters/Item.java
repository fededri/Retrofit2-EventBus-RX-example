package com.buses.adapters;

import android.content.Context;
import android.view.View;

public interface Item {

	public Object getData();

	public void setData(Object data);

	public View getView(View view, Context context);

}
