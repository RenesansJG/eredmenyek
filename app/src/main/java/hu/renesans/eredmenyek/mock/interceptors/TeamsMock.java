package hu.renesans.eredmenyek.mock.interceptors;

import android.content.Context;
import android.net.Uri;

import hu.renesans.eredmenyek.network.NetworkConfig;
import okhttp3.Request;
import okhttp3.Response;

import static hu.renesans.eredmenyek.mock.interceptors.MockHelper.makeResponse;

public class TeamsMock {
    private static final String PATH = "teams";

    public static Response process(Context context, Request request) {
        Uri uri = Uri.parse(request.url().toString());

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + PATH) &&
                request.method().equals("GET")) {
            return MockInterceptor.process(context, request, PATH);
        } else {
            return makeResponse(request, request.headers(), 503, new byte[0]);
        }
    }
}
