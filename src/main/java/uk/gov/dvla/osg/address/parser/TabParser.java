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
import com.univocity.parsers.tsv.*;

import uk.gov.dvla.osg.address.config.ParserConfig;
import uk.gov.dvla.osg.address.config.TsvConfig;
import uk.gov.dvla.osg.address.model.Address;
import uk.gov.dvla.osg.address.model.Address.AddressBuilder;

public class TabParser implements IAddressParser {

    private static final Logger LOGGER = LogManager.getLogger();

    private final String inputFile;
    private final List<Address> addresses = new ArrayList<>();
    private final TsvConfig config;

    public TabParser(ParserConfig pc, String inputFile) {
        this.inputFile = inputFile;
        config = pc.getTsv();
        load();
    }

    private void load() {
        // Create an instance of TsvParser with the default settings
        TsvParser parser = createParser();
        // Extract all records from the input file
        List<Record> allRecords = parser.parseAllRecords(new File(inputFile));
        // Extract the address from each record
        for (Record record : allRecords) {
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

    }

    /**
     * Create a new instance of a TsvParser
     * 
     * @return A TsvParser set to handle header rows
     */
    private TsvParser createParser() {
        TsvParserSettings parserSettings = new TsvParserSettings();
        parserSettings.setNullValue("");
        parserSettings.setProcessor(new ConcurrentRowProcessor(new RowListProcessor()));
        parserSettings.setLineSeparatorDetectionEnabled(true);
        parserSettings.setHeaderExtractionEnabled(false);
        return new TsvParser(parserSettings);
    }

    public void save(String outputFilepath) {
        // copy the input file
        File srcFile = new File(inputFile);
        File outputFile = new File(outputFilepath);

        try (FileWriter fw = new FileWriter(outputFile)) {
            // Create an instance of TsvWriter with the default settings
            TsvWriter writer = new TsvWriter(fw, new TsvWriterSettings());
            // Keep track of which row is being processed
            AtomicInteger counter = new AtomicInteger(0);
            // Build a parser that loops through the original dpf file
            TsvParser parser = createParser();
            // Extract records from the input file
            List<String[]> allRecords = parser.parseAll(srcFile);
            // Write all records to the output file
            for (String[] record : allRecords) {
                // Write out the original row of data
                writer.addValues((Object[]) record);
                // Replace changed values
                writer.addValue(config.getAddress1(), this.addresses.get(counter.get()).getAddress1());
                writer.addValue(config.getAddress2(), this.addresses.get(counter.get()).getAddress2());
                writer.addValue(config.getAddress3(), this.addresses.get(counter.get()).getAddress3());
                writer.addValue(config.getAddress4(), this.addresses.get(counter.get()).getAddress4());
                writer.addValue(config.getAddress5(), this.addresses.get(counter.get()).getAddress5());
                writer.addValue(config.getPostcode(), this.addresses.get(counter.getAndIncrement()).getPostcode());
                writer.writeValuesToRow();
            }
            // Flushes and closes the writer
            writer.close();
        } catch (IOException ex) {
            LOGGER.fatal("Unable to write to {} : {}", inputFile, ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public List<Address> getAddresses() {
        return this.addresses;
    }
}
