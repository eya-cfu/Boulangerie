package com.example.bakerieslibrary.tags;


import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.Boulangeries;
import com.example.bakerieslibrary.models.customresponses.BoulangerieAndResp;
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
 *
 * This class in the controller class for API operations under Boulangeries tag, available for the
 * mobile apps only.
 */

public class BoulangeriesController {

    private String basePath = "https://app.167-172-50-144.plesk.page";
    private ApiInvoker apiInvoker ;
    private String path = "";
    private List<Pair> queryParams = new ArrayList<Pair>();
    private Map<String, String> headerParams = new HashMap<>();
    private String contentType = "application/json";
    private String[] authNames = new String[]{};

    public BoulangeriesController(Context context){
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
     * returns one Boulangerie by ID
     *
     * @param idBoulangerie : BL id
     * @return Boulangeries
     */
    public Boulangeries getBoulangerieById(Integer idBoulangerie) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {
        // verify the required parameter 'idBoulangerie' is set
        if (idBoulangerie == null) {
            throw new VolleyError("Missing the required parameter",
                    new ApiException(400, "Missing the required parameter 'idBoulangerie' when" +
                            " calling getBoulangerieById"));
        }

        // create path and map variables
        path = "/boulangeries/ById/" + apiInvoker.escapeString(idBoulangerie.toString());

        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", null,
                    null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                return (Boulangeries) ApiInvoker.deserialize(localVarResponse, "",
                        Boulangeries.class);
            } else {
                return null;
            }
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
     * returns one Boulangerie by ID
     *
     * @param idBoulangerie : id BL
     */
    public void getBoulangerieById(Integer idBoulangerie, final Response.Listener<Boulangeries> responseListener,
                                   final Response.ErrorListener errorListener)  {

        // verify the required parameter 'idBoulangerie' is set
        if (idBoulangerie == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'idBoulangerie' when " +
                                "calling getBoulangerieById"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/boulangeries/ById/" + apiInvoker.escapeString(idBoulangerie.toString());


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null, headerParams,
                    contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((Boulangeries) ApiInvoker.deserialize(localVarResponse,
                                        "", Boulangeries.class));
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
     * returns one Boulangerie by Matricule of Responsable
     *
     * @param matricule : du responsable
     * @return Boulangeries
     */
    public Boulangeries getBoulangerieByMatricule(Integer matricule) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {
        // verify the required parameter 'matricule' is set
        if (matricule == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameter 'matricule' when " +
                            "calling getBoulangerieByMatricule"));
        }

        // create path and map variables
        path = "/boulangeries/" + apiInvoker.escapeString(matricule.toString());


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", null,
                    null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                return (Boulangeries) ApiInvoker.deserialize(localVarResponse, "",
                        Boulangeries.class);
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
     * returns one Boulangerie by Matricule of Responsable
     *
     * @param matricule : matricule du responsable
     */
    public void getBoulangerieByMatricule(Integer matricule, final Response.Listener<Boulangeries> responseListener,
                                          final Response.ErrorListener errorListener) {

        // verify the required parameter 'matricule' is set
        if (matricule == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'matricule' when" +
                                " calling getBoulangerieByMatricule"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/boulangeries/" + apiInvoker.escapeString(matricule.toString());

        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null, headerParams,
                    contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((Boulangeries) ApiInvoker.deserialize(localVarResponse,
                                        "", Boulangeries.class));
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
     * get all boulangeries
     * returns Boulangeries filetered collection
     *
     * @return List<BoulangerieAndResp>
     */
    public List<BoulangerieAndResp> getBoulangeriesAndResp() throws TimeoutException, ExecutionException,
            InterruptedException, ApiException {

        // create path and map variables
        path = "/boulangeries";


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", null,
                    null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<BoulangerieAndResp>) ApiInvoker.deserialize(localVarResponse,
                        "array", BoulangerieAndResp.class);
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
     * get all boulangeries
     * returns Boulangeries filetered collection: BoulangerieAndResp
     */
    public void getBoulangeriesAndResp(final Response.Listener<List<BoulangerieAndResp>> responseListener,
                                final Response.ErrorListener errorListener) {

        // create path and map variables
        path = "/boulangeries";


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null, headerParams,
                    contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener.onResponse((List<BoulangerieAndResp>) ApiInvoker.deserialize(localVarResponse,
                                        "array", BoulangerieAndResp.class));
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
