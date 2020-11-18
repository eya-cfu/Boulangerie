package com.example.bakerieslibrary.tags;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.Livreurs;
import com.example.bakerieslibrary.utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * BoulangerieApi
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class LivreursController {


    private String basePath = "https://app.167-172-50-144.plesk.page";
    private ApiInvoker apiInvoker ;
    private String path = "";
    private List<Pair> queryParams = new ArrayList<Pair>();
    private Map<String, String> headerParams = new HashMap<>();
    private String contentType = "application/json";
    private String[] authNames = new String[]{};
    private Object postBody = null;

    public LivreursController(Context context){
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
     * returns Livreur by Matricule
     *
     * @param matricule du livreur
     * @return Livreurs
     */
    public Livreurs getLivreurByMatricule(Integer matricule) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {
        // verify the required parameter 'matricule' is set
        if (matricule == null) {
            throw new VolleyError("Missing the required parameter",
                    new ApiException(400, "Missing the required parameter 'matricule' when " +
                            "calling getLivreurByMatricule"));
        }

        // create path and map variables
        path = "/livreurs/" + apiInvoker.escapeString(matricule.toString());

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", null,
                    null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                return (Livreurs) ApiInvoker.deserialize(localVarResponse, "", Livreurs.class);
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
     * returns Livreur by Matricule
     *
     * @param matricule du livreur
     */
    public void getLivreurByMatricule(Integer matricule,
                                      final Response.Listener<Livreurs> responseListener,
                                      final Response.ErrorListener errorListener) {

        // verify the required parameter 'matricule' is set
        if (matricule == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'matricule' when " +
                                "calling getLivreursByMatricule"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/livreurs/" + apiInvoker.escapeString(matricule.toString());


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((Livreurs) ApiInvoker
                                        .deserialize(localVarResponse, "", Livreurs.class));
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
