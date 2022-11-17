package file;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvService {


    public void writer(String[] header, List<String[]> data, String name) throws IOException {

        Writer writer = Files.newBufferedWriter(Path.of("E:\\Projects\\java spring camp\\" +
                "week_5_practice_java_ATM_OOP_Log_Exception_Collection_TAhalKard\\src\\" + name + ".csv"));


        ICSVWriter csvWriter = new CSVWriterBuilder(writer)
                .build();

        // write header record
        csvWriter.writeNext(header);

        csvWriter.writeAll(data);

        // close writers
        csvWriter.close();
        writer.close();
    }


    public void reader(Path path) {

    }

}
