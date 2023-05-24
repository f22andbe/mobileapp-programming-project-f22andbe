package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class AminoAcidViewAdapter extends RecyclerView.Adapter<AminoAcidViewAdapter.AminoAcidViewHolder> {

    Context context;
    List<AminoAcid> aminoList;

    public AminoAcidViewAdapter(Context context, List<AminoAcid> aminoList) {
        this.context = context;
        this.aminoList = aminoList;
    }

    /* this gets called everytime the adapter needs to create a new viewholder
    *  it inflates the view defined in res/layout/recycler_layout.xml, passes that
    *  to the constructor for the viewholder that can use it to create a viewholder object
    *  which is then passed back to the adapter to show when needed */
    @Override
    public AminoAcidViewAdapter.AminoAcidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        //set the layout defined in recycler_layout.xml as the layout for the view
        View view = inflater.inflate(R.layout.recycler_layout, parent, false);

        return new AminoAcidViewAdapter.AminoAcidViewHolder(view);
    }

    /* this gets called every time a viewholder is about to be displayed on screen, it binds
     * the data to be displayed in the viewholder to it
     */
    @Override
    public void onBindViewHolder(AminoAcidViewAdapter.AminoAcidViewHolder holder, int position) {
        //String imageUrl = aminoList.get(position).auxdata.getImg();
        holder.aminoName.setText("Name: " + aminoList.get(position).getName());

        holder.aminoThreeLetterSymbol.setText("Three letter symbol: " + aminoList.get(position).auxdata.getThreeLetterSymbol());
        holder.aminoOneLetterSymbol.setText("One letter symbol: " + aminoList.get(position).auxdata.getOneLetterSymbol() );
        holder.aminoOneLetterSymbol.setText("Class: " + aminoList.get(position).auxdata.getCategory() );
        //holder.aminoWiki.setText("Wiki: " + aminoList.get(position).auxdata.getWikipage());

        /* the the background color of the viewholder */
        if((position % 2) == 0) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.lightgrey));
        }else{
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.lightblue));
        }

        /* This is the click listener for viewholder, everytime there
         * is a click it packs information into an intent and starts
         * the DetailActivity
         */
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

    /* this defines the dataitems that the viewadapter manages */
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
