package com.github.ativey.pdftodolist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Component
public class YamlLoader {

    public LinkedHashMap<Category, List<Task>> load(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return load(fileInputStream);
    }


    public LinkedHashMap<Category, List<Task>> load(InputStream istream) throws IOException {

        TreeMap<String, String> l;

        ObjectMapper mapper = createObjectMapper();

        Map m = new LinkedHashMap<String, List<String>>();
        m = mapper.readValue(istream, m.getClass());

        var ret = new LinkedHashMap<Category, List<Task>>();

        m.forEach((key, value) -> {

            String key1 = (String) key;
            List<String> taskNames = (List<String>) value;
            Category category = new Category(key1, 1);
            List tasks = new ArrayList<Task>();
            ret.put(category, tasks);
            if (taskNames != null) {
                for (String taskName : taskNames) {
                    Task task = new Task(taskName, category, true);
                    tasks.add(task);
                }
            }

        });

        return ret;
    }




    public static ObjectMapper createObjectMapper() {
        final YAMLFactory yamlFactory = new YAMLFactory()
                .configure(YAMLGenerator.Feature.USE_NATIVE_TYPE_ID, false)
                .configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, true)
                .configure(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS, true);

        ObjectMapper mapper = new ObjectMapper(yamlFactory)
                .registerModule(new Jdk8Module())
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .enable(SerializationFeature.INDENT_OUTPUT);
//                .disable(SerializationFeature.WRITE_NULL_MAP_VALUES);

        return mapper;
    }



}
