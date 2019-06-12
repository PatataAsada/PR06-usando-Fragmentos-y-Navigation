package es.iessaladillo.yeraymoreno.pr06_new.data.di;

import android.content.Context;

import com.ashokvarma.gander.GanderInterceptor;

import java.util.concurrent.TimeUnit;

import es.iessaladillo.yeraymoreno.pr06_new.base.TagCallFactory;
import es.iessaladillo.yeraymoreno.pr06_new.data.RepositoryJoke;
import es.iessaladillo.yeraymoreno.pr06_new.data.RepositoryJokeImpl;
import es.iessaladillo.yeraymoreno.pr06_new.data.remote.ApiServiceImpl;
import es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.retrofitfragment.RetrofitViewModelFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injector {
    private Injector() { }

    private static OkHttpClient okHttpClient;

    public static RetrofitViewModelFactory provideMainFragmentViewModelFactory(Context context) {
        return new RetrofitViewModelFactory(getRepository(context));
    }

    private static RepositoryJoke getRepository(Context context) {
        return RepositoryJokeImpl.getInstance(getApiService(context));
    }

    private static ApiServiceImpl getApiService(Context context) {
        return ApiServiceImpl.getInstance(provideApi(context), provideOkHttpClient(context));
    }

    private static ApiServiceImpl.Api provideApi(Context context) {
        OkHttpClient okHttpClient = provideOkHttpClient(context);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.chucknorris.io/jokes/")
                .client(okHttpClient)
                .callFactory(new TagCallFactory(okHttpClient))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiServiceImpl.Api.class);
    }

    private static GanderInterceptor provideGanderInterceptor(Context context) {
        return new GanderInterceptor(context).showNotification(true);
    }

    private static OkHttpClient provideOkHttpClient(Context context) {
        if (okHttpClient == null) {
            synchronized (Injector.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(provideGanderInterceptor(context))
                            .callTimeout(5, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
    }
}
