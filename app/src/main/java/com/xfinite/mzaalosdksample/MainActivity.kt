package com.xfinite.mzaalosdksample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xfinite.mzaaloauth.MzaaloEnvironment
import com.xfinite.mzaalorewards.MzaaloRewards
import com.xfinite.mzaalorewards.MzaaloRewardsInitListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MzaaloRewardsInitListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInitialize.setOnClickListener {
            progress.visibility = View.VISIBLE

            //Add to initialize Mzaalo Rewards
            MzaaloRewards.init(this, edtPartnerCode.text.toString(), this,getSelectedEnvironment())
        }
    }

    private fun getSelectedEnvironment():MzaaloEnvironment{
        return if (spinnerEnvironment.selectedItem.toString() == "STAGING") {
            MzaaloEnvironment.STAGING
        }else {
            MzaaloEnvironment.PRODUCTION
        }
    }

    override fun onError(error: String) {
        progress.visibility = View.GONE
        Toast.makeText(this,"Initialization failed : $error",Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        progress.visibility = View.GONE
        Toast.makeText(this,"Initialization Successful",Toast.LENGTH_SHORT).show()

        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}
