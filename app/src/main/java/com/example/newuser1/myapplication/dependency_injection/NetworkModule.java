package com.example.newuser1.myapplication.dependency_injection;


import com.example.newuser1.myapplication.models.RealmString;
import com.example.newuser1.myapplication.services.error_handling.RxErrorHandlingCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmList;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sam Piercy on 11/02/2017.
 */
@Module
public class NetworkModule
{
    private String url;

    public NetworkModule(String url)
    {
        this.url = url;
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaCallAdapterFactory()
    {
        return RxErrorHandlingCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {}.getType(), new RealmStringDeserializer())
                .create();
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, CallAdapter.Factory rxErrorHandlingCallAdapterFactory)
    {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(rxErrorHandlingCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    //For the sticky situation created by the need for RealmList<RealmString>
    public class RealmStringDeserializer implements
            JsonDeserializer<RealmList<RealmString>> {

        @Override
        public RealmList<RealmString> deserialize(JsonElement json, Type typeOfT,
                                                  JsonDeserializationContext context) throws JsonParseException {

            RealmList<RealmString> realmStrings = new RealmList<>();
            JsonArray stringList = json.getAsJsonArray();

            for (JsonElement stringElement : stringList) {
                realmStrings.add(new RealmString(stringElement.getAsString()));
            }

            return realmStrings;
        }
    }


}
