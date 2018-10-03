package com.example.android.inventory.data;

import android.provider.BaseColumns;

public final class InventoryContract {


    private InventoryContract() {
    }

    public static abstract class InventoryEntry implements BaseColumns {

        public static final String TABLE_NAME = "inventory";


        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "product";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier";
        public static final String COLUMN_SUPPLIER_PHONE = "phone";

        /**
         * Price Values
         */
        public static final int PRICE_FREE = 0;
        public static final int PRICE_CHEAP = 1;
        public static final int PRICE_FEMALE = 2;

    }
}
