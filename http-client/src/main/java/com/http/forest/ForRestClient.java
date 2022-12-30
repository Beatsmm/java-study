package com.http.forest;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Request;

public interface ForRestClient {

    @Request(
            url = "http://httpbin.org/post",
            type = "POST"
    )
    String simplePost(@Body("name") String name);
}
