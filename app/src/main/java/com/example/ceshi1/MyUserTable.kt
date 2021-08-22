package com.example.ceshi1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyUserTable(val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {      //创建SQLite数据库
    //创建用户表
    private val createUser = "create table User (" +
            " id integer primary key autoincrement," +
            "name text," +
            "sex text," +
            "passage text," +
            "email text," +
            "city text," +
            "phone text)"
    //创建好友表2
    private val createFriend = "create table Friend (" +
            "id integer primary key autoincrement," +
            "friend_name text," +
            "friend_phone integer)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createUser)
        db.execSQL(createFriend)
        Toast.makeText(context,"创建成功！",Toast.LENGTH_LONG).show()

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists User")
        db.execSQL("drop table if exists Friend")
        onCreate(db)
    }

}