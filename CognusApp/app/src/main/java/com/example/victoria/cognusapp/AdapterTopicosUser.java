package com.example.victoria.cognusapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.Topico;

/**
 * Created by d-zero on 18/07/17.
 */

public class AdapterTopicosUser extends BaseAdapter implements Filterable {

    private List<Topico> topicos;
    private final Activity act;
    public List<Topico> orig;

    public AdapterTopicosUser(List<Topico> topicos, Activity act) {
        this.topicos = topicos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return topicos.size();
    }

    @Override
    public Topico getItem(int position) {
        return topicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_layout_lista_topicos, parent, false);
        Topico topico = topicos.get(position);
        final Integer posicao;

        TextView lblTopico = (TextView) view.findViewById(R.id.lblTopico);

        lblTopico.setText(topico.getdescricao_topico());

        return view;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Topico> results = new ArrayList<Topico>();

                if (orig == null)
                    orig = topicos;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Topico g : orig) {
                            if (g.getdescricao_topico().toLowerCase()
                                    .contains(constraint.toString().toLowerCase()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                topicos = (ArrayList<Topico>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
