package com.honetware.statussaver

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.ui.SavedImagesFragment
import com.honetware.statussaver.ui.ViewedImagesFragment
import com.honetware.statussaver.ui.video.SavedVideoFragment
import com.honetware.statussaver.ui.video.ViewedVideoFragment

class MainActivity : AppCompatActivity() ,ActivityCompat.OnRequestPermissionsResultCallback{

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private val titles = arrayOf("Images", "Video","Saved Images","Saved Video")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0F

        // tab titles


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            viewPager.adapter = ViewPagerFragmentAdapter(this)

            // attaching tab mediator
            TabLayoutMediator(tabLayout, viewPager,
                TabLayoutMediator.TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                    tab.text = titles[position]
                }
            ).attach()
        }else{
            App.requestStoragePermissionExpPdf(viewPager,this)
        }

    }
    private class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return ViewedImagesFragment()
                1 -> return ViewedVideoFragment()
                2 -> return SavedImagesFragment()
                3 -> return SavedVideoFragment()
            }
            return ViewedImagesFragment()
        }

        override fun getItemCount(): Int {
            return 4
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode  == 20){
            if (grantResults.size ==1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                viewPager.adapter = ViewPagerFragmentAdapter(this)
                // attaching tab mediator
                TabLayoutMediator(tabLayout, viewPager,
                    TabLayoutMediator.TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                        tab.text = titles[position]
                    }
                ).attach()
            }else{
               finish()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_privacy -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_policy_url))))
                true
            }
            R.id.action_rateus -> {
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$packageName")
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                        )
                    )
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
