package com.example.mjperrera.cardnobinidentifier.Presenter;

import android.content.Context;

import com.example.mjperrera.cardnobinidentifier.Base.RestInterface;
import com.example.mjperrera.cardnobinidentifier.Interface.IBinInfoPresenter;
import com.example.mjperrera.cardnobinidentifier.Interface.IBinInfoView;
import com.example.mjperrera.cardnobinidentifier.View.BinInfoView;

public class BinInfoPresenter implements IBinInfoPresenter {
    private IBinInfoView iBinInfoView;

    public BinInfoPresenter(IBinInfoView iBinInfoView) {
        this.iBinInfoView = iBinInfoView;
    }

    @Override
    public void fetchBinInfo(Context context, RestInterface restInterface, String cardNo) {
        BinInfoView binInfoView = new BinInfoView(context, restInterface);
        binInfoView.getBinInfo(iBinInfoView, cardNo);
    }
}
