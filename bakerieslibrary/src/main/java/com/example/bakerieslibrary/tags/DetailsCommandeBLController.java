package com.example.bakerieslibrary.tags;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.DetailsCommandeBL;
import com.example.bakerieslibrary.utils.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

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

public class DetailsCommandeBLController {

    private String basePath = "https://app.167-172-50-144.plesk.page";
    private ApiInvoker apiInvoker ;
    private String path = "";
    private List<Pair> queryParams = new ArrayList<Pair>();
    private Map<String, String> headerParams = new HashMap<>();
    private String contentType = "application/json";
    private String[] authNames = new String[]{};
    private Object postBody = null;

    public DetailsCommandeBLController(Context context){
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
     * add detailsCommandesBL
     *
     * @param body DetailsCommandeBl object that needs to be added
     * @return String value of API response, if any.
     */

    public String addDetailsCommandeBL(DetailsCommandeBL body) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {

        postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameter 'body' when calling" +
                            " addDetailsCommandeBL"));
        }

        // create path and map variables
        path = "/detailsCommandesBL";

        try {
            return apiInvoker.invokeAPI(basePath, path, "POST", null,
                    postBody, headerParams, contentType, authNames);

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
     * add detailsCommandesBL
     *
     * @param body DetailsCommandeBl object that needs to be added
     */

    public void addDetailsCommandeBL(DetailsCommandeBL body,
                                     final Response.Listener<String> responseListener,
                                     final Response.ErrorListener errorListener) {

        JSONObject jsonObject = new JSONObject();

        // verify the required parameter 'body' is set
        if (body == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'body' when " +
                                "calling addDetailsCommandeBL"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        JSONArray code = new JSONArray();
        //ArrayList<String> code = new ArrayList<>();
        code.put(String.valueOf(body.getCodeProduit()));
        //ArrayList<String> idCmd= new ArrayList<>();
        JSONArray idCmd = new JSONArray();
        idCmd.put(body.getIdCommandeBL());
        // create path and map variables
        path = "/detailsCommandesBL";

        try {

            jsonObject.put("codeProduit",code);
            jsonObject.put("idCommandeBL",idCmd);
            jsonObject.put("quantiteProd",body.getQuantiteProd());



            postBody = jsonObject;

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
        } catch (ApiException| JSONException ex) {
            errorListener.onErrorResponse(new VolleyError(ex));
        }
    }


    /**
     * get detailsCommandesBL by codeProduit and dueDate
     *
     * @param codeProduit : Prod Id
     * @param dueDate     : due date of corresponding commandeBL
     * @return List<DetailsCommandesBL>
     */

    public List<DetailsCommandeBL> getDetailByProduitAndDate(Integer codeProduit, Date dueDate)
            throws TimeoutException, ExecutionException, InterruptedException, ApiException,
            VolleyError {

        // verify the required parameters 'codeProduit' and 'dueDate' are set
        if (codeProduit == null || dueDate == null) {
            throw new VolleyError("Missing the required parameters ",
                    new ApiException(400, "Missing the required parameter 'codeProduit' / 'dueDate'" +
                            " when calling getDetailByProduitAndDate"));
        }


        // create path and map variables
        path = "/detailsCommandesBL/findByProduitAndDate";

        // query params
        queryParams.clear();
        queryParams.addAll(ApiInvoker.parameterToPairs("", "codeProduit", codeProduit));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "dueDate", dueDate));


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", queryParams,
                    null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<DetailsCommandeBL>) ApiInvoker.deserialize(localVarResponse,
                        "array", DetailsCommandeBL.class);
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
     * get detailsCommandesBL by codeProduit and dueDate
     *
     * @param codeProduit * @param dueDate
     */

    public void getDetailByProduitAndDate(Integer codeProduit, Date dueDate,
                                          final Response.Listener<List<DetailsCommandeBL>> responseListener,
                                          final Response.ErrorListener errorListener) {

        // verify the required parameters 'codeProduit' and 'dueDate' are set
        if (codeProduit == null || dueDate == null) {
            try {
                throw new VolleyError("Missing the required parameters ",
                        new ApiException(400, "Missing the required parameter 'codeProduit' / 'dueDate'" +
                                " when calling getDetailByProduitAndDate"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/detailsCommandesBL/findByProduitAndDate";

        // query params
        queryParams.clear();
        queryParams.addAll(ApiInvoker.parameterToPairs("", "codeProduit", codeProduit));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "dueDate", dueDate));


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener.onResponse((List<DetailsCommandeBL>) ApiInvoker
                                        .deserialize(localVarResponse, "array",
                                                DetailsCommandeBL.class));
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
     * get all details for all commandes BL
     *
     * @return List<DetailsCommandeBL>
     */

    public List<DetailsCommandeBL> getdetailsCommandesBL() throws TimeoutException,
            ExecutionException, InterruptedException, ApiException {

        // create path and map variables
        path = "/detailsCommandesBL";


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", null,
                    null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<DetailsCommandeBL>) ApiInvoker.deserialize(localVarResponse,
                        "array", DetailsCommandeBL.class);
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
     * get all details for all commandes BL
     */
    public void getdetailsCommandesBL(final Response.Listener<List<DetailsCommandeBL>> responseListener,
                                      final Response.ErrorListener errorListener) {

        // create path and map variables
        path = "/detailsCommandesBL";


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null,
                    null, headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener.onResponse((List<DetailsCommandeBL>) ApiInvoker
                                        .deserialize(localVarResponse, "array",
                                                DetailsCommandeBL.class));
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
     * patching the quantity of a detailsCommandesBL
     *
     * @param idDetail     : id
     * @param quantiteProd : quantity of corresponding prod
     * @return String value of API response, if any.
     */
    public String patchDetailQuantity(Integer idDetail, Integer quantiteProd) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {

        postBody = quantiteProd;

        // verify the required parameters 'idDetail' and 'quantiteProd' are set
        if (idDetail == null || quantiteProd == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameters 'idDetail'/'quantiteProd'" +
                            " when calling patchDetailQuantity"));
        }

        // create path and map variables
        path = "/detailsCommandesBL/" + apiInvoker.escapeString(idDetail.toString());

        try {
            return apiInvoker.invokeAPI(basePath, path, "PATCH", null,
                    postBody, headerParams, contentType, authNames);
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
     * patching the quantity of a detailsCommandesBL
     *
     * @param idDetail * @param quantiteProd
     */

    public void patchDetailQuantity(Integer idDetail, Integer quantiteProd,
                                    final Response.Listener<String> responseListener,
                                    final Response.ErrorListener errorListener) {
        postBody = quantiteProd;

        // verify the required parameter 'idDetail' is set
        if (idDetail == null || quantiteProd == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameters 'idDetail'/'quantiteProd'" +
                                " when calling patchDetailQuantity"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/detailsCommandesBL/" + apiInvoker.escapeString(idDetail.toString());


        try {
            apiInvoker.invokeAPI(basePath, path, "PATCH", null, postBody,
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

}
