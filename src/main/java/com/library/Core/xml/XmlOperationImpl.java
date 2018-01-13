package com.library.Core.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.library.Core.Operation;

/**
 * Created by rbuga on 1/9/2018.
 */
public class XmlOperationImpl implements Operation{

    public void print(Object obj) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        System.out.println(xml);
    }
}
