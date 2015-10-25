package com.legen.ifthenlight.Base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.legen.ifthenlight.Utils.BlueTooth;
import com.legen.ifthenlight.Utils.Yangson;


public abstract class BaseActivity extends Activity {
    static BlueTooth bt=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initViews();
        initListeners();
        initData();
        if(bt==null) {
            bt=new BlueTooth();
            bt.connectDevice();
            MagCardDataListenThread.start();
        }

    }
    public abstract void setContentView();

    public abstract void initViews();

    public abstract void initListeners();

    public abstract void initData();

    public void sendMessage(String msg)
    {
        bt.sendMessage(msg);
    }
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch(msg.what){
                case 0:
                    //添加自定义消息处理
                    bt.sendMessage("b");
                    //...
                    break;
                default:break;
            }
        }

    };

    private Thread MagCardDataListenThread = new Thread(new Runnable(){

        @Override
        public void run() {

            // TODO Auto-generated method stub
            while(true){
                new Yangson(){

                    @Override
                    protected void onPostExecute(String result) {
                        if(result.equals("0"))
                        {

                        }
                        else
                        {

                            try
                            {
                                String[] resultArray = result.split("\\|\\|");
                                for (int ii = 0; ii < resultArray.length; ii++) {
                                    String[] item = resultArray[ii].split("\\|\\*\\|");


                                    //String tempContent = new String(Base64.decode(item[2].toString().getBytes(), Base64.DEFAULT));


                                    String Tag = item[4].toString();
                                    Message msg = new Message();
                                    msg.what = 0;
                                    handler.sendMessage(msg);

                                }
                            }
                            catch (Exception e)
                            {

                            }
                            new Yangson().execute("http://legenself.6655.la:15779/message/SetRead?userid=10");


                        }

                    }
                }.execute("http://legenself.6655.la:15779/message/GetUnRead?userid=10");
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                    //状态改变时发送消息到主线程

            }

        }
    });
}
