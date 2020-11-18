package com.example.bakerieslibrary.tags;


import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bakerieslibrary.ApiException;
import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.CommandesBL;
import com.example.bakerieslibrary.models.customresponses.Count;
import com.example.bakerieslibrary.models.customresponses.FilteredDetailsCommandeBL;
import com.example.bakerieslibrary.models.customresponses.HeadlessCmdLabo;
import com.example.bakerieslibrary.utils.Pair;

import org.json.JSONException;
import org.json.JSONObject;

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

public class CommandesBLController {


    private String basePath = "https://app.167-172-50-144.plesk.page";
    private ApiInvoker apiInvoker ;
    private String path = "";
    private List<Pair> queryParams = new ArrayList<Pair>();
    private Map<String, String> headerParams = new HashMap<>();
    private String contentType = "application/json";
    private String[] authNames = new String[]{};
    private Object postBody = null;

    public CommandesBLController(Context context){
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
     * create an orderBL
     *
     * @param body CommandeBl object that needs to be added
     * @return String value of API response, if any.
     */
    public String addCommandeBL(CommandesBL body) throws TimeoutException, ExecutionException,
            InterruptedException, ApiException, VolleyError {

        postBody = body;
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new VolleyError("Missing the required parameter",
                    new ApiException(400, "Missing the required parameter 'body' when " +
                            "calling addCommandeBL"));
        }

        // create path and map variables
        path = "/commandesBL";


        try {
            return apiInvoker.invokeAPI(basePath, path, "POST",
                    null, postBody, headerParams, contentType, authNames);

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
     * create an orderBL
     *
     * @param body CommandeBl object that needs to be added
     */
    public void addCommandeBL(CommandesBL body, final Response.Listener<String> responseListener,
                              final Response.ErrorListener errorListener) {
        postBody = body;


        // verify the required parameter 'body' is set
        if (body == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'body' when calling addCommandeBL"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/commandesBL";



        try {
            apiInvoker.invokeAPI(basePath, path, "POST", null, postBody, headerParams,
                    contentType, authNames,
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
     * returns a commandeBL
     *
     * @param idCommandeBL cmd ID
     * @return CommandesBL
     */
    public CommandesBL getCommandeBLById(String idCommandeBL) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {

        // verify the required parameter 'idCommandeBL' is set
        if (idCommandeBL == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameter 'idCommandeBL' when " +
                            "calling getCommandeBLById"));
        }

        // create path and map variables
        path = "/commandesBL/ById/" + apiInvoker.escapeString(idCommandeBL.toString());


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET",
                    null, postBody, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                return (CommandesBL) ApiInvoker.deserialize(localVarResponse, "",
                        CommandesBL.class);
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
     * returns a commandeBL
     *
     * @param idCommandeBL cmd ID
     */
    public void getCommandeBLById(String idCommandeBL, final Response
            .Listener<CommandesBL> responseListener,
                                  final Response.ErrorListener errorListener) {

        // verify the required parameter 'idCommandeBL' is set
        if (idCommandeBL == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'idCommandeBL' when" +
                                " calling getCommandeBLById"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/commandesBL/ById/" + apiInvoker.escapeString(idCommandeBL.toString());


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((CommandesBL) ApiInvoker
                                        .deserialize(localVarResponse,
                                                "", CommandesBL.class));
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


    public Count getCount() throws TimeoutException, ExecutionException,
            InterruptedException, ApiException, VolleyError {


        // create path and map variables
        path = "/commandesBL/getCount";



        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET", null,
                    null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                return (Count) ApiInvoker.deserialize(localVarResponse,
                        "", Count.class);
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

    public void getCount(final Response
            .Listener<Count> responseListener,
                         final Response.ErrorListener errorListener ){

        // create path and map variables
        path = "/commandesBL/getCount";



        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                responseListener.onResponse((Count) ApiInvoker
                                        .deserialize(localVarResponse,
                                                "", Count.class));
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
     * get Commandes collection of the Boulangerie by Etat
     *
     * @param etat           : nouvelle/en cours/honoree
     * @param idBoulangerie: BL Id
     * @return List<CommandesBL>
     */
    public List<CommandesBL> getCmdsByEtatAndBoulangerieID(String etat, Integer idBoulangerie)
            throws TimeoutException, ExecutionException, InterruptedException, ApiException, VolleyError {

        // verify the required query parameters are set
        if (etat == null || idBoulangerie == null) {
            throw new VolleyError("Missing the required parameter",
                    new ApiException(400, "Missing the required query parameters when " +
                            "calling getCmdsByEtatAndBoulangerieID"));
        }
        // create path and map variables
        path = "/commandesBL/getCmdsbyEtatAndBoulangerieID";
        //add query params
        queryParams.clear();
        queryParams.addAll(ApiInvoker.parameterToPairs("", "etat", etat));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "idBoulangerie",
                idBoulangerie));


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET",
                    queryParams, null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<CommandesBL>) ApiInvoker.deserialize(localVarResponse,
                        "array", CommandesBL.class);
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
     * get Commandes collection of the Boulangerie by Etat
     *
     * @param etat * @param idBoulangerie
     */
    public void getCmdsByEtatAndBoulangerieID(String etat, Integer idBoulangerie,
                                              final Response.Listener<List<CommandesBL>> responseListener,
                                              final Response.ErrorListener errorListener) {


        // verify the required query parameters are set
        if (etat == null || idBoulangerie == null) {
            try {
                throw new VolleyError("Missing the required parameter",
                        new ApiException(400, "Missing the required query parameters when " +
                                "calling getCmdsByEtatAndBoulangerieID"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/commandesBL/getCmdsbyEtatAndBoulangerieID";
        //add query params
        queryParams.clear();
        queryParams.addAll(ApiInvoker.parameterToPairs("", "etat", etat));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "idBoulangerie",
                idBoulangerie));


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener.onResponse((List<CommandesBL>) ApiInvoker
                                        .deserialize(localVarResponse,
                                                "array", CommandesBL.class));
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
     * gets commandes collection by matricule for livreur
     *
     * @param etat      : nouvelle/en cours/honoree
     * @param matricule : matricule du livreur
     * @return List<CommandesBL>
     */
    public List<CommandesBL> getCmdsByEtatAndmatricule(String etat, Integer matricule)
            throws TimeoutException, ExecutionException, InterruptedException, ApiException, VolleyError {

        // verify the required query parameters are set
        if (etat == null || matricule == null) {
            throw new VolleyError("Missing the required parameter",
                    new ApiException(400, "Missing the required query parameters when " +
                            "calling getCmdsByEtatAndmatricule"));
        }
        // create path and map variables
        path = "/commandesBL/getCmdsByEtatAndMatricule";

        //add query params
        queryParams.clear();
        queryParams.addAll(ApiInvoker.parameterToPairs("", "etat", etat));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "matricule", matricule));


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET",
                    queryParams, null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<CommandesBL>) ApiInvoker.deserialize(localVarResponse,
                        "array", CommandesBL.class);
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
     * gets commandes collection by matricule for livreur
     *
     * @param etat * @param matricule
     */
    public void getCmdsByEtatAndmatricule(String etat, Integer matricule,
                                          final Response.Listener<List<CommandesBL>> responseListener,
                                          final Response.ErrorListener errorListener) {

        // verify the required query parameters are set
        if (etat == null || matricule == null) {
            try {
                throw new VolleyError("Missing the required parameter",
                        new ApiException(400, "Missing the required query parameters when " +
                                "calling getCmdsByEtatAndmatricule"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }
        // create path and map variables
        path = "/commandesBL/getCmdsByEtatAndMatricule";

        //add query params
        queryParams.clear();
        queryParams.addAll(ApiInvoker.parameterToPairs("", "etat", etat));
        queryParams.addAll(ApiInvoker.parameterToPairs("", "matricule", matricule));


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener.onResponse((List<CommandesBL>) ApiInvoker
                                        .deserialize(localVarResponse, "array",
                                                CommandesBL.class));
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
     * gets commandes filtered for laboratoire
     *
     * @param etat : nouvelle/enCours/honoree
     * @return List<HeadlessCmdLabo>
     */
    public List<HeadlessCmdLabo> getCmdsForLab(String etat) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {

        // verify the required query parameter is set
        if (etat == null) {
            throw new VolleyError("Missing the required parameter",
                    new ApiException(400, "Missing the required query parameters when " +
                            "calling getCmdsForLab"));
        }
        // create path and map variables
        path = "/commandesBL/getCmdsForLab";

        //add query params
        queryParams.clear();
        queryParams.addAll(ApiInvoker.parameterToPairs("", "etat", etat));


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET",
                    queryParams, null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<HeadlessCmdLabo>) ApiInvoker.deserialize(localVarResponse,
                        "array", HeadlessCmdLabo.class);
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
     * gets commandes filtered for laboratoire
     *
     * @param etat : nouvelle/enCours/honoree
     */
    public void getCmdsForLab(String etat, final Response.Listener<List<HeadlessCmdLabo>> responseListener,
                              final Response.ErrorListener errorListener) {

        // verify the required query parameter is set
        if (etat == null) {
            try {
                throw new VolleyError("Missing the required parameter",
                        new ApiException(400, "Missing the required query parameters when " +
                                "calling getCmdsForLab"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/commandesBL/getCmdsForLab";

        // add query params
        queryParams.clear();
        queryParams.addAll(ApiInvoker.parameterToPairs("", "etat", etat));


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener
                                        .onResponse((List<HeadlessCmdLabo>) ApiInvoker.
                                                deserialize(localVarResponse,
                                                        "array", HeadlessCmdLabo.class));
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
     * returns detailsCommandes filtered collection by idCommande
     *
     * @param idCommandeBL : id cmd
     * @return List<FilteredDetailsCommandeBL>
     */
    public List<FilteredDetailsCommandeBL> getDetailsByCommandeBL(String idCommandeBL)
            throws TimeoutException, ExecutionException, InterruptedException, ApiException, VolleyError {

        // verify the required parameter 'idCommandeBL' is set
        if (idCommandeBL == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameter 'idCommandeBL' when" +
                            " calling getDetailsByCommandeBL"));
        }

        // create path and map variables
        path = "/commandesBL/" + apiInvoker.escapeString(idCommandeBL.toString()) + "/detailsCmdBL";


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET",
                    null, null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<FilteredDetailsCommandeBL>) ApiInvoker.deserialize(localVarResponse,
                        "array", FilteredDetailsCommandeBL.class);
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
     * returns detailsCommandes filtered collection by idCommande
     *
     * @param idCommandeBL : id cmd
     */
    public void getDetailsByCommandeBL(String idCommandeBL, final Response
            .Listener<List<FilteredDetailsCommandeBL>> responseListener,
                                       final Response.ErrorListener errorListener) {

        // verify the required parameter 'idCommandeBL' is set
        if (idCommandeBL == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'idCommandeBL' when " +
                                "calling getDetailsByCommandeBL"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/commandesBL/" + apiInvoker.escapeString(idCommandeBL.toString()) + "/detailsCmdBL";


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener
                                        .onResponse((List<FilteredDetailsCommandeBL>) ApiInvoker
                                                .deserialize(localVarResponse,
                                                        "array",
                                                        FilteredDetailsCommandeBL.class));
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
     * returns all commandesBL
     *
     * @return List<CommandesBL>
     */
    public List<CommandesBL> getcommandesBL() throws TimeoutException, ExecutionException,
            InterruptedException, ApiException {

        // create path and map variables
        path = "/commandesBL";


        try {
            String localVarResponse = apiInvoker.invokeAPI(basePath, path, "GET",
                    null, null, headerParams, contentType, authNames);
            if (localVarResponse != null) {
                //noinspection unchecked
                return (List<CommandesBL>) ApiInvoker.deserialize(localVarResponse,
                        "array", CommandesBL.class);
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
     * returns all commandesBL
     */
    public void getcommandesBL(final Response.Listener<List<CommandesBL>> responseListener,
                               final Response.ErrorListener errorListener) {

        // create path and map variables
        path = "/commandesBL";


        try {
            apiInvoker.invokeAPI(basePath, path, "GET", null, null,
                    headerParams, contentType, authNames,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String localVarResponse) {
                            try {
                                //noinspection unchecked
                                responseListener.onResponse((List<CommandesBL>) ApiInvoker
                                        .deserialize(localVarResponse,
                                                "array", CommandesBL.class));
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
     * patching etat of a commandeBL objet
     *
     * @param idCommandeBL : id cmd
     * @param etat         : nouvelle/en cours/honoree in body
     * @return String value of API response, if any
     */
    public String patchCmdBlEtat(String idCommandeBL, String etat) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {

        postBody = etat;
        // verify the required parameter 'idCommandeBL' is set
        if (idCommandeBL == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameter 'idCommandeBL' when" +
                            " calling patchCmdBlEtat"));
        }

        // verify the required 'etat' parameter is set
        if (etat == null) {
            throw new VolleyError("Missing the required parameter",
                    new ApiException(400, "Missing the required 'etat' parameter when " +
                            "calling patchCmdBlEtat"));
        }

        // create path and map variables
        path = "/commandesBL/" + apiInvoker.escapeString(idCommandeBL.toString());


        try {
            return apiInvoker.invokeAPI(basePath, path, "PATCH",
                    null, postBody, headerParams, contentType, authNames);

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
     * patching etat of a commandeBL objet
     *
     * @param idCommandeBL * @param etat
     */
    public void patchCmdBlEtat(String idCommandeBL, String etat,
                               final Response.Listener<String> responseListener,
                               final Response.ErrorListener errorListener) {
        JSONObject jsonObject = new JSONObject();


        // verify the required parameter 'idCommandeBL' is set
        if (idCommandeBL == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'idCommandeBL' when " +
                                "calling patchCmdBlEtat"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // verify the required 'etat' parameter is set
        if (etat == null) {
            try {
                throw new VolleyError("Missing the required parameter",
                        new ApiException(400, "Missing the required 'etat' parameter when " +
                                "calling patchCmdBlEtat"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/commandesBL/" + apiInvoker.escapeString(idCommandeBL.toString());



        try {
            jsonObject.put("etat",etat);
            postBody = jsonObject;
           // Log.d("post body",jsonObject.toString());
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
        } catch (ApiException|JSONException ex ) {
            errorListener.onErrorResponse(new VolleyError(ex));
        }
    }

    /**
     * putting in the matricule of Livreur
     *
     * @param idCommandeBL : id cmd to update
     * @param matricule    : matricule of Livreur
     * @return String value of API response, if any.
     */
    public String patchLivreur(String idCommandeBL, Integer matricule) throws TimeoutException,
            ExecutionException, InterruptedException, ApiException, VolleyError {

        postBody = matricule;
        // verify the required parameter 'idCommandeBL' is set
        if (idCommandeBL == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameter 'idCommandeBL' when " +
                            "calling patchLivreur"));
        }
        // verify the required parameter 'matricule' is set
        if (matricule == null) {
            throw new VolleyError("Missing the required parameter ",
                    new ApiException(400, "Missing the required parameter 'matricule' when calling" +
                            " patchLivreur"));
        }

        // create path and map variables
        path = "/commandesBL/" + apiInvoker.escapeString(idCommandeBL.toString()) + "/identifyLivreur";


        try {
            return apiInvoker.invokeAPI(basePath, path, "PATCH",
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
     * putting in the matricule of Livreur
     *
     * @param idCommandeBL * @param matricule
     */
    public void patchLivreur(String idCommandeBL, Integer matricule,
                             final Response.Listener<String> responseListener,
                             final Response.ErrorListener errorListener) {
        postBody = matricule;

        // verify the required parameter 'idCommandeBL' is set
        if (idCommandeBL == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'idCommandeBL' when " +
                                "calling patchLivreur"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }
        // verify the required parameter 'matricule' is set
        if (matricule == null) {
            try {
                throw new VolleyError("Missing the required parameter ",
                        new ApiException(400, "Missing the required parameter 'matricule' when calling " +
                                "patchLivreur"));
            } catch (VolleyError volleyError) {
                errorListener.onErrorResponse(volleyError);
                return;
            }
        }

        // create path and map variables
        path = "/commandesBL/" + apiInvoker.escapeString(idCommandeBL.toString()) + "/identifyLivreur";


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
