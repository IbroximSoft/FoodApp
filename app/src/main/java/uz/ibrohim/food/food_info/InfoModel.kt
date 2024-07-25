package uz.ibrohim.food.food_info

import androidx.lifecycle.ViewModel
import uz.ibrohim.food.home.response.Meal

class InfoModel : ViewModel() {
    var name: String = ""
    var description: String = ""
    var info: String = ""
    var image: String = ""
}