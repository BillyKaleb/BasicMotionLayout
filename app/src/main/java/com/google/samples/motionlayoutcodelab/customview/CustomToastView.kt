package com.google.samples.motionlayoutcodelab.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.samples.motionlayoutcodelab.R
import kotlinx.android.synthetic.main.custom_toast_view.view.*
import java.util.*
import kotlin.concurrent.schedule

/**
 * @author Yoko Ahadazaro (yoko.ahadazaro@dana.id)
 * @version CustomToastView, v 0.1 09/03/20 19.11 by Yoko Ahadazaro
 */
class CustomToastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var view: View

    init {
        init(context, attrs)
    }

    private fun init(
        context: Context?,
        attrs: AttributeSet?
    ) {
        view = inflate(context, getLayout(), this)
    }

    public fun doTheThing() {
        startAnimation()
    }

    private fun getLayout(): Int = R.layout.custom_toast_view

    private fun startAnimation() {
        ml_custom_toast.setTransition(R.id.custom_toast_start, R.id.custom_toast_mid)
        setupListenerComplete(
            {
                ml_custom_toast.transitionToState(R.id.custom_toast_mid)
            }, {
                setupListenerComplete(
                    {
                        ml_custom_toast.transitionToState(R.id.custom_toast_end)
                    },
                    {
                        startTimer()
                    }
                )
            }
        )

    }

    private fun startTimer() {
        Timer("SettingUp", false).schedule(3000) {
            closeAnimation()
        }
    }

    private fun closeAnimation() {
        setupListenerComplete(
            {
                ml_custom_toast.transitionToStart()
            }, {
                setupListenerComplete(
                    {
                        ml_custom_toast.setTransition(
                            R.id.custom_toast_start,
                            R.id.custom_toast_mid
                        )
                        ml_custom_toast.progress = 1f
                        ml_custom_toast.transitionToStart()
                    }, { Unit }
                )
            }
        )
    }

    private fun setupListenerComplete(todo: () -> Unit, onCompletetoDo: () -> Unit) {
        ml_custom_toast.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                //no op
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                //no op
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                //no op
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                onCompletetoDo()
            }
        })
        todo()
    }
}