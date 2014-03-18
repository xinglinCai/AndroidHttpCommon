
package com.common.androidhttpcommon;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.common.http.HttpGetRequst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();
    private String sessionIdString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new TestRunnable()).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    class TestRunnable implements Runnable {

        @Override
        public void run() {
            HttpGetRequst httpGetRequst = null;
            try {
                httpGetRequst = new HttpGetRequst("http://www.baidu.com", 1000, 1000);
                if (sessionIdString != null) {
                    httpGetRequst.setRequestProperty("Cookie", sessionIdString);
                }
                Log.e(TAG, httpGetRequst.getHeaderField("Set-Cookie") + ":hhh");
                String[] sessionId = httpGetRequst.getHeaderField("Set-Cookie").split(";");
                Log.e(TAG, "start get");
                String result = readStream(httpGetRequst.getBufferedInputStream());
                sessionIdString = sessionId[0];
                Log.e(TAG, "first" + sessionIdString);
                httpGetRequst.disconnect();
                new Thread(new TestRunnable()).start();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1024);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

}
