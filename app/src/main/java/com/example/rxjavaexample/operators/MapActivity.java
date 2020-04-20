package com.example.rxjavaexample.operators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.rxjavaexample.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

public class MapActivity extends AppCompatActivity {

    private static final String TAG = "VeryImportantOperatorsA";

    // ده بيفصل الـ observable عن الـ observer
    CompositeDisposable compositeDisposable;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        compositeDisposable = new CompositeDisposable();

        editText = findViewById(R.id.editText);

        compositeDisposable.add(Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() != 0)
                            emitter.onNext(s); // دي بتبعت لـ الميثود doOnNext علشاان تتصرف لماا الـ emitter يبعتلهاا اللي جااي
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        })
                // الـ doOnNext بتطلع الحااجة اللي في الـ upStream
                // الـ upStream ده الحااجة اللي بتطلع من الـ observable قبل ماا توصل لـ الـ observer
                .doOnNext(c -> Log.i(TAG, "VIO upStream: " + c))
                // الـ map بتااخد الدااتاا من الـ upStream تعمل عليهاا شوية عملياات و بعدين تبعتهاا لـ الـ downStream
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) {
                        return Integer.parseInt(o.toString()) * 2;
                    }
                })
                // الـ subscribe دي الخااصة بـ الـ downStream
                // الـ downStream ده اللي بيحصل في الـ observer
                .subscribe(s -> Log.i(TAG, "VIO downStream: " + s)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
