package com.bye.hi.lie.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://ckdaud001.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPW, String userName, String userJob,  String cgName,  String  certName,/* int cgNum */ Response.Listener<String> listener) {

        super(Method.POST, URL,listener,null);
        parameters =new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPW",userPW);
        parameters.put("userName",userName);
        parameters.put("userJob",userJob);
        parameters.put("cgName",cgName);
        parameters.put("certName",certName);

        //parameters.put("cgNum",cgNum +"");
    }

    @Override
    public  Map<String, String> getParams() {
        return parameters;
    }
}
        /*
        this.parameters = parameters;
    }
}
        super(Method.POST,URL,listener,null);
    parameters =new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPW",userPW);
        parameters.put("userName",userName);
        parameters.put("userJob",ususerJob);
        parameters.put("cgNum",cgNum + "");
}


    }
}
        */