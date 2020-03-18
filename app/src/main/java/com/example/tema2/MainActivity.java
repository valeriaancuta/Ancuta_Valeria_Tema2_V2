package com.example.tema2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addUser;
    private Button deleteUser;
    private EditText nume;
    private EditText nota;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<TestUser> testUsers = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        adapter = new MyRecyclerViewAdapter(testUsers);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

        addUser = findViewById(R.id.add);
        deleteUser=findViewById(R.id.delete);
        nume = findViewById(R.id.nume);
        nota = findViewById(R.id.nota);

        users.addAll(new ApplicationController().getAppDatabase().userDao().getAll());
        for (int i = 0; i < users.size(); i++) {
            testUsers.add(new TestUser(users.get(i).getName(), users.get(i).getMark()));
            adapter.notifyDataSetChanged();
        }

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final User user = new User(nume.getText().toString(), nota.getText().toString());
                new UserRepo(getApplicationContext()).insertTask(user, new OnUserRepositoryActionListener() {
                    @Override
                    public void actionSuccess() {
                        testUsers.add(new TestUser(nume.getText().toString(), nota.getText().toString()));
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new UserRepo(getApplicationContext()).getUserByName(nume.getText().toString());
                if (user != null) {
                    new UserRepo(getApplicationContext()).deleteTask(user, new OnUserRepositoryActionListener() {
                        @Override
                        public void actionSuccess() {
                            for (int i = 0; i < testUsers.size(); i++)
                                if (testUsers.get(i).getmText1().equals(nume.getText().toString()))
                                    testUsers.remove(i);
                                    adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }
}

