package com.bye.hi.lie;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ChkidRequest extends StringRequest {

    final static private String URL = "http://ckdaud001.cafe24.com/RegiseterChkid.php";
    private Map<String, String> parameters;

    public ChkidRequest(String userID, Response.Listener<String> listener) {

        super(Method.POST, URL,listener,null);
        parameters =new HashMap<>();
        parameters.put("userID",userID);
    }

    @Override
    public  Map<String, String> getParams() {
        return parameters;
    }
}
