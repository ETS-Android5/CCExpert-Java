package fr.unice.polytech.ccexpert.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static String DB_NAME = "ccexpert_database";
    private SQLiteDatabase db;
    private final Context myContext;
    private Sets sets;

    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        this.sets = Sets.getInstance();
    }

    public void openDataBase() {
        String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void execute() {
        loadHeroes();
        loadDungeons();
        loadTalents();
        loadPets();
        loadArchdemons();
    }

    public void createDataBase() {
        checkDataBase();
        this.getReadableDatabase();
        try {
            copyDataBase();
        } catch (IOException e) {
            throw new Error("Error copying database", e);
        }
    }

    private void checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void loadHeroes() {
        Cursor c = db.rawQuery("select * from heroes", null);
        c.moveToFirst();
        Hero hero;
        List<String> enchantments;

        while (!c.isAfterLast()) {
            enchantments = new ArrayList<>();
            for (int i = 11; i <= 13; i++) {
                if (!c.isNull(i)) {
                    enchantments.add(c.getString(i));
                }
            }

            hero = new Hero(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6),
                    c.getString(7), c.getString(8), c.getString(9), c.getString(10), enchantments);
            sets.addHero(hero, c.getInt(0));

            c.moveToNext();
        }
        c.close();
    }

    private void loadDungeons() {
        Cursor c = db.rawQuery("select * from Dungeons", null);
        c.moveToFirst();
        Dungeon d;
        while (!c.isAfterLast()) {
            d = new Dungeon(c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4));
            sets.addDungeon(d);
            c.moveToNext();
        }
        c.close();
    }

    private void loadTalents() {
        Cursor c = db.rawQuery("select * from talents", null);
        c.moveToFirst();
        Talent t;
        while (!c.isAfterLast()) {
            t = new Talent(c.getString(0), c.getString(1));
            sets.addTalent(t);
            c.moveToNext();
        }
        c.close();
    }

    private void loadPets() {
        Cursor c = db.rawQuery("select * from pets", null);
        c.moveToFirst();
        Pet p;
        while (!c.isAfterLast()) {
            p = new Pet(c.getString(0), c.getString(1));
            sets.addPet(p);
            c.moveToNext();
        }
        c.close();
    }

    private void loadArchdemons() {
        Cursor c = db.rawQuery("select * from archdemons", null);
        c.moveToFirst();
        Archdemon a;
        while (!c.isAfterLast()) {
            String[] heroes = {c.getString(2), c.getString(5), c.getString(8), c.getString(11), c.getString(14), c.getString(17)};
            String[] talents = {c.getString(3), c.getString(6), c.getString(9), c.getString(12), c.getString(15), c.getString(18)};
            String[] crests = {c.getString(4), c.getString(7), c.getString(10), c.getString(13), c.getString(16), c.getString(19)};

            a = new Archdemon(c.getString(0), c.getString(1), heroes, talents, crests);
            sets.addArchdemon(a);
            c.moveToNext();
        }
        c.close();
    }
}
