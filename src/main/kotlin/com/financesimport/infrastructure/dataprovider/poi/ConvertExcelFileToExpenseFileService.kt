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
        val expenseItemList = ArrayList<ExpenseFileItem>()
        for (row in sheet) {
            var description : String = ""
            var amount : Double = 0.0
            var buyDate : LocalDate = LocalDate.now()
            for (cell in row) {
                log.info("column ${cell.address.column} celValue ${cell.stringCellValue}")
                if(cell.stringCellValue.isEmpty()) {
                    break
                }
                when (cell.address.column) {
                    0 -> buyDate = LocalDate.parse(cell.stringCellValue, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    1 -> description= cell.stringCellValue
                    2 -> amount = cell.stringCellValue.replace(",", ".").toDouble()
                }
            }
            if(description.isEmpty()) {
                break
            }
            expenseItemList.add(ExpenseFileItem(buyDate, description, amount))
        }
        return ExpenseFile(expenseItemList, objectKey)
    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)!!
    }
}