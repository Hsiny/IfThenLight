package com.legen.ifthenlight.Activity;


import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ListView;
import android.widget.TextView;

import com.legen.ifthenlight.Base.BaseActivity;
import com.legen.ifthenlight.Object.Message;
import com.legen.ifthenlight.R;
import com.legen.ifthenlight.Utils.Yangson;
import com.legen.ifthenlight.adapter.BaseAdapterHelper;
import com.legen.ifthenlight.adapter.QuickAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends BaseActivity {
    private QuickAdapter<Message> MessageAdapter;
    private ListView listView;
    private TextView textView;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);



    }

    @Override
    public void initViews() {
        textView=(TextView)findViewById(R.id.userCenter_txt);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {
        new Yangson(){
            @Override
            protected void onPostExecute(String result) {
                try {


                    listView = (ListView) findViewById(R.id.list);
                    if (MessageAdapter == null) {
                        MessageAdapter = new QuickAdapter<Message>(getApplicationContext(), R.layout.item_mainlist) {
                            @Override
                            protected void convert(BaseAdapterHelper helper, Message item) {
                                helper.setText(R.id.list_item_title, item.Title);
                                helper.setText(R.id.list_item_content, item.Content);
                                helper.setText(R.id.list_item_create, item.CreateDt);
                                helper.setText(R.id.list_item_tag, item.Tag);
                            }
                        };
                    }
                    ArrayList<Message> list = new ArrayList<Message>();
                /*for (int i = 0; i < 10; i++) {
                    Message m = new Message();
                    m.Content = "内容啊啊啊啊啊啊啊啊啊啊啊" + i;
                    m.CreateDt = i + "日" + "";
                    m.Tag = i + "标签";
                    m.Title = i + "表情";
                    list.add(m);

                }
                */
                    String[] resultArray = result.split("\\|\\|");
                    for (int ii = 0; ii < resultArray.length; ii++) {
                        String[] item = resultArray[ii].split("\\|\\*\\|");
                        Message m = new Message();

                        //String tempContent = new String(Base64.decode(item[2].toString().getBytes(), Base64.DEFAULT));

                        m.Content = item[2].toString();
                        m.CreateDt = item[3].toString();
                        m.Tag = item[4].toString();
                        m.Title = item[1].toString();
                        list.add(m);
                    }
                    MessageAdapter.addAll(list);
                    listView.setAdapter(MessageAdapter);


                } catch (Exception e) {

                }
            }

        }.execute("http://legenself.6655.la:15779/message/GetList?userid=10");

    }


}


