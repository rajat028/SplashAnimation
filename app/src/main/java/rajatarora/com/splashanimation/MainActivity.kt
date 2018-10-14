package rajatarora.com.splashanimation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_main.*
import pl.droidsonroids.gif.GifDrawable
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    lateinit var bitmap: Bitmap

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gifSplash.setImageResource(R.drawable.macaw)

        val existingOriginalDrawable = gifSplash.drawable as GifDrawable?
        existingOriginalDrawable!!.addAnimationListener {
            if (existingOriginalDrawable.canPause()) {
                existingOriginalDrawable.pause()

                // capturing bitmap of specified view
                rlMain.isDrawingCacheEnabled = true
                bitmap = Bitmap.createBitmap(rlMain.drawingCache)
                rlMain.isDrawingCacheEnabled = false

                // converting bitmap to drawable, which will be used as window background
                val drawable = BitmapDrawable(bitmap) as Drawable
                window.setBackgroundDrawable(drawable)

                // calling next activity
                moveToNextActivity()
            }
        }
    }

    // navigate to next activity with bitmap
    private fun moveToNextActivity() {
        // creating a pause.
        Handler().postDelayed({
            // converting bitmap to byte array.
            val bStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream)
            val byteArray = bStream.toByteArray()

            // calling intent
            val intentSecond = Intent(this@MainActivity, SecondActivity::class.java)
            intentSecond.putExtra("image", byteArray)
            startActivity(intentSecond)
            finish()
            overridePendingTransition(0, 0)
        }, 200)

    }
}
