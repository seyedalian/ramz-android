package sssa.ir.ramz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.view.animation.AlphaAnimation
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    lateinit var ramzsaz:TextView
    lateinit var testRamz:TextView
    lateinit var rahnama:TextView

    fun init(){
        ramzsaz =findViewById(R.id.ramzsaz)
        testRamz =findViewById(R.id.testRamz)
        rahnama=findViewById(R.id.rahnama)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        animat(ramzsaz)
        animat(testRamz)
        animat(rahnama)
    }

    fun animat(v:View){
        v.translationX = -100f
        v.alpha =0f
        v.animate().translationX(0f).alpha(1f).setDuration(2000L).start()

    }
    fun onClick(v:View){
        if(v.id==R.id.ramzsaz){

        }
        if(v.id==R.id.testRamz){

        }
        if(v.id==R.id.rahnama){

        }
    }

    fun checkHaveNum(input: String):Boolean {
        val KV_REGEX = "([1-9])"
        val KV_PATTERN = Pattern.compile(KV_REGEX)

        val matcher = KV_PATTERN.matcher(input)



        while (matcher.find()) {
           return true
        }
        return false
    }
    fun checkHaveSmall(input: String):Boolean {
        val KV_REGEX = "([a-z])"
        val KV_PATTERN = Pattern.compile(KV_REGEX)

        val matcher = KV_PATTERN.matcher(input)



        while (matcher.find()) {
            return true
        }
        return false
    }
    fun checkHaveCap(input: String):Boolean {
        val KV_REGEX = "([A-Z])"
        val KV_PATTERN = Pattern.compile(KV_REGEX)

        val matcher = KV_PATTERN.matcher(input)



        while (matcher.find()) {
            return true
        }
        return false
    }
    fun checkHaveAtherCaracter(input: String):Boolean {
        for(i in 0 .. input.length ){
            if( input[i]<= 32.toChar() && input[i]<=47.toChar() ||
                input[i]<= 58.toChar() && input[i]<=64.toChar() ||
                input[i]<= 91.toChar() && input[i]<=96.toChar() ||
                input[i]<= 123.toChar() && input[i]<=126.toChar()){
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
}
