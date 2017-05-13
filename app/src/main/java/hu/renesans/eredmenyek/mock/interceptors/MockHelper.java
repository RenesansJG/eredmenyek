package hu.renesans.eredmenyek.mock.interceptors;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;

public class MockHelper {
    public static Response makeResponse(Request request, Headers headers, int code, byte[] content) {
        return new Response.Builder().protocol(Protocol.HTTP_2).code(code).request(request).headers(headers).body(new ResponseBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("application/json");
            }

            @Override
            public long contentLength() {
                return content.length;
            }

            @Override
            public BufferedSource source() {
                return Okio.buffer(Okio.source(new ByteArrayInputStream(content)));
            }
        }).build();
    }

    public static String bodyToString(Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "";
        }
    }
}
