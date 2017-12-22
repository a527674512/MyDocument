package com.example.lhf.mydocument.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lhf.mydocument.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/22.
 */

public class VolleyTest extends BaseActivity {

    private RequestQueue queue;

    private ImageView ivImageRequest;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_volleytest);
        queue = Volley.newRequestQueue(this);
        ivImageRequest = findViewById(R.id.iv_image_request);

        Button sendrequest = findViewById(R.id.btn_send_request);
        sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
        Button sendJson = findViewById(R.id.btn_send_json_request);
        sendJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendJasonRequest();
            }
        });

        Button sendImage = findViewById(R.id.btn_send_image_request);
        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImageRequest();
            }
        });


    }

    private void request() {
        StringRequest stringRequest = new StringRequest("http://www.163.com/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", "返回结果" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    private void sendPostRequest() {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("params1", "value1");
//                map.put("params2", "value2");
//                return map;
//            }
//        };
    }

    private void sendJasonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void sendImageRequest(){
        ImageRequest imageRequest = new ImageRequest(
                "https://image.baidu.com/search/detail?z=0&word=%E7%8E%8B%E4%B9%89%E5%8D%9A%E4%BD%9C%E5%93%81&hs=0&pn=3&spn=0&di=0&pi=42860157042&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cs=1180371380%2C3607269624&os=&simid=&adpicid=0&lpn=0&fm=&sme=&cg=&bdtype=-1&oriquery=&objurl=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d980cbfdd618dfa9ec8b13cdd7.jpg&fromurl=&gsm=0&catename=pcindexhot",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ivImageRequest.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ivImageRequest.setImageResource(R.drawable.format_music);
            }
        });
        queue.add(imageRequest);
    }
}
