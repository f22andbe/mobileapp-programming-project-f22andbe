package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class AminoAcidViewAdapter extends RecyclerView.Adapter<AminoAcidViewAdapter.AminoAcidViewHolder> {

    Context context;
    List<AminoAcid> aminoList;

    public AminoAcidViewAdapter(Context context, List<AminoAcid> aminoList) {
        this.context = context;
        this.aminoList = aminoList;
    }

    @Override
    public AminoAcidViewAdapter.AminoAcidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_layout, parent, false);

        return new AminoAcidViewAdapter.AminoAcidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AminoAcidViewAdapter.AminoAcidViewHolder holder, int position) {
        String imageUrl = aminoList.get(position).auxdata.getImg();
        holder.aminoName.setText("Name: " + aminoList.get(position).getName());

        holder.aminoThreeLetterSymbol.setText("Three letter symbol: " + aminoList.get(position).auxdata.getThreeLetterSymbol());
        holder.aminoOneLetterSymbol.setText("One letter symbol: " + aminoList.get(position).auxdata.getOneLetterSymbol() );
        holder.aminoOneLetterSymbol.setText("Class: " + aminoList.get(position).auxdata.getCategory() );
        //holder.aminoWiki.setText("Wiki: " + aminoList.get(position).auxdata.getWikipage());



        if((position % 2) == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.lightgrey));
        }else{
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.lightblue));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("aminoname", aminoList.get(position).getName());
                intent.putExtra("aminothreelettersymbol", aminoList.get(position).auxdata.getThreeLetterSymbol());
                intent.putExtra("aminoonelettersymbol", aminoList.get(position).auxdata.getOneLetterSymbol());
                intent.putExtra("aminowikiurl", aminoList.get(position).auxdata.getWikipage());
                intent.putExtra("aminoimgurl", aminoList.get(position).auxdata.getImg());

                context.startActivity(intent);
            }
        });


    }



    public int getItemCount() {
        return aminoList.size();
    }

    public static class AminoAcidViewHolder extends RecyclerView.ViewHolder {

        TextView aminoName;
        TextView aminoThreeLetterSymbol;
        TextView aminoOneLetterSymbol;
        TextView aminoCategory;
        TextView aminoWiki;
        ImageView aminoImage; // we now also have an ImageView in the ViewHolder

        public AminoAcidViewHolder(@NonNull View itemView)  {
            super(itemView);
            aminoName = itemView.findViewById(R.id.aminoname);
            aminoThreeLetterSymbol = itemView.findViewById(R.id.aminothreeletter);
            aminoOneLetterSymbol = itemView.findViewById(R.id.aminooneletter);
            aminoCategory = itemView.findViewById(R.id.aminocategory);
            aminoWiki = itemView.findViewById(R.id.aminowiki);
            aminoImage = itemView.findViewById(R.id.aminoimage); //here we get a reference to it

        }


    }


}
