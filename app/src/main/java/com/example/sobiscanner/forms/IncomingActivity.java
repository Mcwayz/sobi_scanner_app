package com.example.sobiscanner.forms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.sobiscanner.R;
import com.example.sobiscanner.model.ProductRequest;
import com.example.sobiscanner.model.ProductResponse;
import com.example.sobiscanner.model.ScannerRequest;
import com.example.sobiscanner.model.ScannerResponse;
import com.example.sobiscanner.service.ApiService;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomingActivity extends AppCompatActivity {

    private Dialog dialog;
    private int product_id;
    private ImageView imgBack;
    private Button btn_stock, btn_scan;
    private EditText quantity, barcode;
    private String product_last_action, product_name, code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming);

        imgBack = findViewById(R.id.img_back_mno);
        btn_stock = findViewById(R.id.btn_add_stock);
        barcode = findViewById(R.id.tf_product_barcode);
        quantity = findViewById(R.id.tf_product_quantity);
        btn_scan = findViewById(R.id.btn_get_price);

        imgBack.setOnClickListener(v -> finish());

        btn_scan.setOnClickListener(v -> scanCode());
        btn_stock.setOnClickListener(view -> processStock(addStock()));
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Product Barcode");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                barcode.setText(result.getContents());
                product_last_action = "Addition";
                String barcodeValue = result.getContents();
                try {
                    code =  barcodeValue;
                    getProductID(getProduct(code));
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid Barcode Format", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "No Results Found", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    // API Call To Retrieve Product ID and Name
    private void getProductID(ProductRequest productRequest) {
        Call<ProductResponse> call = ApiService.getProductApiService().postBarcode(productRequest);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    if (productResponse != null) {
                        product_name = productResponse.getProduct_name();
                        product_id = productResponse.getProduct_id();
                        AlertDialog.Builder builder = new AlertDialog.Builder(IncomingActivity.this);
                        builder.setTitle("Product Name");
                        builder.setMessage("Product Name : " + product_name);
                        builder.setPositiveButton("Okay", (dialog, which) -> {
                            dialog.dismiss();
                        });
                        builder.show();
                    } else {
                        Toast.makeText(IncomingActivity.this, "Invalid Response", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(IncomingActivity.this, "Request Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(IncomingActivity.this, "Request Failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "Request Failed: " + t.getLocalizedMessage());
            }
        });
    }

    // API Call To Retrieve Product ID and Name
    private ProductRequest getProduct(String code) {

        ProductRequest productRequest = new ProductRequest();
        productRequest.setOuter_product_case_barcode(code);
        return productRequest;
    }


    // Initial Parameter Instance

    private ScannerRequest addStock(){
        int product, product_quantity;
        String product_last_action;
        product = product_id;
        product_last_action = "Addition";
        ScannerRequest scannerRequest = new ScannerRequest();
        product_quantity = Integer.parseInt(quantity.getText().toString());
        if(product_id == 0){
            Toast.makeText(this, "Please Scan Product Barcode", Toast.LENGTH_SHORT).show();
        } else if (product_quantity == 0) {
            Toast.makeText(this, "Please Enter Product Quantity", Toast.LENGTH_SHORT).show();
        }else{
            scannerRequest.setProduct(product);
            scannerRequest.setProduct_quantity(product_quantity);
            scannerRequest.setProduct_last_action(product_last_action);
        }
        return scannerRequest;
    }


    private void processStock(ScannerRequest scannerRequest)
    {
        Call<ScannerResponse> scannerCall = ApiService.getProductApiService().postProduct(scannerRequest);
        scannerCall.enqueue(new Callback<ScannerResponse>() {
            @Override
            public void onResponse(Call<ScannerResponse> call, Response<ScannerResponse> response) {
                if(response.isSuccessful()){
                    ScannerResponse responseBody = response.body();
                    if (responseBody != null) {
                        responseBody.getProduct_quantity();
                        AlertDialog.Builder builder = new AlertDialog.Builder(IncomingActivity.this);
                        builder.setTitle("Inventory Product Quantity Update");
                        builder.setMessage("Product Quantity Update To : " + responseBody.getProduct_quantity()+"\n" +
                                "Action Performed : "+ responseBody.getProduct_last_action()+"\n" +
                                "Date & Time : "+responseBody.getProduct_last_update()+"\n" +
                                "Thank You!");
                        builder.setPositiveButton("Okay", (dialog, which) -> {
                            dialog.dismiss();
                        });
                        builder.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ScannerResponse> call, Throwable t) {
                Toast.makeText(IncomingActivity.this, "Request Failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "Request Failed: " + t.getLocalizedMessage());
            }
        });

    }



}
