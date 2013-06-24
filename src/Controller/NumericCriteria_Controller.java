/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.NumericCriteria;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import Interfaces.Report;
/**
 *
 * @author User
 */
public class NumericCriteria_Controller {
     public ArrayList<String> AddProjectController(String file)  throws Exception{
     NumericCriteria NC=new NumericCriteria();
     ArrayList<String> rest=NC.AddProject(file);
     return rest;
     }
     public ArrayList<String> CalculationController(ArrayList<String> arrlist,double DisRate)  throws Exception{
     NumericCriteria NC=new NumericCriteria();
     ArrayList<String> rest=NC.Calculation(arrlist,DisRate);
     Report re=new Report();
     ArrayList<String> res=re.report(rest);
     return rest;
     }
}