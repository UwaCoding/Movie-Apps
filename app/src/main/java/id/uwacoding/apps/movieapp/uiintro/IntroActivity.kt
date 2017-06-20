package id.uwacoding.apps.movieapp.uiintro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import id.uwacoding.apps.movieapp.R
import id.uwacoding.apps.movieapp.data.movie.MovieRepository
import id.uwacoding.apps.movieapp.uimovie.MovieActivity
import kotlinx.android.synthetic.main.activity_intro.*

/**
 * Created by Firman on 6/15/2017.
 */
class IntroActivity : AppCompatActivity() {

    val TAG: String = this::class.java.name

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_intro)
        startAnimation(patrickLeft, 350)
        startAnimation(patrickCenter, 540)
        startAnimation(patrickRight, 255)
        startAnimation(prepareLabel, 1000)
        startPopcornAnimation()
        loadFirstData()

    }

    private fun startAnimation(view: View, duration: Long) {
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        anim.duration = duration
        view.startAnimation(anim)
    }

    private fun startPopcornAnimation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.fade_in_zoom)
        popcornCircle.startAnimation(anim)
    }

    private fun loadFirstData() {
        val repository = MovieRepository()
        repository.getPopular()
                .subscribe { data, error ->
                    Log.d(TAG, "Subscired result data=$data and error=$error")
                    if (data != null) {
                        startActivity(Intent(this, MovieActivity::class.java))
                        finish()
                    } else{
                        Toast.makeText(this, "Application Error ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}