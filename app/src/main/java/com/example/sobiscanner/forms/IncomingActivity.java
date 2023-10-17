package com.example.sobiscanner.forms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sobiscanner.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.android.material.textfield.TextInputEditText;

public class IncomingActivity extends AppCompatActivity {

    private Button btn_stock, btn_scan;
    private ImageView imgBack;
    private String product_last_action;
    private TextInputEditText quantity, barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming);

        // Initialize your views
        imgBack = findViewById(R.id.img_back_mno);
        btn_stock = findViewById(R.id.btn_add_stock);
        barcode = findViewById(R.id.tf_product_barcode);
        quantity = findViewById(R.id.tf_product_quantity);
        btn_scan = findViewById(R.id.btn_get_price);
        // Set a click listener for the stock button
        btn_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add stock logic here
            }
        });

        // Set a click listener for the back button
        imgBack.setOnClickListener(v -> finish());

        // Set a click listener for barcode scanning
        btn_scan.setOnClickListener(v -> scanCode());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scan Results");
                builder.setPositiveButton("Add To Inventory", (dialogInterface, i) -> {
                    barcode.setText(result.getContents());
                    product_last_action = "Addition";
                }).setNegativeButton("Finish", (dialogInterface, i) -> finish());
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(this, "No Results Found", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
