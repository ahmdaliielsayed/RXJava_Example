package com.example.rxjavaexample.operators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaexample.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FromArrayActivity extends AppCompatActivity {

    private static final String TAG = "FromArrayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_array);

        Integer[] list = new Integer[5];

        list[0] = 1;
        list[1] = 2;
        list[2] = 3;
        list[3] = 4;
        list[4] = 5;

        Observable observable = Observable.fromArray(list);

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "observer: onSubscribe(@NonNull Disposable d): ");
            }

            @Override
            public void onNext(Object o) {
                Log.i(TAG, "observer: onNext(Object o): " + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "observer: onError(@NonNull Throwable e): " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "observer: onComplete(): ");
            }
        };
        observable.subscribe(observer);
    }
}
