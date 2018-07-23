package uk.gov.dvla.osg.address.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.univocity.parsers.common.processor.ConcurrentRowProcessor;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.tsv.*;

import uk.gov.dvla.osg.address.config.DpfConfig;
import uk.gov.dvla.osg.address.config.ParserConfig;
import uk.gov.dvla.osg.address.model.Address;
import uk.gov.dvla.osg.address.model.Address.AddressBuilder;

public class DpfParser implements IAddressParser {
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    private final String inputFile;
    private final List<Address> addresses = new ArrayList<>();
    private final DpfConfig config;
    private String[] headers;
    
    public DpfParser(ParserConfig pc, String inputFile) {
        this.inputFile = inputFile;
        config = pc.getDpf();
        load();
    }

    private void load() {
        
        TsvParser parser = createParser();
        parser.parseAllRecords(new File(inputFile)).forEach(record -> {
            Address address = AddressBuilder.getInstance()
                    .address1(record.getString(config.getAddress1()))
                    .address2(record.getString(config.getAddress2()))
                    .address3(record.getString(config.getAddress3()))
                    .address4(record.getString(config.getAddress4()))
                    .address5(record.getString(config.getAddress5()))
                    .postcode(record.getString(config.getPostcode()))
                    .build();

            addresses.add(address);
        });
        headers = parser.getRecordMetadata().headers();
    }

    /**
     * Create a new instance of a TsvParser
     * @return A TsvParser set to handle header rows
     */
    private TsvParser createParser() {
        TsvParserSettings parserSettings = new TsvParserSettings();
        parserSettings.setNullValue("");
        parserSettings.setProcessor(new ConcurrentRowProcessor(new RowListProcessor()));
        parserSettings.setLineSeparatorDetectionEnabled(true);
        parserSettings.setHeaderExtractionEnabled(true);
        return new TsvParser(parserSettings);
    }

    @Override
    public void save() {
        // copy the input file
        File srcFile = new File(inputFile);
        File tempFile = new File(inputFile+".bak");
        try {
            FileUtils.copyFile(srcFile, tempFile);
        } catch (IOException ex1) {
            LOGGER.debug("Error copying input file. Unable to save data.");
            System.exit(1);
        }
        
        try (FileWriter fw = new FileWriter(srcFile)) {
            // Create an instance of TsvWriter with the default settings
            TsvWriter writer = new TsvWriter(fw, new TsvWriterSettings());
            // Writes the file headers
            writer.writeHeaders(headers);
            // Keep track of which row is being processed
            AtomicInteger counter = new AtomicInteger(0);
            // Build a parser that loops through the original dpf file
            TsvParser parser = createParser();
            parser.parseAll(tempFile).forEach(record -> {
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
            }); 
            // Flushes and closes the writer
            writer.close();
            FileUtils.deleteQuietly(tempFile);
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
