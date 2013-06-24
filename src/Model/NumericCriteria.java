/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author User
 */
public class NumericCriteria {

    ArrayList<String> ListFile = new ArrayList<>();
    ArrayList<String> ListAns = new ArrayList<>();
    ArrayList<ArrayList<String>> ListOfAns = new ArrayList<ArrayList<String>>();
    String data[][];
    int count = 0;

    public ArrayList<String> AddProject(String file) {
        try {
            String file1 = file.replace("\\", "\\\\");
            ListFile.add(file1);
            return ListFile;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry. The process has been failed! Please try again with correct details.");            
            return ListFile;
        }
    }

    public ArrayList<String> Calculation(ArrayList<String> ListFile, double DisRate) {

        try {
            for (int i = 0; i < ListFile.size(); i++) {
                double[] ArrCost = new double[100];
                double[] ArrBen = new double[100];

//================================filein);========================================================
                FileInputStream filein = new FileInputStream(new File(ListFile.get(i)));
                HSSFWorkbook workbookPD = new HSSFWorkbook(filein);

                //Get first sheet from the workbook
                HSSFSheet worksheetPD = workbookPD.getSheetAt(0);

                //Iterate through each rows from first sheet
                Iterator<Row> rowIterator = worksheetPD.iterator();
                int y = 0;
                while (rowIterator.hasNext()) {
                    Row rowPD = rowIterator.next();

                    Iterator<Cell> cellIterator = rowPD.cellIterator();
                    int x = 0;
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();
                        if (cell.getCellType() == 0) {
                            if (x == 0) {
                                ArrCost[y] = cell.getNumericCellValue();

                            }
                            if (x == 1) {
                                ArrBen[y] = cell.getNumericCellValue();
                            }
                        }
                        x++;
                    }
                    y++;
                }
                filein.close();
                
                String filename = "E:\\Project\\Calculation" + i + ".xls";
            
//======================================================================================

                FileOutputStream fileOut = new FileOutputStream(filename);
                FileInputStream file = new FileInputStream(new File(filename));


                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet worksheet = workbook.createSheet("Project");

                // index from 0,0... cell A1 is cell(0,0)
                HSSFRow row1 = worksheet.createRow((short) 0);
                HSSFRow row2 = worksheet.createRow((short) 1);
                HSSFRow row3 = worksheet.createRow((short) 2);
                HSSFRow row4 = worksheet.createRow((short) 3);
                HSSFRow row5 = worksheet.createRow((short) 4);
                HSSFRow row6 = worksheet.createRow((short) 5);
                HSSFRow row7 = worksheet.createRow((short) 6);
                HSSFRow row8 = worksheet.createRow((short) 7);
                HSSFRow row9 = worksheet.createRow((short) 8);
                HSSFRow row10 = worksheet.createRow((short) 9);
                HSSFRow row11 = worksheet.createRow((short) 10);
                HSSFRow row12 = worksheet.createRow((short) 11);


                Cell cellA1 = row1.createCell((short) 0);
                cellA1.setCellValue("Discount Rate");
                Cell cellA2 = row2.createCell((short) 0);
                cellA2.setCellValue("Costs");
                Cell cellA3 = row3.createCell((short) 0);
                cellA3.setCellValue("Benefits");
                Cell cellA4 = row4.createCell((short) 0);
                cellA4.setCellValue("Cash Folw");
                Cell cellA5 = row5.createCell((short) 0);
                cellA5.setCellValue("NPV");

                Cell cellA6 = row6.createCell((short) 0);
                cellA6.setCellValue("Discount Factor");

                Cell cellA7 = row7.createCell((short) 0);
                cellA7.setCellValue("Discounted Costs");
                Cell cellA8 = row8.createCell((short) 0);
                cellA8.setCellValue("Discounted Benefits");
                Cell cellA9 = row9.createCell((short) 0);
                cellA9.setCellValue("Discounted Benefits - Costs");
                Cell cellA10 = row10.createCell((short) 0);
                cellA10.setCellValue("Cumulative Ben - Cost");
                Cell cellA11 = row11.createCell((short) 0);
                cellA11.setCellValue("ROI");
                Cell cellA12 = row12.createCell((short) 0);
                cellA12.setCellValue("Payback Period");

                String DiscountRate = new Double(DisRate).toString();
                double DisRatePer = DisRate / 100;
                String DF = "";
                double DF1;
                int Years = y;
                Cell cellB1 = row1.createCell((short) 1);
                cellB1.setCellValue(DiscountRate);
                int z = 0;
                for (z = 1; z < Years; z++) {
                    HSSFCell cellCost = row2.createCell((short) z);
                    cellCost.setCellValue(ArrCost[z]);
                }
                for (z = 1; z < Years; z++) {
                    HSSFCell cellBen = row3.createCell((short) z);
                    cellBen.setCellValue(ArrBen[z]);
                }
                for (z = 1; z < Years; z++) {
                    HSSFCell cellDF = row6.createCell((short) z);


                    int x = z - 1;
                    DF = "(1/(1+" + DiscountRate + ")^(" + x + "))";

                    DF1 = 1 / (Math.pow((1 + DisRatePer), (z - 1)));
                    cellDF.setCellValue((DF1));
                }

                workbook.write(fileOut);
                fileOut.flush();
                fileOut.close();

                HSSFSheet sheet = workbook.getSheetAt(0);
                Cell cell = null;

                //Update the value of cell
                Cell cell1 = null;
                Cell cell2 = null;
                Cell cell3 = null;
                Cell cell5 = null;
                Cell cell6 = null;
                Cell cellDF = null;
                Cell cellDCB = null;
                Cell cellCumulativeBC = null;
                Cell cellPBP = null;
                z = 0;
                double CumulativeBC = 0.0;
                int[] ArrPBP = {0};
                double TotDFBen = 0.0;
                double TotDFCost = 0.0;
                for (z = 1; z < Years; z++) {
                    cell1 = sheet.getRow(1).getCell(z);

                    cell2 = sheet.getRow(2).getCell(z);

                    cell3 = row4.createCell((short) z);

                    cell3.setCellValue(cell2.getNumericCellValue() - cell1.getNumericCellValue());

                    cellDF = sheet.getRow(5).getCell(z);

                    cell5 = row7.createCell((short) z);
                    double DFCost = cellDF.getNumericCellValue() * cell1.getNumericCellValue();
                    cell5.setCellValue(DFCost);


                    cell6 = row8.createCell((short) z);
                    double DFBen = cellDF.getNumericCellValue() * cell2.getNumericCellValue();
                    cell6.setCellValue(DFBen);

                    cellDCB = row9.createCell((short) z);
                    double DB_C = DFBen - DFCost;
                    cellDCB.setCellValue(DB_C);

                    TotDFBen = TotDFBen + DFBen;
                    TotDFCost = TotDFCost + DFCost;

                    CumulativeBC = CumulativeBC + DB_C;
                    cellCumulativeBC = row10.createCell((short) z);
                    cellCumulativeBC.setCellValue(CumulativeBC);
                    if ((CumulativeBC > 0) && (ArrPBP[0] == 0.0)) {
                        ArrPBP[0] = z - 1;
                        cellPBP = row12.createCell((short) 1);
                        cellPBP.setCellValue(ArrPBP[0]);
                    }
                }
                Cell cell4 = row5.createCell((short) 1);
                cell4.setCellValue(TotDFBen - TotDFCost);
                Cell cellROI = row11.createCell((short) 1);
                cellROI.setCellValue(((TotDFBen - TotDFCost) / TotDFCost) * 100);
                file.close();

                FileOutputStream outFile = new FileOutputStream(new File(filename));
                workbook.write(outFile);
                fileOut.flush();
                outFile.close();

//========================================================================================
                FileInputStream file1 = new FileInputStream(new File(filename));
                HSSFWorkbook workbook1 = new HSSFWorkbook(file1);
                HSSFSheet worksheet1 = workbook1.getSheetAt(0);

                Cell cellNPV = worksheet1.getRow(4).getCell(1);
                Cell cellR = worksheet1.getRow(10).getCell(1);
                Cell cellBP = worksheet1.getRow(11).getCell(1);

                String Pro = Integer.toString(i);
                String StrcellNPV = new Double(Math.round(cellNPV.getNumericCellValue())).toString().replaceAll("[.0]+$", "");
                String StrcellR = new Double(Math.round(cellR.getNumericCellValue())).toString().replaceAll("[.0]+$", "") + "%";
                String StrcellBP = new Double(Math.round(cellBP.getNumericCellValue())).toString().replaceAll("[.0]+$", "");
                ListAns.add(Pro);
                ListAns.add(StrcellNPV);
                ListAns.add(StrcellR);
                ListAns.add(StrcellBP);
            }

            return ListAns;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry. The operation has been failed! Please try again with correct details.");            
           
            System.out.println(e.toString());
            return ListFile;
        }
    }
}
