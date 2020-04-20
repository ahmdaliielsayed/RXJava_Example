package com.example.rxjavaexample.operators;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaexample.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ControlThreadingActivity extends AppCompatActivity {

    private static final String TAG = "ControlThreadingActivit";

    // ده بيفصل الـ observable عن الـ observer
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_threading);

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(Observable.just(1, 2, 3, 4, 5)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                // الـ doOnNext بتطلع الحااجة اللي في الـ upStream
                // الـ upStream ده الحااجة اللي بتطلع من الـ observable قبل ماا توصل لـ الـ observer
                .doOnNext(c -> Log.i(TAG, "threading upStream: " + c + " currentThread: " + Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                // الـ subscribe دي الخااصة بـ الـ downStream
                // الـ downStream ده اللي بيحصل في الـ observer
                .subscribe(o -> Log.i(TAG, "threading downStream: " + o + " currentThread: " + Thread.currentThread().getName())));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
