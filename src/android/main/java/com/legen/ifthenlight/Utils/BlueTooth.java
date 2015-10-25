package com.legen.ifthenlight.Utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by hsin on 10/25/2015.
 */
public   class BlueTooth {

    private static BluetoothAdapter mBluetoothAdapter = null;

    private static BluetoothSocket btSocket = null;

    private static OutputStream outStream = null;

    private static InputStream inStream = null;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");  //这条是蓝牙串口通用的UUID，不要更改

    private static String address = "00:12:09:25:31:14"; // <==要连接的蓝牙设备MAC地址

    public  void connectDevice()
    {
        try {

            //new DownloadWebpageTask().execute("http://legenself.6655.la:15779/message/GetList?userid=10");

            mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device=mBluetoothAdapter.getRemoteDevice(address);
            btSocket=device.createRfcommSocketToServiceRecord(MY_UUID);
            //mBluetoothAdapter.cancelDiscovery();
            //btSocket.connect();
            boolean connected=false;
            int connecttime=0;
            while(!connected&&connecttime<30)
            {
                try{
                    btSocket.connect();
                    connected=true;
                }
                catch(IOException e1)
                {
                    connecttime++;
                    connected=false;
                }
            }

            String sendstr="a";
            outStream=btSocket.getOutputStream();
            inStream=btSocket.getInputStream();
            byte[] msgBuffer=sendstr.getBytes();
            outStream.write(msgBuffer);

        }
        catch (Exception e) {
            String temp = e.getMessage().toString();
        }
    }

    public void sendMessage(String msg)
    {
        try {
            outStream=btSocket.getOutputStream();
            byte[] msgBuffer = msg.getBytes();
            outStream.write(msgBuffer);
        }
        catch(Exception e)
        {
            String xx=e.getMessage().toString();
        }

    }


}