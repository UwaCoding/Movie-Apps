package id.uwacoding.apps.movieapp.base

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Firman on 6/15/2017.
 */
@Suppress("LeakingThis")
abstract class BaseLifecycleActivity<T : AndroidViewModel> : AppCompatActivity(), LifecycleRegistryOwner {

    abstract val vmClass: Class<T>
    protected lateinit var viewModel: T
    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry = registry

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        viewModel = ViewModelProviders.of(this).get(vmClass)
    }
}
