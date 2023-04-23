package cream.geuntae.webtoonwebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import cream.geuntae.webtoonwebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                val textView = TextView(this@MainActivity)
                textView.text = "position $position"
                textView.gravity = Gravity.CENTER

                tab.customView = textView
            }
        }.attach()

    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]

        if (currentFragment is WebViewFragment) {

            if (currentFragment.canGoBack()) {
                currentFragment.goBack()

            } else {
                super.onBackPressed()
            }

        } else {
            super.onBackPressed()
        }

    }
}