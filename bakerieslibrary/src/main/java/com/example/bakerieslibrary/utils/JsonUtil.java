package com.example.bakerieslibrary.utils;

import com.example.bakerieslibrary.ApiInvoker;
import com.example.bakerieslibrary.models.*;
import com.example.bakerieslibrary.models.customresponses.BoulangerieAndResp;
import com.example.bakerieslibrary.models.customresponses.Count;
import com.example.bakerieslibrary.models.customresponses.FilteredDetailsCommandeBL;
import com.example.bakerieslibrary.models.customresponses.HeadlessCmdLabo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

/**
 * BoulangerieApi
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class JsonUtil {


    public static GsonBuilder gsonBuilder;

    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setDateFormat("dd-MM-yyyy HH-mm");
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {

                return ApiInvoker.parseDate(json.getAsString());
            }
        });
    }

    public static Gson getGson() {
        return gsonBuilder.create();
    }

    public static String serialize(Object obj) {
        return getGson().toJson(obj);
    }

    public static <T> T deserializeToList(String jsonString, Class cls) {
        return getGson().fromJson(jsonString, getListTypeForDeserialization(cls));
    }

    public static <T> T deserializeToObject(String jsonString, Class cls) {
        return getGson().fromJson(jsonString, getTypeForDeserialization(cls));
    }

    public static Type getListTypeForDeserialization(Class cls) {
        String className = cls.getSimpleName();

        if ("Administrateurs".equalsIgnoreCase(className)) {
            return new TypeToken<List<Administrateurs>>() {
            }.getType();
        }

        if ("Boulangeries".equalsIgnoreCase(className)) {
            return new TypeToken<List<Boulangeries>>() {
            }.getType();
        }

        if ("CommandesBL".equalsIgnoreCase(className)) {
            return new TypeToken<List<CommandesBL>>() {
            }.getType();
        }

        if ("CommandesLabo".equalsIgnoreCase(className)) {
            return new TypeToken<List<CommandesLabo>>() {
            }.getType();
        }

        if ("Composants".equalsIgnoreCase(className)) {
            return new TypeToken<List<Composants>>() {
            }.getType();
        }

        if ("CompositionsProduit".equalsIgnoreCase(className)) {
            return new TypeToken<List<CompositionsProduit>>() {
            }.getType();
        }

        if ("DetailsCommandeBL".equalsIgnoreCase(className)) {
            return new TypeToken<List<DetailsCommandeBL>>() {
            }.getType();
        }

        if ("BoulangerieAndResp".equalsIgnoreCase(className)) {
            return new TypeToken<List<BoulangerieAndResp>>() {
            }.getType();
        }

        if ("HeadlessCmdLabo".equalsIgnoreCase(className)) {
            return new TypeToken<List<HeadlessCmdLabo>>() {
            }.getType();
        }

        if ("FilteredDetailsCommandeBL".equalsIgnoreCase(className)) {
            return new TypeToken<List<FilteredDetailsCommandeBL>>() {
            }.getType();
        }

        if ("Livreurs".equalsIgnoreCase(className)) {
            return new TypeToken<List<Livreurs>>() {
            }.getType();
        }

        if ("Produits".equalsIgnoreCase(className)) {
            return new TypeToken<List<Produits>>() {
            }.getType();
        }

        if ("Profils".equalsIgnoreCase(className)) {
            return new TypeToken<List<Profils>>() {
            }.getType();
        }

        return new TypeToken<List<Object>>() {
        }.getType();
    }

    public static Type getTypeForDeserialization(Class cls) {
        String className = cls.getSimpleName();

        if ("Administrateurs".equalsIgnoreCase(className)) {
            return new TypeToken<Administrateurs>() {
            }.getType();
        }

        if ("Count".equalsIgnoreCase(className)) {
            return new TypeToken<Count>() {
            }.getType();
        }

        if ("Boulangeries".equalsIgnoreCase(className)) {
            return new TypeToken<Boulangeries>() {
            }.getType();
        }

        if ("CommandesBL".equalsIgnoreCase(className)) {
            return new TypeToken<CommandesBL>() {
            }.getType();
        }

        if ("CommandesLabo".equalsIgnoreCase(className)) {
            return new TypeToken<CommandesLabo>() {
            }.getType();
        }

        if ("Composants".equalsIgnoreCase(className)) {
            return new TypeToken<Composants>() {
            }.getType();
        }

        if ("CompositionsProduit".equalsIgnoreCase(className)) {
            return new TypeToken<CompositionsProduit>() {
            }.getType();
        }

        if ("DetailsCommandeBL".equalsIgnoreCase(className)) {
            return new TypeToken<DetailsCommandeBL>() {
            }.getType();
        }

        if ("BoulangerieAndResp".equalsIgnoreCase(className)) {
            return new TypeToken<BoulangerieAndResp>() {
            }.getType();
        }

        if ("HeadlessCmdLabo".equalsIgnoreCase(className)) {
            return new TypeToken<HeadlessCmdLabo>() {
            }.getType();
        }

        if ("FilteredDetailsCommandeBL".equalsIgnoreCase(className)) {
            return new TypeToken<FilteredDetailsCommandeBL>() {
            }.getType();
        }

        if ("Livreurs".equalsIgnoreCase(className)) {
            return new TypeToken<Livreurs>() {
            }.getType();
        }

        if ("Produits".equalsIgnoreCase(className)) {
            return new TypeToken<Produits>() {
            }.getType();
        }

        if ("Profils".equalsIgnoreCase(className)) {
            return new TypeToken<Profils>() {
            }.getType();
        }

        return new TypeToken<Object>() {
        }.getType();
    }


}
