package com.boliviabytes.cosmetica.catalogo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class TaskRunner extends AsyncTask<String,String,String>{
    private int SOAP_ACTION_PREFIX=0,URL=1,METHOD=2,NAMESPACE=3;
    private  Handler handler;
    public TaskRunner(Handler handler){
        this.handler=handler;
    }
    @Override
    protected String doInBackground(String... params) {
        String resp=null;
        publishProgress("Loading contents..."); // Calls onProgressUpdate(
        try {
            // SoapEnvelop.VER11 is SOAP Version 1.1 constant
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //bodyOut is the body object to be sent out with this envelope
            envelope.bodyOut = new SoapObject(params[NAMESPACE], params[METHOD]);
            HttpTransportSE transport = new HttpTransportSE(params[URL]);
            try {
                transport.call(params[NAMESPACE] + params[SOAP_ACTION_PREFIX] + params[METHOD], envelope);
            } catch (IOException e) {
               // e.printStackTrace();
                System.out.println(e.getMessage());
                System.out.println(e.getClass().toString());

            }
            //bodyIn is the body object received with this envelope
            if (envelope.bodyIn != null) {
                //getProperty() Returns a specific property at a certain index.
                SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                resp=resultSOAP.toString();
            }
        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println(e.getMessage());
            resp = e.getMessage();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        handler.sendEmptyMessage(0);
                System.out.println(values[0]);
    }
}
