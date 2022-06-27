package developer.abdusamid.trafficlawsapp

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import developer.abdusamid.trafficlawsapp.MainActivity
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_change.view.*
import kotlinx.android.synthetic.main.fragment_change.view.description
import kotlinx.android.synthetic.main.fragment_change.view.imageAdd
import kotlinx.android.synthetic.main.fragment_change.view.name
import kotlinx.android.synthetic.main.fragment_change.view.spinner
import kotlinx.android.synthetic.main.fragment_editor.view.*
import developer.abdusamid.trafficlawsapp.DB.DbHelper
import developer.abdusamid.trafficlawsapp.Models.User
import developer.abdusamid.trafficlawsapp.Utils.Data

class EditorFragment : Fragment() {

    private lateinit var root: View

    override fun onStart() {
        super.onStart()

        (activity as MainActivity?)?.isVisible(false)

        Data.isHome = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_editor, container, false)

        val user = arguments?.getSerializable("user1") as User

        var image: String = ""
        val CODE_REQUEST = 1


        root.back12.setOnClickListener {
            findNavController().popBackStack()
        }

        root.imageAdd.setImageURI(Uri.parse(user.image))
        root.name.setText(user.name)
        root.description.setText(user.about)
        image = user.image!!

        when (user.type) {
            "Ogohlantiruvchi" -> {
                root.spinner.setSelection(1)
            }
            "Imtiyozli" -> {
                root.spinner.setSelection(2)
            }
            "Ta'qiqlovchi" -> {
                root.spinner.setSelection(3)
            }
            "Buyuruvchi" -> {
                root.spinner.setSelection(4)
            }
        }

        root.change.setOnClickListener {
            val name = root.name.text.toString()
            val about = root.description.text.toString()
            val type = when (root.spinner.selectedItemPosition) {
                0 -> {
                    "empty"
                }
                1 -> {
                    "Ogohlantiruvchi"
                }
                2 -> {
                    "Imtiyozli"
                }
                3 -> {
                    "Ta'qiqlovchi"
                }
                4 -> {
                    "Buyuruvchi"
                }
                else -> {
                    "empty"
                }
            }
            val liked = user.liked

            if (name != "") {
                if (about != "") {
                    if (image != "") {
                        DbHelper(root.context).updateMember(
                            User(
                                user.id,
                                image,
                                name,
                                about,
                                type,
                                liked
                            )
                        )
                        Toast.makeText(context, "Changed", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    } else {
                        Snackbar.make(root, "Empty Image", Snackbar.LENGTH_LONG).show()
                    }
                } else {
                    Snackbar.make(root, "Empty About", Snackbar.LENGTH_LONG).show()
                }
            } else {
                Snackbar.make(root, "Empty Name", Snackbar.LENGTH_LONG).show()
            }
        }

        return root
    }

}