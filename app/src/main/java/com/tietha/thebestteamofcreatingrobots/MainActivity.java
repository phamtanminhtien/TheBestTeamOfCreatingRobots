package com.tietha.thebestteamofcreatingrobots;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 123;
    private static final int REQUEST_CONNECT_BT = 124;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    TextView mStatus;
    Button mConnect, mControl, mInfo;
    Intent mIntentConnect,mIntentControl,mIntentInfo;
    String address = null;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ads

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mStatus = findViewById(R.id.status);
        mConnect = findViewById(R.id.connect);
        mControl = findViewById(R.id.control);
        mInfo = findViewById(R.id.info);
        mIntentConnect = new Intent(this, Connect.class);
        mIntentControl = new Intent(this, ControlPanel.class);
        mIntentInfo = new Intent(this, Info.class);
        //kiem tra thiet bi co ho tro khong
        if (bluetoothAdapter == null) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Notification!")
                    .setMessage("Thiệt Bị Của bạn Không Tích Hợp Bluetooth nên không thể sử dụng ứng dụng này. Thoát ứng dụng?")
                    .setCancelable(false)
                    .setNegativeButton("Đồng Ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .show();
        }
        //bat ble
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(mIntentConnect, REQUEST_CONNECT_BT);
            }
        });
        mControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntentControl.putExtra("address", address);
                startActivity(mIntentControl);
            }
        });
        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mIntentInfo);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){

            case REQUEST_ENABLE_BT:
                if(resultCode == RESULT_OK){
                    Toast.makeText(this, "Đã bật Bluetooth", Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("Notification!")
                            .setMessage("Bật Bluetooth không thành công. Thử lại?")
                            .setCancelable(false)
                            .setNegativeButton("Đồng Ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                                }
                            })
                            .show();
                }
                break;
            case REQUEST_CONNECT_BT:
                if(resultCode == RESULT_OK){
                    address = data.getStringExtra("address");
                    String name = data.getStringExtra("name");
                    mStatus.setBackgroundResource(R.drawable.bg_status_connect);
                    String text = "Status: Choose <font color='red'>"+name+"</font> with mac address <font color='red'>"+address+"</font>";
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mStatus.setText(Html.fromHtml(text,  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                    } else {
                        mStatus.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
                    }
                }
        }
    }
}
