package com.example.b1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.b1.R
import kotlinx.android.synthetic.main.main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        addFragment(HomeFragment.newInstance())
        bottomBar.show(0)
        bottomBar.add(MeowBottomNavigation.Model(0,R.drawable.ic_home))
        bottomBar.add(MeowBottomNavigation.Model(1,R.drawable.ic_history))
        bottomBar.add(MeowBottomNavigation.Model(2,R.drawable.ic_setting))
        bottomBar.add(MeowBottomNavigation.Model(3,R.drawable.ic_user))


        bottomBar.setOnClickMenuListener {
            when(it.id){
                0->{
                    replaceFragment(HomeFragment.newInstance())
                }
                1->{
                    replaceFragment(HistoryFragment.newInstance())
                }
                2->{
                    replaceFragment(SettingFragment.newInstance())
                }
                3->{
                    replaceFragment(ProfileFragment.newInstance())
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