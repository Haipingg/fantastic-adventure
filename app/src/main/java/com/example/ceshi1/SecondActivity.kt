package com.example.ceshi1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_second.*
import java.lang.StringBuilder

class SecondActivity : AppCompatActivity() {

    private val starList = ArrayList<Star>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        initStars()             //这里的initStars()方法，用于初始化所以好友数据
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = StarAdapter(starList)
        recyclerView.adapter = adapter


    }


    private fun initStars() {
        val dbHelper = MyUserTable(this, "User.db", 6)
        val db = dbHelper.writableDatabase
        val Userphone = intent.getStringExtra("Login_name")         //上个Activity传递的用户电话号码在这里获得
        val cursor = db.query("Friend" ,null,"friend_phone = $Userphone",null,null,null,null)
        //与好友表的数据进行对照，如果好友的电话和用户的电话相同，说明这个好友是当前用户的好友
        if(cursor.moveToFirst()){
            do{
                val friendname = cursor.getString(cursor.getColumnIndex("friend_name"))
                val friendphone = cursor.getString(cursor.getColumnIndex("friend_phone"))
                starList.add(Star(friendname,R.drawable.mx0))           //将好友的数据打印出来
            }while (cursor.moveToNext())


        }


//        repeat(2) {
//            starList.add(Star("小美", R.drawable.mx0))
//            starList.add(Star("花花", R.drawable.mx1))
//            starList.add(Star("阿灿", R.drawable.mx2))
//            starList.add(Star("梅梅", R.drawable.mx3))
//            starList.add(Star("猪屁登", R.drawable.mx4))
//            starList.add(Star("MS美少女", R.drawable.mx5))
//            starList.add(Star("小太阳", R.drawable.mx6))
//            starList.add(Star("春风不渡你我", R.drawable.mx7))
//            starList.add(Star("久日难得一遇", R.drawable.mx8))
//        }




    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }


//以下全是菜单栏的跳转，还有数据的传输
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val Addphone = intent.getStringExtra("Login_name")
        val intent4 = Intent(this,Add::class.java)
        intent.putExtra("A_phone",Addphone)
        startActivity(intent4)

        val intent = Intent(this,Add::class.java)
        when(item.itemId){
            R.id.add_item ->
                startActivity(intent)
        }

        val intent2 = Intent(this,DeleteActivity::class.java)
        when(item.itemId){
            R.id.remove_item ->
                startActivity(intent2)
        }

        val intent3 = Intent(this,WeatherActivity::class.java)
        when(item.itemId){
            R.id.weather_item ->
                startActivity(intent3)
        }

        val intent5 = Intent(this,First::class.java)
        when(item.itemId){
            R.id.back_item ->
                startActivity(intent5)
        }


        return true
    }





}

