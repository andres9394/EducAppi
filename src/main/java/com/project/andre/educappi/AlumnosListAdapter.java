package com.project.andre.educappi;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andre on 15/05/2017.
 */

public class AlumnosListAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> nombres;
    private ArrayList<String> curps;

    public AlumnosListAdapter( Context context, int resource, int textViewResourceId, String[] strings){
        super(context, resource, textViewResourceId, strings);
        this.context = context;
        nombres = new ArrayList<>();
        curps = new ArrayList<>();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vista = inflater.inflate(R.layout.row_layout, parent, false);

        TextView nombre = (TextView) vista.findViewById(R.id.txtvNombre);
        TextView curp = (TextView) vista.findViewById(R.id.txtvCurp);

        nombre.setText(nombres.get(position));
        curp.setText(curps.get(position));
        return vista;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setNombres(ArrayList<String> nombres){
        this.nombres = nombres;
    }

    public void setCurps(ArrayList<String> curps){
        this.curps = curps;
    }

}
