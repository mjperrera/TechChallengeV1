package com.example.mjperrera.cardnobinidentifier.Interface;

import com.example.mjperrera.cardnobinidentifier.Object.BinResponse;

public interface IBinInfoView {
    void fetchBinInfoSuccess(BinResponse binResponse);
    void fetchBinFailed(String message);
}
