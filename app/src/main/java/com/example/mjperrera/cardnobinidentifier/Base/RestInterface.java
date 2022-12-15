package com.example.mjperrera.cardnobinidentifier.Base;

import com.example.mjperrera.cardnobinidentifier.Object.BinResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestInterface {
    @GET("/{cardNo}")
    Observable<Response<BinResponse>> FETCH_BIN_DATA(@Path("cardNo") String cardNo);
}
