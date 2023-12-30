package com.rentgain.model.ui;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
public class Header {
    public String title;
    public List<Metum> meta;
    public List<Link> links;
    public List<Script> scripts;

    public List<String> getMetaString() {
        List<String> sb = new ArrayList<String>();
        meta.stream().forEach(m -> {
            String s = "< ";
            if (m.charset != null) {
                s += "charset=" + m.getCharset();
            }
            if (m.content != null) {
                s += "content=" + m.getContent();
                s += " name=" + m.getContent();
            }

            sb.add(s);
        });
        return sb;
    }

    public List<String> getLinkString() {
        List<String> sb = new ArrayList<String>();
        links.stream().forEach(m -> {
            StringJoiner s = new StringJoiner(" ");
            s.add("href=" + m.getHref());

            if (m.getLinkClass() != null) {
                s.add("class=" + m.getLinkClass());
            }
            if (m.getCrossOrigin() != null) {
                s.add("crossorigin=" + m.getCrossOrigin());
            }
            if (m.getType() != null) {
                s.add("type=" + m.getType());
            }
            s.add("rel=" + m.getRel());
            sb.add(s.toString());
        });
        return sb;
    }

}

