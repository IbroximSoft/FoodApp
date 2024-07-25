package uz.ibrohim.food.utils

import androidx.fragment.app.Fragment
import es.dmoral.toasty.Toasty

fun Fragment.successToast(text: String) {
    Toasty.success(requireContext(), text, Toasty.LENGTH_SHORT, true).show()
}

fun Fragment.warningToast(text: String) {
    Toasty.warning(requireContext(), text, Toasty.LENGTH_SHORT, true).show()
}

fun Fragment.errorToast(text: String) {
    Toasty.error(requireContext(), text, Toasty.LENGTH_SHORT, true).show()
}