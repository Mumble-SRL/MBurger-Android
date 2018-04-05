package mumble.nooko3.sdk.NControllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NConstants.NUserConst;

/**
 * Database instance used for offline caching
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NCachingHelper extends SQLiteOpenHelper {

    /**
     * Caching database name
     */
    private static final String DATABASE_NAME = "cache.sqlite";

    /**
     * Caching database version
     */
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_REQUESTS = "requests";
    private static final String COLUMN_REQUESTS_API = "api";
    private static final String COLUMN_REQUESTS_RESPONSE = "response";
    private static final String COLUMN_REQUESTS_API_TIME = "time";

    /**
     * Caching database creator
     */
    private static final String DATABASE_CREATE_CACHING_TABLE =
            "CREATE TABLE " + TABLE_REQUESTS + " ( " +
                    COLUMN_REQUESTS_API + " TEXT PRIMARY KEY, " +
                    COLUMN_REQUESTS_RESPONSE + " TEXT, " +
                    COLUMN_REQUESTS_API_TIME + " TEXT)";

    public NCachingHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_CACHING_TABLE);
    }

    /**
     * On caching DB upgrade clear the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUESTS);
    }

    /**
     * Adds a new request to the caching db with the current device time
     */
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

    /**
     * Given the API gets a response from the caching DB, if it's too old, returns null and deletes the row from the DB
     */
    public String getRequestResponse(String api) {
        String response = null;
        String query = "SELECT * FROM " + TABLE_REQUESTS + " WHERE " + COLUMN_REQUESTS_API + " = " + api;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                response = cursor.getString(1);
                long responseTime = cursor.getLong(2);
                if (!isResponseValid(responseTime)) {
                    response = null;
                    deleteResponse(api);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return response;
    }

    /**
     * Given the API deletes a response
     */
    private int deleteResponse(String api) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_REQUESTS, COLUMN_REQUESTS_API + " = " + api, null);
    }

    /**
     * Given the API deletes a response
     */
    private ContentValues createContentValuesRequest(String api, String response) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_REQUESTS_API, api);
        values.put(COLUMN_REQUESTS_RESPONSE, response);
        values.put(COLUMN_REQUESTS_API_TIME, System.currentTimeMillis());
        return values;
    }

    /**
     * Checks if there is already a cached element with the given API "key"
     */
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

    /**
     * Checks if the response is valid considering caching time
     */
    private static boolean isResponseValid(long responseTime) {
        long diff = System.currentTimeMillis() - responseTime;
        if (diff > NUserConst.cachingTime) {
            return false;
        }

        return true;
    }

}
