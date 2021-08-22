package com.example.ceshi1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity.apply
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat.apply
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),RadioGroup.OnCheckedChangeListener {

    var change = ""
    @SuppressLint("ResourceType")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = MyUserTable(this, "User.db", 6)
        btn_delete.setOnClickListener {
            dbHelper.writableDatabase
        }

        btn_back.setOnClickListener {
            val intent = Intent(this, First::class.java)
            startActivity(intent)
        }

        btn_singUp.setOnClickListener() {


            val UserId = edt_UserName.text.toString()
            val pwdone = edt_pwd_one.text.toString()
            val pwdtwo = edt_pwd_two.text.toString()
            val emailStr = edt_email.text.toString()
            val userphone = act_phone.text.toString()


            val cityData = listOf("广州", "深圳", "佛山", "珠海")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityData)
            sp_city.adapter = adapter

            val usercity = sp_city.selectedItem.toString()


            if (UserId.length < 3 || UserId.length > 10) {
                edt_UserName.setError("用户帐号长度不符合要求，请输入4-10个字符！")
            } else if (UserId.contains(" ")) {
                edt_UserName.setError("用户帐号不能包括空格！")
            }
            if (pwdone != pwdtwo) {
                edt_pwd_two.setError("两次密码不一致！")
            }
            val rStr = Regex("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
            if (rStr.matches(emailStr) == false) {
                imgv_email.setImageResource(R.drawable.error)
            } else {
                imgv_email.setImageResource(R.drawable.right)

                if(userphone.length != 11){
                    act_phone.setError("电话号码长度有误！")
                }

                btn_singUp.setOnClickListener {
                    Toast.makeText(this, "注册成功！请返回登录界面进行登录", Toast.LENGTH_SHORT).show()
                }

            }
                val db = dbHelper.writableDatabase
                val vallue1 = ContentValues().apply{
                    put("name",UserId)
                    put("sex",change)
                    put("passage",pwdone)
                    put("email",emailStr)
                    put("city",usercity)
                    put("phone",userphone)
                }
                db.insert("User",null,vallue1)          //存储用户数据到数据库



        }

        rg_sex.setOnCheckedChangeListener(this)
    }




    fun deleteOnClick(v: View?) {
        when(v?.id){
            R.id.btn_delete -> {
                Toast.makeText(this,"you clicked delete button!",Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun onCheckedChanged(group:  RadioGroup?, rgbtnId: Int) {
        when(rgbtnId){
            R.id.rgbtn_boy-> {
                change = rgbtn_boy.text.toString()
                Toast.makeText(this,"你的性别是男",Toast.LENGTH_SHORT).show()
            }
            R.id.rgbtn_girl ->{
                change = rgbtn_girl.text.toString()
                Toast.makeText(this,"你的性别是女" ,Toast.LENGTH_SHORT).show()
            }
        }

    }



}
