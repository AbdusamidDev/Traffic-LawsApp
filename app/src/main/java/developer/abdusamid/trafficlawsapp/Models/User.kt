package developer.abdusamid.trafficlawsapp.Models

import java.io.Serializable

class User : Serializable{
    var id: Int? = null
    var image: String? = null
    var name: String? = null
    var about: String? = null
    var type: String? = null
    var liked: String? = null

    constructor()

    constructor(image: String?, name: String?, about: String?, type: String?, liked: String?) {
        this.image = image
        this.name = name
        this.about = about
        this.type = type
        this.liked = liked
    }

    constructor(id: Int?, image: String?, name: String?, about: String?, type: String?, liked: String?, ) {
        this.id = id
        this.image = image
        this.name = name
        this.about = about
        this.type = type
        this.liked = liked
    }

    override fun toString(): String {
        return "User(id=$id, image=$image, name=$name, about=$about, type=$type, liked=$liked)"
    }

}