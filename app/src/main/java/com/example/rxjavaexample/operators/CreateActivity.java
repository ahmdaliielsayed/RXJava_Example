package com.example.rxjavaexample.operators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaexample.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class CreateActivity extends AppCompatActivity {

    private static final String TAG = "CreateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

//        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
//
//            }
//        });

        Observable observable = Observable.create(emitter -> {
            for (int i = 0; i < 5; i++) {
                if (i == 3) {
                    emitter.onNext(3 / 0);
                } else {
                    emitter.onNext(i);
                }
            }
            emitter.onComplete();
        });

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
