package com.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.library.annotations.Value;
import com.library.model.Book;
import com.library.model.BookOrder;
import com.library.model.Reader;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Hello world!
 *
 */

public class App
{
    public static  ObjectMapper mapper = new ObjectMapper();
    @Value(value = "filepath")
    private static  String filepath;
    @Value(value = "storagepath")
    private static  String storagepath;

    public static List<Book> listBook = new ArrayList<>();
    public static List<Reader> listReader = new ArrayList<>();
    public static List<BookOrder> listOrder = new ArrayList<>();

    public static void main( String[] args ) throws Exception {

        Properties properties = new Properties();
        InputStream is = App.class.getResourceAsStream("/application.properties");
        properties.load(is);

        Field[] declaredField = App.class.getDeclaredFields();
        for (Field field: declaredField) {
            if(field.isAnnotationPresent(Value.class)){
                field.setAccessible(true);
                String annotationValue = field.getAnnotation(Value.class).value();
                field.set(App.class, properties.getProperty(annotationValue));
            }
        }
        /*System.out.println(properties.getProperty("filepath"));
        Reflections reflections = new Reflections("com.library",new FieldAnnotationsScanner());
        Set<Field> fieldsAnnotatedWith = reflections.getFieldsAnnotatedWith(Value.class);*/

        writer(CreateBook.getRandomBook(), "book.json", filepath);
        reader("data.book", storagepath);
        listBook.add((Book) reader("book.json", filepath));
        System.out.println(listBook.size());
        writer(listBook,"data.book", storagepath);


    }
    private static void writer(Object obj, String type, String way) throws IOException {
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            writeContent(json, (way + type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writer(List list, String type, String way) throws IOException {
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            writeContent(json, (way + type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object reader(String type, String way) throws Exception {
        Book book = null;
        TypeReference typeReference = null;
        String fileContent = "";
        File directory = new File(way);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.getName().equals(type)) {
                List<String> strings = FileUtils.readLines(new File(way + type));
                StringBuilder builder = new StringBuilder();
                strings.forEach(builder::append);
                fileContent = builder.toString();
            }
        }

        File f = new File(way + type);
        if (type.equals("data.book")) {
            if (!f.exists()) {
                listBook = new ArrayList<>();
            }else {
                typeReference = new TypeReference<List<Book>>() {
                };
                listBook = mapper.readValue(fileContent, mapper.getTypeFactory()
                        .constructCollectionType(List.class, Book.class));
            }
        }

        if (type.equals("data.reader")) {
            if (!f.exists()) {
                listReader = new ArrayList<>();
            }else {
                typeReference = new TypeReference<List<Reader>>() {
                };
                listReader = mapper.readValue(fileContent, mapper.getTypeFactory()
                        .constructCollectionType(List.class, Reader.class));
            }
        }
        if (type.equals("data.order")) {
            if (!f.exists()) {
                listOrder = new ArrayList<>();
            }else{
                typeReference = new TypeReference<List<BookOrder>>() {
                };
                listOrder = mapper.readValue(fileContent, mapper.getTypeFactory()
                        .constructCollectionType(List.class, BookOrder.class));
            }
        }
        if (type.equals("book.json")) {

            if (!f.exists()) {
                return null;
            }else {
                book = mapper.readValue(new File(way + type), Book.class);

            }
        }
        return book;
    }


    private static String getJsonBook(Set<Book> settBook) throws IOException {
        return mapper.writeValueAsString(settBook);
    }

    private static String getJsonReader(Set<Reader> settReader) throws IOException {
        return mapper.writeValueAsString(settReader);
    }

    private static Reader createReader(){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Reader reader = new Reader();

        System.out.println("Input ID: ");
        reader.setId(getFromKeyboard(br));

        System.out.println("Input First Name: ");
        reader.setFirstName(getFromKeyboard(br));

        System.out.println("Input Last Name: ");
        reader.setLastName(getFromKeyboard(br));

        reader.setYearOfBirth(getRandomNumber(1950, 2000 + 1));

        return  reader;
    }

    private static String getFromKeyboard(BufferedReader br) {
        String result = "";
        while (true) {
            try {
                result = br.readLine();
            } catch (IOException e) {
                // nooperation
            }
            if (result != "") {
                break;
            }
            System.out.println("Empty name not allowed!");
        }
        return result;
    }
    private static int getRandomNumber(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }



    private static void writeContent(String type, String filetype) throws IOException {
            FileUtils.write(new File(filetype), type);
        }
}
