package hu.renesans.eredmenyek.mock.interceptors;

import android.content.Context;
import android.net.Uri;

import hu.renesans.eredmenyek.network.NetworkConfig;
import okhttp3.Request;
import okhttp3.Response;

import static hu.renesans.eredmenyek.mock.interceptors.MockHelper.makeResponse;

public class MatchesMock {
    private static final String PATH = "matches";
    private static final String PATH_BY_TOURNAMENT = PATH + "/byTournament";
    private static final String PATH_BY_TEAM = PATH + "/byTeam";

    public static Response process(Context context, Request request) {
        Uri uri = Uri.parse(request.url().toString());
        String fileName;

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + PATH_BY_TOURNAMENT) &&
                request.method().equals("GET")) {
            fileName = PATH_BY_TOURNAMENT + "/" + uri.getQueryParameter("id");
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + PATH_BY_TEAM) &&
                request.method().equals("GET")) {
            fileName = PATH_BY_TEAM + "/" + uri.getQueryParameter("id");
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + PATH) &&
                request.method().equals("GET")) {
            fileName = PATH + "/" + request.url().pathSegments().get(request.url().pathSize() - 1);
        } else {
            return makeResponse(request, request.headers(), 503, new byte[0]);
        }

        return MockInterceptor.process(context, request, fileName);
    }
}
