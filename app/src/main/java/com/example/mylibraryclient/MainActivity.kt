package com.example.mylibraryclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lib.loopsdk.core.util.RxBusOnboardingRelay
import com.logger.Logger
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private val disposables by lazy { CompositeDisposable() }

    private companion object {
        const val CHECKOUT_WITH_AFTERPAY = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Logger.isEnabled = true
        //Logger.d("MainActivity", "hello")

        disposables.add(RxBusOnboardingRelay.subscribe{ primaryUserId->
            Intent(this, LoginActivity::class.java).apply {
                this.putExtra("USERID", primaryUserId)
                startActivity(this)
            }
            this.finish()
        })


    }
    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

}