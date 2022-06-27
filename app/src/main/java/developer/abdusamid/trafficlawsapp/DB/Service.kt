package developer.abdusamid.trafficlawsapp.DB

import developer.abdusamid.trafficlawsapp.Models.User

interface Service {
    fun addMember(user: User)
    fun updateMember(user: User): Int
    fun deleteMember(user: User)
    fun getAllMember(): ArrayList<User>
}