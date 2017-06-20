package id.uwacoding.apps.movieapp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import id.uwacoding.apps.movieapp.data.DBMaker

/**
 * Created by Firman on 6/15/2017.
 */
class App : Application() {
    override fun onCreate() {
       super.onCreate()
        DBMaker.createDb(this)
        Fresco.initialize(this)
    }
}