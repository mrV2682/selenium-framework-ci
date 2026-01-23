package utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvDataProvider {
    @DataProvider(name = "searchData", parallel = true)
    public static Object[][] searchData() {
        List<Object[]> data = new ArrayList<>();

        try (
                InputStream is = CsvDataProvider.class
                        .getClassLoader()
                        .getResourceAsStream("testdata/search_keywords.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))
        ) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                String keyword = parts[0];
                int expectedMinResults = Integer.parseInt(parts[1]);

                data.add(new Object[]{keyword, expectedMinResults});
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV test data", e);
        }

        return data.toArray(new Object[0][]);
    }
}
