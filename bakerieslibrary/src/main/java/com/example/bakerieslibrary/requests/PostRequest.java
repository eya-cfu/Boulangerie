package com.example.bakerieslibrary.requests;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * BoulangerieApi
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 *
 * Contact: leafyteam@notaRealCompany.com
 */

public class PostRequest extends JsonRequest<String> {

    private String entity;
    private final Response.Listener<String> mListener;
    private String contentType;
    private Map<String, String> apiHeaders;

    public PostRequest(String url, Map<String, String> apiHeaders, String contentType,
                       String entity, Response.Listener<String> listener,
                       Response.ErrorListener errorListener) {
        super(Method.POST, url,entity,listener, errorListener);
        mListener = listener;
        this.entity = entity;
        this.contentType = contentType;
        this.apiHeaders = apiHeaders;
       // Log.d("Got","Here\n");
      //  Log.d("post body\n",entity);
    }


/*
    @Override
    public String getBodyContentType() {
        if (entity == null) {
            return null;
        }
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (entity == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    */
    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    /* (non-Javadoc)
     * @see com.android.volley.Request#getHeaders()
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
        if (apiHeaders != null && !apiHeaders.equals(Collections.emptyMap())) {
            headers.putAll(apiHeaders);
        }
        if (contentType != null) {
            headers.put("Content-Type", contentType);
        }

        return headers;
    }

}