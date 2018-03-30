package mumble.nooko3.Datatypes.NControllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "cache.sqlite";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_REQUESTS = "requests";

    public static final String COLUMN_REQUESTS_API = "api";
    public static final String COLUMN_REQUESTS_RESPONSE = "response";

    private static final String DATABASE_CREATE_SLIDESHOW_IMAGES =
            "CREATE TABLE " + TABLE_REQUESTS + " ( " +
                    COLUMN_REQUESTS_API + " TEXT PRIMARY KEY, " +
                    COLUMN_REQUESTS_RESPONSE + " TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SLIDESHOW_IMAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUESTS);
    }

    public void addRequest(String api, String result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = createContentValuesRequest(api, result);
        if (isDataAlreadyInDBorNot(api)) {
            db.insert(TABLE_REQUESTS, null, values);
        } else {
            db.update(TABLE_REQUESTS, values, COLUMN_REQUESTS_API + " = " + api, null);
        }
        db.close();
    }

    public String getRequestResponse(String api) {
        String response = null;
        String query = "SELECT * FROM " + TABLE_REQUESTS + " WHERE " + COLUMN_REQUESTS_API + " = " + api;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                response = cursor.getString(1);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return response;
    }

    public ContentValues createContentValuesRequest(String api, String response){
        ContentValues values = new ContentValues();
        values.put(COLUMN_REQUESTS_API, api);
        values.put(COLUMN_REQUESTS_RESPONSE, response);
        return values;
    }

    public boolean isDataAlreadyInDBorNot(String api) {
        SQLiteDatabase sqldb = getWritableDatabase();
        String Query = "Select * from " + TABLE_REQUESTS + " where " + COLUMN_REQUESTS_API + " = " + api;
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

}
