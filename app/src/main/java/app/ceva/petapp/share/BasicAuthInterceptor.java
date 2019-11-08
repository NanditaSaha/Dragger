package app.ceva.petapp.share;

import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    @Inject
    public BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder().method(request.method(), request.body()).addHeader("Authorization", credentials)

                .addHeader("Content-Type","application/json").build();
        Log.d("Nandita-----",authenticatedRequest.header("Authorization")+authenticatedRequest.header("Content-Type"));
        return chain.proceed(authenticatedRequest);
    }
}
