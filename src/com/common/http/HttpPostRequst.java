
package com.common.http;

import java.io.IOException;

public class HttpPostRequst extends HttpBaseRequest {

    public HttpPostRequst(String url, int connTimeout, int readTimeout) throws IOException {
        getHttpURLConnection(url);
        httpURLConnection.setRequestMethod("POST");
        setTimeout(connTimeout, readTimeout);
    }

}
