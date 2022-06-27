package developer.abdusamid.trafficlawsapp.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import developer.abdusamid.trafficlawsapp.R
import developer.abdusamid.trafficlawsapp.databinding.RvItemBinding
import developer.abdusamid.trafficlawsapp.DB.DbHelper
import developer.abdusamid.trafficlawsapp.Models.User


class RvAdapter(
    var context: Context,
    private val list: ArrayList<User>,
    var type: Int,
    var click: Click
) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: RvItemBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: User, p: Int) {

            val anim = AnimationUtils.loadAnimation(context, R.anim.bottom)
            itemRv.cardBg.animation = anim

            itemRv.rvImage.setImageURI(Uri.parse(user.image))

            if (user.liked == "like") {
                itemRv.rvLike.setImageResource(R.drawable.like)
            } else {
                itemRv.rvLike.setImageResource(R.drawable.unlike)
            }

            itemRv.rvText.text = user.name

            itemRv.rvLike.setOnClickListener {
                if (user.liked == "like") {
                    user.liked = "unliked"
                    DbHelper(context).updateMember(user)
                } else {
                    user.liked = "like"
                    DbHelper(context).updateMember(user)
                }
                notifyItemChanged(p)
            }

            itemRv.change.setOnClickListener {
                click.change(user)
            }
            itemRv.delete.setOnClickListener {
                click.remove(user)
            }

            itemRv.root.setOnClickListener {
                click.clicking(user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface Click {
        fun clicking(user: User)
        fun like(user: User, image: ImageView)
        fun change(user: User)
        fun remove(user: User)
    }

}
