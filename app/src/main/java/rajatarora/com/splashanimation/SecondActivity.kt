package rajatarora.com.splashanimation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.w
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bmp: Bitmap
        val byteArray = intent.getByteArrayExtra("image")
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        ivMain.setImageBitmap(bmp)
        animateView()

    }

    private fun animateView() {
        val scaleAnimation = ScaleAnimation(1f, 3f, 1f, 3f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 500

        val alphaAnimation = AlphaAnimation(1f, 0f)
        alphaAnimation.duration = 500

        val animatorSet = AnimationSet(true)
        animatorSet.addAnimation(scaleAnimation)
        animatorSet.addAnimation(alphaAnimation)
        ivMain.startAnimation(animatorSet)

        animatorSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                ivMain.alpha = 0f
                setupRecyclerView()
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })
    }

    private fun setupRecyclerView() {
        rvDemo.layoutManager = LinearLayoutManager(this@SecondActivity)
        rvDemo.adapter = DemoAdapter()
    }
}
