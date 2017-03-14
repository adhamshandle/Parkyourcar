package com.example.aa.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

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

public class Clientregister extends AppCompatActivity {
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientregister);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Register = (Button) findViewById(R.id.Registerasclient);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientregister register = new clientregister();
                register.execute();
                Intent i = new Intent(Clientregister.this, Welcomepage.class);
                startActivity(i);


            }
        });
    }

    private class clientregister extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            StringBuilder sb = new StringBuilder();

            String http = "http://amit-learning.com/parkForMe/index.php/RegisterUser";


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
                jsonParam.put("name","mahmoud");
                jsonParam.put("email","1234@gmail.com");
                jsonParam.put("password", "12345");
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