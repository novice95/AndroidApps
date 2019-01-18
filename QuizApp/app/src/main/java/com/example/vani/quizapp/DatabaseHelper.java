package com.example.vani.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =2;
    public static String DATABASE = "quiz.db";
//    public static final Integer NO;
    public static String TABLE ="mytable";
    public static String ID = "id";
    public static String QUESTION ="question";
    public static String ANSWER ="answer";

    String br;

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        
        //  br= "CREATE TABLE mytable(name TEXT,company TEXT,city TEXT,country TEXT);";

        //+NO+"INTEGER PRIMARY KEY AUTOINCREMENT, "
        br = "CREATE TABLE "+TABLE+" ("+QUESTION+ " Text, "+ANSWER+ " Text);";
//        br = "CREATE TABLE "+TABLE+ "( ID INTEGER PRIMARY KEY AUTOINCREMENT , QUESTION TEXT, ANSWER TEXT);";
        db.execSQL(br);
//        System.out.println("OnCreate database");

//        SQLiteDatabase db = this.getWritableDatabase();
//        insertdata(1,"You cannot format text in an e-mail message.","null");

        addQuestions();
//        System.out.println("OnCreate Inserted");

    }

    private void addQuestions() {
        Question q1 = new Question("You cannot format text in an e-mail message.","aaa");
        insertdata(q1);

        Question q2 = new Question("You must include a subject in any mail message you compose.","aaa");
        insertdata(q2);

        Question q3 = new Question("If you want to respond to the sender of a message, click the Respond button.","aaa");
        insertdata(q3);

        Question q4 = new Question("You type the body of a reply the same way you would type the body of a new message.","aaa");
        insertdata(q4);

        Question q5 = new Question("When you reply to a message, you need to enter the text in the Subject: field.","aaa");
        insertdata(q5);

        Question q6 = new Question("You can only print one copy of a selected message.","aaa");
        insertdata(q6);

        Question q7 = new Question("You cannot preview a message before you print it.","aaa");
        insertdata(q7);

        Question q8 = new Question("There is only one way to print a message. true or false.","aaa");
        insertdata(q8);

        Question q9 = new Question("When you print a message, it is automatically deleted from your Inbox.","aaa");
        insertdata(q9);

        Question q10 = new Question("You need to delete a contact and creat a new one to change contact information.","aaa");
        insertdata(q10);

        Question q11 = new Question("You must complete all fields in the Contact form before you can save the contact.","aaa");
        insertdata(q11);

        Question q12 = new Question("You cannot edit Contact forms.","aaa");
        insertdata(q12);

        Question q13 = new Question("You should always open and attachment before saving it","aaa");
        insertdata(q13);

        Question q14 = new Question("All attachment are safe.","aaa");
        insertdata(q14);

        Question q15 = new Question("It is impossible to send a worm or virus over the Internet using in attachment.","aaa");
        insertdata(q15);

        Question q16= new Question("You can only send one attachment per e-mail message.","aaa");
        insertdata(q16);

        Question q17 = new Question("There is only one way to delete a message.","aaa");
        insertdata(q17);

        Question q18 = new Question("The Delete key is for deleting text, it will not delete messages from your Inbox.","aaa");
        insertdata(q18);

        Question q19 = new Question("Pressing the Delete key and clicking the Delete button produce the same result.","aaa");
        insertdata(q19);

        Question q20 = new Question("In Outlook, you must store all of your messages in the Inbox.","aaa");
        insertdata(q20);

        Question q21 = new Question("You can only store messages in a new folder if they are received after you creat the folder.","aaa");
        insertdata(q21);

        Question q22 = new Question("New folders must all be at the same level.","aaa");
        insertdata(q22);

        Question q23 = new Question("There is only one way to create a new folder.","aaa");
        insertdata(q23);

        Question q24 = new Question("You cannot send a file from a Web-based e-mail account.","aaa");
        insertdata(q24);

        Question q25 = new Question("Your e-mail address must be unique","aaa");
        insertdata(q25);

        Question q26 = new Question("Web-based e-mail accounts do not required passwords.","aaa");
        insertdata(q26);

        Question q27 = new Question("You can store Web-based e-mail messages in online folders.","aaa");
        insertdata(q27);

        Question q28 = new Question("You can delete e-mails from a Web-based e-mail account.","aaa");
        insertdata(q28);

        Question q29 = new Question("You can sign up for Web-based e-mail without accepting the Web site's terms and conditions.","aaa");
        insertdata(q29);

        Question q30 = new Question("Your password should be something others will be able to figured out, such as your birthday or wedding anniversary.","aaa");
        insertdata(q30);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");
        onCreate(db);
    }


    public void insertdata(Question ques){
//        System.out.print("InsertData dddddd");
//        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("QUESTION", ques.getQuestion());
        contentValues.put("ANSWER", ques.getAnswer());

        db.insert(TABLE,null,contentValues);
//        db.close();
    }


    public List<Question> getdata() {
//        System.out.print("GetData " + br);
        // Question mQues = new Question();
        List<Question> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();


        if (cursor.moveToFirst()) {
            do {
                Question mQues = new Question();
//                Integer mid = cursor.getInt(cursor.getColumnIndex("id"));
                String mQuestion = cursor.getString(cursor.getColumnIndex("question"));
                String mAnswer = cursor.getString(cursor.getColumnIndex("answer"));
//                mQues.setId(mid.toString());
                mQues.setQuestion(mQuestion);
                mQues.setAnswer(mAnswer);

                stringBuffer.append(mQues);

                data.add(mQues);
            } while (cursor.moveToNext());

            for (Question mo : data) {

                Log.i("Hellomo", "" + mo.getQuestion());

            }
        }
        cursor.close();
        return data;


    }


    public boolean updatedata(String id, String ques, String ans)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
//        contentValues.put("ID", id);
        contentValues.put("QUESTION", ques);
        contentValues.put("ANSWER", ans);
        int result= db.update(TABLE, contentValues, "question= ?",new String[]{ ques});
        return true;
    }

    public Cursor raw()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("select * from " + TABLE + " ;", null);
        return cursor1;
    }
}
