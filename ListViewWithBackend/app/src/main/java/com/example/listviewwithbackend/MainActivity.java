package com.example.listviewwithbackend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listviewwithbackend.pojo.User;
import com.example.listviewwithbackend.retorfit.NetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String APP_TAG = "LiashenkoApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ShowAllUsersInListView(View view) {
        NetworkService.getInstance()
                .getJSONApi()
                .getAllUsers()
                .enqueue(new Callback<ArrayList<User>>() {
                    ArrayList<User> users;
                    final ListView listView = findViewById(R.id.listView);

                    @Override
                    public void onResponse(@NonNull Call<ArrayList<User>> call, @NonNull Response<ArrayList<User>> response) {
                        users = response.body();
                        Log.i(APP_TAG, "Collection successfully received");
                        if (users != null) {
                            sendUsersInListView();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<User>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(MainActivity.this,
                                "Error! Don't get Data!", Toast.LENGTH_SHORT).show();
                    }

                    public void sendUsersInListView() {
                        listView.setAdapter(null);
                        String[] stringUsers = new String[users.size()];
                        for (int i = 0; i < users.size(); i++) {
                            stringUsers[i] = users.get(i).toString();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                                android.R.layout.simple_list_item_1, stringUsers);
                        listView.setAdapter(adapter);
                    }
                });
    }
}