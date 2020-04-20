package com.example.rxjavaexample.observable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaexample.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class ColdObservableActivity extends AppCompatActivity {

    private static final String TAG = "ColdObservableActivity";

    // ده بيفصل الـ observable عن الـ observer
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_observable);

        compositeDisposable = new CompositeDisposable();

        Observable<Long> coldObservable = Observable.intervalRange(0,5,0,1, TimeUnit.SECONDS);

        compositeDisposable.add(coldObservable.subscribe(i -> Log.i(TAG, "onCreate() student1: " + i)));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        compositeDisposable.add(coldObservable.subscribe(i -> Log.i(TAG, "onCreate() student2: " + i)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
