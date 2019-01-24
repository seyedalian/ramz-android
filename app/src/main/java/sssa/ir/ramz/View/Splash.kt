package sssa.ir.ramz.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ir.sssa.esmbazi.sharedPreferences
import sssa.ir.ramz.FilesName
import sssa.ir.ramz.R


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(sharedPreferences().checkExit(this, FilesName().RAMZE_FILE)){

        }else{
            startActivity(Intent(this@Splash, MainActivity::class.java))
        }

    }

}
