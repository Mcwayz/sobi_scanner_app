package com.example.sobiscanner.forms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sobiscanner.R;
import com.example.sobiscanner.model.DispatchRequest;
import com.example.sobiscanner.model.DispatchResponse;
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

public class OutgoingActivity extends AppCompatActivity {


    private Dialog dialog;
    private ImageView imgBack;
    private Button btn_out, btn_scan;
    private EditText quantity, barcode;
    private String product_last_action, code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoing);
        imgBack = findViewById(R.id.img_back);
        btn_out = findViewById(R.id.btn_out_stock);
        btn_scan = findViewById(R.id.btn_get_scan);
        barcode = findViewById(R.id.tf_product_barcode);
        quantity = findViewById(R.id.tf_product_quantity);
        btn_scan.setOnClickListener(view -> scanCode());
        imgBack.setOnClickListener(view -> {
            Intent incoming = new Intent(OutgoingActivity.this, MainActivity.class);
            startActivity(incoming);
            finish();
        });
        btn_out.setOnClickListener(view -> processStock(outStock()));
    }


// Scanner Function
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
                product_last_action = "Subtraction";
                String barcodeValue = result.getContents();
                try {
                    code =  barcodeValue;

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



    // Initial Parameter Instance

    private DispatchRequest outStock(){
        int product_quantity;
        String product_last_action, outer_product_case_barcode;
        product_last_action = "Subtraction";
        outer_product_case_barcode = barcode.getText().toString();
        DispatchRequest dispatchRequest = new DispatchRequest();
        product_quantity = Integer.parseInt(quantity.getText().toString());

        if (product_quantity == 0) {
            Toast.makeText(this, "Please Enter Product Quantity", Toast.LENGTH_SHORT).show();
        }else{
            dispatchRequest.setOuter_product_case_barcode(outer_product_case_barcode);
            dispatchRequest.setProduct_last_action(product_last_action);
            dispatchRequest.setProduct_quantity(product_quantity);

        }
        return dispatchRequest;
    }

    private void processStock(DispatchRequest dispatchRequest)
    {
        Call<DispatchResponse> scannerCall = ApiService.getProductApiService().postDispatch(dispatchRequest);
        scannerCall.enqueue(new Callback<DispatchResponse>() {
            @Override
            public void onResponse(Call<DispatchResponse> call, Response<DispatchResponse> response) {
                if(response.isSuccessful()){
                    DispatchResponse responseBody = response.body();
                    if (responseBody != null) {
                        responseBody.getProduct_quantity();
                        AlertDialog.Builder builder = new AlertDialog.Builder(OutgoingActivity.this);
                        builder.setTitle("Inventory Product Quantity Update");
                        builder.setMessage("Product Quantity Update To : " + responseBody.getProduct_quantity()+"\n" +
                                "Action Performed : Subtraction \n" +
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
            public void onFailure(Call<DispatchResponse> call, Throwable t) {
                Toast.makeText(OutgoingActivity.this, "Request Failed: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "Request Failed: " + t.getLocalizedMessage());
            }
        });

    }


}