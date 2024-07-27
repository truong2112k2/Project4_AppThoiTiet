package com.example.da4_appthoitiet.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.da4_appthoitiet.R
import com.example.da4_appthoitiet.data.weather.Hour
import com.example.da4_appthoitiet.databinding.ActivityScreenHourWeatherDetailBinding
import com.example.da4_appthoitiet.databinding.AlertdialogAnnonateBinding
import com.squareup.picasso.Picasso

class ScreenHourWeatherDetail : AppCompatActivity() {
    private lateinit var binding : ActivityScreenHourWeatherDetailBinding
    private lateinit var dataHour: Hour
    private lateinit var animation: Animation
    private lateinit var alertDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityScreenHourWeatherDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        animation = AnimationUtils.loadAnimation(this@ScreenHourWeatherDetail, R.anim.animation)

       createAlertDialog() // tạo alert ghi chú
        toLastScreen() // hàm trở lại màn hình trước
        getDataHourLastScreen() // lấy data thông tin thời tiết từng giờ từ màn hình trước
        createAdvice() // tạo lời khuyên cho ng dùng

    }

    private fun getDataHourLastScreen() {
        try{
            dataHour = intent.getParcelableExtra<Hour>("hour")!!
            showData()

        }catch ( e: Exception){
            Log.i("error", e.message.toString())
        }
    }

    private fun createAdvice() {
        Glide.with(this@ScreenHourWeatherDetail)// hiển thị hình động
            .asGif()
            .load(R.drawable.dor)
            .into(binding.imgDoctor)
         setContentAdvice() // set nội dung lời khuyên


    }

    private fun setContentAdvice() {
        setConentTemperatureAdvice() // lời khuyên về nhiệt độ
        setContentRainAdvice() // lời khuyên về tỉ lệ mưa
        setContentUvAdvice() // lời khuyên về tia UV


    }

    @SuppressLint("SetTextI18n")
    private fun setContentUvAdvice() {
        if( dataHour.uv >0 && dataHour.uv <2){
          binding.noteUV.text = " Có thể ở ngoài trời mà không cần quá lo lắng về tác hại của UV. Tuy nhiên, nếu ở ngoài trời lâu, hãy sử dụng kem chống nắng để bảo vệ da."
        }else if(dataHour.uv in 2.0..5.0){
            binding.noteUV.text = "Sử dụng kem chống nắng với SPF ít nhất 30 và đeo kính râm. Hãy tìm bóng râm khi có thể và tránh ánh nắng mạnh nhất trong khoảng giữa ngày."

        }else if(dataHour.uv in 6.0..7.0){
            binding.noteUV.text = "Nên bảo vệ da và mắt cẩn thận. Sử dụng kem chống nắng với SPF cao, đeo kính râm và đội mũ rộng vành. Hạn chế tiếp xúc với ánh nắng từ 10 giờ sáng đến 4 giờ chiều."

        }else if(dataHour.uv in 8.0..10.0){
            binding.noteUV.text = "Đặc biệt cẩn thận với sự tiếp xúc với ánh nắng. Nên ở trong bóng râm nhiều nhất có thể, sử dụng kem chống nắng với SPF cao và đeo các bảo vệ chống UV"

        }else if( dataHour.uv >= 11){
            binding.noteUV.text = "Tránh tiếp xúc với ánh nắng trực tiếp. Nếu phải ra ngoài, hãy trang bị đầy đủ bảo vệ da và mắt, bao gồm kem chống nắng, kính râm, mũ và quần áo chống UV."
        }else if(dataHour.uv.toInt() == 0){
            binding.noteUV.text = "Thời tiết lúc này không có mặt trời"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setContentRainAdvice() {
          if( dataHour.chance_of_rain > 0 && dataHour.chance_of_rain < 20){
              binding.txtNoteRain.text ="Khả năng mưa thấp, nhưng vẫn nên chuẩn bị một chiếc ô hoặc áo mưa nếu bạn có kế hoạch ngoài trời, đặc biệt là nếu dự báo thời tiết có sự thay đổi."
          }else if( dataHour.chance_of_rain > 20 && dataHour.chance_of_rain < 50){
              binding.txtNoteRain.text ="Có khả năng mưa nhẹ hoặc mưa rào. Mang theo ô hoặc áo mưa là ý tưởng tốt. Cân nhắc điều chỉnh kế hoạch ngoài trời để tránh mưa."

          }else if( dataHour.chance_of_rain > 50 && dataHour.chance_of_rain < 80){
              binding.txtNoteRain.text ="Chuẩn bị cho mưa liên tục hoặc rải rác. Mặc quần áo chống nước và bảo vệ đồ đạc của bạn khỏi bị ướt.\n"

          }else if( dataHour.chance_of_rain > 80){
              binding.txtNoteRain.text ="Mưa lớn hoặc mưa không ngừng. Hạn chế ra ngoài nếu không cần thiết, và chuẩn bị các biện pháp phòng chống ngập lụt."
          }else if( dataHour.chance_of_rain ==0){
              binding.txtNoteRain.text ="Không cần đem theo các dụng cụ đi mưa"

          }
    }

    @SuppressLint("SetTextI18n")
    private fun setConentTemperatureAdvice() {
        if( dataHour.temp_c <= 0 ){
            binding.txtNoteTemp.text = "Mặc áo ấm, găng tay, và mũ. Hãy cẩn thận với băng và tuyết. Sử dụng các lớp áo để giữ ấm và chuẩn bị cho các tình huống khẩn cấp như xe bị kẹt trong tuyết."
        }else if( dataHour.temp_c > 0 && dataHour.temp_c < 10){
            binding.txtNoteTemp.text = "Mặc áo khoác ấm, có thể cần lớp áo bên trong để giữ ấm. Cân nhắc các hoạt động ngoài trời có thể gây lạnh."

        }else if( dataHour.temp_c > 10 && dataHour.temp_c < 20){
            binding.txtNoteTemp.text = "Thường là thời tiết dễ chịu. Mặc áo khoác nhẹ hoặc áo sơ mi dài tay. Thời tiết có thể thay đổi, nên mang theo áo khoác nhẹ hoặc áo mưa."

        }else if(dataHour.temp_c > 20 && dataHour.temp_c < 28 ){
            binding.txtNoteTemp.text = "Thời tiết ấm áp, lý tưởng cho các hoạt động ngoài trời. Mặc quần áo thoáng mát, nhẹ nhàng và uống nhiều nước để tránh mất nước."

        }else if( dataHour.temp_c > 28){
            binding.txtNoteTemp.text = "Hạn chế ra ngoài trong những giờ nắng gắt. Mặc quần áo nhẹ và thoáng khí, sử dụng kem chống nắng, và uống nhiều nước"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showData() {
        Picasso.get().load("https:"+dataHour.condition.icon).into(binding.imgWeather) // show image weather
        binding.txtShowTime.text = reverseDateFormatWithTime(dataHour.time) +" AM"

        if(dataHour.will_it_rain == 1){
            binding.txtStatusRain.text = "Có khả năng mưa"
        }else{
            binding.txtStatusRain.text = "Không có khả năng mưa"

        }
        binding.txtTemperature.text = dataHour.temp_c.toString()+"℃"
        binding.txtHumidity.text = dataHour.humidity.toString()+"%"
        binding.txtAmountOfRain.text = dataHour.precip_in.toString() +" mm"
        binding.txtChanceOfRain.text = dataHour.chance_of_rain.toString() +"%"
        binding.txtAirPressrure.text = dataHour.pressure_in.toString() +" in"
        binding.chanceOfSnow.text = dataHour.chance_of_snow.toString() +"%"
        binding.txtdirectionWind.text = dataHour.wind_dir.toString()
        binding.txtVision.text = dataHour.vis_km.toString() +"km"
        binding.txtCloud.text = dataHour.cloud.toString() + "%"
        binding.txtDew.text = dataHour.dewpoint_c.toString()

    }


    private fun toLastScreen() {
        binding.btnBack.setOnClickListener {
            binding.btnBack.startAnimation(animation)
            onBackPressed()
        }
    }

    private fun createAlertDialog() {
        binding.btnQuestion.setOnClickListener {
            binding.btnQuestion.startAnimation(animation)
            val build = AlertDialog.Builder(this@ScreenHourWeatherDetail)
            val view = AlertdialogAnnonateBinding.inflate(layoutInflater)
            build.setView(view.root)
            alertDialog = build.create()
            alertDialog.show()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    fun reverseDateFormatWithTime(dateTimeString: String): String {
        val parts = dateTimeString.split(" ")
        val dateParts = parts[0].split("-")
        return "${dateParts[2]}-${dateParts[1]}-${dateParts[0]} ${parts[1]}"
    }
}