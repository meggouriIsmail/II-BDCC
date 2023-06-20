package com.example.distantdb;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<String> tasks;
    private final Activity context;
    String url = "http://192.168.0.156/calender/";

    public MainAdapter(List<String> tasks, Activity context) {
        this.tasks = tasks;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tasks, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.ViewHolder holder, int position) {
        holder.textTask.setText(tasks.get(position));

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        final myapi api = retrofit.create(myapi.class);
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int taskId = Integer.parseInt(tasks.get(holder.getAdapterPosition()).split(":")[0]);
                Call<Task> call = api.deleteTask(taskId);
                call.enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        Log.i("reponse retrofit", response.toString());
                        // Notify when country is deleted
                        int position = holder.getAdapterPosition();
                        tasks.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, tasks.size());
                    }
                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Log.i("failure ", t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Initialize variable
        TextView textTask;
        ImageView btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Assign variable

            textTask = itemView.findViewById(R.id.task);
            btDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
