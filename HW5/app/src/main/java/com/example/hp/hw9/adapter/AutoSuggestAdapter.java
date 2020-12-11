package com.example.hp.hw9.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.hp.hw9.models.PlaceApi;

import java.util.ArrayList;

public class AutoSuggestAdapter extends ArrayAdapter implements Filterable{
           ArrayList<String> results;
           Context context;
           int resource;
           PlaceApi placeApi = new PlaceApi();

           public AutoSuggestAdapter(Context context, int resId){
               super(context, resId);
               this.context = context;
               this.resource = resId;
           }
             @Override
           public int getCount(){
               return results.size();
           }
           @Override
           public String getItem(int pos){
               return results.get(pos);
           }
           @NonNull
           @Override
           public Filter getFilter(){
               Filter filter = new Filter() {
                   @Override
                   protected FilterResults performFiltering(CharSequence constraint) {
                       FilterResults filterResults = new FilterResults();
                       if(constraint!=null){
                           results = placeApi.autoComplete(constraint.toString());

                           filterResults.values = results;
                           filterResults.count = results.size();
                       }
                       return filterResults;
                   }

                   @Override
                   protected void publishResults(CharSequence constraint, FilterResults results) {
                       if(results!=null && results.count>0){
                           notifyDataSetChanged();
                       }
                       else{
                           notifyDataSetInvalidated();
                       }
                   }
               };
               return filter;
           }

}