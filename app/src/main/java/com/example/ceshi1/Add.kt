package com.example.ceshi1

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class Add : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val dbHelper = MyUserTable(this, "User.db", 6)
        btn_add.setOnClickListener {

            val friendname = edt_friendname.text.toString()
            val friendphone = edt_friendphone.text.toString()
            val Userphone1 = intent.getStringExtra("A_phone")

            if (friendname.length < 2 || friendname.length > 10) {
                edt_friendname.setError("好友帐号长度不符合要求，请输入4-10个字符！")
            } else if (friendname.contains(" ")) {
                edt_friendname.setError("好友帐号不能包括空格！")
            }

            //判断添加的好友电话号嘛是否是当前用户的电话，否则添加不成功
            if(friendphone == Userphone1){
                edt_friendphone.setError("电话号码不匹配！")
            }else{
                    Toast.makeText(this, "添加好友成功！", Toast.LENGTH_SHORT).show()

            }

            //上传好友数据到好友表
            val db = dbHelper.writableDatabase
            val vallue1 = ContentValues().apply{
                put("friend_name",friendname)
                put("friend_phone",friendphone)

            }
            db.insert("Friend",null,vallue1)

//            val values2 = ContentValues().apply {
//                put("friend_name","小美")
//                put("friend_phone","15623452333")
//            }
//            db.insert("Friend",null,values2)
//
//            val values3 = ContentValues().apply {
//                put("friend_name","花花")
//                put("friend_phone","15623452333")
//            }
//            db.insert("Friend",null,values3)
//
//            val values4 = ContentValues().apply {
//                put("friend_name","阿灿")
//                put("friend_phone","15623452333")
//            }
//            db.insert("Friend",null,values4)
//
//            val values5 = ContentValues().apply {
//                put("friend_name","梅梅")
//                put("friend_phone","15623452333")
//            }
//            db.insert("Friend",null,values5)
//
//            val values6 = ContentValues().apply {
//                put("friend_name","猪屁登")
//                put("friend_phone","15623452222")
//            }
//            db.insert("Friend",null,values6)
//
//            val values7 = ContentValues().apply {
//                put("friend_name","MS美少女")
//                put("friend_phone","15623452222")
//            }
//            db.insert("Friend",null,values7)

        }
    }
}