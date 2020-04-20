package com.example.rxjavaexample.retrofit.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxjavaexample.retrofit.db.APIClient;
import com.example.rxjavaexample.retrofit.db.dao.APIInterface;
import com.example.rxjavaexample.retrofit.db.entity.Post;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ManyPostsViewModel extends ViewModel {

    private static final String TAG = "ManyPostsViewModel";

    private APIInterface apiInterface;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ManyPostsViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public LiveData<ArrayList<Post>> getManyPosts() {
        final MutableLiveData<ArrayList<Post>> manyPostsMutableLiveData = new MutableLiveData<>();

//        Observable<ArrayList<Post>> observable = apiInterface.getPosts()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
        Single<ArrayList<Post>> observable = apiInterface.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

//        Observer<ArrayList<Post>> observer = new Observer<ArrayList<Post>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(ArrayList<Post> value) {
//                manyPostsMutableLiveData.setValue(value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "onError: " + e);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

        compositeDisposable.add(observable.subscribe(manyPostsMutableLiveData::setValue, e -> Log.i(TAG, "getManyPosts: " + e)));

//        apiInterface.getPosts().enqueue(new Callback<ArrayList<Post>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
//                if (response.isSuccessful()) {
//                    manyPostsMutableLiveData.setValue(response.body());
//                } else {
//                    // error
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
//                t.printStackTrace();
//                // error
//                // manyPostsMutableLiveData.setValue(null);
//            }
//        });
        return manyPostsMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
