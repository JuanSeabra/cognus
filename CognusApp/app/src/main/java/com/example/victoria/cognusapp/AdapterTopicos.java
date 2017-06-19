package com.example.victoria.cognusapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by victoria on 19/06/17.
 */

public class AdapterTopicos extends BaseAdapter {

    private final List<String> topicos;
    private final Activity act;

    public AdapterTopicos(List<String> topicos, Activity act) {
        this.topicos = topicos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return topicos.size();
    }

    @Override
    public Object getItem(int position) {
        return topicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_layout_cadastro_topicos, parent, false);
        String topico = topicos.get(position);

        TextView lblTopico = (TextView) view.findViewById(R.id.lblTopico);

        lblTopico.setText(topico);
        return view;
    }
}
