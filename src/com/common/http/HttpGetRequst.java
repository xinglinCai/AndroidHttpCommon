
package com.common.http;

import java.io.IOException;

public class HttpGetRequst extends HttpBaseRequest {

    public HttpGetRequst(String url, int connTimeout, int readTimeout) throws IOException {
        getHttpURLConnection(url);
        httpURLConnection.setRequestMethod("GET");
        setTimeout(connTimeout, readTimeout);
    }

}
