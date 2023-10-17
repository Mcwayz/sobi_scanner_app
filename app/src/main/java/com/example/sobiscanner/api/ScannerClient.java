package com.example.sobiscanner.api;

import com.example.sobiscanner.model.ProductRequest;
import com.example.sobiscanner.model.ProductResponse;
import com.example.sobiscanner.model.ScannerRequest;
import com.example.sobiscanner.model.DispatchRequest;
import com.example.sobiscanner.model.ScannerResponse;
import com.example.sobiscanner.model.DispatchResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Headers;


public interface ScannerClient {

    String base_url = "http://192.168.0.80:8000";

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    // Get Product Sections
    @GET("/api/getSections/")
    Call<String> getSections();

    // Add Product To Inventory
    @POST("/api/addInventory/")
    Call<ScannerResponse> postProduct(@Body ScannerRequest scanRequest);

    // Dispatch Product
    @POST("/api/dispatchProduct/")
    Call<DispatchResponse> postDispatch(@Body DispatchRequest dispatchRequest);

    // Get Product ID
    @POST("/api/getProductId/")
    Call<ProductResponse> postBarcode(@Body ProductRequest productRequest);

}
