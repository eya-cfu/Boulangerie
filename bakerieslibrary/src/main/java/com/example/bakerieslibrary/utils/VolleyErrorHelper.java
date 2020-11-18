package com.example.bakerieslibrary.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;


public class VolleyErrorHelper {

    private static final String genericServerDown = "Problème de serveur, veuillez réessayer ultérieurement";
    private static final String noInternet = "Vérifiez votre connexion Internet et réessayez";
    private static final String genericError = "Un problème est survenu, veuillez réessayer ultérieurement";
    private static final String notFound = "aucun résultat trouvé";


    /**
     * Returns appropriate message which is to be displayed to the user
     * against the specified error object.
     *
     * @param error
     */
    public static String getMessage(Object error) {
        if (error instanceof TimeoutError) {
            return genericServerDown;
        } else if (isServerProblem(error)) {
            return handleServerError(error);
        } else if (isNetworkProblem(error)) {
            return noInternet;
        }
        return genericError;
    }

    /**
     * Determines whether the error is related to network
     *
     * @param error
     */
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }

    /**
     * Determines whether the error is related to server
     *
     * @param error
     */
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    /**
     * Handles the server error, tries to determine whether to show a stock message or to
     * show a message retrieved from the server.
     *
     * @param err
     */
    private static String handleServerError(Object err) {
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;

        if (response != null) {
            switch (response.statusCode) {
                case 404:
                    return notFound;
                case 422:
                case 401:
                   /* try {
                        // server might return error like this { "error": "Some error occured" }
                        // Use "Gson" to parse the result
                        HashMap<String, String> result = new Gson().fromJson(new String(response.data),
                                new TypeToken<Map<String, String>>() {
                                }.getType());

                        if (result != null && result.containsKey("error")) {
                            return result.get("error");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                    // invalid request
                    return error.getMessage();

                default:
                    return genericServerDown;

            }
        }
        return genericError;

    }

}

