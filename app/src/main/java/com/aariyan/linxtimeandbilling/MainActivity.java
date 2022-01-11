package com.aariyan.linxtimeandbilling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aariyan.linxtimeandbilling.Adapter.UserListAdapter;
import com.aariyan.linxtimeandbilling.Model.UserListModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserListAdapter adapter;

    private List<UserListModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("User Login");

        intiUI();
    }

    private void intiUI() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setDummyData();
    }

    private void setDummyData() {
        for (int i = 0; i < 10; i++) {
            list.add(new UserListModel(
                    i, "Name:  " + i,
                    "" + i + "" + i + "" + i + "" + i
            ));
        }

        adapter = new UserListAdapter(MainActivity.this, list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}