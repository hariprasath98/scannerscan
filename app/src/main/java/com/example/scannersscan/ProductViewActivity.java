package com.example.scannersscan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductViewActivity extends AppCompatActivity {

    Button btnScan;
    DatabaseReference databaseReference;
    ListView listViewProduct;
    List<Product> products;
    private static String productId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view);
        products = new ArrayList<Product>();
        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        btnScan = (Button) findViewById(R.id.btnScan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent();
                startActivity(browserIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        listViewProduct = (ListView) findViewById(R.id.listViewProduct);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                products.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    Product product = postsnapshot.getValue(Product.class);
                    products.add(product);
                }
                ProductList productAdapter = new ProductList(ProductViewActivity.this, products, databaseReference);
                listViewProduct.setAdapter(productAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
