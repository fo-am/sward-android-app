package am.fo.swardapp.data

import java.text.SimpleDateFormat
import java.util.*

class DateWrangler constructor() {
    companion object {
        fun dateViewToInternal(date: String): String {
            // get the value from the data text and convert it for internal storage
            val dateTime = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(date)
            dateTime?.let {
                return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(dateTime)
            }
            return "Date error: $date"
        }

        fun dateInternalToView(date: String): String {
            // get the value from the data text and convert it for internal storage
            val dateTime = if (date.contains(".")) {
                // deal with old version
                SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(date)
            } else {
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date)
            }
            dateTime?.let {
                return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(dateTime)
            }
            return "Date error: $date"
        }

        fun timeAsView(time: Date): String {
            return SimpleDateFormat("dd/MM/yyyy", Locale.UK).format(time)
        }

        fun timeAsInternal(time: Date): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.UK).format(time)
        }

        fun nowAsView():String {
            return SimpleDateFormat("dd/MM/yyyy",Locale.UK).format(System.currentTimeMillis())
        }
    }
}