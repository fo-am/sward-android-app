package am.fo.swardapp.data

import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

class SwardExport(val data: List<FieldWithSurveysAndRecords>, val fileName: String, val thunk: () -> Unit ) {
    fun export() {
        csvWriter().open(fileName) {
            // Header
            writeRow(
                listOf(
                    "[Field ID]",
                    "[Field name]",
                    "[Field date sown]",
                    "[Field soil type]",
                    "[Field notes]",
                    "[Survey ID]",
                    "[Survey time]",
                    "[Record sample]",
                    "[Record species]"
                )
            )
            data.forEach { field ->
                for (surveyAndRecords in field.surveysAndRecords) {
                    for (record in surveyAndRecords.records) {
                        writeRow(
                            listOf(
                                field.field.fieldId,
                                field.field.name,
                                field.field.dateSown,
                                field.field.soilType,
                                field.field.notes,
                                surveyAndRecords.survey.surveyId,
                                surveyAndRecords.survey.time,
                                record.sample,
                                record.species
                            )
                        )
                    }
                }
            }
        }
        thunk()
    }
}