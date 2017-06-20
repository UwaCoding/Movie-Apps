package id.uwacoding.apps.movieapp.uimovie

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import id.uwacoding.apps.movieapp.R
import id.uwacoding.apps.movieapp.base.BaseLifecycleActivity
import id.uwacoding.apps.movieapp.data.movie.Movie
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import kotlinx.android.synthetic.main.activity_movie.*

/**
 * Created by Firman on 6/15/2017.
 */
class MovieActivity : BaseLifecycleActivity<MovieViewModel>(),
        SwipeRefreshLayout.OnRefreshListener {


    override val vmClass = MovieViewModel::class.java
    private val adapter = MovieAdapter()
    private var currentFilter = MovieViewModel.FILTER_POPULAR
    private val TAB_POSITION = "tab_position"

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_movie)
        rvMovies.setHasFixedSize(true)
        val animateAdapter = AlphaInAnimationAdapter(adapter)
        animateAdapter.setDuration(1000)
        rvMovies.adapter = animateAdapter
        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)
        if (bundle == null) {
            viewModel.setFilter(MovieViewModel.FILTER_POPULAR)
        }
        setSupportActionBar(toolbar)
        setupComponents()
    }

    override fun onResume() {
        super.onResume()
        observeLiveData()
    }

    /**
     * Inflate option menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.reload_menu -> onRefresh()
        }
        return super.onOptionsItemSelected(item)
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TAB_POSITION, tabs.selectedTabPosition)
    }

    override fun onRestoreInstanceState(saved: Bundle?) {
        super.onRestoreInstanceState(saved)
        saved?.let {
            val pos = it.getInt(TAB_POSITION)
            tabs.getTabAt(pos)?.select()
        }
    }

    /**
     * Observe live data
     */
    private fun observeLiveData() {
        viewModel.isLoadingLiveData.observe(this, Observer<Boolean> {
            it?.let { swipeRefresh.isRefreshing = it }
        })
        viewModel.reposLiveData.observe(this, Observer<List<Movie>> {
            it?.let { adapter.dataSource = it }
        })
        viewModel.throwableLiveData.observe(this, Observer<Throwable> {
            //it?.let { Snackbar.make(rvMovies, "No movie for current $currentFilter filter.", Snackbar.LENGTH_LONG).show() }
        })
    }

    override fun onRefresh() {
        viewModel.setFilter(currentFilter)
    }


    private fun setupComponents() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position ?: 0) {
                    0 -> currentFilter = MovieViewModel.FILTER_POPULAR
                    1 -> currentFilter = MovieViewModel.FILTER_TOP_RATED
                    2 -> currentFilter = MovieViewModel.FILTER_FAVORITE
                }
                onRefresh()
            }
        })
    }
}
