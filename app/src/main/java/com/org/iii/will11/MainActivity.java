package com.org.iii.will11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private MyDBhelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        dbHelper = new MyDBhelper(this, "iii", null, 1);
        db = dbHelper.getReadableDatabase();
    }

    public void update(View v) {
        // update
        ContentValues data = new ContentValues();
        data.put("cname", "Blue");
        data.put("birthday", "1992-01-03");
        data.put("tel", "0912");
        db.update("cust", data, "id = ?", new String[] {"5"});
        search(null);
    }

    public void delete(View v) {
        // DELETE FROM cust WHERE id = 3 AND cname = 'Brad'
        db.delete("cust", "id = ? AND cname = ?", new String[] {"16", "Will"});
        search(null);
    }

    public void add(View v) {
        // INSERT INTO cust (cname,birthday,tel) VALUES ('Brad','1999-09-08','123');
        ContentValues data = new ContentValues();
        data.put("cname", "Will");
        data.put("birthday", "1992-10-03");
        data.put("tel", "0912345678");
        db.insert("cust", null, data);

        search(null);
    }

    public void search(View V) {
        textView.setText("");
        // SELECT * FROM cust
        // db.execSQL("SELECT * FROM cust");
        Cursor cursor = db.query("cust",null,null,null,null,null,"cname DESC, birthday DESC");

        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String cname = cursor.getString(cursor.getColumnIndex("cname"));
            String birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            String tel = cursor.getString(cursor.getColumnIndex("tel"));
            textView.append(id +":"+ cname + ":" + birthday + ":" + tel + "\n");
        }
    }
}
