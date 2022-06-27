package developer.abdusamid.trafficlawsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import developer.abdusamid.trafficlawsapp.databinding.FragmentAboutBinding
import developer.abdusamid.trafficlawsapp.databinding.FragmentInfoBinding
import kotlinx.android.synthetic.main.fragment_about.view.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FragmentAbout : Fragment() {
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_about, container, false)
        //Instagram Profile
        root.insta_profile.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.instagram.com/muhammadov_abdusamid/")
            )
            startActivity(intent)
        }
        //Telegram Profile
        root.tg_profile.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://t.me/Muhammadov_Abdusamid")
            )
            startActivity(intent)
        }
        //Linkedin Profile
        root.linkedin_profile.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/in/abdusamid-muhammadov-b8245b242/")
            )
            startActivity(intent)
        }
        root.github_profile.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/Abdusamid07")
            )
            startActivity(intent)
        }
        return root
    }
}