package com.example.android.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.inventory.data.InventoryContract;


/**
 * {@link InventoryCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of inventory data as its data source. This adapter knows
 * how to create list items for each row of inventory data in the {@link Cursor}.
 */
public class InventoryCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the inventory data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current inventory can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.product_id);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity_id);
        TextView priceTextView = (TextView) view.findViewById(R.id.price_id);
        ImageButton saleButton = (ImageButton) view.findViewById(R.id.sale_buttom_id);


        // Find the columns of inventory attributes that we're interested in
        int _id = cursor.getInt(cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry._ID));
        String nameColumnIndex = cursor.getString(cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME));
        int quantityColumnIndex = cursor.getInt(cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry.COLUMN_QUANTITY));
        int priceColumnIndex = cursor.getInt(cursor.getColumnIndexOrThrow(InventoryContract.InventoryEntry.COLUMN_PRICE));

        // Update the TextViews with the attributes for the current inventory
        nameTextView.setText(nameColumnIndex);
        quantityTextView.setText(Integer.toString(quantityColumnIndex));
        priceTextView.setText(Integer.toString(priceColumnIndex));

        Product productObj = new Product(_id, quantityColumnIndex);
        saleButton.setTag(productObj);

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product obj = (Product) v.getTag();
                String selection = InventoryContract.InventoryEntry._ID + "= ?";
                String[] selectionArgs = {Integer.toString(obj.getmProductId())};
                Uri updateURI = Uri.withAppendedPath(InventoryContract.InventoryEntry.CONTENT_URI, Integer.toString(obj.getmProductId()));
                if (obj.getmProductQuantity() > 0) {
                    ContentValues values = new ContentValues();
                    values.put(InventoryContract.InventoryEntry.COLUMN_QUANTITY, obj.getmProductQuantity() - 1);
                    int count = context.getContentResolver().update(updateURI, values, selection, selectionArgs);
                }

            }
        });


    }
}