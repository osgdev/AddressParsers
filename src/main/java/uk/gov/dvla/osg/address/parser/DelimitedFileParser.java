package uk.gov.dvla.osg.address.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.univocity.parsers.common.processor.ConcurrentRowProcessor;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.*;

import uk.gov.dvla.osg.address.config.DelimitedConfig;
import uk.gov.dvla.osg.address.config.ParserConfig;
import uk.gov.dvla.osg.address.model.Address;
import uk.gov.dvla.osg.address.model.Address.AddressBuilder;
import uk.gov.dvla.osg.address.model.FileType;

public class DelimitedFileParser implements IAddressParser {

    private static final Logger LOGGER = LogManager.getLogger();
    private final  String inputFile;
    private final List<Address> addresses = new ArrayList<>();
    private final DelimitedConfig config;
    private String[] headers;

    /**
     * Instantiates a new delimited file parser.
     *
     * @param pc the parser configuration
     * @param inputFile the input file
     * @param type the type
     */
    public DelimitedFileParser(ParserConfig pc, String inputFile, FileType type) {
        this.inputFile = inputFile;
        this.config = pc.getAppConfig(type);
        load();
    }

    private void load() {
        CsvParser parser = createParser();
        List<Record> allRecords = parser.parseAllRecords(new File(inputFile));
        
        for (Record record : allRecords) {
            //Ignore row if first field matches the Footer Marker
            if (record.getString(0).equals(config.getFooterMarker())) {
                return;
            }
                
           Address address = AddressBuilder.getInstance()
                   .address1(record.getString(config.getAddress1()))
                   .address2(record.getString(config.getAddress2()))
                   .address3(record.getString(config.getAddress3()))
                   .address4(record.getString(config.getAddress4()))
                   .address5(record.getString(config.getAddress5()))
                   .postcode(record.getString(config.getPostcode()))
                   .build();

            addresses.add(address);
        }

        if (config.hasHeader()) {
            headers = parser.getRecordMetadata().headers();
        }
    }

    public void save(String outputFilePath) {
        // copy the input file
        File srcFile = new File(inputFile);
        File outputFile = new File(outputFilePath);

        try (FileWriter fw = new FileWriter(outputFile)) {
            // Create an instance of TsvWriter set with the provided delimiter
            CsvWriterSettings writerSettings = new CsvWriterSettings();
            writerSettings.getFormat().setDelimiter(config.getDelimiter());
            CsvWriter writer = new CsvWriter(fw, writerSettings);
           
            // Writes the file headers
            if (config.hasHeader()) {
                writer.writeHeaders(headers);
            }
            
            // Keep track of which row is being processed
            AtomicInteger counter = new AtomicInteger(0);
            // Build a parser that loops through the original dpf file
            CsvParser parser = createParser();
            
            List<String[]> allRecords = parser.parseAll(srcFile);
            
            for (String[] record : allRecords) {
                // Write out the original row of data
                writer.addValues((Object[]) record);
                // Replace changed values
                if (counter.get() < addresses.size()) { // IGNORE FOOTER ROW
                    writer.addValue(config.getAddress1(), this.addresses.get(counter.get()).getAddress1());
                    writer.addValue(config.getAddress2(), this.addresses.get(counter.get()).getAddress2());
                    writer.addValue(config.getAddress3(), this.addresses.get(counter.get()).getAddress3());
                    writer.addValue(config.getAddress4(), this.addresses.get(counter.get()).getAddress4());
                    writer.addValue(config.getAddress5(), this.addresses.get(counter.get()).getAddress5());
                    writer.addValue(config.getPostcode(), this.addresses.get(counter.getAndIncrement()).getPostcode());
                }
                writer.writeValuesToRow();
            }

            // Flushes and closes the writer
            writer.close();
        } catch (IOException ex) {
            LOGGER.fatal("Unable to write to {} : {}", inputFile, ex.getMessage());
            System.exit(1);
        }

    }

    /**
     * Create a new instance of a TsvParser
     * 
     * @return A TsvParser set to handle header rows
     */
    private CsvParser createParser() {
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.getFormat().setDelimiter(config.getDelimiter());
        parserSettings.setHeaderExtractionEnabled(config.hasHeader());
        parserSettings.setNullValue("");
        parserSettings.setProcessor(new ConcurrentRowProcessor(new RowListProcessor()));
        parserSettings.setLineSeparatorDetectionEnabled(true);
        return new CsvParser(parserSettings);
    }
    
    public List<Address> getAddresses() {
        return this.addresses;
    }
}
