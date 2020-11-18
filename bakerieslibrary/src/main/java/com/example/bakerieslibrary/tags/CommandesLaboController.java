package com.example.bakerieslibrary.tags;

import android.content.Context;

import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.CommandesLabo;
import com.example.bakerieslibrary.utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Response;
import com.android.volley.VolleyError;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * BoulangerieApi
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class CommandesLaboController {

    private String basePath = "https://app.167-172-50-144.plesk.page";
    private ApiInvoker apiInvoker ;
    private String path = "";
    private List<Pair> queryParams = new ArrayList<Pair>();
    private Map<String, String> headerParams = new HashMap<>();
    private String contentType = "application/json";
    private String[] authNames = new String[]{};
    private Object postBody = null;

    public CommandesLaboController(Context context){
        apiInvoker = ApiInvoker.getInstance(context);
    }


    public void addHeader(String key, String value) {
        getInvoker().addDefaultHeader(key, value);
    }

    public ApiInvoker getInvoker() {
        return apiInvoker;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }

    /**
     * Add a new commandesLabo
     *
     * @param body commandeLabo object that needs to be added
     * @return String value of API response, if any.
     */
    public String addcommandeLabo(CommandesLabo body) throws TimeoutException, ExecutionException,
            InterruptedException, ApiException, VolleyError {

        postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameter 'body' when calling " +
                            "addcommandeLabo"));
        }

        // create path and map variables
        path = "/commandesLabo";


        try {
            return apiInvoker.invokeAPI(basePath, path, "POST",
                    null, postBody, headerParams, contentType, authNames);

        } catch (ExecutionException ex) {
            if (ex.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) ex.getCause();
                if (volleyError.networkResponse != null) {
                    throw new ApiException(volleyError.networkResponse.statusCode,
                            volleyError.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * Add a new commandesLabo
     *
     * @param body commandeLabo object that needs to be added
     */
    public void addcommandeLabo(CommandesLabo body, final Response.Listener<String> responseListener,
                                final Response.ErrorListener errorListener) {
        postBody = body;

        // verify the required parameter 'body' is set
        if (body == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'body' when calling" +
                                " addcommandeLabo"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/commandesLabo";


        try {
            apiInvoker.invokeAPI(basePath, path, "POST", null, postBody,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            responseListener.onResponse(localVarResponse);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });
        } catch (ApiException ex) {
            errorListener.onErrorResponse(new VolleyError(ex));
        }
    }

    /**
     * get all commandesLabo
     *
     * @return List<CommandesLabo>
     */
    public List<CommandesLabo> getCommandesLabo() throws TimeoutException, ExecutionException,
            InterruptedException, ApiException {

        // create path and map variables
        path = "/commandesLabo";

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", null,
                    null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<CommandesLabo>) ApiInvoker.deserialize(localVarResponse,
                        "array", CommandesLabo.class);
            } else {
                return null;
            }
        } catch (ExecutionException ex) {
            if (ex.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) ex.getCause();
                if (volleyError.networkResponse != null) {
                    throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                }
            }
            throw ex;
        }
    }

    /**
     * get all commandesLabo
     */
    public void getCommandesLabo(final Response.Listener<List<CommandesLabo>> responseListener,
                                 final Response.ErrorListener errorListener) {


        // create path and map variables
        path = "/commandesLabo";


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener.onResponse((List<CommandesLabo>) ApiInvoker
                                        .deserialize(localVarResponse, "array",
                                                CommandesLabo.class));
                            } catch (ApiException exception) {
                                errorListener.onErrorResponse(new VolleyError(exception));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });
        } catch (ApiException ex) {
            errorListener.onErrorResponse(new VolleyError(ex));
        }
    }

}
