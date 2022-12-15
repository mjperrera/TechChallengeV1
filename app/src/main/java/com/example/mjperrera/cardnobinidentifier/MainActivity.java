package com.example.mjperrera.cardnobinidentifier;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mjperrera.cardnobinidentifier.Base.BaseActivity;
import com.example.mjperrera.cardnobinidentifier.Dialog.ResultDialog;
import com.example.mjperrera.cardnobinidentifier.Interface.IBinInfoPresenter;
import com.example.mjperrera.cardnobinidentifier.Interface.IBinInfoView;
import com.example.mjperrera.cardnobinidentifier.Object.BinResponse;
import com.example.mjperrera.cardnobinidentifier.Presenter.BinInfoPresenter;

import static com.example.mjperrera.cardnobinidentifier.Base.Utils.editTextSetContentMemorizeSelection;

public class MainActivity extends BaseActivity implements IBinInfoView, View.OnClickListener {
    private final String TAG = "MAIN";
    private IBinInfoPresenter iBinInfoPresenter;
    private EditText edtCardNo;
    private Button btnSearch;
    private ProgressBar progressBar;

    private void initProps() {
        edtCardNo = findViewById(R.id.edt_card_no);
        btnSearch = findViewById(R.id.btn_search);
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.black));

        initProps();
        iBinInfoPresenter = new BinInfoPresenter(this);
        btnSearch.setOnClickListener(this);

        String initValue = "45717360";
        edtCardNo.setText(formatStrWithSpaces(initValue));
        progressBar.setVisibility(View.GONE);

        edtCardNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtCardNo.length() < 4) {
                    btnSearch.setEnabled(false);
                } else {
                    btnSearch.setEnabled(true);
                }

                String origin = s.toString().replaceAll(" ", "");
                String formatStr = formatStrWithSpaces(origin);
                if (!s.toString().equals(formatStr)) {
                    editTextSetContentMemorizeSelection(edtCardNo, formatStr);
                    if (before == 0 && count == 1 && formatStr.charAt(edtCardNo.getSelectionEnd() - 1) == ' ') {
                        edtCardNo.setSelection(edtCardNo.getSelectionEnd() + 1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edtCardNo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (edtCardNo.getRight() -
                            edtCardNo.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        edtCardNo.setText("");
                        return true;
                    }
                }
                return false;
            }
        });


    }

    @Override
    public void fetchBinInfoSuccess(BinResponse binResponse) {
        progressBar.setVisibility(View.GONE);
        Log.d(TAG, "fetchBinInfoSuccess");
        if (binResponse != null) {
            ResultDialog dialog = new ResultDialog(MainActivity.this, binResponse.getNumber(),
                    binResponse.getScheme(), binResponse.getType(), binResponse.getBrand(),
                    binResponse.isPrepaid(), binResponse.getCountry(), binResponse.getBank());
            dialog.show();
        }
    }

    @Override
    public void fetchBinFailed(String message) {
        progressBar.setVisibility(View.GONE);
        Log.d(TAG, "fetchBinFailed");
        if (message != null) {
            Log.e(TAG, "Error occurred: " + message);
            Toast.makeText(this, "Error occurred: " + message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                progressBar.setVisibility(View.VISIBLE);
                iBinInfoPresenter.fetchBinInfo(this, bRestInterface, edtCardNo.getText()
                        .toString().replace(" ", ""));
                break;
        }
    }

    public static String formatStrWithSpaces(CharSequence str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (i != 0 && i % 4 == 0) {
                sb.append(' ');
            }
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }
}