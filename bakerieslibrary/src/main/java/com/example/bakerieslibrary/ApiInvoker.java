package com.example.bakerieslibrary;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.RequestFuture;
import com.example.bakerieslibrary.requests.*;
import com.example.bakerieslibrary.utils.JsonUtil;
import com.example.bakerieslibrary.utils.Pair;
import com.google.gson.JsonParseException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * BoulangerieApi
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 * <p>
 * Singeleton class for API handling.
 */

public class ApiInvoker {
    private static ApiInvoker INSTANCE;
    private Map<String, String> defaultHeaderMap = new HashMap<>();
    private RequestQueue mRequestQueue;
    private int connectionTimeout;
    /**
     * ISO 8601 date time format.
     * <p>
     * https://en.wikipedia.org/wiki/ISO_8601
     */
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH-mm",
                                                                                Locale.FRANCE);
    /**
     * ISO 8601 date format.
     * <p>
     * https://en.wikipedia.org/wiki/ISO_8601
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy",
                                                                             Locale.FRANCE);

    static {
        // Use UTC as the default time zone.
        DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static void setUserAgent(String userAgent) {
        INSTANCE.addDefaultHeader("User-Agent", userAgent);
    }

    public static Date parseDateTime(String str) {
        try {
            return DATE_TIME_FORMAT.parse(str);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseDate(String str) {
        try {
            return DATE_FORMAT.parse(str);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDateTime(Date datetime) {
        return DATE_TIME_FORMAT.format(datetime);
    }

    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String parameterToString(Object param) {
        if (param == null) {
            return "";
        } else if (param instanceof Date) {
            return formatDate((Date) param);
        } else if (param instanceof Collection) {
            StringBuilder b = new StringBuilder();
            for (Object o : (Collection) param) {
                if (b.length() > 0) {
                    b.append(",");
                }
                b.append(o);
            }
            return b.toString();
        } else {
            return String.valueOf(param);
        }
    }

    /*
      Format to Pair objects.
    */
    public static List<Pair> parameterToPairs(String collectionFormat, String name, Object value) {
        List<Pair> params = new ArrayList<Pair>();

        // preconditions
        if (name == null || name.isEmpty() || value == null) return params;

        Collection valueCollection = null;
        if (value instanceof Collection) {
            valueCollection = (Collection) value;
        } else {
            params.add(new Pair(name, parameterToString(value)));
            return params;
        }

        if (valueCollection.isEmpty()) {
            return params;
        }

        //  collection format default: csv

        // create the params based on the collection format


        String delimiter = ",";


        StringBuilder sb = new StringBuilder();
        for (Object item : valueCollection) {
            sb.append(delimiter);
            sb.append(parameterToString(item));
        }

        params.add(new Pair(name, sb.substring(1)));

        return params;
    }


    public static void initializeInstance(Context mContext) {
        // Setup 1 MB disk-based cache
        Cache cache = new DiskBasedCache(mContext.getCacheDir(), 1024 * 1024);
        initializeInstance(cache, null, 0, null, 30);
    }

    public static void initializeInstance(Cache cache, Network network, int threadPoolSize,
                                          ResponseDelivery delivery, int connectionTimeout) {

        INSTANCE = new ApiInvoker(cache, network, threadPoolSize, delivery, connectionTimeout);
        /*
         * API testing user-agent, no auths configured.
         */
        setUserAgent("android/app");

        // Setup authentications and prevent the authentications from being modified.
    }

    private ApiInvoker(Cache cache, Network network, int threadPoolSize, ResponseDelivery delivery,
                       int connectionTimeout) {
        if (cache == null) cache = new NoCache();
        if (network == null) {
            // Use HttpURLConnection as the HTTP client
            network = new BasicNetwork(new HurlStack());
        }

        if (delivery == null) {
            initConnectionRequest(cache, network);
        } else {
            initConnectionRequest(cache, network, threadPoolSize, delivery);
        }
        this.connectionTimeout = connectionTimeout;
    }

    private void initConnectionRequest(Cache cache, Network network) {
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
    }

    private void initConnectionRequest(Cache cache, Network network, int threadPoolSize,
                                       ResponseDelivery delivery) {
        mRequestQueue = new RequestQueue(cache, network, threadPoolSize, delivery);
        mRequestQueue.start();
    }

    public static ApiInvoker getInstance(Context context) {
        if (INSTANCE == null) initializeInstance(context);
        return INSTANCE;
    }

    public void addDefaultHeader(String key, String value) {
        defaultHeaderMap.put(key, value);
    }



    public static Object deserialize(String json, String containerType, Class cls) throws ApiException {
        try {
            if ("list".equalsIgnoreCase(containerType) || "array".equalsIgnoreCase(containerType)) {
                try {
                    return JsonUtil.deserializeToList(json, cls);
                } catch (Exception e) {
                    // problem receiving a single element json array
                    List<Object> tmp = new ArrayList<>();
                    tmp.add(JsonUtil.deserializeToObject(json,cls));
                    return tmp;
                }
            } else if (String.class.equals(cls)) {
                    return json;
            } else {
                return JsonUtil.deserializeToObject(json, cls);
            }
        } catch (JsonParseException e) {
            throw new ApiException(500, e.getMessage());
        }
    }

    public static String serialize(Object obj) throws ApiException {
        try {
            if (obj != null)
                return JsonUtil.serialize(obj);
            else
                return null;
        } catch (JsonParseException e) {
            throw new ApiException(422, e.getMessage());
        }
    }


    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }


    public String invokeAPI(String host, String path, String method, List<Pair> queryParams,
                            Object body, Map<String, String> headerParams, String contentType,
                            String[] authNames) throws ApiException, InterruptedException,
            ExecutionException, TimeoutException {
        try {
            RequestFuture<String> future = RequestFuture.newFuture();
            Request request = createRequest(host, path, method, queryParams, body, headerParams,
                    contentType, authNames, future, future);
            if (request != null) {
                mRequestQueue.add(request);
                return future.get(connectionTimeout, TimeUnit.SECONDS);
            } else {
                return "no data";
            }
        } catch (UnsupportedEncodingException ex) {
            throw new ApiException(0, "UnsupportedEncodingException");
        }
    }

    public void invokeAPI(String host, String path, String method, List<Pair> queryParams,
                          Object body, Map<String, String> headerParams,
                          String contentType, String[] authNames,
                          Response.Listener<String> stringRequest,
                          Response.ErrorListener errorListener) throws ApiException {
        try {
            Request request = createRequest(host, path, method, queryParams, body, headerParams,
                    contentType, authNames, stringRequest, errorListener);
            if (request != null) {
                mRequestQueue.add(request);
            }
        } catch (UnsupportedEncodingException ex) {
            throw new ApiException(0, "UnsupportedEncodingException");
        }
    }

    // Method for URL encoding
    public String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
    @param  authNames is to be set once authentication with server is done.
     */
    public Request<String> createRequest(String host, String path, String method,
                                         List<Pair> queryParams, Object body,
                                         Map<String, String> headerParams, String contentType,
                                         String[] authNames, Response.Listener<String> stringRequest,
                                         Response.ErrorListener errorListener) throws ApiException,
            UnsupportedEncodingException {

        StringBuilder b = new StringBuilder();
        String queryString = "";

        if (queryParams != null) {
            b.append("?");
            for (Pair queryParam : queryParams) {
                if (!queryParam.getName().isEmpty()) {
                    b.append(escapeString(queryParam.getName()));
                    b.append("=");
                    b.append(escapeString(queryParam.getValue()));
                    b.append("&");
                }
            }
            queryString = b.substring(0);
        }



        String url = host + path + queryString;

        HashMap<String, String> headers = new HashMap<>();

        for (String key : headerParams.keySet()) {
            headers.put(key, headerParams.get(key));
        }

        for (String key : defaultHeaderMap.keySet()) {
            if (!headerParams.containsKey(key)) {
                headers.put(key, defaultHeaderMap.get(key));
            }
        }
        headers.put("Accept", "application/json");

        Request request = null;

        if ("GET".equals(method)) {
            request = new GetRequest(url, headers, null, stringRequest, errorListener);
        } else if ("POST".equals(method)) {
            if (body != null) {
                if (body instanceof JSONObject) {
                    request = new PostRequest(url, headers, "application/json", ((JSONObject)body).toString(),
                            stringRequest, errorListener);
                } else {
                    request = new PostRequest(url, headers, contentType,
                            serialize(body), stringRequest, errorListener);
                }
            } else {
                request = new PostRequest(url, headers, null, null, stringRequest,
                        errorListener);
            }
        } else if ("PUT".equals(method)) {
            if (body != null) {
                if (body instanceof HttpEntity) {
                    request = new PutRequest(url, headers, "application/json", (HttpEntity) body,
                            stringRequest, errorListener);
                } else {
                    request = new PutRequest(url, headers, contentType,
                            new StringEntity(serialize(body), "UTF-8"), stringRequest,
                            errorListener);
                }
            } else {
                request = new PutRequest(url, headers, null, null, stringRequest,
                        errorListener);
            }
        } else if ("DELETE".equals(method)) {
            if (body != null) {
                if (body instanceof HttpEntity) {
                    request = new DeleteRequest(url, headers, "application/json", (HttpEntity) body,
                            stringRequest, errorListener);
                } else {
                    request = new DeleteRequest(url, headers, contentType,
                            new StringEntity(serialize(body), "UTF-8"), stringRequest,
                            errorListener);
                }
            } else {
                request = new DeleteRequest(url, headers, null, null, stringRequest,
                        errorListener);
            }
        } else if ("PATCH".equals(method)) {
            if (body != null) {
                if (body instanceof JSONObject) {
                    request = new PatchRequest(url, headers, "application/json", ((JSONObject)body).toString(),
                            stringRequest, errorListener);
                } else {
                    request = new PatchRequest(url, headers, "application/json",
                            serialize(body), stringRequest, errorListener);
                }

            } else {
                request = new PatchRequest(url, headers, null, null, stringRequest,
                        errorListener);
            }
        }

        if (request != null) {
            request.setRetryPolicy(new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(this.connectionTimeout),
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }

        //noinspection unchecked
        return request;
    }


    public void stopQueue() {
        mRequestQueue.stop();
    }

}
