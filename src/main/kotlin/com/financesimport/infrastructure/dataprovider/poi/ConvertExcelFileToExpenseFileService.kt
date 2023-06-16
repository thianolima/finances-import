package com.financesimport.infrastructure.dataprovider.poi

import com.financesimport.core.gateway.ConvertExcelFileToExpenseFile
import com.financesimport.core.model.ExpenseFile
import com.financesimport.core.model.ExpenseFileItem
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class ConvertExcelFileToExpenseFileService (

) : ConvertExcelFileToExpenseFile {

    override fun execute(fileXLS: InputStream, objectKey: String): ExpenseFile {
        val workbook : Workbook = WorkbookFactory.create(fileXLS)
        val sheet: Sheet = workbook.getSheetAt(0)
        var expenseItemList = ArrayList<ExpenseFileItem>()
        for (row in sheet) {
            var description : String = ""
            var amount : Double = 0.0
            var buyDate : LocalDate = LocalDate.now()
            for (cell in row) {
                when (cell.address.column) {
                    0 -> buyDate = toLocalDate(cell.stringCellValue)
                    1 -> description= cell.stringCellValue
                    2 -> amount = toDouble(cell.stringCellValue)
                }
            }
            when(amount == 0.00) {
                true -> break
                false -> expenseItemList.add(ExpenseFileItem(buyDate, description, amount))
            }
        }
        return ExpenseFile(expenseItemList, objectKey)
    }

    private fun toDouble(value : String) : Double {
        val valueReplace = value.replace(",", ".")
        try {
            return valueReplace.toDouble()
        }catch (e: Exception){
            log.error("parse string $value to Double error $e.message")
        }
        return 0.00
    }

    private fun toLocalDate(value : String) : LocalDate {
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        } catch (e: Exception) {
            log.error("parse string $value to LocalDate error $e.message")
        }
        return LocalDate.now()
    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)!!
    }
}