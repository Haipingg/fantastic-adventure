package com.example.ceshi1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.activity_main.*

class First : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)


        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password",false)
        if (isRemember){
            //这里是记住密码功能，将账号和密码都设置到文本框中。
            val account = prefs.getString("account","")
            val password =  prefs.getString("password","")
            edt_UserId.setText(account)
            edt_pwd1.setText(password)
            rememberPass.isChecked = true
        }

        btn_zhuce.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        btn_login45.setOnClickListener(){
            val dbHelper = MyUserTable(this, "User.db", 6)      //引用数据库

            val db = dbHelper.writableDatabase
            val cursor = db.query("User",null,null,null,null,null,null)
            if(cursor.moveToFirst()){
                do{
                    var user_phone = ""
                    val username = cursor.getString(cursor.getColumnIndex("name"))
                    val userpassage = cursor.getString(cursor.getColumnIndex("passage"))        //获取到数据库中的用户名和密码

                    val name = edt_UserId.text.toString()
                    val passage = edt_pwd1.text.toString()
                    if (username == name && userpassage == passage){
                        user_phone = cursor.getString(cursor.getColumnIndex("phone"))           //判断输入的用户名和密码是否与数据库匹配
                        val editor = prefs.edit()
                        if (rememberPass.isChecked){
                            //这里是账号密码正确后，记住密码功能开始检查复选框是否被选中
                            editor.putBoolean("remember_password",true)
                            editor.putString("account",name)
                            editor.putString("password",passage)
                        }else{
                            editor.clear()
                        }
                        editor.apply()
                        val intent = Intent(this,SecondActivity::class.java)        //传递数据到下一个Activity
                        intent.putExtra("Login_name",user_phone)
                        startActivity(intent)




                    }else {
                        Toast.makeText(this, "用户名或密码不正确！", Toast.LENGTH_SHORT).show()
                    }

                }while (cursor.moveToNext())
            }




        }
    }



}
