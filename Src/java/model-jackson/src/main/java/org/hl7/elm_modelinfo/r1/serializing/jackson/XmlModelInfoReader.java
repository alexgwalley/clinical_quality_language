package org.hl7.elm_modelinfo.r1.serializing.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.hl7.elm_modelinfo.r1.ModelInfo;
import org.hl7.elm_modelinfo.r1.TypeInfo;
import org.hl7.elm_modelinfo.r1.TypeSpecifier;
import org.hl7.elm_modelinfo.r1.serializing.ModelInfoReader;
import org.hl7.elm_modelinfo.r1.serializing.jackson.mixins.TypeInfoMixIn;
import org.hl7.elm_modelinfo.r1.serializing.jackson.mixins.TypeSpecifierMixIn;

import java.io.*;
import java.net.URI;
import java.net.URL;

public class XmlModelInfoReader implements ModelInfoReader {
    static XmlMapper mapper = new XmlMapper().builder()
            .defaultUseWrapper(false)
            .defaultMergeable(true)
            .enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION)
            .enable(ToXmlGenerator.Feature.WRITE_XML_1_1)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
            .enable(MapperFeature.USE_BASE_TYPE_AS_DEFAULT_IMPL)
            .addModule(new JaxbAnnotationModule())
            .addMixIn(TypeInfo.class, TypeInfoMixIn.class)
            .addMixIn(TypeSpecifier.class, TypeSpecifierMixIn.class)
            .build();

    public ModelInfo read(File src) throws IOException {
        return mapper.readValue(src, ModelInfo.class);
    }

    public ModelInfo read(Reader src) throws IOException {
        return mapper.readValue(src, ModelInfo.class);
    }

    public ModelInfo read(InputStream src) throws IOException {
        return mapper.readValue(src, ModelInfo.class);
    }

    public ModelInfo read(URL url) throws IOException {
        return mapper.readValue(url, ModelInfo.class);
    }

    public ModelInfo read(URI uri) throws IOException {
        return mapper.readValue(uri.toURL(), ModelInfo.class);
    }

    public ModelInfo read(String string) throws IOException {
        return mapper.readValue(string, ModelInfo.class);
    }
}
