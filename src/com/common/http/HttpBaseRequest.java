
package com.common.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpBaseRequest {
    protected HttpURLConnection httpURLConnection;

    protected void getHttpURLConnection(String url) throws MalformedURLException, IOException {
        URL httpURL = new URL(url);
        httpURLConnection = (HttpURLConnection) httpURL.openConnection();
    }

    protected void setTimeout(int connTimeout, int readTimeout) {
        httpURLConnection.setConnectTimeout(connTimeout);
        httpURLConnection.setReadTimeout(readTimeout);
    }

    public int getResponseCode() throws IOException {
        return httpURLConnection.getResponseCode();
    }

    public BufferedOutputStream getBufferedOutputStream() throws IOException {
        return new BufferedOutputStream(httpURLConnection.getOutputStream());
    }

    public BufferedInputStream getBufferedInputStream() throws IOException {
        return new BufferedInputStream(httpURLConnection.getInputStream());
    }

    public void disconnect() {
        httpURLConnection.disconnect();
    }

}
