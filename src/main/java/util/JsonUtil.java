package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T load(String path, TypeReference<T> ref) {
        try (InputStream is = new FileInputStream(path)) {
            return mapper.readValue(is, ref);
        } catch (IOException e) {
            System.out.println("Could not load " + path + ": " + e.getMessage());
            return null;
        }
    }

    public static void save(String path, Object obj) {
        try (OutputStream os = new FileOutputStream(path)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(os, obj);
        } catch (IOException e) {
            System.out.println(" Could not save to " + path + ": " + e.getMessage());
        }
    }
}
