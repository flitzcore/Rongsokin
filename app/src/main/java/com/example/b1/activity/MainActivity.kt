package com.example.b1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.b1.R
import kotlinx.android.synthetic.main.main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)



        addFragment(HomeFragment.newInstance())
        bottomBar.show(2)
        bottomBar.add(MeowBottomNavigation.Model(0,R.drawable.ic_news))
        bottomBar.add(MeowBottomNavigation.Model(1,R.drawable.ic_chat))
        bottomBar.add(MeowBottomNavigation.Model(2,R.drawable.ic_home))
        bottomBar.add(MeowBottomNavigation.Model(3,R.drawable.ic_history))
        bottomBar.add(MeowBottomNavigation.Model(4,R.drawable.ic_setting))



        bottomBar.setOnClickMenuListener {
            when(it.id){
                0->{
                    replaceFragment(NewsFragment.newInstance())
                }
                1->{
                    replaceFragment(ChatFragment.newInstance())
                }
                2->{
                    replaceFragment(HomeFragment.newInstance())
                }
                3->{
                    replaceFragment(HistoryFragment.newInstance())
                }
                4->{
                    replaceFragment(SettingFragment.newInstance())
                }
                else->{
                    replaceFragment(HomeFragment.newInstance())
                }

            }
        }

    }
    private fun replaceFragment(fragment:Fragment)
    {
        val fragmentTransition=supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()

    }
    private fun addFragment(fragment:Fragment)
    {
        val fragmentTransition=supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()

    }
}