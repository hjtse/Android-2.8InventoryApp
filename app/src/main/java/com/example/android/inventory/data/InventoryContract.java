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

        /**
         * Gender Values
         */
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

    }
}
