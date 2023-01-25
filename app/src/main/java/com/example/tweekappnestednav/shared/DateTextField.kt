package com.example.tweekappnestednav.shared

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DateTextField(
    value: TextFieldValue,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit
) {

    var isBackspacePressed by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        visualTransformation = DateFormatVisualTransformation(LocalTextStyle.current),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        onValueChange = {
            // ex: "01-1M-yyyy" -> "011"
            val date = it.text.takeDigitString()

            if (date.length < 9) {

                // if date length is 3 or 5, move cursor to index + 1
                // ex: (| = cursor) "01-|1M-yyyy" to "01-1|M-yyyy"
                val selection = if (!isBackspacePressed) {
                    when (date.length) {
                        3, 5 -> it.selection.start + 1
                        else -> it.selection.start
                    }
                } else it.selection.start

                onValueChange(
                    it.copy(
                        text = TextFieldDateFormatter.format(it),
                        selection = TextRange(selection)
                    )
                )
            }
        },
        modifier = modifier
            .onKeyEvent { event ->
                if (event.key == Key.Backspace) {
                    isBackspacePressed = true
                    return@onKeyEvent true
                } else {
                    isBackspacePressed = false
                }

                false
            }
    )
}

class DateFormatVisualTransformation(
    private val textStyle: TextStyle
    ): VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = buildAnnotatedString {
                for (word in text) {
                    withStyle(
                        textStyle.copy(
                            color = if (word.isDigit() || word == '-') textStyle.color
                            else Color.Gray
                        ).toSpanStyle()
                    ) {
                        append(word)
                    }
                }
            },
            offsetMapping = OffsetMapping.Identity
        )
    }
}

object TextFieldDateFormatter {

    private const val ddMMyyyy = "ddMMyyyy"

    /**
     * format "ddMMyyyy" to "dd-MMM-yyyy"
     * @return formatted string, ex: "11-01-2007" or "11-0M-YYYY"
     */
    fun format(
        fieldValue: TextFieldValue,
        minYear: Int = 2000,
        maxYear: Int = 3000
    ): String {
        val builder = StringBuilder()

        val s = fieldValue.text.replace(
            oldValue = listOf(" ", ".", ",", "-", "d", "M", "y"),
            newValue = ""
        )

        if (s.length != 8) {
            for (i in 0 until 8) {
                builder.append(
                    try {
                        s[i]
                    } catch (e: Exception) {
                        ddMMyyyy[i]
                    }
                )
            }
        } else builder.append(s)

        repeat(3) {
            when (it) {
                0 -> {
                    val day = try {
                        "${builder[0]}${builder[1]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (day != null) {
                        val dayMax = day
                            .coerceIn(
                                minimumValue = 1,
                                maximumValue = 31,
                            )
                            .toString()

                        builder.replace(
                            0,
                            2,
                            if (dayMax.length == 1) "0$dayMax" else dayMax
                        )
                    }
                }
                1 -> {
                    val month = try {
                        "${builder[2]}${builder[3]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (month != null) {
                        val monthMax = month
                            .coerceIn(
                                minimumValue = 1,
                                maximumValue = 12,
                            )
                            .toString()

                        builder.replace(
                            2,
                            4,
                            if (monthMax.length == 1) "0$monthMax" else monthMax
                        )
                    }
                }
                2 -> {
                    val year = try {
                        "${builder[4]}${builder[5]}${builder[6]}${builder[7]}".toInt()
                    } catch (e: Exception) {
                        null
                    }

                    if (year != null) {
                        val yearMaxMin = year.coerceIn(
                            minimumValue = minYear,
                            maximumValue = maxYear
                        ).toString()

                        builder.replace(4, 6, yearMaxMin.substring(0, 2))
                        builder.replace(6, 8, yearMaxMin.substring(2, 4))
                    }
                }
            }
        }

        return builder.toString()
            .addStringBefore("-", 2)  // dd-MMyyyy
            .addStringBefore("-", 5)	// dd-MM-yyyy
    }

    /**
     * format time in milli second to formatted date
     * @return formatted string, ex: "11-01-2007"
     */
    fun format(timeInMillis: Long): String {
        return SimpleDateFormat("dd-MM-yyyy", Locale.US).format(timeInMillis)
    }

    /**
     * check if formattedDate is valid
     *
     * "01-01-2000" -> valid
     * "01-0M-YYYY" -> not valid
     * @param formattedDate "01-01-2000"
     */
    fun isValid(formattedDate: String): Boolean {
        return formattedDate.takeDigitString().length == 8
    }

    /**
     * convert formatted string date to time in millis
     * @param formattedDate "01-01-2000"
     * @return time in millis
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun parse(formattedDate: String): Long {
        val date = "${formattedDate[6]}${formattedDate[7]}${formattedDate[8]}${formattedDate[9]}-"
            .plus("${formattedDate[3]}${formattedDate[4]}-")
            .plus("${formattedDate[0]}${formattedDate[1]}")
            .plus("T00:00")

        return LocalDateTime.parse(date)
            .atZone(ZoneId.of(TimeZone.getDefault().id, ZoneId.SHORT_IDS))
            .toInstant()
            .toEpochMilli()
    }

}

/**
 * take a string that is a number
 *
 * ex:
 * val s = "123a456b"
 * s.takeDigitString() => "123456"
 * @author kafri8889
 */
fun String.takeDigitString(): String {
    var builder = ""

    forEach { if (it.isDigit()) builder += it }

    return builder
}

/**
 * add a new string before the given index
 * @author kafri8889
 */
fun String.addStringBefore(s: String, index: Int): String {
    val result = StringBuilder()
    forEachIndexed { i, c ->
        if (i == index) {
            result.append(s)
            result.append(c)
        } else result.append(c)
    }

    return result.toString()
}

/**
 * Returns a new string with all occurrences of oldValue replaced with newValue.
 * @author kafri8889
 */
fun String.replace(
    oldValue: List<String>,
    newValue: String,
    ignoreCase: Boolean = false
): String {
    var result = this

    oldValue.forEach { s ->
        if (this.contains(s)) {
            result = result.replace(s, newValue, ignoreCase)
        }
    }

    return result
}