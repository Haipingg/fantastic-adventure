package com.example.ceshi1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_delete.*

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val dbHelper = MyUserTable(this, "User.db", 6)
        btn_delete2.setOnClickListener {

            val frienddelete = edt_delete.text.toString()
            val db = dbHelper.writableDatabase
            db.delete("Friend","friend_name = ?", arrayOf(frienddelete))            //输入好友的名字通过SQLite的delete方法删除好友
        }


    }
}