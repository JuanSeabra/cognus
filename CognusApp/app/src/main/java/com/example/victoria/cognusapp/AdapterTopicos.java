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
import java.util.List;

/**
 * Created by victoria on 19/06/17.
 */

public class AdapterTopicos extends BaseAdapter implements Filterable {

    private List<String> topicos;
    private final Activity act;
    public List<String> orig;
    public boolean[] itemChecked;

    public AdapterTopicos(List<String> topicos, Activity act) {
        this.topicos = topicos;
        this.act = act;
        this.itemChecked = new boolean[topicos.size()];
    }

    @Override
    public int getCount() {
        return topicos.size();
    }

    @Override
    public String getItem(int position) {
        return topicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_layout_lista_topicos_checkbox, parent, false);
        String topico = topicos.get(position);

        TextView lblTopico = (TextView) view.findViewById(R.id.lblTopico);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        checkBox.setChecked(false);
        lblTopico.setText(topico);

        if(itemChecked[position]){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    itemChecked[position] = true;
                }else{
                    itemChecked[position] = false;
                }
            }
        });

        return view;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<String> results = new ArrayList<String>();
                if (orig == null)
                    orig = topicos;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final String g : orig) {
                            if (g.toLowerCase()
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
                topicos = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public List<String> getTopicosSelecionados(){
        List<String> lista_topicos  = new ArrayList<String >();

        for(int i = 0; i < itemChecked.length; i++){
            if(itemChecked[i] == true){
                String topico = getItem(i);
                lista_topicos.add(topico);
            }
        }

        return lista_topicos;
    }

}
