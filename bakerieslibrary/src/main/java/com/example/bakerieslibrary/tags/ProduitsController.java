package com.example.bakerieslibrary.tags;


import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.Produits;
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
 *
 * Contact: leafyteam@notaRealCompany.com
 *
 */

public class ProduitsController {


    private String basePath = "https://app.167-172-50-144.plesk.page";
    private ApiInvoker apiInvoker ;
    private String path = "";
    private List<Pair> queryParams = new ArrayList<Pair>();
    private Map<String, String> headerParams = new HashMap<>();
    private String contentType = "application/json";
    private String[] authNames = new String[]{};
    private Object postBody = null;

    public ProduitsController(Context context){
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
         * get one produit
         *
         * @param codeProduit : id
         * @return Produits
         */
        public Produits getProduitByCodeProduit (Integer codeProduit) throws TimeoutException,
                ExecutionException, InterruptedException, ApiException,VolleyError {
            // verify the required parameter 'codeProduit' is set
            if (codeProduit == null) {
                throw  new VolleyError("Missing the required parameter " ,
                        new ApiException(400, "Missing the required parameter 'codeProduit' when " +
                                "calling getProduitByCodeProduit"));
            }

            // create path and map variables
            path = "/produits/"+ apiInvoker.escapeString(codeProduit.toString());



            try {
                String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", null,
                        null, headerParams, contentType, authNames);
                if (localVarResponse != null) {
                    return (Produits) ApiInvoker.deserialize(localVarResponse, "", Produits.class);
                } else {
                    return null;
                }
            } catch (ExecutionException ex) {
                if (ex.getCause() instanceof VolleyError) {
                    VolleyError volleyError = (VolleyError)ex.getCause();
                    if (volleyError.networkResponse != null) {
                        throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                    }
                }
                throw ex;
            }
        }

        /**
         * get one produit
         *
         * @param codeProduit : id
         */
        public void getProduitByCodeProduit (Integer codeProduit,
                                             final Response.Listener<Produits> responseListener,
                                             final Response.ErrorListener errorListener) {

            // verify the required parameter 'codeProduit' is set
            if (codeProduit == null) {
                try {
                    throw new VolleyError("Missing the required parameter " ,
                            new ApiException(400, "Missing the required parameter 'codeProduit' when" +
                                    " calling getProduitByCodeProduit"));
                } catch (VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            }

            // create path and map variables
            path = "/produits/"+ apiInvoker.escapeString(codeProduit.toString());




            try {
                apiInvoker.invokeAPI(basePath, path, "GET", null, null,
                        headerParams, contentType, authNames,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String localVarResponse) {
                                try {
                                    responseListener.onResponse((Produits) ApiInvoker
                                            .deserialize(localVarResponse,  "", Produits.class));
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
        /**
         * get all produits
         *
         * @return List<Produits>
         */
        public List<Produits> getProduits () throws TimeoutException, ExecutionException,
                InterruptedException, ApiException {

            // create path and map variables
            path = "/produits";



            try {
                String localVarResponse = apiInvoker.invokeAPI (basePath, path, "GET", null,
                        null, headerParams, contentType, authNames);
                if (localVarResponse != null) {
                    //noinspection unchecked
                    return (List<Produits>) ApiInvoker.deserialize(localVarResponse,
                            "array", Produits.class);
                } else {
                    return null;
                }
            } catch (ExecutionException ex) {
                if (ex.getCause() instanceof VolleyError) {
                    VolleyError volleyError = (VolleyError)ex.getCause();
                    if (volleyError.networkResponse != null) {
                        throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                    }
                }
                throw ex;
            }
        }

        /**
         * get all produits
         *
         */
        public void getProduits (final Response.Listener<List<Produits>> responseListener,
                                 final Response.ErrorListener errorListener) {


            // create path and map variables
            path = "/produits";



            try {
                apiInvoker.invokeAPI(basePath, path, "GET", null, null,
                        headerParams, contentType, authNames,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String localVarResponse) {
                                try {
                                    //noinspection unchecked
                                    responseListener.onResponse((List<Produits>) ApiInvoker
                                            .deserialize(localVarResponse,  "array", Produits.class));
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
