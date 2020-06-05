package com.example.busanapp.calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busanapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarFragment extends Fragment {
    public static Context mContext;
    public static final int REQUEST_CODE_INSERT = 1000;

    private View view;
    private CalendarView mCalendarView;
    private TextView mTextDate;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/M/d");
    private String mTime;
    private RecyclerView recyclerView;
    private TextAdapter textAdapter;
    private MemoDBHelper dbHelper;
    private ArrayList<String> list;
    private AlertDialog.Builder builder;
    private RecyclerView.LayoutManager mLayoutManager;

    CustomCalendarView customCalendarView;

    public CalendarFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calendar, container, false);

        list = new ArrayList<>();
        builder = new AlertDialog.Builder(getActivity());
        textAdapter = new TextAdapter(list);

        customCalendarView = (CustomCalendarView) view.findViewById(R.id.custom_calendar_view);
        mTextDate = (TextView) view.findViewById(R.id.selectDate);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerMemo);

        Date date = new Date();
        mTime = mFormat.format(date);
//      mTextDate.setText(mTime);       // error

        mContext = getActivity();

        dbHelper = MemoDBHelper.getInstance(getActivity());

//      recyclerView.setHasFixedSize(true);         // error

        mLayoutManager = new LinearLayoutManager(getActivity());
//      recyclerView.setLayoutManager(mLayoutManager);      // error

//      recyclerView.setAdapter(textAdapter);           // error

        getMemoCursor();
        /*
        // error
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mTime = year + "/" + (month + 1) + "/" + dayOfMonth;
                getMemoCursor();
                mTextDate.setText(mTime);
            }
        });
        */
        textAdapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(getActivity(), MemoActivity.class);
                intent.putExtra("SelectedDate", mTime);

                String[] params = {mTime};

                Cursor cursor = (Cursor) dbHelper.getReadableDatabase().query(MemoContract.MemoEntry.TABLE_NAME, null, "date=?", params, null, null, null);
                cursor.moveToPosition(pos);

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_TITLE));
                String contents = cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_CONTENTS));

                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("contents", contents);

                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });

        textAdapter.setOnItemLongClickListener(new TextAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int pos) {
                String[] params = {mTime};

                Cursor cursor = (Cursor) dbHelper.getReadableDatabase().query(MemoContract.MemoEntry.TABLE_NAME, null, "date=?", params, null, null, null);
                cursor.moveToPosition(pos);

                final int id = cursor.getInt(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry._ID));

                builder.setTitle("메모 삭제");
                builder.setMessage("메모를 삭제하시겠습니까?");
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        int deletedCount = db.delete(MemoContract.MemoEntry.TABLE_NAME, MemoContract.MemoEntry._ID + "=" + id, null);

                        if (deletedCount == 0) {
                            Toast.makeText(getActivity(), "삭제 실패", Toast.LENGTH_SHORT).show();
                        } else {
                            getMemoCursor();
                            Toast.makeText(getActivity(), "메모가 삭제되었습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_add) {
            Intent intent = new Intent(getActivity(), MemoActivity.class);
            intent.putExtra("SelectedDate", mTime);

            startActivityForResult(intent, REQUEST_CODE_INSERT);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMemoCursor() {
        String[] params = {mTime};
        list.clear();

        Cursor cursor = dbHelper.getReadableDatabase().query(MemoContract.MemoEntry.TABLE_NAME, null, "date=?", params, null, null, null);

        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex(MemoContract.MemoEntry.COLUMN_NAME_TITLE)));
        }

        textAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_INSERT) {
            getMemoCursor();
        }
    }

    public void show() {
        dbHelper = MemoDBHelper.getInstance(getActivity());

        String[] params = {mTime};

        Cursor cursor = (Cursor) dbHelper.getReadableDatabase().query(MemoContract.MemoEntry.TABLE_NAME, null, "date=?", params, null, null, null);
        cursor.moveToFirst();

        Intent intent = new Intent(getActivity(), CalendarFragment.class);
    }
}