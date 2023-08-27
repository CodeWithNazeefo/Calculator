package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.calculator.databinding.ActivityMainBinding
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager


class MainActivity : AppCompatActivity() {

    var isLightOn: Boolean = true
    private var result = ""


    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        binding.uiModeSwitch.setOnClickListener(View.OnClickListener {
            if (isLightOn) {
                binding.uiModeSwitch.toggle()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                isLightOn = false
            } else {

                binding.uiModeSwitch.toggle()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                isLightOn = true
            }

        })

        binding.btnOne?.setOnClickListener(View.OnClickListener {
            updateValue(1)
        })
        binding.btnTwo?.setOnClickListener(View.OnClickListener { updateValue(2) })
        binding.btnThree?.setOnClickListener(View.OnClickListener { updateValue(3) })
        binding.btnFour?.setOnClickListener(View.OnClickListener { updateValue(4) })
        binding.btnFive?.setOnClickListener(View.OnClickListener { updateValue(5) })
        binding.btnSix?.setOnClickListener(View.OnClickListener { updateValue(6) })
        binding.btnSeven?.setOnClickListener(View.OnClickListener { updateValue(7) })
        binding.btnEight?.setOnClickListener(View.OnClickListener { updateValue(8) })
        binding.btnNine?.setOnClickListener(View.OnClickListener { updateValue(9) })
        binding.btnZero?.setOnClickListener(View.OnClickListener { updateValue(0) })
        binding.btnDot?.setOnClickListener(View.OnClickListener { updateValue(".") })
        binding.btndivide?.setOnClickListener(View.OnClickListener { updateValue("/") })
        binding.btnAdd?.setOnClickListener(View.OnClickListener { updateValue("+") })
        binding.btnMinus?.setOnClickListener(View.OnClickListener { updateValue("-") })
        binding.btnMod?.setOnClickListener(View.OnClickListener { updateValue("%") })
        binding.btnMultiply?.setOnClickListener(View.OnClickListener { updateValue("*") })
        binding.btnBackSpace.setOnClickListener(View.OnClickListener {
           try {
               result = result.substring(0, result.length - 1)
               binding.inputData?.text = result
           }
           catch (e: Throwable){
               Toast.makeText(this,"Don't try before type any digit" , Toast.LENGTH_LONG).show()
           }

           })
        binding.btnAc?.setOnClickListener(View.OnClickListener { clearCalculator() })
        binding.btnEqualTo?.setOnClickListener(View.OnClickListener {
              try {
                  var ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
                  binding.resultData?.text= ScriptEngine.eval(result).toString()


              }
              catch (e: Throwable){
                  Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()

              }
        })
    }
    fun updateValue(oppendNumber :Int){
          result = result + oppendNumber
           binding.inputData?.text = result

       }
    fun updateValue(oppendNumber :String){
         result += oppendNumber
         binding.inputData.text = result

       }
    private fun clearCalculator() {
        binding.resultData?.setText("")
        binding.inputData?.setText("")
        result =""
    }


}




