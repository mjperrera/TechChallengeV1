package com.example.mjperrera.cardnobinidentifier.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mjperrera.cardnobinidentifier.Object.Bank;
import com.example.mjperrera.cardnobinidentifier.Object.Country;
import com.example.mjperrera.cardnobinidentifier.Object.Number;
import com.example.mjperrera.cardnobinidentifier.R;


public class ResultDialog extends Dialog implements View.OnClickListener {
    private Activity mActivity;
    private ImageView ivClose;
    private TextView tvScheme, tvbrand, tvType, tvPrepaid, tvLength, tvLuhn, tvEmoji, tvCountry,
            tvLat, tvLong, tvBankName, tvBankPhone, tvBankCity, tvBankUrl, tvCurrency;
    private int length;
    private Number number;
    private String scheme, type, brand;
    private boolean prepaid;
    private Country country;
    private Bank bank;

    private void initProps() {
        ivClose = findViewById(R.id.iv_close);
        tvScheme = findViewById(R.id.tv_scheme);
        tvbrand = findViewById(R.id.tv_brand);
        tvType = findViewById(R.id.tv_type);
        tvPrepaid = findViewById(R.id.tv_prepaid);
        tvLength = findViewById(R.id.tv_length);
        tvLuhn = findViewById(R.id.tv_luhn);
        tvEmoji = findViewById(R.id.tv_emoji);
        tvCountry = findViewById(R.id.tv_country_name);
        tvLat = findViewById(R.id.tv_lat);
        tvLong = findViewById(R.id.tv_long);
        tvBankName = findViewById(R.id.tv_bank_name);
        tvBankPhone = findViewById(R.id.tv_bank_phone);
        tvBankCity = findViewById(R.id.tv_bank_city);
        tvBankUrl = findViewById(R.id.tv_bank_url);
        tvCurrency = findViewById(R.id.tv_country_currency);
    }

    public ResultDialog(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    public ResultDialog(Activity activity, Number number, String scheme, String type,
                        String brand, boolean prepaid, Country country, Bank bank) {
        super(activity);
        this.mActivity = activity;
        this.number = number;
        this.scheme = scheme;
        this.type = type;
        this.brand = brand;
        this.prepaid = prepaid;
        this.country = country;
        this.bank = bank;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_result_bin);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
        initProps();
        ivClose.setOnClickListener(this);

        tvScheme.setText(scheme);
        tvbrand.setText(brand);
        tvType.setText(type);
        tvPrepaid.setText("No");
        if (prepaid) {
            tvPrepaid.setText("Yes");
        }
        tvLength.setText(String.valueOf(number.getLength()));
        tvLuhn.setText("No");
        if (number.getLuhn()) {
            tvLuhn.setText("Yes");
        }

        if (country != null){
            tvEmoji.setText(country.getEmoji());
            tvCountry.setText(country.getName());
            tvLat.setText(String.valueOf(country.getLatitude()));
            tvLong.setText(String.valueOf(country.getLongitude()));
            tvCurrency.setText(String.valueOf("(" + country.getCurrency()) + ")");
        }

        if (bank != null){
            tvBankName.setText(bank.getName());
            tvBankPhone.setText(bank.getPhone());
            tvBankCity.setText(bank.getCity());
            tvBankUrl.setText(bank.getUrl());
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
        }
    }
}
