package com.example.rxjavaexample.retrofit.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.rxjavaexample.R;
import com.example.rxjavaexample.retrofit.db.entity.Post;
import com.example.rxjavaexample.retrofit.ui.adapter.UserAdapter;
import com.example.rxjavaexample.retrofit.ui.viewmodel.ManyPostsViewModel;

import java.util.ArrayList;

public class ManyPostsActivity extends AppCompatActivity {

    ProgressBar progressBar;

    RecyclerView myRecyclerView;
    UserAdapter userAdapter;

    ManyPostsViewModel manyPostsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_posts);

        init();
        getData();
    }

    public void init() {
        progressBar = findViewById(R.id.progressBar);
        myRecyclerView = findViewById(R.id.recyvleView);

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);

//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        myRecyclerView.setHasFixedSize(true);

        // adapter
        userAdapter = new UserAdapter(this);
        myRecyclerView.setAdapter(userAdapter);

        // View Model
        manyPostsViewModel = ViewModelProviders.of(this).get(ManyPostsViewModel.class);
    }

    public void getData() {
        manyPostsViewModel.getManyPosts().observe(this, new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(ArrayList<Post> posts) {
                if (posts != null) {
                    progressBar.setVisibility(View.GONE);
                    fillData(posts);
                } else {
                    // error or make method to show the specified error
                }
            }
        });
    }

    public void fillData(ArrayList<Post> dataModelList) {
        userAdapter.setDataToAdapter(dataModelList);
    }
}