package com.google.samples.motionlayoutcodelab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_toast.*


/**
 * @author Yoko Ahadazaro (yoko.ahadazaro@dana.id)
 * @version CustomToastShowcase, v 0.1 10/03/20 09.00 by Yoko Ahadazaro
 */
class CustomToastShowcase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_toast)

        btn_custom_toast.setOnClickListener { ctv_custom_toast.doTheThing() }
    }
}