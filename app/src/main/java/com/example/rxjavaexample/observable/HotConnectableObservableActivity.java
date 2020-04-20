package com.example.rxjavaexample.observable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaexample.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observables.ConnectableObservable;

public class HotConnectableObservableActivity extends AppCompatActivity {

    private static final String TAG = "HotConnectablObservable";

    // ده بيفصل الـ observable عن الـ observer
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_connectable_observable);

        compositeDisposable = new CompositeDisposable();

        ConnectableObservable<Long> hotConnectableObservable = ConnectableObservable.intervalRange(0,5,0,1, TimeUnit.SECONDS).publish();

        hotConnectableObservable.connect();

        compositeDisposable.add(hotConnectableObservable.subscribe(i -> Log.i(TAG, "onCreate() student1: " + i)));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        compositeDisposable.add(hotConnectableObservable.subscribe(i -> Log.i(TAG, "onCreate() student2: " + i)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
