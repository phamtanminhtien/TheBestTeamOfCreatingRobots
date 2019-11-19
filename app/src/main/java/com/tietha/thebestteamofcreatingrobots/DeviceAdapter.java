package com.tietha.thebestteamofcreatingrobots;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class DeviceAdapter extends ArrayAdapter<DeviceTac> {

    private Context context;
    private int resource;
    private List<DeviceTac> arrContact;

    public DeviceAdapter(Context context, int resource, ArrayList<DeviceTac> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_devices, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DeviceTac deviceTac = arrContact.get(position);
        viewHolder.name.setText(deviceTac.getmName());
        viewHolder.address.setText(deviceTac.getmAddress());
        return convertView;
    }


    public class ViewHolder {
        TextView name, address;

    }
}