package hu.renesans.eredmenyek.mock.interceptors;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;

import hu.renesans.eredmenyek.network.NetworkConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static hu.renesans.eredmenyek.EredmenyekApplication.injector;
import static hu.renesans.eredmenyek.mock.interceptors.MockHelper.makeResponse;
import static hu.renesans.eredmenyek.utils.AssetHelper.readContent;

public class MockInterceptor implements Interceptor {
    @Inject
    Context context;

    public MockInterceptor() {
        injector.inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }

    public Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        Log.d("Mock Http Client", "URL call: " + uri.toString());
        Headers headers = request.headers();

        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "tournaments")) {
            return TournamentsMock.process(context, request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "teams")) {
            return TeamsMock.process(context, request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "matches")) {
            return MatchesMock.process(context, request);
        }

        return makeResponse(request, headers, 404, new byte[0]);
    }

    public static Response process(Context context, Request request, String fileName) {
        byte[] content;
        int responseCode;

        try {
            content = readContent(context, "json/" + fileName + ".json");
            responseCode = 200;
        } catch (FileNotFoundException e) {
            content = new byte[0];
            responseCode = 404;
        } catch (IOException e) {
            content = new byte[0];
            responseCode = 500;
        }

        return makeResponse(request, request.headers(), responseCode, content);
    }
}
