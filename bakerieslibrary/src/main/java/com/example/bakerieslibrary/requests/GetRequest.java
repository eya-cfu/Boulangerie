package com.example.bakerieslibrary.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * BoulangerieApi
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */


public class GetRequest extends StringRequest {

    private Map<String, String> apiHeaders;
    private String contentType;

    public GetRequest(String url, Map<String, String> apiHeaders, String contentType,
                      Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);
        this.apiHeaders = apiHeaders;
        this.contentType = contentType;
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
