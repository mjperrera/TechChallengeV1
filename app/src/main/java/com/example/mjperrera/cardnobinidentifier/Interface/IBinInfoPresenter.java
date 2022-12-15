package com.example.mjperrera.cardnobinidentifier.Interface;

import android.content.Context;

import com.example.mjperrera.cardnobinidentifier.Base.RestInterface;

public interface IBinInfoPresenter {
    void fetchBinInfo(Context context, RestInterface restInterface, String cardNo);
}
