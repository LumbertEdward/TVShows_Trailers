package com.example.tvshowstrails

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.tvshowstrails.dataClasses.*
import com.example.tvshowstrails.fragmentClasses.*
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, SelectedItemInterface {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var coordinatorLayout: CoordinatorLayout
    private var toolbar: Toolbar? = null
    private lateinit var bottomView: BottomNavigationView
    private lateinit var navigationView: NavigationView
    private lateinit var title: TextView
    //fragments
    private var popularFragment: PopularFragment? = null
    private var genresFragment: GenresFragment? = null
    private var onAirFragment: OnAirFragment? = null
    private var todayFragment: TodayFragment? = null
    private var topRatedFragment: TopRatedFragment? = null
    private var homeFragment: HomeFragment? = null
    private var detailsFragment: DetailsFragment? = null
    private var episodesFragment: EpisodesFragment? = null
    private var episodeDetails: EpisodeDetails? = null
    private var thumbNailFragment: ThumbNailFragment? = null
    private var videoFragment: VideoFragment? = null
    private var searchFragment: SearchFragment? = null
    private var genreFragment: GenreFragment? = null

    private var tags: ArrayList<String> = ArrayList()
    private var fragmentTags: ArrayList<FragClass> = ArrayList()
    private var mCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer)
        toolbar = findViewById(R.id.tool)
        coordinatorLayout = findViewById(R.id.coordinatorTool)
        setSupportActionBar(toolbar)
        bottomView = findViewById(R.id.bottomNav)
        navigationView = findViewById(R.id.navigation)
        title = findViewById(R.id.titleTool)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.navigationBarColor = ContextCompat.getColor(this, R.color.back)
        }
        drawerToggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_Drawer
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        initFragment()
        navigationView.setNavigationItemSelectedListener(this)
        bottomView.setOnNavigationItemSelectedListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            when(it.itemId){
                R.id.homeBot -> {
                    if (homeFragment == null){
                        homeFragment = HomeFragment()
                        fragmentTransaction.add(R.id.frame, homeFragment!!, "Home")
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        fragmentTransaction.commit()
                        tags.add("Home")
                        fragmentTags.add(FragClass(homeFragment!!, "Home"))
                    }
                    else{
                        tags.remove("Home")
                        tags.add("Home")
                    }
                    setVisibility("Home")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.todayBot -> {
                    if (todayFragment == null){
                        todayFragment = TodayFragment()
                        fragmentTransaction.add(R.id.frame, todayFragment!!, "Today")
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        fragmentTransaction.commit()
                        tags.add("Today")
                        fragmentTags.add(FragClass(todayFragment!!, "Today"))
                    }
                    else{
                        tags.remove("Today")
                        tags.add("Today")
                    }
                    setVisibility("Today")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.airBot -> {
                    if (onAirFragment == null){
                        onAirFragment = OnAirFragment()
                        fragmentTransaction.add(R.id.frame, onAirFragment!!, "OnAir")
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        fragmentTransaction.commit()
                        tags.add("OnAir")
                        fragmentTags.add(FragClass(onAirFragment!!, "OnAir"))
                    }
                    else{
                        tags.remove("OnAir")
                        tags.add("OnAir")
                    }
                    setVisibility("OnAir")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.popBot -> {
                    if (popularFragment == null){
                        popularFragment = PopularFragment()
                        fragmentTransaction.add(R.id.frame, popularFragment!!, "Popular")
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        fragmentTransaction.commit()
                        tags.add("Popular")
                        fragmentTags.add(FragClass(popularFragment!!, "Popular"))
                    }
                    else{
                        tags.remove("Popular")
                        tags.add("Popular")
                    }
                    setVisibility("Popular")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.genresBot -> {
                    if (genresFragment == null){
                        genresFragment = GenresFragment()
                        fragmentTransaction.add(R.id.frame, genresFragment!!, "Genres")
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        fragmentTransaction.commit()
                        tags.add("Genres")
                        fragmentTags.add(FragClass(genresFragment!!, "Genres"))
                    }
                    else{
                        tags.remove("Genres")
                        tags.add("Genres")
                    }
                    setVisibility("Genres")
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setVisibility(tag: String){
        if (tag == "Home"){
            title.text = "TV Shows"
            coordinatorLayout.visibility = View.VISIBLE
            showBottom()
        }
        else if (tag == "Popular"){
            title.text = "Popular Shows"
            coordinatorLayout.visibility = View.VISIBLE
            showBottom()
        }
        else if (tag == "TopRated"){
            title.text = "Top Rated Shows"
            coordinatorLayout.visibility = View.VISIBLE
            showBottom()
        }
        else if (tag == "Today"){
            title.text = "Today Shows"
            coordinatorLayout.visibility = View.VISIBLE
            showBottom()
        }
        else if (tag == "OnAir"){
            title.text = "TV Shows On Air"
            coordinatorLayout.visibility = View.VISIBLE
            showBottom()
        }
        else if (tag == "Genres"){
            title.text = "Genres"
            coordinatorLayout.visibility = View.VISIBLE
            showBottom()
        }
        else if (tag == "Details"){
            coordinatorLayout.visibility = View.GONE
            hideBottom()
        }
        else if (tag == "Episodes"){
            coordinatorLayout.visibility = View.GONE
            hideBottom()
        }
        else if (tag == "EpisodeDetails"){
            coordinatorLayout.visibility = View.GONE
            hideBottom()
        }
        else if (tag == "Thumb"){
            coordinatorLayout.visibility = View.GONE
            hideBottom()
        }
        else if (tag == "Video"){
            coordinatorLayout.visibility = View.GONE
            hideBottom()
        }
        else if (tag == "Search"){
            coordinatorLayout.visibility = View.GONE
            hideBottom()
        }
        else if (tag == "GenreFrag"){
            coordinatorLayout.visibility = View.VISIBLE
            showBottom()
        }
        for (i in fragmentTags.indices){
            if (tag == (fragmentTags[i].string)){
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.show(fragmentTags[i].fragment)
                fragmentTransaction.commit()
            }
            else{
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.hide(fragmentTags[i].fragment)
                fragmentTransaction.commit()
            }
        }
    }
    private fun showBottom(){
        bottomView.visibility = View.VISIBLE
    }
    private fun hideBottom(){
        bottomView.visibility = View.GONE
    }

    override fun onBackPressed() {
        val tot = tags.size
        if (tot > 1){
            val top = tags[tot - 1]
            val bot = tags[tot - 2]
            setVisibility(bot)
            tags.remove(top)
            mCount = 0
        }
        else if (tot == 1){
            val str = tags[tot - 1]
            if (str == "Home"){
                Toast.makeText(applicationContext, "End", Toast.LENGTH_LONG).show()
                mCount++
            }
            else{
                mCount++
            }
        }
        if (mCount >= 2){
            super.onBackPressed()
        }

    }


    private fun initFragment(){
        if (homeFragment == null){
            homeFragment = HomeFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frame, homeFragment!!, "Home")
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            fragmentTransaction.commit()
            tags.add("Home")
            fragmentTags.add(FragClass(homeFragment!!, "Home"))
        }
        else{
            tags.remove("Home")
            tags.add("Home")
        }
        setVisibility("Home")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        return when(item.itemId){
            R.id.home -> {
                if (homeFragment == null){
                    homeFragment = HomeFragment()
                    fragmentTransaction.add(R.id.frame, homeFragment!!, "Home")
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    fragmentTransaction.commit()
                    tags.add("Home")
                    fragmentTags.add(FragClass(homeFragment!!, "Home"))
                }
                else{
                    tags.remove("Home")
                    tags.add("Home")
                }
                setVisibility("Home")
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.today -> {
                if (todayFragment == null){
                    todayFragment = TodayFragment()
                    fragmentTransaction.add(R.id.frame, todayFragment!!, "Today")
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    fragmentTransaction.commit()
                    tags.add("Today")
                    fragmentTags.add(FragClass(todayFragment!!, "Today"))
                }
                else{
                    tags.remove("Today")
                    tags.add("Today")
                }
                setVisibility("Today")
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.air -> {
                if (onAirFragment == null){
                    onAirFragment = OnAirFragment()
                    fragmentTransaction.add(R.id.frame, onAirFragment!!, "OnAir")
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    fragmentTransaction.commit()
                    tags.add("OnAir")
                    fragmentTags.add(FragClass(onAirFragment!!, "OnAir"))
                }
                else{
                    tags.remove("OnAir")
                    tags.add("OnAir")
                }
                setVisibility("OnAir")
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            R.id.popNav -> {
                if (popularFragment == null){
                    popularFragment = PopularFragment()
                    fragmentTransaction.add(R.id.frame, popularFragment!!, "Popular")
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    fragmentTransaction.commit()
                    tags.add("Popular")
                    fragmentTags.add(FragClass(popularFragment!!, "Popular"))
                }
                else{
                    tags.remove("Popular")
                    tags.add("Popular")
                }
                setVisibility("Popular")
                drawerLayout.closeDrawer(GravityCompat.START)
               true
            }
            R.id.top -> {
                if (topRatedFragment == null){
                    topRatedFragment = TopRatedFragment()
                    fragmentTransaction.add(R.id.frame, topRatedFragment!!, "TopRated")
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    fragmentTransaction.commit()
                    tags.add("TopRated")
                    fragmentTags.add(FragClass(topRatedFragment!!, "TopRated"))
                }
                else{
                    tags.remove("TopRated")
                    tags.add("TopRated")
                }
                setVisibility("TopRated")
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            else -> {
                return false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.searchTool -> {
                if (searchFragment == null){
                    searchFragment = SearchFragment()
                    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.frame, searchFragment!!, "Search")
                    fragmentTransaction.commit()
                    tags.add("Search")
                    fragmentTags.add(FragClass(searchFragment!!, "Search"))
                }
                else{
                    tags.remove("Search")
                    tags.add("Search")
                }
                setVisibility("Search")
                true
            }
            else -> {
                false
            }
        }
    }

    override fun passId(popular: Popular?) {
        if (detailsFragment != null){
            supportFragmentManager.beginTransaction().remove(detailsFragment!!).commitAllowingStateLoss()
        }
        detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("DATA", popular)
        detailsFragment!!.arguments = bundle
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame, detailsFragment!!, "Details")
        fragmentTransaction.commit()
        tags.add("Details")
        fragmentTags.add(FragClass(detailsFragment!!, "Details"))
        setVisibility("Details")

    }
    override fun getSeasonEpisodes(season: Season?) {
        if (episodesFragment != null){
            supportFragmentManager.beginTransaction().remove(episodesFragment!!).commitAllowingStateLoss()
        }
        episodesFragment = EpisodesFragment()
        var bundle = Bundle()
        bundle.putParcelable("EPISODE", season)
        episodesFragment!!.arguments = bundle
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame, episodesFragment!!, "Episodes")
        fragmentTransaction.commit()
        tags.add("Episodes")
        fragmentTags.add(FragClass(episodesFragment!!, "Episodes"))
        setVisibility("Episodes")
    }

    override fun getEpisodeData(episodes: Episodes?) {
        if (episodeDetails != null){
            supportFragmentManager.beginTransaction().remove(episodeDetails!!).commitAllowingStateLoss()
        }
        episodeDetails = EpisodeDetails()
        var bundle: Bundle = Bundle()
        bundle.putParcelable("EPISODEDETAILS", episodes)
        episodeDetails!!.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame, episodeDetails!!, "EpisodeDetails")
        fragmentTransaction.commit()
        tags.add("EpisodeDetails")
        fragmentTags.add(FragClass(episodeDetails!!, "EpisodeDetails"))
        setVisibility("EpisodeDetails")
    }

    override fun passTrailer(trailers: Trailers?) {
        if (videoFragment != null){
            supportFragmentManager.beginTransaction().remove(videoFragment!!).commitAllowingStateLoss()
        }
        videoFragment = VideoFragment()
        val bundle: Bundle = Bundle()
        bundle.putParcelable("VIDEO", trailers)
        videoFragment!!.arguments = bundle
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame, videoFragment!!, "Video")
        fragmentTransaction.commit()
        tags.add("Video")
        fragmentTags.add(FragClass(videoFragment!!, "Video"))
        setVisibility("Video")
    }

    override fun passThumb(popular: Popular?) {
        if (thumbNailFragment != null){
            supportFragmentManager.beginTransaction().remove(thumbNailFragment!!).commitAllowingStateLoss()
        }
        thumbNailFragment = ThumbNailFragment()
        val bundle: Bundle = Bundle()
        bundle.putParcelable("TRAILER", popular)
        thumbNailFragment!!.arguments = bundle
        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame, thumbNailFragment!!, "Thumb")
        fragmentTransaction.commit()
        tags.add("Thumb")
        fragmentTags.add(FragClass(thumbNailFragment!!, "Thumb"))
        setVisibility("Thumb")
    }

    override fun getAir() {
        if (onAirFragment == null){
            onAirFragment = OnAirFragment()
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frame, onAirFragment!!, "OnAir")
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            fragmentTransaction.commit()
            tags.add("OnAir")
            fragmentTags.add(FragClass(onAirFragment!!, "OnAir"))
        }
        else{
            tags.remove("OnAir")
            tags.add("OnAir")
        }
        setVisibility("OnAir")

    }

    override fun getTop() {
        if (topRatedFragment == null){
            topRatedFragment = TopRatedFragment()
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frame, topRatedFragment!!, "TopRated")
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            fragmentTransaction.commit()
            tags.add("TopRated")
            fragmentTags.add(FragClass(topRatedFragment!!, "TopRated"))
        }
        else{
            tags.remove("TopRated")
            tags.add("TopRated")
        }
        setVisibility("TopRated")

    }

    override fun getToday() {
        if (todayFragment == null){
            todayFragment = TodayFragment()
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frame, todayFragment!!, "Today")
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            fragmentTransaction.commit()
            tags.add("Today")
            fragmentTags.add(FragClass(todayFragment!!, "Today"))
        }
        else{
            tags.remove("Today")
            tags.add("Today")
        }
        setVisibility("Today")

    }


    override fun getGenreDetailsData(genres: Genres?) {
        title.text = genres!!.name
        //Toast.makeText(applicationContext, genres!!.id.toString(), Toast.LENGTH_LONG).show()
        if (genreFragment != null){
            supportFragmentManager.beginTransaction().remove(genreFragment!!).commitAllowingStateLoss()
        }
        genreFragment = GenreFragment()
        val bundle: Bundle = Bundle()
        bundle.putInt("GENREID", genres!!.id!!)
        genreFragment!!.arguments = bundle
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame, genreFragment!!, "GenreFrag")
        fragmentTransaction.commit()
        tags.add("GenreFrag")
        fragmentTags.add(FragClass(genreFragment!!, "GenreFrag"))
        setVisibility("GenreFrag")
    }
}