package com.example.aa.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CompanyRegister extends AppCompatActivity {
Button Registercompany;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.companyregister);
        Registercompany = (Button) findViewById(R.id.Registerascompany);
        Registercompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyregister register = new companyregister();
                register.execute();
                Intent i = new Intent(CompanyRegister.this,Welcomepage.class);
                startActivity(i);

            }
        });

    }


    private class companyregister extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            StringBuilder sb = new StringBuilder();

            String http = "http://amit-learning.com/parkForMe/index.php/RegisterCompany";


            HttpURLConnection urlConnection = null;
            try

            {
                URL url = null;

                url = new URL(http);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.setConnectTimeout(10000);
                urlConnection.setReadTimeout(10000);
                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestProperty("Host", "android.schoolportal.gr");
                urlConnection.connect();

                //Create JSONObject here
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("email","rty34u34@gmail.com");
                jsonParam.put("password", "admin");
                jsonParam.put("address","test");
                jsonParam.put("companyFiled","test");
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
                out.write(jsonParam.toString());
                out.close();

                int HttpResult = urlConnection.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(), "utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    System.out.println("" + sb.toString());

                } else {
                    System.out.println(urlConnection.getResponseMessage());
                }
            } /*catch (MalformedURLException e) {

        e.printStackTrace();
    }
    catch (IOException e) {

        e.printStackTrace();
    } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }*/

            catch(
                    MalformedURLException e1
                    )

            {
                e1.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally

            {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return null;
        }
    }
}