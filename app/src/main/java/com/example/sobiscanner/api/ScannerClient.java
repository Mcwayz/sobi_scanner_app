package com.example.sobiscanner.api;

import com.example.sobiscanner.model.ScannerRequest;
import com.example.sobiscanner.model.DispatchRequest;
import com.example.sobiscanner.model.ScannerResponse;
import com.example.sobiscanner.model.DispatchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Headers;


public interface ScannerClient {

    String base_url = "http://127.0.0.1:8000";

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    // Add Product To Inventory
    @POST("/api/addProduct/")
    Call<ScannerResponse> postProduct(@Body ScannerRequest scanRequest);

    // Dispatch Product
    @POST("/api/dispatchProduct/")
    Call<DispatchResponse> postDispatch(@Body DispatchRequest dispatchRequest);

}
