package com.legen.ifthenlight.Utils;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hsin on 10/25/2015.
 */
public class Yangson extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {

        // params comes from the execute() call: params[0] is the url.
        try {
            return downloadUrl(urls[0]);
        } catch (Exception e) {

            return "Unable to retrieve web page. URL may be invalid.";
        }
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {

//            try {
//
//
//                listView = (ListView) findViewById(R.id.list);
//                if (MessageAdapter == null) {
//                    MessageAdapter = new QuickAdapter<Message>(getApplicationContext(), R.layout.item_mainlist) {
//                        @Override
//                        protected void convert(BaseAdapterHelper helper, Message item) {
//                            helper.setText(R.id.list_item_title, item.Title);
//                            helper.setText(R.id.list_item_content, item.Content);
//                            helper.setText(R.id.list_item_create, item.CreateDt);
//                            helper.setText(R.id.list_item_tag, item.Tag);
//                        }
//                    };
//                }
//                ArrayList<Message> list = new ArrayList<Message>();
//                /*for (int i = 0; i < 10; i++) {
//                    Message m = new Message();
//                    m.Content = "内容啊啊啊啊啊啊啊啊啊啊啊" + i;
//                    m.CreateDt = i + "日" + "";
//                    m.Tag = i + "标签";
//                    m.Title = i + "表情";
//                    list.add(m);
//
//                }
//                */
//                String[] resultArray = result.split("\\|\\|");
//                for (int ii = 0; ii < resultArray.length; ii++) {
//                    String[] item = resultArray[ii].split("\\|\\*\\|");
//                    Message m = new Message();
//
//                    //String tempContent = new String(Base64.decode(item[2].toString().getBytes(), Base64.DEFAULT));
//
//                    m.Content = item[2].toString();
//                    m.CreateDt = item[3].toString();
//                    m.Tag = item[4].toString();
//                    m.Title = item[1].toString();
//                    list.add(m);
//                }
//                MessageAdapter.addAll(list);
//                listView.setAdapter(MessageAdapter);
//
//
//            } catch (Exception e) {
//
//            }

    }

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 3000;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            //Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            //String contentAsString = readIt(is, len);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[4096];
            int count = -1;
            while ((count = is.read(data, 0, 4096)) != -1)
                outStream.write(data, 0, count);

            data = null;
            return new String(outStream.toByteArray(), "UTF-8");


            // return contentAsString;


            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        //catch(Exception e)
        //{
        //   String s=e.getMessage().toString();
        //    return "";
        //}
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while((count = stream.read(data,0,4096)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(),"UTF-8");
    }
}
