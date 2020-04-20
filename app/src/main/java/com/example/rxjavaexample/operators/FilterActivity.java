package com.example.rxjavaexample.operators;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.rxjavaexample.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;

public class FilterActivity extends AppCompatActivity {

    private static final String TAG = "FilterActivity";

    // ده بيفصل الـ observable عن الـ observer
    CompositeDisposable compositeDisposable;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

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
                // الـ debounce ده بيأخر الدااتاا إنهاا توصل لـ الـ observer
                // مفيدة جداََ في حااجة زي الـ Google API Places بتااعت الـ map ...
                // بدل ماا كل شوية تروح تـ hit الـ API و عماال يتحسب عليكـ فلوس ممكن تأخر إنكـ تـ hit الـ API بـ الـ debounce
                .debounce(3, TimeUnit.SECONDS)
//                // الـ distinctUntilChanged لو انت عملت hit الـ API بـ حااجة و مسحت و كتبتهاا تااني مش هيعمل hit تااني لأن انت رحت مرة و رجعت بـ result فـ متصيعش
//                .distinctUntilChanged()
                .filter(c -> !c.toString().equals("ahmed"))
                // الـ subscribe دي الخااصة بـ الـ downStream
                // الـ downStream ده اللي بيحصل في الـ observer
                .subscribe(s -> {
                    Log.i(TAG, "VIO downStream(Hit API): " + s);
                    sendDataToAPI(s.toString());
                }));
    }

    public void sendDataToAPI(String data) {
        Observable observable = Observable.just("calling API to send " + data);
        compositeDisposable.add(observable.subscribe(c -> Log.i(TAG, "VIO sendDataToAPI: " + c)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
