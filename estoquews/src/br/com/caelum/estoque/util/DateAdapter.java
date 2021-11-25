package br.com.caelum.estoque.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private String pattern = "MM/dd/yyyy";

    public String marshal(Date date) throws Exception {
        return new SimpleDateFormat(pattern).format(date);
    }

    public Date unmarshal(String dateString) throws Exception {
        System.out.println(dateString);
        return new SimpleDateFormat(pattern).parse(dateString);
    }
}
