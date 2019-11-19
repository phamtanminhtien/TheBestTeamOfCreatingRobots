package com.tietha.thebestteamofcreatingrobots;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class ControlPanel extends AppCompatActivity {

    private BluetoothSocket bTSocket = null;
    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Intent mainIntent;
    String address ="";
    TextView mstatus;
    Button forward, back, left, right, risemod, anchormode;
    SeekBar speed;
    SendALL sendALL;
    Car car = null;

    @Override
    protected void onPause() {
        super.onPause();
        if(bTSocket != null){
            try {
                bTSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);
        mstatus = findViewById(R.id.status);
        mainIntent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        forward = findViewById(R.id.forward);
        back = findViewById(R.id.back);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        speed = findViewById(R.id.speed);
        risemod = findViewById(R.id.risemode);
        anchormode = findViewById(R.id.anchormode);

        actionBar.hide();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.status);
        mstatus.startAnimation(animation);

        address = mainIntent.getStringExtra("address");
        if(address != null){
            mstatus.setBackgroundResource(R.drawable.bg_status_connect);
            mstatus.setText("Status: Connected");
            BluetoothDevice hc06 = bluetoothAdapter.getRemoteDevice(address);
            ConnectThread connectThread = new ConnectThread();
            connectThread.connect(hc06, mUUID);
            try {
                sendALL = new SendALL();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendALL.start();
            car = new Car();
            //
            forward.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        car.ForWardDown();
                        sendALL.changeText(car.GetStatus());
                    }else if(event.getAction() == MotionEvent.ACTION_UP){
                        car.ForWardUp();
                        sendALL.changeText(car.GetStatus());
                    }
                    return false;
                }
            });
            back.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        car.BackDown();
                        sendALL.changeText(car.GetStatus());
                    }else if(event.getAction() == MotionEvent.ACTION_UP){
                        car.BackUp();
                        sendALL.changeText(car.GetStatus());
                    }
                    return false;
                }
            });
            left.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        car.LeftDown();
                        sendALL.changeText(car.GetStatus());
                    }else if(event.getAction() == MotionEvent.ACTION_UP){
                        car.LeftUp();
                        sendALL.changeText(car.GetStatus());
                    }
                    return false;
                }
            });
            right.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        car.RightDown();
                        sendALL.changeText(car.GetStatus());
                    }else if(event.getAction() == MotionEvent.ACTION_UP){
                        car.RightUp();
                        sendALL.changeText(car.GetStatus());
                    }
                    return false;
                }
            });
            speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(progress == 0){

                    }else if(progress<10){
                        try {
                            sendALL.blink((byte)'1');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(progress<20){
                        try {
                            sendALL.blink((byte)'2');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(progress<30){
                        try {
                            sendALL.blink((byte)'3');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(progress<40){
                        try {
                            sendALL.blink((byte)'4');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(progress<50){
                        try {
                            sendALL.blink((byte)'5');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(progress<60){
                        try {
                            sendALL.blink((byte)'6');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(progress<70){
                        try {
                            sendALL.blink((byte)'7');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(progress<80){
                        try {
                            sendALL.blink((byte)'8');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(progress<90){
                        try {
                            sendALL.blink((byte)'9');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            sendALL.blink((byte)'q');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }

            });
            final boolean[] rise = {false};
            risemod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rise[0] == false){
                        risemod.setBackgroundResource(R.drawable.bg_mode_checked);
                        risemod.setText("Hạ");
                        try {
                            sendALL.blink((byte)'n');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        rise[0] = true;
                    }else{
                        risemod.setBackgroundResource(R.drawable.bg_mode_idle);
                        risemod.setText("Nâng");
                        try {
                            sendALL.blink((byte)'h');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        rise[0] = false;
                    }
                }
            });

            final boolean[] anchor = {false};
            anchormode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(anchor[0] == false){
                        anchormode.setBackgroundResource(R.drawable.bg_mode_checked);
                        anchormode.setText("Thả");
                        try {
                            sendALL.blink((byte)'k');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        anchor[0] = true;
                    }else{
                        anchormode.setBackgroundResource(R.drawable.bg_mode_idle);
                        anchormode.setText("Kẹp");
                        try {
                            sendALL.blink((byte)'t');
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        anchor[0] = false;
                    }
                }
            });
        }else{
            mstatus.setBackgroundResource(R.drawable.bg_status_none);
            mstatus.setText("Status: Disconnected");
        }

    }
    public class SendALL extends Thread{
        byte t = 'S';
        byte t2 = 'S';
        OutputStream outputStream = bTSocket.getOutputStream();

        public SendALL() throws IOException {
        }

        public void changeText(byte c){
            this.t = c;
        }
        public void changeText2(byte c) {this.t2 = c;}
        public void resetText(){ this.t = 'S';}
        public void resetText2(){ this.t2 = 'S';}
        public void start(){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(t2 != 'S'){
                        try {
                            outputStream.write(t2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        outputStream.write(t);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    start();
                }
            }, 20 );
        }
        public void blink(byte c) throws IOException {
            outputStream.write(c);
        }

    }
    public class ConnectThread extends Thread{

        public boolean connect(BluetoothDevice bTDevice, UUID mUUID) {
            try {
                bTSocket = bTDevice.createRfcommSocketToServiceRecord(mUUID);
            } catch (IOException e) {
                Log.d("CONNECTTHREAD","Could not create RFCOMM socket:" + e.toString());
                return false;
            }
            try {
                bTSocket.connect();
                address = bTDevice.getAddress();
            } catch(IOException e) {
                Log.d("CONNECTTHREAD","Could not connect: " + e.toString());
                Toast.makeText(ControlPanel.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();
                try {
                    bTSocket.close();
                } catch(IOException close) {
                    Log.d("CONNECTTHREAD", "Could not close connection:" + e.toString());
                    return false;
                }
            }
            return true;
        }

        public boolean cancel() {
            try {
                bTSocket.close();
            } catch(IOException e) {
                Log.d("CONNECTTHREAD","Could not close connection:" + e.toString());
                return false;
            }
            return true;
        }
    }

}
