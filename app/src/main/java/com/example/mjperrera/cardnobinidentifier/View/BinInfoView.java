package com.example.mjperrera.cardnobinidentifier.View;

import android.content.Context;
import android.util.Log;

import com.example.mjperrera.cardnobinidentifier.Base.RestInterface;
import com.example.mjperrera.cardnobinidentifier.Interface.IBinInfoView;
import com.example.mjperrera.cardnobinidentifier.Object.BinResponse;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class BinInfoView {
    private Context context;
    private final RestInterface restInterface;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private Response<BinResponse> binInfoResponse;

    public BinInfoView(Context context, RestInterface restInterface) {
        this.context = context;
        this.restInterface = restInterface;
    }


    public void getBinInfo(final IBinInfoView iBinInfoView, String cardNo) {
        disposables.add(restInterface.FETCH_BIN_DATA(cardNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<BinResponse>>() {
                    @Override
                    public void onNext(Response<BinResponse> binResponse) {
                        binInfoResponse = binResponse;
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBinInfoView.fetchBinFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (binInfoResponse.raw().code() == 200) {
                            iBinInfoView.fetchBinInfoSuccess(binInfoResponse.body());
                        } else {
                            int code = 0;
                            try {
                                code = binInfoResponse.raw().code();
                                iBinInfoView.fetchBinFailed(String.valueOf(code));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }));
    }
}
