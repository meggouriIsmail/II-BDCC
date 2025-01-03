package com.example.roomdatabasedemo;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    // Initialize variable
    private final List<Country> countries;
    private final Activity context;
    private RoomDB database;


    //Create constructor
    public MainAdapter(Activity context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position) {

        // Initialize main country
        final Country country = countries.get(position);

        // Initialize database
        database = RoomDB.getInstance(context);
        // Set text on text view
        holder.textCountry.setText(country.getName());
        holder.textCap.setText(country.getCapital());
        holder.textPop.setText(String.valueOf(country.getPopulation()));

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Initialize main country
                Country c = countries.get(holder.getAdapterPosition());

                // Get id
                final int sID = c.getID();

                // Get data
                String sName = c.getName();
                int sPop = c.getPopulation();
                String sCap = c.getCapital();

                // create dialog
                final Dialog dialog = new Dialog(context);

                // set content view
                dialog.setContentView(R.layout.dialog_update);

                //Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;

                //Initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                //Set layout
                dialog.getWindow().setLayout(width, height);

                //show dialog
                dialog.show();

                //Initialize and assign variable
                final EditText editName = dialog.findViewById(R.id.countryInput);
                final EditText editCap = dialog.findViewById(R.id.capitalInput);
                final EditText editPop = dialog.findViewById(R.id.populationInput);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                // Set text on edit text
                editName.setText(sName);
                editCap.setText(sCap);
                editPop.setText(String.valueOf(sPop));

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Dismiss Dialog
                        dialog.dismiss();

                        //Get Updated text from edit text
                        String uName = editName.getText().toString().trim();
                        String uCap = editCap.getText().toString().trim();
                        int uPop = Integer.parseInt(String.valueOf(editPop.getText()));

                        // Update text in database
                        database.mainDao().update(sID, uName, uPop, uCap);

                        //notify when country is updated
                        countries.clear();
                        countries.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }


        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize main country
                Country d = countries.get(holder.getAdapterPosition());

                // Delete text from database
                database.mainDao().delete(d);

                // Notify when country is deleted
                int position = holder.getAdapterPosition();
                countries.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, countries.size());

            }
        });


    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Initialize variable
        TextView textCountry;
        TextView textCap;
        TextView textPop;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable

            textCountry = itemView.findViewById(R.id.text_country);
            textCap = itemView.findViewById(R.id.text_capital);
            textPop = itemView.findViewById(R.id.text_population);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
