package com.example.rxjavaexample.observable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaexample.R;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectObservableActivity extends AppCompatActivity {

    private static final String TAG = "ReplaySubjectObservable";

    // ده بيفصل الـ observable عن الـ observer
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_subject_observable);

        compositeDisposable = new CompositeDisposable();

        ReplaySubject<String> subject = ReplaySubject.create();

        compositeDisposable.add(subject.subscribe(i -> Log.i(TAG, "onCreate student1: " + i)));
        subject.onNext("A");
        sleep(1000);
        subject.onNext("B");
        sleep(1000);
        subject.onNext("C");
        sleep(1000);
        subject.onNext("D");
        sleep(1000);
        compositeDisposable.add(subject.subscribe(i -> Log.i(TAG, "onCreate student2: " + i))); // من تااني علشاان النااظر
        subject.onNext("E");
        sleep(1000);
        subject.onNext("F");
        sleep(1000);
        subject.onNext("G");
        sleep(1000);
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
