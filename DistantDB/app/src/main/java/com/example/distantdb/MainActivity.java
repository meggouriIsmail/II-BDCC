package com.example.distantdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText edit;
    ListView listTasks;
    ArrayAdapter<String> adapter = null;
    // String url = "http://10.0.2.2:80/calendar/"
    String url = "http://192.168.0.156/calender/";

    ArrayList<String> DATA = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listTasks = findViewById(R.id.listTasks);
        btn = findViewById(R.id.addButton);
        edit = findViewById(R.id.editTask);

        //Création  d'instance de la classe Retrofit pour envoyer des requêtes HTTP et gérer les réponses.
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        // Création d'nstance de l'interface myapi à l'aide de Retrofit pour appeler les services Web de l'API.
        myapi api = retrofit.create(myapi.class);

        // L'objet Call<List<Task>> est utilisé pour exécuter la requête et recevoir la réponse, qui sera une liste d'objets Task.
        Call<List<Task>> call = api.getAllTasks();

        // L'exécution de la requête de manière asynchrone en utilisant enqueue(), la requête sera effectuée en arrière-plan sans bloquer le thread principal.
        loadData(call);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process();
            }
        });


    }

    private void loadData(Call<List<Task>> call) {
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                List<Task> data = response.body();

                adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, Listin(data));
                listTasks.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        });
    }

    public void process() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        myapi api = retrofit.create(myapi.class);
        final String task = edit.getText().toString();
        Call<Task> call = api.addTask(task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {

                edit.setText("");

                Log.i("reponse retrofit", response.toString());
                Toast.makeText(getApplicationContext(), "Inseré avec success", Toast.LENGTH_LONG).show();
                int id = Integer.parseInt(DATA.get(DATA.size() - 1).split(":")[0]);
                DATA.add(String.valueOf(id + 1) + ": " + task);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });


    }


    ArrayList Listin(List<Task> l) {
        for (int i = 0; i < l.size(); i++) {
            DATA.add(l.get(i).getId() + ": " + l.get(i).getTask());
        }
        return DATA;
    }
}





