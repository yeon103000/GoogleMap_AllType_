package com.example.busanapp.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.busanapp.R;

public class MemoActivity extends AppCompatActivity {
    private EditText mTitle;
    private EditText mContent;
    private String SeletedDate;
    private int mMemoID;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        mTitle = (EditText) findViewById(R.id.Title_Edit);
        mContent = (EditText) findViewById(R.id.Content_Edit);
        toolbar = (Toolbar) findViewById(R.id.memo_toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white_24dp);

        Intent intent = getIntent();
        if (intent != null) {
            SeletedDate = intent.getStringExtra("SelectedDate");

            mMemoID = intent.getIntExtra("id", -1);

            if (mMemoID != -1) {
                String title = intent.getStringExtra("title");
                String contents = intent.getStringExtra("contents");

                mTitle.setText(title);
                mContent.setText(contents);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.item_save:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        String title = mTitle.getText().toString();
        String contents = mContent.getText().toString();

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(contents)) {
            Toast.makeText(this, "입력된 내용이 없어 저장되지 않았습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(MemoContract.MemoEntry.DATE, SeletedDate);
        contentValues.put(MemoContract.MemoEntry.COLUMN_NAME_TITLE, title);
        contentValues.put(MemoContract.MemoEntry.COLUMN_NAME_CONTENTS, contents);

        SQLiteDatabase db = MemoDBHelper.getInstance(this).getWritableDatabase();

        if (mMemoID == -1) {
            long newRowId = db.insert(MemoContract.MemoEntry.TABLE_NAME, null, contentValues);
            if (newRowId == -1) {
                Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
            }

        } else {
            int count = db.update(MemoContract.MemoEntry.TABLE_NAME, contentValues, MemoContract.MemoEntry._ID + "=" + mMemoID, null);

            if (count == 0) {
                Toast.makeText(this, "수정에 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
            }
        }
        super.onBackPressed();
    }
}