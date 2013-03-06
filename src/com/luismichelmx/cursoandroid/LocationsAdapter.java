package com.luismichelmx.cursoandroid;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationsAdapter extends ArrayAdapter<Locations> {

	private ArrayList<Locations> locations;
	
	
	
	public LocationsAdapter(Context context, int resource, ArrayList<Locations> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		
		this.locations = objects;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, null);
		}
		
		Locations l = locations.get(position);
		
		if( l != null)
		{
			TextView tv_location = (TextView) v.findViewById(R.id.textView1);
			TextView tv_city = (TextView) v.findViewById(R.id.textView2);
			ImageView im_icon = (ImageView) v.findViewById(R.id.imageView1);
			
			if( tv_location != null )
			{
				tv_location.setText( l.getLocation() );
			}
			
			if( tv_city != null)
			{
				tv_city.setText( l.getCity() );
			}
			
			if( im_icon != null )
			{
				im_icon.setImageResource( l.getIcon());
			}
			
		}
		
		return v;
		
	}

}
