package com.example.scannersscan;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ProductList extends ArrayAdapter<Product> {

    private Activity context;
    private List<Product> Products;
    DatabaseReference databaseReference;

    public ProductList(@NonNull Activity context, List<Product> Products, DatabaseReference databaseReference) {
        super(context, R.layout.layout_product_list, Products);
        this.context = context;
        this.Products = Products;
        this.databaseReference = databaseReference;
    }
    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_product_list, null ,true);

        TextView textView = (TextView) listViewItem.findViewById(R.id.txtName);

        final Product product = Products.get(pos);
        textView.setText(product.getName());

        return listViewItem;
    }
}
