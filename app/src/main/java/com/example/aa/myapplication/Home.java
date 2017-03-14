package com.example.aa.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

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

public class Home extends AppCompatActivity {
Button Loginbtn;
        Button signupascompanybtn;
    Button signupasclientbtn;
    EditText username;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        username = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pw);


        Loginbtn = (Button) findViewById(R.id.login);
        signupasclientbtn = (Button) findViewById(R.id.signupasclient);
        signupascompanybtn = (Button) findViewById(R.id.signupascompany);
       username = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pw);
        SharedPreferences sps=getSharedPreferences("mypref",MODE_PRIVATE);
        String name1=sps.getString("name","");
        String pwd1=sps.getString("password","");
        username.setText(name1);
        pass.setText(pwd1);
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String password = pass.getText().toString();
                InsertLoginTask login = new InsertLoginTask();
                login.execute();
                    Intent i=new Intent(Home.this,Welcomepage.class);
                    startActivity(i);
                    //put ur code here(shared pref elly ta7t da m


            }
        });
        signupasclientbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent (Home.this,Clientregister.class);
                startActivity(i2);
            }
        });
        signupascompanybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(Home.this,CompanyRegister.class);
                startActivity(i3);
            }
        });
    }

    private class InsertLoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            StringBuilder sb = new StringBuilder();

            String http = "http://amit-learning.com/parkForMe/index.php/GetAllLocation";


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