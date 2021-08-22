package com.example.ceshi1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.VoicemailContract
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_weather.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import javax.net.ssl.SSLEngineResult
import kotlin.concurrent.thread

class WeatherActivity : AppCompatActivity() {

    var tem: String = ""
    var sky: String = ""
    var comfort: String = ""
    var desc: String = ""
    var wind: String = ""
    var wind2: String = ""
    var pm: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        btn_weather.setOnClickListener {
            sendRequestWithOkHttp()
            look_tem.setText("温度:$tem℃")
            look_wind.setText("风向：$wind    风速：$wind2")
            look_sky.setText("天气状况：$sky")
            look_comfort.setText("舒适度指数：$comfort")
            look_desc.setText("紫外线指数：$desc")
            look_pm.setText("PM2.5浓度：$pm(ug/m3)")
//            调用下面的sendRequestWithOkHttp方法，点击button时，将数据打印在TextView里。
        }



    }

    private fun sendRequestWithOkHttp() {

                var tt: Thread = thread {
                    try {
                        val client = OkHttpClient()
                        val request = Request.Builder()
                            .url("https://api.caiyunapp.com/v2.5/iYElJD2VOG47MYy5/113.147759,23.025412/realtime.json")      //获取到自己在彩云平台的令牌
                            .build()
                        val response = client.newCall(request).execute()
                        val responseData = response.body?.string()
                        if (responseData != null) {
                            var result = JSONObject(responseData)
                            tem = result.getJSONObject("result").getJSONObject("realtime")
                                .getString("temperature")                                           //获取温度
                            wind = result.getJSONObject("result").getJSONObject("realtime")
                                .getJSONObject("wind").getString("direction")                   //获取风向
                            wind2 = result.getJSONObject("result").getJSONObject("realtime")
                                .getJSONObject("wind").getString("speed")                           //获取风速
                            sky = result.getJSONObject("result").getJSONObject("realtime")
                                .getString("skycon")                                                //获取天气状况
                            comfort = result.getJSONObject("result").getJSONObject("realtime")
                                .getJSONObject("life_index").getJSONObject("comfort").getString("index")            //获取舒适度
                            desc = result.getJSONObject("result").getJSONObject("realtime")
                                .getJSONObject("life_index").getJSONObject("ultraviolet").getString("index")        //获取紫外线指数
                            pm = result.getJSONObject("result").getJSONObject("realtime")
                                .getJSONObject("air_quality").getString("pm25")                 //获取PM2.5指数

                        }


                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                tt.join()







    }


}