package com.honetware.statussaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.honetware.statussaver.ui.SavedImagesFragment
import com.honetware.statussaver.ui.ViewedImagesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0F

        // tab titles
        val titles = arrayOf("New Images", "Saved Images")

        viewPager.adapter = ViewPagerFragmentAdapter(this)

        // attaching tab mediator
        TabLayoutMediator(tabLayout, viewPager,
                TabLayoutMediator.TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                    tab.text = titles[position]
                }
        ).attach()
    }
    private class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return ViewedImagesFragment()
                1 -> return SavedImagesFragment()
            }
            return ViewedImagesFragment()
        }

        override fun getItemCount(): Int {
            return 2
        }
    }
}
