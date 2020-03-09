package com.google.samples.motionlayoutcodelab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_stepfinal.*


/**
 * @author Yoko Ahadazaro (yoko.ahadazaro@dana.id)
 * @version FlashyFinish, v 0.1 05/03/20 18.01 by Yoko Ahadazaro
 */
class StepFinalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stepfinal)

        fab.setOnClickListener { openAnimation() }
    }

    private fun openAnimation() {
        ml_activity_stepfinal.setTransition(R.id.set1_base, R.id.set2_path)
        setupListenerComplete(
            {
                ml_activity_stepfinal.transitionToState(R.id.set2_path)
            }, {
                setupListenerComplete(
                    {
                        ml_activity_stepfinal.transitionToState(R.id.set3_reveal)
                    }, {
                        ml_activity_stepfinal.transitionToState(R.id.set4_settle)
                    }
                )
            }
        )
        fab.setOnClickListener { closeAnimation() }
    }

    private fun closeAnimation() {
        setupListenerComplete(
            {
                ml_activity_stepfinal.transitionToStart()
            }, {
                setupListenerComplete(
                    {
                        ml_activity_stepfinal.setTransition(R.id.set2_path, R.id.set3_reveal)
                        ml_activity_stepfinal.progress = 1f
                        ml_activity_stepfinal.transitionToStart()
                    }, {
                        setupListenerComplete(
                            {
                                ml_activity_stepfinal.setTransition(R.id.set1_base, R.id.set2_path)
                                ml_activity_stepfinal.progress = 1f
                                ml_activity_stepfinal.transitionToStart()
                            }, {
                                Unit
                            }
                        )
                    }
                )
            }
        )
        fab.setOnClickListener { openAnimation() }
    }

    private fun setupListenerComplete(todo: () -> Unit, onCompletetoDo: () -> Unit) {
        ml_activity_stepfinal.setTransitionListener(object : MotionLayout.TransitionListener {
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