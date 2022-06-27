package developer.abdusamid.trafficlawsapp.Fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdusamid.trafficlawsapp.Adapter.ViewPager
import developer.abdusamid.trafficlawsapp.DB.DbHelper
import developer.abdusamid.trafficlawsapp.MainActivity
import developer.abdusamid.trafficlawsapp.Models.User
import developer.abdusamid.trafficlawsapp.R
import developer.abdusamid.trafficlawsapp.Utils.Data
import developer.abdusamid.trafficlawsapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.item_tab.view.*

class MainFragment : Fragment() {

    lateinit var root: View
    lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        root = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(root)



        return root
    }

    override fun onStart() {
        super.onStart()

        (activity as MainActivity?)?.isVisible(true)
        Data.isHome = true

        binding.viewPager.adapter = ViewPager(binding.root.context, object : ViewPager.Click2 {
            override fun clicking(user: User) {
                findNavController().navigate(
                    R.id.showFragment,
                    bundleOf(
                        "image" to user.image,
                        "name" to user.name,
                        "description" to user.about
                    )
                )
            }

            override fun like(user: User, i: ImageView) {
                if (user.liked == "like") {
                    user.liked = "unliked"
                    DbHelper(root.context).updateMember(user)
                } else {
                    user.liked = "liked"
                    DbHelper(root.context).updateMember(user)
                }
                onStart()
            }

            override fun remove(user: User) {
                val d = BottomSheetDialog(root.context)

                val i =
                    LayoutInflater.from(root.context).inflate(R.layout.delete_dialog, null, false)

                d.setContentView(i)

                i.btnYoq.setOnClickListener {
                    d.hide()
                }
                i.btnHa.setOnClickListener {
                    d.hide()
                    DbHelper(root.context).deleteMember(user)
                    onStart()
                }

                d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                d.setCancelable(true)

                d.show()


            }

            override fun change(user: User) {
                findNavController().navigate(R.id.editorFragment, bundleOf("user1" to user))
            }
        })

        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        binding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val customView = tab?.customView
                    customView?.txt1?.setBackgroundResource(R.drawable.tab_fon_selected)
                    customView?.txt1?.setTextColor(resources.getColor(R.color.blue))
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val customView = tab?.customView
                    customView?.txt1?.setBackgroundResource(R.drawable.tab_fon_unselected)
                    customView?.txt1?.setTextColor(resources.getColor(R.color.white))
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

        val tabList = ArrayList<String>()
        tabList.add("Warning")
        tabList.add("Privileged")
        tabList.add("Prohibiting")
        tabList.add("Ordering")
        TabLayoutMediator(binding.tabLayout, binding.viewPager)
        { tab, position ->
            val tabView = LayoutInflater.from(context).inflate(R.layout.item_tab, null, false)

            tab.customView = tabView

            tabView.txt1.text = tabList[position]

            binding.viewPager.setCurrentItem(tab.position, true)
        }.attach()

        root.add_Member.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }

    }


}