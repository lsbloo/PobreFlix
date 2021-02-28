package app.student.movieapp.home.extensions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

val PATTERN_BR="dd/MM/yyyy HH:mm:ss"
fun Date.formatDateBr(): String{
    val dateFormat: SimpleDateFormat = SimpleDateFormat(PATTERN_BR)
    return dateFormat.format(this)
}