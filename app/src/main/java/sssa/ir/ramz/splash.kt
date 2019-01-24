package sssa.ir.ramz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ir.sssa.esmbazi.ShareReference


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(ShareReference().checkExit(this,FilesName().RAMZE_FILE)){

        }else{
            startActivity(Intent(this@Splash,MainActivity::class.java))
        }

    }

}
