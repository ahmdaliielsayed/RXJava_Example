package com.example.rxjavaexample.operators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaexample.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class JustActivity extends AppCompatActivity {

    private static final String TAG = "JustActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just);

        Observable observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

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
