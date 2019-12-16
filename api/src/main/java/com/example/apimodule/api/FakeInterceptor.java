package com.example.apimodule.api;

import com.example.apimodule.BuildConfig;

import java.io.IOException;
import java.net.URI;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FakeInterceptor implements Interceptor {
    // FAKE RESPONSES.
    private final static String PRODUCT_ID_1 = "{\"id\":\"1\",\"phone\":8115288300,\"country\":\"Japan\",\"status\":1}";
    private final static String PRODUCT_ID_2 ="{\"id\":\"2\",\"phone\":8527358449,\"country\":\"India\",\"status\":1}";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response = null;
        if(BuildConfig.DEBUG) {
            String responseString;
            // Get Request URI.
            final URI uri = chain.request().url().uri();
            // Get Query String.
            final String query = uri.getQuery();
            // Parse the Query String.
            final String[] parsedQuery = query.split("=");
            if(parsedQuery[0].equalsIgnoreCase("id") && parsedQuery[1].equalsIgnoreCase("1")) {
                responseString = PRODUCT_ID_1;
            }
            else if(parsedQuery[0].equalsIgnoreCase("id") && parsedQuery[1].equalsIgnoreCase("2")){
                responseString = PRODUCT_ID_2;
            }
            else {
                responseString = "";
            }

            response = new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        }
        else {
            response = chain.proceed(chain.request());
        }

        return response;
    }
}