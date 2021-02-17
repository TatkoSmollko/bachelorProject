package sk.stuba.bachelorProject.services;



import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.bachelorProject.model.Item;
import sk.stuba.bachelorProject.model.PriceOffer;
import sk.stuba.bachelorProject.model.UsedItem;
import sk.stuba.bachelorProject.repositories.PriceOfferRepository;

import java.io.*;


@Service
public class XlsService {

    @Autowired
    PriceOfferRepository priceOfferRepository;

    public void createPriceOfferExcel(String name, String priceOfferId) throws IOException, InvalidFormatException {
        //input source excel file which contains sheets to be copied
        PriceOffer priceOffer = priceOfferRepository.findById(priceOfferId).orElseThrow(
                ()->new ObjectNotFoundException("id","PriceOffer"));
        POIFSFileSystem file = new POIFSFileSystem(new FileInputStream(new File("/Users/tomasvago/Downloads/bachelorProject/ponukaVzor.xls")));
        HSSFWorkbook workbookinput = new HSSFWorkbook(file);

    //output new excel file to which we need to copy the above sheets
    //this would copy entire workbook from source
        HSSFWorkbook workbookoutput=workbookinput;
        Sheet sheet = workbookoutput.getSheetAt(0);
        int startIndex = 19;
        for(UsedItem item:priceOffer.getItems()){
            sheet.getRow(startIndex).getCell(0).setCellValue(item.getParentItem().getName());
            sheet.getRow(startIndex).getCell(1).setCellValue(item.getCount());
            startIndex++;
        }

    //To write your changes to new workbook
        FileOutputStream out = new FileOutputStream("/Users/tomasvago/Downloads/bachelorProject/ponukaVzor2.xls");
        workbookoutput.write(out);
        out.close();
    }
}
