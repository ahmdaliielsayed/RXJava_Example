package com.example.rxjavaexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.rxjavaexample.observable.AsyncSubjectObservableActivity;
import com.example.rxjavaexample.observable.BehaviorSubjectObservableActivity;
import com.example.rxjavaexample.observable.ColdObservableActivity;
import com.example.rxjavaexample.observable.HotConnectableObservableActivity;
import com.example.rxjavaexample.observable.PublishSubjectObservableActivity;
import com.example.rxjavaexample.observable.ReplaySubjectObservableActivity;
import com.example.rxjavaexample.operators.ControlThreadingActivity;
import com.example.rxjavaexample.operators.CreateActivity;
import com.example.rxjavaexample.operators.DebounceActivity;
import com.example.rxjavaexample.operators.FilterActivity;
import com.example.rxjavaexample.operators.FromArrayActivity;
import com.example.rxjavaexample.operators.JustActivity;
import com.example.rxjavaexample.operators.RangeActivity;
import com.example.rxjavaexample.operators.MapActivity;
import com.example.rxjavaexample.retrofit.ui.activities.ManyPostsActivity;

public class MainActivity extends AppCompatActivity {

    Button btnColdObservable, btnHotConnectableObservable, btnPublishSubjectObservable,
            btnBehaviorSubjectObservable, btnReplaySubjectObservable, btnAsyncSubjectObservable,
            btnCreate, btnJust, btnFromArray, btnRange, btnControlThreading, btnMap, btnDebounce,
            btnFilter, btnRXJavaRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnColdObservable = findViewById(R.id.btnColdObservable);
        btnColdObservable.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ColdObservableActivity.class)));

        btnHotConnectableObservable = findViewById(R.id.btnHotConnectableObservable);
        btnHotConnectableObservable.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HotConnectableObservableActivity.class)));

        btnPublishSubjectObservable = findViewById(R.id.btnPublishSubjectObservable);
        btnPublishSubjectObservable.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PublishSubjectObservableActivity.class)));

        btnBehaviorSubjectObservable = findViewById(R.id.btnBehaviorSubjectObservable);
        btnBehaviorSubjectObservable.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BehaviorSubjectObservableActivity.class)));

        btnReplaySubjectObservable = findViewById(R.id.btnReplaySubjectObservable);
        btnReplaySubjectObservable.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReplaySubjectObservableActivity.class)));

        btnAsyncSubjectObservable = findViewById(R.id.btnAsyncSubjectObservable);
        btnAsyncSubjectObservable.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AsyncSubjectObservableActivity.class)));

        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CreateActivity.class)));

        btnJust = findViewById(R.id.btnJust);
        btnJust.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, JustActivity.class)));

        btnFromArray = findViewById(R.id.btnFromArray);
        btnFromArray.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FromArrayActivity.class)));

        btnRange = findViewById(R.id.btnRange);
        btnRange.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RangeActivity.class)));

        btnControlThreading = findViewById(R.id.btnControlThreading);
        btnControlThreading.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ControlThreadingActivity.class)));

        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MapActivity.class)));

        btnDebounce = findViewById(R.id.btnDebounce);
        btnDebounce.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DebounceActivity.class)));

        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FilterActivity.class)));

        btnRXJavaRetrofit = findViewById(R.id.btnRXJavaRetrofit);
        btnRXJavaRetrofit.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ManyPostsActivity.class)));
    }
}
