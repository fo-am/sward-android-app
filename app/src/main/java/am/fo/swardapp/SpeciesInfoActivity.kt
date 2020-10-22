/*
   Sward App Copyright (C) 2020 FoAM Kernow

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package am.fo.swardapp

import am.fo.swardapp.data.SpeciesDesc
import am.fo.swardapp.data.SpeciesDesc.Companion.createSpeciesDesc
import am.fo.swardapp.data.SpeciesInfo
import am.fo.swardapp.data.SpeciesInfo.Companion.createSpeciesInfo
import am.fo.swardapp.species_fragments.SpeciesPhotoFragment
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_species_info.*

class SpeciesInfoActivity : SwardActivity() {
    lateinit var speciesDesc: SpeciesDesc
    lateinit var speciesInfo: SpeciesInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_species_info)
        setSupportActionBar(toolbar)

        speciesDesc = createSpeciesDesc(intent.getStringExtra("SPECIES"))
        speciesInfo = createSpeciesInfo(intent.getStringExtra("SPECIES"))

        species_name.setText(speciesDesc.name)
        species_name_lat.setText(speciesDesc.lat)

        // set up the images
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        species_images.adapter = pagerAdapter

        // add the slider dots to indicate this is swipable
        val dotscount = pagerAdapter.itemCount
        val dots = arrayOfNulls<ImageView>(dotscount)

        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.non_active_dot
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            slider_dots.addView(dots[i], params)
        }
        dots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                this, R.drawable.active_dot
            )
        )

        // set up the viewpager to resize itself by the current view inside it
        species_images.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // update dots
                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            baseContext, R.drawable.non_active_dot
                        )
                    )
                }
                dots[position]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        baseContext, R.drawable.active_dot
                    )
                )

                val view: View? = supportFragmentManager.findFragmentByTag("f" + position)!!.view;
                view?.let { view ->
                    view.post {
                        val wMeasureSpec =
                            View.MeasureSpec.makeMeasureSpec(
                                view.width,
                                View.MeasureSpec.EXACTLY
                            )
                        val hMeasureSpec =
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                        view.measure(wMeasureSpec, hMeasureSpec)
                        // only get bigger, never smaller
                        if (species_images.measuredHeight < view.measuredHeight) {
                            species_images.layoutParams =
                                (species_images.layoutParams as LinearLayout.LayoutParams)
                                    .also { lp -> lp.height = view.measuredHeight }

// animate - looks a bit worse
/*
                            val anim = ValueAnimator.ofInt(species_images.measuredHeight, view.measuredHeight)
                            anim.addUpdateListener { valueAnimator ->
                                val v = valueAnimator.getAnimatedValue() as Int
                                val layoutParams = view.getLayoutParams()
                                layoutParams.height = v
                                view.setLayoutParams(layoutParams)
                            }
                            anim.setDuration(500)
                            anim.start();
*/

                        }
                    }
                }
            }
        })

        // set up the traits
        val adapter = SpeciesInfoAdapter(this)
        trait_list.adapter = adapter
        trait_list.layoutManager = LinearLayoutManager(this)
        //trait_list.setNestedScrollingEnabled(false)
        adapter.setSpeciesInfo(speciesInfo)

        traits_blurb.text = Html.fromHtml(getString(R.string.trait_list_blurb))
        traits_blurb.movementMethod = LinkMovementMethod.getInstance()
        //traits_blurb.linksClickable = true
        traits_blurb.isClickable = true
    }

   /* override fun onBackPressed() {
        if (species_images.currentItem == 0) {
            super.onBackPressed()
        } else {
            species_images.currentItem = species_images.currentItem - 1
        }
    }
*/
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = speciesDesc.imgs.size
        override fun createFragment(position: Int): Fragment = SpeciesPhotoFragment(speciesDesc.imgs[position])
    }


}