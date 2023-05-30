package com.financesimport.infrastructure.dataprovider.poi

import com.financesimport.core.gateway.ConvertExcelFileToExpenseFile
import com.financesimport.core.model.ExpenseFile
import com.financesimport.core.model.ExpenseFileItem
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.stereotype.Service
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class ConvertExcelFileToExpenseFileService (

) : ConvertExcelFileToExpenseFile {

    override fun execute(fileXLS: InputStream): ExpenseFile {
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
            if (amount == 0.00) break
            expenseItemList.add(ExpenseFileItem(buyDate, description, amount))
        }
        return ExpenseFile(expenseItemList)
    }

    private fun toDouble(value : String) : Double {
        val valueReplace = value.replace(",", ".")
        try {
            return valueReplace.toDouble()
        }catch (e: Exception){
            println("valor $value valorReplace $valueReplace erro $e.message")
        }
        return 0.00
    }

    private fun toLocalDate(value : String) =
        LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}