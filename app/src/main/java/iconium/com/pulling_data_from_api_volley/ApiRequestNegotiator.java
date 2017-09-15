package iconium.com.pulling_data_from_api_volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by ogie on 9/14/2017.
 */

public class ApiRequestNegotiator extends Request<ApiDevelopersPayLoadResolver> {


    private static int method ;
    private static String url ;
    private static Map<String, String> headers;
    private static  Response.Listener listener;
    private static  Response.ErrorListener errorListener;
    private static Class<ApiDevelopersPayLoadResolver> devs;
    private static Gson gson = new Gson();
   // private final String string;


    public ApiRequestNegotiator(int _method,
                                String _url,
                                Class<ApiDevelopersPayLoadResolver> _devs,
                                Map<String, String> _headers,
                                Response.Listener _listener,
                                Response.ErrorListener _errorListener) {

        super(_method, _url,_errorListener);
        method = _method;
        devs = _devs;
        url=_url;
        headers = _headers;
        listener = _listener;
        errorListener = _errorListener;
    }




    @Override
    protected Response<ApiDevelopersPayLoadResolver> parseNetworkResponse(NetworkResponse networkResponse) {

//run network request
        try {
            String json = new String(
                    networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            //return model on sucess
            return Response.success(
                    gson.fromJson(json, devs),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }




    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(ApiDevelopersPayLoadResolver _devs) {
        listener.onResponse(_devs);
    }





}
