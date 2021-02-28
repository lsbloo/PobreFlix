package app.student.movieapp.home.adapter.extensions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

val PATTERN_BR="dd/MM/yyyy HH:mm:ss"
fun Date.formatDateBr(): String{
    val dateFormat: DateFormat = SimpleDateFormat(PATTERN_BR)
    val date: Date = dateFormat.parse(this.time.toString())
    return dateFormat.format(date)
}