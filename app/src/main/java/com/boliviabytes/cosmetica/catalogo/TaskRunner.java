package com.boliviabytes.cosmetica.catalogo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;


import java.io.IOException;

import retrofit.Call;


public class TaskRunner extends AsyncTask<String,String,Object>{

    private  Handler handler;
    private  Call call;
    public TaskRunner(Handler handler,Call call){
        this.handler=handler;
        this.call=call;
    }

    @Override
    protected Object doInBackground(String... params) {
        publishProgress("Loading contents..."); // Calls onProgressUpdate(
        try {
            return call.execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        System.out.println(o);
        handler.dispatchMessage(new Message());
    }

    @Override
    protected void onProgressUpdate(String... values) {

        System.out.println(values[0]);
    }



}
