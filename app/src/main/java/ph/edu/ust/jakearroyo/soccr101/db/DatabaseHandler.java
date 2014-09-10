package ph.edu.ust.jakearroyo.soccr101.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ph.edu.ust.jakearroyo.soccr101.db.DaoMaster.DevOpenHelper;

public class DatabaseHandler extends DevOpenHelper {
    private static final String DATABASE_NAME = "data";
    private static String DB_PATH = "";

    private static DatabaseHandler helper;
    private DaoSession daoSession;
    private final Context mContext;

    private SQLiteDatabase db;

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (helper == null) {
            helper = new DatabaseHandler(context, DATABASE_NAME, null);
        }
        helper.initializeDataBase();
        return helper;
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        mContext = context;
        DB_PATH = mContext.getDatabasePath(DATABASE_NAME).getAbsolutePath();
    }

    public void initializeDataBase() {
        db = helper.getWritableDatabase();
        try {
            /*
             * Write over the empty data that was created in internal
             * storage with the one in assets and then cache it.
             */
            copyDataBase();
        } catch (IOException e) {
            throw new Error("Error copying database");
        }
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
        /*
         * Close SQLiteOpenHelper so it will commit the created empty database
         * to internal storage.
         */
        helper.close();

        /*
         * Open the database in the assets folder as the input stream.
         */
        InputStream myInput = mContext.getAssets().open(DATABASE_NAME);

        /*
         * Open the empty db in interal storage as the output stream.
         */
        OutputStream myOutput = new FileOutputStream(DB_PATH);

        /*
         * Copy over the empty db in internal storage with the database in the
         * assets folder.
         */
        FileHelper.copyFile(myInput, myOutput);

        /*
         * Access the copied database so SQLiteHelper will cache it and mark it
         * as created.
         */
        db.close();
    }

    public void setDB(SQLiteDatabase db) {
        daoSession = (new DaoMaster(db)).newSession();
    }

    public DaoSession getSession() {
        return daoSession;
    }
}
