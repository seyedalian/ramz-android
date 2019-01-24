package sssa.ir.ramz.View

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import sssa.ir.ramz.R

class PowerAssessment : AppCompatActivity() {
    var points =0
    lateinit var note:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_power_assessment)
        val text:EditText =findViewById(R.id.text)
        val process:CircleProcess =findViewById(R.id.process)
        process.isAutoColor =true
        note =findViewById(R.id.note)
        text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val mNote=checkPower(p0.toString())
                process.setProgress(points)
                note.text=mNote

            }
        })
    }
    fun checkPower(input: String):String{
        points =0
        var mNote:String=""
        if(input.length>=8){
            if(checkHaveNum(input)){
                points+=25
            }else{
                mNote+="فاقد عدد\n"
            }

            if(checkHaveSmall(input)){
                points+=25
            }else{
                mNote+="فاقد حرف کوچک\n"
            }
            if(checkHaveCap(input)){
                points+=25
            }else{
                mNote+="فاقد حرف بزرگ\n"
            }
            if(checkHaveAtherCaracter(input)){
                points+=25
            }else{
                mNote+="فاقد کاراکتر های غیرحرفی وغیر عدد\n"
            }
            if(checkHaveContinuousWord(input)){
                if((points>=25))
                    points-=25
                mNote+="مشکل حروف پشت سر هم کیبور یا اعداد پشت سر هم یا حروف پشت سر هم در الفبا\n"
            }
        }else{
            mNote="طول رمز کمتر از 8رقم است.\n"

        }
        if (checkHaveSame(input)){
            if((points>=25))
                points-=25
            mNote +="دارای کاراکتر های مشابه پشت سرهم \n "
        }
        if(points ==100){
            mNote="رمز قوی!"

        }
      return mNote

    }
    fun animat(v: View){
        v.translationX = -100f
        v.alpha =0f
        v.animate().translationX(0f).alpha(1f).setDuration(2000L).start()

    }

    fun checkHaveNum(input: String):Boolean {
        for(i in 0 until  input.length ){
            if(input[i].toInt() in 48..57){
                return true
            }

        }
        return false
    }
    fun checkHaveSmall(input: String):Boolean {
        for(i in 0 until  input.length ){
            if( input[i].toInt() in 97..122){
                return true
            }

        }
        return false
    }
    fun checkHaveCap(input: String):Boolean {
        for(i in 0 until input.length ){
            if( input[i].toInt() in 65..90){
                return true
            }

        }
        return false
    }
    fun checkHaveAtherCaracter(input: String):Boolean {
        for(i in 0 until input.length ){
            if( 32.toChar()<=input[i]  && input[i]<=47.toChar() ||
                58.toChar()<=input[i] && input[i]<=64.toChar() ||
                91.toChar()<= input[i]  && input[i]<=96.toChar() ||
                123.toChar()<=input[i] && input[i]<=126.toChar()){
                return true
            }

        }
        return false
    }
    fun checkHaveContinuousWord(input:String):Boolean{
        if(input.contains("abc") || input.contains("cde") ||
            input.contains("fgh") || input.contains("abc")||
            input.contains("qwe")|| input.contains("asd")||
            input.contains("zxc")|| input.contains("qaz")||
            input.contains("wsx")|| input.contains("edc")||
            input.contains("ert")|| input.contains("wer")||
            input.contains("asd")|| input.contains("zxc")||
            input.contains("sdf")|| input.contains("xcv")||
            input.contains("123")|| input.contains("456")||
            input.contains("789")|| input.contains("234")||
            input.contains("456")|| input.contains("678")){
            return true

        }
        return false
    }
    fun checkHaveSame(input: String):Boolean{
        for(i in 0 until input.length){
                if(input.length>2 && i!=input.length-1)
                if(input[i]==input[i+1]){
                    return true

            }
        }
        return false

    }
}
