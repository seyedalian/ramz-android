package sssa.ir.ramz.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import sssa.ir.ramz.R


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
        if(v.id== R.id.ramzsaz){

        }
        if(v.id== R.id.testRamz){
            startActivity(Intent(this@MainActivity, PowerAssessment::class.java))
        }
        if(v.id== R.id.rahnama){

        }
    }

}
